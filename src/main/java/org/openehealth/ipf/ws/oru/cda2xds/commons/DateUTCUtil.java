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
package org.openehealth.ipf.ws.oru.cda2xds.commons;

import org.openehealth.ipf.ws.oru.cda2xds.beans.BeanVisitor;
import org.openehealth.ipf.ws.oru.cda2xds.beans.MetadataBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author Anu Jandhyala
 * Utility class to retrieve date-time in GMT(UTC) notation. Right now, it only
 * looks for seconds or minute granularity. TODO: make sure all precisions are
 * handled
 * 
 * History of Changes:
 */
public class DateUTCUtil implements BeanVisitor {

	private static final transient Log LOG = LogFactory.getLog(DateUTCUtil.class);
	
	private static final int DATE_NO_SECS = 17;
	private static final int DATE_WITH_SECS = 19;
	
	public void UpdateBean(MetadataBean metaBean) {
		String serviceStartTime = metaBean.getServiceStartTime();
		if(serviceStartTime != null)
		{
			if(hasGMT(serviceStartTime) ) {
				serviceStartTime = getUTC(serviceStartTime);
				metaBean.setServiceStartTime(serviceStartTime);
			}
			String serviceEndTime = metaBean.getServiceEndTime();
			if ( serviceEndTime != null && hasGMT(serviceEndTime)) {
				serviceEndTime = getUTC(serviceEndTime); 
				metaBean.setServiceEndTime(serviceEndTime);
	    	}
			String creationTime = metaBean.getCreationTime();
			if ( creationTime != null && hasGMT(creationTime)) {
				creationTime = getUTC(creationTime);
				metaBean.setCreationTime(creationTime);
			}			
			
			LOG.debug("DateUTCUtil: serviceStartTime: " + serviceStartTime);
			LOG.debug("DateUTCUtil: serviceEndTime: " + serviceEndTime);
			LOG.debug("DateUTCUtil: creationTime: " + creationTime);
		}
		else
			LOG.debug("DateUTCUtil: serviceStartTime is null");


	}
	
	private boolean hasGMT(String dateTime) {
		boolean hasgmt = false;
		if(dateTime.indexOf("-") >= 0 ||
			dateTime.indexOf("+") >= 0) {
			hasgmt = true;
		}
		return hasgmt;
	}
	
	 public static String getUTC(String dateTime) {
	        String returnDate = null;
	        String datePattern = null;
	        
	        if (dateTime.length() == DATE_NO_SECS) {
	        	datePattern = "yyyyMMddHHmm";
	        } else if (dateTime.length() == DATE_WITH_SECS) {
	        	datePattern = "yyyyMMddHHmmss";
	        }
	        String tzPattern = datePattern + "Z";
	        LOG.info("DateUTCUtil: tzPattern: " + tzPattern);
	        SimpleDateFormat simpleDate = new SimpleDateFormat(tzPattern);
	        SimpleDateFormat outSimpleDate = new SimpleDateFormat(datePattern);
	        outSimpleDate.setTimeZone(TimeZone.getTimeZone("GMT-0:00"));
	        try {
	            returnDate = outSimpleDate.format(simpleDate.parse(dateTime));
	        } catch (ParseException e) {
	        	LOG.error("DateUTCUtil.getUTC():ERROR, cause:  "+ e.getCause());
	        }
	        return returnDate;
	    }
}
