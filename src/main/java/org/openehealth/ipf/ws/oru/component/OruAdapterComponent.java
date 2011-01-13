package org.openehealth.ipf.ws.oru.component;

import ca.uhn.hl7v2.parser.Parser;
import org.apache.camel.CamelContext;
import org.openehealth.ipf.modules.hl7.parser.CustomModelClassFactory;
import org.openehealth.ipf.modules.hl7.parser.PipeParser;
import org.openehealth.ipf.platform.camel.ihe.mllp.core.MllpAuditStrategy;
import org.openehealth.ipf.platform.camel.ihe.mllp.core.MllpComponent;
import org.openehealth.ipf.platform.camel.ihe.mllp.core.MllpTransactionConfiguration;
import org.openehealth.ipf.platform.camel.ihe.mllp.core.NakFactory;
import org.openehealth.ipf.platform.camel.ihe.pixpdq.BasicNakFactory;

import java.util.Collections;
import java.util.Map;

/**
 * Camel component for ORU Adapter.
 * @author Dmytro Rud
 */
public class OruAdapterComponent extends MllpComponent {

    private static final MllpTransactionConfiguration CONFIGURATION =
        new MllpTransactionConfiguration(
                "2.4",
                "ORU Adapter",
                "GE",
                207, 
                207,
                new String[] {"ORU"},
                new String[] {"R01"},
                new String[] {"ACK"},
                new String[] {"*"},
                new boolean[] {false},
                new boolean[] {false}); 
  
    private static final MllpAuditStrategy CLIENT_AUDIT_STRATEGY =
        new OruAuditStrategy();
    private static final MllpAuditStrategy SERVER_AUDIT_STRATEGY =
        new OruAuditStrategy();
    private static final NakFactory NAK_FACTORY = new BasicNakFactory();

    private static final Parser PARSER;
    static {
        // TODO
        /*
        Map<String, String[]> map = Collections.singletonMap(
                "2.3.1", new String[] {"com.icw.ehf.integration.omiadapter.definitions.v25"});
        PARSER = new PipeParser(new CustomModelClassFactory(map));
        */
        PARSER = new PipeParser();
    }
    
    public OruAdapterComponent() {
        super();
    }

    public OruAdapterComponent(CamelContext camelContext) {
        super(camelContext);
    }
    
    @Override
    public MllpAuditStrategy getClientAuditStrategy() {
        return CLIENT_AUDIT_STRATEGY;
    }

    @Override
    public MllpAuditStrategy getServerAuditStrategy() {
        return SERVER_AUDIT_STRATEGY;
    }
    
    @Override
    public MllpTransactionConfiguration getTransactionConfiguration() {
        return CONFIGURATION;
    }

    @Override
    public Parser getParser() {
        return PARSER;
    }

    @Override
    public NakFactory getNakFactory() {
        return NAK_FACTORY;
    }
}
