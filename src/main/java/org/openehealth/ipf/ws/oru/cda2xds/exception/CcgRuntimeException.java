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

package org.openehealth.ipf.ws.oru.cda2xds.exception;

/**
 * Common exception class to be used across modules
 * Extends RuntimeException
 * @author Swapnil Sharma
 *
 */
public class CcgRuntimeException extends RuntimeException { 
	public CcgRuntimeException(Exception e){
		super(e);
	}
}
