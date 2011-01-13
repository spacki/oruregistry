package org.openehealth.ipf.ws.oru

import org.apache.camel.model.ProcessorDefinition
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.openehealth.ipf.modules.hl7dsl.MessageAdapter
import org.openehealth.ipf.modules.hl7dsl.util.Messages
import ca.uhn.hl7v2.model.Message

class OruModelExtension {
    private static final transient Log LOG = LogFactory.getLog(OruModelExtension.class);

    static extensions = {

        ProcessorDefinition.metaClass.output = { String message ->
            return delegate.process {
                LOG.debug("\n${'-' * 20} ${message} ${'-' * 20}\n${it.in.body}")
            }
        }

    }

}
