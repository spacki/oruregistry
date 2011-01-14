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

import org.openehealth.ipf.ws.oru.cda2xds.beans.BeanVisitor;
import org.openehealth.ipf.ws.oru.cda2xds.beans.MetadataBean;
import org.openehealth.ipf.ws.oru.cda2xds.pix.PIXTranslator;


/**
 * @author Swapnil Sharma
 * Abstract class that defines the common behavior for
 * handling multiple incoming mesaage types like CDA, MDM etc
 * 
 * History of Changes
 * Anu Jandhyala: Added PerformPIXQuery() and converDateUTC() methods
 */
public abstract class MessageStrategyAbs { 
	/**
	 * Follows template pattern to extract metadata from
	 * incoming message and then perform PIX query transparently 
	 * @return com.gehc.hcit.ccg.ihe.beans.MetadataBean
	 */
	public MetadataBean buildMetaData() {
		MetadataBean metaBean = extractData();

		convertDateUTC(metaBean);
		//performPixQuery(metaBean);	
		
		return metaBean;
	}
	
	/** 
	 * Abstract method implemented by concrete 
	 * classes for different message types
	 * @return com.gehc.hcit.ccg.ihe.beans.MetadataBean
	 */
	protected abstract MetadataBean extractData();

	/**
	 * Performs PIX query and replaces required fields in 
	 * @see com.gehc.hcit.ccg.ihe.beans.MetadataBean
	 * @void
	 */
	protected void performPixQuery(MetadataBean metaBean) {
	    BeanVisitor beanVisitor = new PIXTranslator();
		metaBean.accept(beanVisitor);	
	}
	
	/**
	 * Converts datetimes in metaDate into UTC format 
	 * @see com.gehc.hcit.ccg.ihe.beans.MetadataBean
	 * @void
	 */
	protected void convertDateUTC(MetadataBean metaBean) {
	    BeanVisitor beanVisitor = new DateUTCUtil();
		metaBean.accept(beanVisitor);	
	}
	
	/**
	 * Abstract method for obtaining the content
	 * of incoming message as String. Concreted classes
	 * provide the implementation of how to extact the data
	 * @return
	 */
	public abstract String getDocContent();

}
