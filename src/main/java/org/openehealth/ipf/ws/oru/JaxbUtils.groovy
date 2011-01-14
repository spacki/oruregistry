package org.openehealth.ipf.ws.oru

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.ProvideAndRegisterDocumentSetRequestType
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.RegistryResponseType

/**
 * Helper methods for working with O/X mapping.
 * @author Dmytro Rud
 */
abstract class JaxbUtils {

    /**
     * JAXB context for transforming generated XDS requests to String
     * for logging purposes.
     */
    private static final JAXBContext JAXB_CONTEXT = JAXBContext.newInstance(
            ProvideAndRegisterDocumentSetRequestType.class,
            RegistryResponseType.class,
    )


    /**
     * Returns marshaled XML representation of the given ebXML POJO.
     */
    static String marshal(ebXml) {
        StringWriter writer = new StringWriter()
        Marshaller marshaller = JAXB_CONTEXT.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        marshaller.marshal(ebXml, writer)
        return writer.toString()
    }

}
