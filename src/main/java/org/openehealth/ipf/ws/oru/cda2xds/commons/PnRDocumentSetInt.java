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

package org.openehealth.ipf.ws.oru.cda2xds.commons;

import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.ProvideAndRegisterDocumentSetRequestType;

/**
 * Interface that defines the methods available
 * for Provide and Register API's  
 * @author Swapnil Sharma
 *
 */
public interface PnRDocumentSetInt {
	
	/**
	 * Returns a Provide and Register Document request
	 * @param msgStrategy
	 * @return org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.ProvideAndRegisterDocumentSetRequestType
	 */
	public ProvideAndRegisterDocumentSetRequestType getPnRRequest(MessageStrategyAbs msgStrategy);
	
}
