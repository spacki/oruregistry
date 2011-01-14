/*******************************************************************************
 *
 * C O P Y R I G H T    N O T I C E
 *    (c) Copyright 2009
 *    GE Healthcare Information Technologies, Inc. 
 *    
 *       All Rights Reserved.
 *       No portions of this source code or the resulting compiled
 *       program may be used without express written consent and licensing
 *       by GE Healthcare Information Technologies, Inc. 
 *
 ********************************************************************************/
package org.openehealth.ipf.ws.oru.cda2xds.pix

import ca.uhn.hl7v2.HL7Exception
import ca.uhn.hl7v2.parser.PipeParser
import java.text.DateFormat
import java.text.SimpleDateFormat
import org.apache.camel.CamelContext
import org.apache.camel.ProducerTemplate
import org.apache.camel.impl.DefaultExchange
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.openehealth.ipf.commons.ihe.pixpdq.definitions.v25.pix.message.QBP_Q21
import org.openehealth.ipf.modules.hl7.AbstractHL7v2Exception
import org.openehealth.ipf.modules.hl7dsl.MessageAdapter
import org.openehealth.ipf.modules.hl7dsl.MessageAdapters
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.openehealth.ipf.ws.oru.cda2xds.beans.BeanVisitor
import org.openehealth.ipf.ws.oru.cda2xds.beans.MetadataBean

/**
 * PIXTranslator
 * This class implements the BeanVisitor interface i.e., it acts as a
 * visitor to Doc Source implementors; Performs a PIX query AND
 * if successful, updates the patient Identifier and Assigning Authority.
 *  
 * @author Anu Jandhyala
 * History of Changes:
 */
public class PIXTranslator implements BeanVisitor {
	
	 private static final transient Log LOG = LogFactory.getLog(PIXTranslator.class)

 
	 void UpdateBean(MetadataBean dataBean) {
		 
		if (PIXConfiguration.enablePix == 'false'){
			return;
		}
		
		def appContext = new ClassPathXmlApplicationContext('context.xml')
	    def producerTemplate = appContext.getBean('producerTemplate', ProducerTemplate.class)
	    def camelContext = appContext.getBean('camelContext', CamelContext.class)
		
        def failed = true;
		def endpointUri = "pix-iti9://${PIXConfiguration.pixManagerInfo}"
		LOG.info("PIXTranslator:endpointUri: " + endpointUri);
		
		try {     
        def body = this.createQBP(dataBean)
		
		def exchange = new DefaultExchange(camelContext)

        exchange.in.body = body
		
		LOG.info("PIXTranslator:request:" + exchange.in.body)
		
		def responseExchange = producerTemplate.send(endpointUri, exchange)		
		LOG.info("PIXTranslator:response:" + responseExchange.out.body)

		def rsp_k21 = MessageAdapters.make(new PipeParser(),responseExchange.out.getBody(String.class))
			
		String value = rsp_k21.QUERY_RESPONSE.PID[3].value
        LOG.debug("PID[3] value in response:" + rsp_k21.QUERY_RESPONSE.PID[3].value)
        if(!("".equals(rsp_k21?.QUERY_RESPONSE?.PID?.value)) &&
        		!("".equals(rsp_k21.QUERY_RESPONSE.PID[3][4][2].value))) {
			dataBean.setSourcePatientId(rsp_k21.QUERY_RESPONSE.PID[3][1].value)
			dataBean.setSourcePatientOId(rsp_k21.QUERY_RESPONSE.PID[3][4][2].value)
			LOG.debug("GlobalPatientOID:" + rsp_k21.QUERY_RESPONSE.PID[3][4][2].value)
			LOG.debug("After PIX Translator:" + dataBean.toString())
		}
		else {
				LOG.info("PIX manager did not return a value, so no change to local PID")
		}
       } catch (Exception e) {
            def cause = e.getCause()
            if((e instanceof HL7Exception) || (cause instanceof HL7Exception) ||
               (e instanceof AbstractHL7v2Exception) || (cause instanceof AbstractHL7v2Exception))
            {
                failed = false
            }
            else {
            	LOG.error("pixTranslator.updateBean:ERROR, cause:  "+ e.getCause());
            }
        }
	}
	
	/*
	 * This method is called from PIXTransmogrifrier, to create a QBP^Q23 msg
	 * We copy the MSH segment as is, then set MSH-9 (msg type) and MSH-15 (hl7 version)
	 * 
	 */
	static MessageAdapter createQBP (MessageAdapter msg) {
		QBP_Q21 qbpMsg = new QBP_Q21()
		MessageAdapter qbpAdapter = new MessageAdapter(qbpMsg)

		try {
		   qbpAdapter.MSH = msg.MSH
		   qbpAdapter.MSH[9][1] = 'QBP'
		   qbpAdapter.MSH[9][2] = 'Q23'
		   //qbpAdapter.MSH[12] = '2.5'
		   qbpAdapter.QPD[1][1] = PIXConfiguration.pixQueryId_1
		   qbpAdapter.QPD[1][2] = PIXConfiguration.pixQueryId_2
		   qbpAdapter.QPD[1][3] = PIXConfiguration.pixQueryId_3
		   qbpAdapter.QPD[2] = 'QRY'+ msg.MSH[10].value
		   qbpAdapter.QPD[3][1] = msg.PID[3].value
		   qbpAdapter.QPD[3][2] = ''
		   qbpAdapter.QPD[3][3] = ''
		   qbpAdapter.QPD[3][4][1] = PIXConfiguration.localNamespace
		   qbpAdapter.QPD[3][4][2] = PIXConfiguration.localAssigningAuthority
		   qbpAdapter.QPD[3][4][3] = PIXConfiguration.localUUtype
		   qbpAdapter.QPD[4][1] = ''
		   qbpAdapter.QPD[4][2] = ''
		   qbpAdapter.QPD[4][3] = ''
		   qbpAdapter.QPD[4][4] = PIXConfiguration.globalAssigningAuthority
		   qbpAdapter.RCP[1] = 'I'
	   	} catch (Exception e) {
	   	   // TODO Auto-generated catch block
		   //e.printStackTrace();
	   	   LOG.error("pixTranslator.createQBP:ERROR, cause: " + e.getCause() );
	    } 
	   	LOG.info("QBP Message -> " + qbpAdapter.toString());
	   	return qbpAdapter
	}

