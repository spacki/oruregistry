package org.openehealth.ipf.ws.oru

import org.apache.camel.spring.SpringRouteBuilder

class SampleRouteBuilder extends SpringRouteBuilder {

    void configure() {
        from('direct:input1').transmogrify { it * 2 }
        from('direct:input2').reverse()
    }
    
}
