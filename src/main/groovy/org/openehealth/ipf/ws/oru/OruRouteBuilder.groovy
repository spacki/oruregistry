package org.openehealth.ipf.ws.oru

import org.apache.camel.spring.SpringRouteBuilder
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.ProvideAndRegisterDocumentSetRequestType
import org.openehealth.ipf.commons.ihe.xds.core.responses.Status
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.RegistryResponseType
import org.openehealth.ipf.modules.hl7dsl.MessageAdapter
import org.openehealth.ipf.ws.oru.cda2xds.oru.CdaToEbxmlTranslator

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


        // Dispatch ORU message to the two branches
        from('seda:dispatch')
            .multicast()
                .to('direct:drr', 'direct:gpportal')
                // TODO: what should return the sub-routes, how should these results be aggregated?


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
            .output('XDS ITI-41 response', null)
            .validate().iti41Response()
            .process {
                 RegistryResponseType response = it.in.body
                 if (response.status == Status.SUCCESS.getOpcode30()) {
                    // TODO
                 }
            }


        /* ========== GP Portal Branch ========== */

        from('direct:gpportal')
            .onException(Exception.class)
                .maximumRedeliveries(0)
                .end()
            .process { /* TODO */ }
    }
    
}