	public String createQBP (MetadataBean bean) {
		
		QBP_Q21 qbpMsg = new QBP_Q21()
		MessageAdapter qbpAdapter = new MessageAdapter(qbpMsg)
		
		LOG.info("pixTranslator.MBean:" + bean.toString())
		try {
			
		   qbpAdapter.MSH[1] = '|'
		   qbpAdapter.MSH[2] = bean.encodingChars
		   LOG.debug("pixTranslator.fieldSeparator:" + bean.encodingChars)
		   qbpAdapter.MSH[3] = bean.sendingApplication
		   LOG.debug("pixTranslator.sendingApplication:" + bean.sendingApplication)
		   qbpAdapter.MSH[4] = bean.getSendingFacility()
		   LOG.debug("pixTranslator.getSendingFacility:" + bean.getSendingFacility())
		   qbpAdapter.MSH[5] = bean.getReceivingApplication()
		   LOG.debug("pixTranslator.getReceivingApplication:" + bean.getReceivingApplication())
		   qbpAdapter.MSH[6] = bean.getReceivingFacility()
		   LOG.debug("pixTranslator.getReceivingFacility:" + bean.getReceivingFacility())
		   qbpAdapter.MSH[7] = bean.getCreationTime()
		   LOG.debug("pixTranslator.getCreationTime:" + bean.getCreationTime())
		   qbpAdapter.MSH[8] = ''
		   qbpAdapter.MSH[9][1] = 'QBP'
		   qbpAdapter.MSH[9][2] = 'Q23'
		   
		   DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss")
		   Date now = new Date()
		   String uniqueId = dateFormat.format(now)
		   qbpAdapter.MSH[10] = uniqueId
		   LOG.debug("pixTranslator.uniqueId:" + uniqueId)
		   qbpAdapter.MSH[11] = 'P'
		   qbpAdapter.MSH[12] = bean.hl7Version
		   qbpAdapter.QPD[1][1] = PIXConfiguration.pixQueryId_1
		   qbpAdapter.QPD[1][2] = PIXConfiguration.pixQueryId_2
		   qbpAdapter.QPD[1][3] = PIXConfiguration.pixQueryId_3
		   qbpAdapter.QPD[2] = 'QRY'+ uniqueId
		   qbpAdapter.QPD[3][1] = bean.getSourcePatientId()
		   LOG.debug("pixTranslator.bean.getSourcePatientId():" + bean.getSourcePatientId())
		   qbpAdapter.QPD[3][2] = ''
		   qbpAdapter.QPD[3][3] = ''
		   qbpAdapter.QPD[3][4][1] = PIXConfiguration.localNamespace
		   qbpAdapter.QPD[3][4][2] = bean.getSourcePatientOId()
		   LOG.debug("pixTranslator.bean.getSourcePatientOId():" + bean.getSourcePatientOId())
		   qbpAdapter.QPD[3][4][3] = PIXConfiguration.localUUtype
		   qbpAdapter.QPD[4][1] = ''
		   qbpAdapter.QPD[4][2] = ''
		   qbpAdapter.QPD[4][3] = ''
		   qbpAdapter.QPD[4][4] = PIXConfiguration.globalAssigningAuthority
		   qbpAdapter.RCP[1] = 'I'
		   LOG.debug("pixTranslator.pixConfig.localDomain):" + PIXConfiguration.localAssigningAuthority)
		   LOG.info("QBP Message -> " + qbpAdapter.toString());
	   	} catch (Exception e) {
	   	   // TODO Auto-generated catch block
		   // e.printStackTrace();
	   		LOG.error("pixTranslator.createQBP:ERROR, cause:" + e.getCause() );
	    } 

	   	return qbpAdapter.toString()
	}

	/* 
	 * May be used for debug purposes
	 */
    static String getMessageString(String msh9, String msh12, String qpd3) {
        def s = 'MSH|^~\\&|MESA_PIX_CLIENT|MESA_DEPARTMENT|MESA_XREF|XYZ_HOSPITAL|'+ 
                    "200603121200||${msh9}|10501110|P|${msh12}||||||||\n"
            s += 'QPD|QRY_1001^Query for Corresponding Identifiers^IHEDEMO|QRY10501110|' + 
                    "${qpd3}|||||\n"
            s += 'RCP|I||||||'
        return s
    }	
	
}
