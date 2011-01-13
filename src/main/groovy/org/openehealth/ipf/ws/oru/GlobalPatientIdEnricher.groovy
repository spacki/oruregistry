package org.openehealth.ipf.ws.oru

import org.openehealth.ipf.modules.hl7dsl.MessageAdapter

/**
 * @author Dmytro Rud
 */
class GlobalPatientIdEnricher {

    MessageAdapter enrichGlobalPatientId(MessageAdapter msg) {
        def pidList = msg.PATIENT_RESULT.PATIENT.PID[3]

        // write global PID into additional PID-3 repetition
        def cx = pidList(pidList().size())
        cx[1] = 'globalId'
        cx[4][2] = '1.2.3.4.5'
        cx[4][3] = 'ISO'

        return msg
    }
}
