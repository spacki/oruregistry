package org.openehealth.ipf.ws.oru

import org.apache.camel.model.ProcessorDefinition
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

class OruModelExtension {
    private static final transient Log LOG = LogFactory.getLog(OruModelExtension.class);

    static extensions = {

        ProcessorDefinition.metaClass.output = { String message, Closure c ->
            return delegate.process {
                def payload = c ? c(it.in.body) : it.in.body
                LOG.debug("\n${'-' * 20} ${message} ${'-' * 20}\n${payload}")
            }
        }

    }

}
