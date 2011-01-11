package org.openehealth.ipf.ws.oru;

import org.apache.camel.spring.Main;

public class SampleServer {

    public static void main(String[] args) throws Exception {
        Main.main("-ac", "/context.xml");
    }
    
}
