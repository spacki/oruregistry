/*******************************************************************************
 *
 * C O P Y R I G H T    N O T I C E
 *    (c) Copyright 2009 GE Healthcare
 *
 *    GE Medical Systems Information Technologies, Inc. 
 *    540 W NW HWY
 *    Barrington, Illinois 60010
 *
 *       All Rights Reserved.
 *       No portions of this source code or the resulting compiled
 *       program may be used without express written consent and licensing
 *       by GE Medical Systems Information Technologies, Inc. 
 *
 ********************************************************************************/
package org.openehealth.ipf.ws.oru.cda2xds.commons

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.ProvideAndRegisterDocumentSetRequestType
import org.openehealth.ipf.ws.oru.cda2xds.beans.MetadataBean

/**
 * Implementation class for creating ProvideAndRegisterDocumentSetRequestType
 * @author Swapnil Sharma
 *
 */
public class PnRDocumentSetImpl implements PnRDocumentSetInt {
	
	
	
	/* (non-Javadoc)
	 * @see com.gehc.hcit.ccg.ihe.commons.PnRDocumentSetInt#getPnRRequest(com.gehc.hcit.ccg.ihe.commons.MessageStrategyAbs)
	 */
	public ProvideAndRegisterDocumentSetRequestType getPnRRequest(MessageStrategyAbs msgStrategy){
		Log log = LogFactory.getLog(PnRDocumentSetImpl.class)
		//Build meta data information and populate MetadataBean
		MetadataBean metaBean = msgStrategy.buildMetaData()
		
		log.debug("Message content: " + msgStrategy.getDocContent())
		//Helper class is used for creating the ebXML request
		XdsRequestHelper xdsHelper = new XdsRequestHelper(metaBean)
		//Create new ProvideAndRegisterDocumentSetRequestType
		ProvideAndRegisterDocumentSetRequestType request = new ProvideAndRegisterDocumentSetRequestType()
		//Use helper class to create SubmitObjectsRequest
		request.submitObjectsRequest = 
			xdsHelper.createSubmitObjectsRequest("XDS_b Submission")
		//Get document content as String from MessageStrategy		
		request.document.add(
			xdsHelper.createDocumentContent("Document01", msgStrategy.getDocContent()))	
		
		return request
	}
	
}
