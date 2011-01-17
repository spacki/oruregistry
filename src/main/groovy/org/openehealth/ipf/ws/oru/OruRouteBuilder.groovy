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
                // let the IPF automatically generate an ACK when no error occurred
                it.in.body = null
                it.in.headers[MllpComponent.ACK_TYPE_CODE_HEADER] = AckTypeCode.AA
            }


        // Dispatch ORU message to the two branches
        from('seda:dispatch')
            .multicast()
                .stopOnException()
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

                // status not OK -- collect error info to generate a NAK
                if (response.status != Status.SUCCESS.getOpcode30()) {
                    def exceptions = []
                    for (RegistryError error : response.registryErrorList?.registryError) {
                        exceptions << new HL7v2Exception(createErrorMessage(error))
                    }
                    throw exceptions ?
                        new CompositeHL7v2Exception('XDS registry/repository returned error', exceptions as List) :
                        new HL7v2Exception('XDS transaction failed for unknown reason')
                }
            }


        /* ========== GP Portal Branch ========== */

        from('direct:gpportal')
            .onException(Exception.class)
                .maximumRedeliveries(0)
                .end()
            .process { /* TODO */ }
    }


    /**
     * Creates a string representation of an XDS registry error.
     */
    static String createErrorMessage(RegistryError error) {
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
