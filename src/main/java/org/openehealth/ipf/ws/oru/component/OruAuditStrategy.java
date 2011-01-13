package org.openehealth.ipf.ws.oru.component;

import org.apache.camel.Exchange;
import org.openehealth.ipf.modules.hl7dsl.MessageAdapter;
import org.openehealth.ipf.platform.camel.ihe.mllp.core.MllpAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.mllp.core.MllpAuditStrategy;
import org.openhealthtools.ihe.atna.auditor.codes.rfc3881.RFC3881EventCodes;

/**
 * @author Dmytro Rud
 */
public class OruAuditStrategy implements MllpAuditStrategy {

    @Override
    public MllpAuditDataset createAuditDataset() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] getNecessaryFields(String s) {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void enrichAuditDatasetFromRequest(MllpAuditDataset mllpAuditDataset, MessageAdapter messageAdapter, Exchange exchange) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void enrichAuditDatasetFromResponse(MllpAuditDataset mllpAuditDataset, MessageAdapter messageAdapter) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doAudit(RFC3881EventCodes.RFC3881EventOutcomeCodes rfc3881EventOutcomeCodes, MllpAuditDataset mllpAuditDataset) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void auditAuthenticationNodeFailure(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
