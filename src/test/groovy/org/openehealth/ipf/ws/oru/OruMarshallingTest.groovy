package org.openehealth.ipf.ws.oru

import org.junit.Test
import org.openehealth.ipf.modules.hl7dsl.MessageAdapters
import org.openehealth.ipf.modules.hl7dsl.MessageAdapter
import org.openehealth.ipf.modules.hl7dsl.util.Messages
import ca.uhn.hl7v2.model.Message
import static junit.framework.Assert.*

/**
 * @author Dmytro Rud
 */
class OruMarshallingTest {

    @Test
    void testOruMershalling() {
        InputStream is = OruMarshallingTest.class.getClassLoader().getResourceAsStream('message/oru.hl7.txt')
        MessageAdapter msg = MessageAdapters.make(is)
        println msg

        MessageAdapter msg2 = msg.copy()
        println msg2
        assertEquals(msg.toString(),msg2.toString())
    }
}
