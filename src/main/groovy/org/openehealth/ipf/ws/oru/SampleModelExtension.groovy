package org.openehealth.ipf.ws.oru

import org.apache.camel.model.ProcessorDefinition


     static extensions = {
         
         ProcessorDefinition.metaClass.reverse = {
             delegate.transmogrify { it.reverse() }
         }
         
     }
     
}