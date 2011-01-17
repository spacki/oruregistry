package org.openehealth.ipf.ws.oru

import org.apache.camel.spring.SpringRouteBuilder
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.ProvideAndRegisterDocumentSetRequestType
import org.openehealth.ipf.commons.ihe.xds.core.responses.Status
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.RegistryResponseType
import org.openehealth.ipf.modules.hl7dsl.MessageAdapter
import org.openehealth.ipf.ws.oru.cda2xds.oru.CdaToEbxmlTranslator
import org.openehealth.ipf.platform.camel.ihe.mllp.core.MllpComponent
import org.openehealth.ipf.modules.hl7.AckTypeCode
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.RegistryError
import org.openehealth.ipf.modules.hl7.HL7v2Exception

import org.openehealth.ipf.modules.hl7.CompositeHL7v2Exception

class OruRouteBuilder extends SpringRouteBuilder {

    private static final CdaToEbxmlTranslator CDA_TO_EBXML_TRANSLATOR = new CdaToEbxmlTranslator()

    // injected by Spring
    String iti41EndpointUri


    void configure() {

        // Receive ORU message
        from('oruadapter://0.0.0.0:8888')
            // TODO: validate input
            .output('Received message', null)
            .to('seda:dispatch')
            .process {
                if (! it.in.body.empty) {
                    throw new CompositeHL7v2Exception(it.in.body.collect { String s -> new HL7v2Exception(s) })
                }
            
                it.in.body = null
                it.in.headers[MllpComponent.ACK_TYPE_CODE_HEADER] = AckTypeCode.AA
            }


        // Dispatch ORU message to the two branches
        from('seda:dispatch')
            .multicast()
                .aggregationStrategy(new OruAggregationStrategy())
                .parallelProcessing()
                .to('direct:drr', 'direct:gpportal')


        /* ========== DRR Branch ========== */

        // get global patient ID
        from('direct:drr')
            .onException(Exception.class)
                .maximumRedeliveries(0)
                .end()
            .to('bean:globalPatientIdEnricher')
            .output('Enriched HL7v2 ORU message', null)
            .to('direct:drr-make-cda')


        // transform ORU message into CDA document
        from('direct:drr-make-cda')
            .onException(Exception.class)
                .maximumRedeliveries(0)
                .end()
            .process {
                MessageAdapter oru = it.in.body
                String cda = ORU2CDAMapper.convertORUtoCDA(oru)
                it.in.body = cda
            }
            .output('CDA document', null)
            .to('direct:drr-make-iti41')


        // create an ITI-41 request on the basis of the CDA document
        from('direct:drr-make-iti41')
            .onException(Exception.class)
                .maximumRedeliveries(0)
                .end()
            .process {
                String cda = it.in.body
                ProvideAndRegisterDocumentSetRequestType iti41Request = CDA_TO_EBXML_TRANSLATOR.convert(cda)
                it.in.body = iti41Request
            }
            .output('XDS ITI-41 request') { JaxbUtils.marshal(it) }
            .validate().iti41Request()
            .to('direct:drr-send-iti41')


        // send the ITI-41 request and analyse response
        from('direct:drr-send-iti41')
            .onException(Exception.class)
                .maximumRedeliveries(0)
                .end()
            .to(iti41EndpointUri)
            .output('XDS ITI-41 response') { JaxbUtils.marshal(it) }
            .validate().iti41Response()
            .process {
                RegistryResponseType response = it.in.body

                it.in.body = []
                if (response.status != Status.SUCCESS.getOpcode30()) {
                    for (RegistryError error : response.registryErrorList?.registryError) {
                        it.in.body << createXdsErrorMessage(error)
                    }
                    if (it.in.body.empty) {
                        it.in.body = ['XDS transaction failed for unknown reason']
                    }
                }
            }


        /* ========== GP Portal Branch ========== */

        from('direct:gpportal')
            .onException(Exception.class)
                .maximumRedeliveries(0)
                .end()
            .to('direct:gpportal-call-ws')
            .process {
                def xml = new XmlSlurper(false, true).parseText(it.in.body)
                xml.declareNamespace(
                    '*'   : 'http://gehcit.com/platform/cws/oru/types',
                    'xsi' : 'http://www.w3.org/2001/XMLSchema-instance',
                    'xsd' : 'http://www.w3.org/2001/XMLSchema')

                it.in.body = []
                if (xml.status.text() != 'SUCCESS') {
                    for (error in xml.errordescription) {
                        it.in.body << error.text()
                    }
                    if (it.in.body.empty) {
                        it.in.body = ['GPPortal failed for unknown reason']
                    }
                }
            }


        // TODO: REPLACE WITH REAL WEB SERVICE ROUTE
        from('direct:gpportal-call-ws')
            .setBody(constant('''
                <Q1:ORUResponse xsi:schemaLocation="http://gehcit.com/platform/cws/oru/types ORURegistry.xsd"
                                xmlns:Q1="http://gehcit.com/platform/cws/oru/types"
                                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                    <Q1:status>FAILURE</Q1:status>
                    <Q1:errordescription>descr 1</Q1:errordescription>
                    <Q1:errordescription>descr 2</Q1:errordescription>
                    <Q1:errordescription>descr 3</Q1:errordescription>
                    <Q1:errordescription>descr 4</Q1:errordescription>
                </Q1:ORUResponse>
            '''))
    }


    /**
     * Creates a string representation of an XDS registry error.
     */
    static String createXdsErrorMessage(RegistryError error) {
        StringBuilder sb = new StringBuilder()
            .append(error.severity.substring(error.severity.lastIndexOf(':') + 1))
            .append(' ')
            .append(error.errorCode)

        if (error.codeContext) {
            sb.append(', context=').append(error.codeContext)
        }
        if (error.location) {
            sb.append(', location=').append(error.location)
        }
        return sb.toString()
    }

}
