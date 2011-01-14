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
package org.openehealth.ipf.ws.oru.cda2xds.oru;


import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.ProvideAndRegisterDocumentSetRequestType
import org.openehealth.ipf.ws.oru.cda2xds.commons.PnRDocumentSetImpl
import org.openehealth.ipf.ws.oru.cda2xds.exception.CcgRuntimeException

/**
 * @author Swapnil Sharma
 * Implementation class to convert from 
 * CDA as String to ProvideAndRegisterDocumentSetRequestType
 */
public class CdaToEbxmlTranslator {

	private final static Log log = LogFactory.getLog(CdaToEbxmlTranslator.class)
	
	public ProvideAndRegisterDocumentSetRequestType convert(String arg0){			
		 
		log.debug("Entered CdaToEbxmlTranslator convert")
		def cdaStrategy = null
		def pnrDocumentSet = null
		def pnrDocRequest = null
		
		try {
			cdaStrategy = new CdaMessageStrategy(arg0)
			pnrDocumentSet = new PnRDocumentSetImpl()
			pnrDocRequest = pnrDocumentSet.getPnRRequest(cdaStrategy)
		} catch (Exception e) {
			log.error(e)
			//RuntimeException is being thrown from this code
			throw new CcgRuntimeException(e)
		}
		
		return pnrDocRequest;
	}

}
