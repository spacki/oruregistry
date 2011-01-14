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
package org.openehealth.ipf.ws.oru.cda2xds.pix;

/**
 * PIXConfiguration
 * This class defines all the configurable values that are used to perform a
 * PIX query.
 * @author Anu Jandhyala
 * Rationale behind data Configuration objects:
 * 		PIXConfiguration provides static configuration for PIX QBP message 
 * 		MetadataBean is populated with data that may be modified from an incoming message
 * 
 * History of Changes:
 */
public class PIXConfiguration extends Object{
	public static String enablePix;
	public static String pixManagerInfo;
	public static String localNamespace;
	public static String localAssigningAuthority;
	public static String localUUtype;
	public static String globalAssigningAuthority;
	public static String pixQueryId_1;
	public static String pixQueryId_2;    
	public static String pixQueryId_3;    
	// values that might be used for PIX query
	public static String encodingChars;
	public static String sendingFacility;
	public static String sendingApplication;
	public static String receivingFacility;
	public static String receivingApplication;
	public static String hl7Version;
    
    public String getEnablePix() {
    	return enablePix;
    }
    
    public void setEnablePix(String enablepix) {
    	PIXConfiguration.enablePix = enablepix;
    }
    
    public String getPixManagerInfo() {
    	return pixManagerInfo;
    }
    
    public void setPixManagerInfo(String dummy) {
    	PIXConfiguration.pixManagerInfo = dummy;
    }

    public String getLocalNamespace() {
        return localNamespace;
    }
    
    public void setLocalNamespace(String localNamespace) {
    	PIXConfiguration.localNamespace = localNamespace;

    }
    
    public String getlocalAssigningAuthority() {
    	return localAssigningAuthority;
    }
    
    public void setLocalAssigningAuthority(String localAssigningAuthority) {
    	PIXConfiguration.localAssigningAuthority = localAssigningAuthority;

    }
    
    public String getlocalUUType() {
    	return localUUtype;
    }
    
    public void setLocalUUtype(String localUUtype) {
    	PIXConfiguration.localUUtype = localUUtype;
    }
    
    public String getGlobalAssigningAuthority() {
    	return globalAssigningAuthority;
    }
    
    public void setGlobalAssigningAuthority(String globalAssigningAuthority) {
    	PIXConfiguration.globalAssigningAuthority = globalAssigningAuthority;
    }
    
    public String getpixQueryId_1() {
    	return pixQueryId_1;
    }

    public void setpixQueryId_1(String pixQueryId) {
    	PIXConfiguration.pixQueryId_1 = pixQueryId;
    }
    
    public String getpixQueryId_2() {
    	return pixQueryId_2;
    }
    
    public void setpixQueryId_2(String pixQueryId) {
    	PIXConfiguration.pixQueryId_2 = pixQueryId;
    }
    
    public String getpixQueryId_3() {
    	return pixQueryId_3;
    }

    public void setpixQueryId_3(String pixQueryId) {
    	PIXConfiguration.pixQueryId_3 = pixQueryId;
    }
    
	
	public String getEncodingChars() {
		return encodingChars;
	}
	
	public void setEncodingChars(String EncodingChars) {
		PIXConfiguration.encodingChars = EncodingChars;
	}
	
	public String getSendingFacility() {
		return sendingFacility;
	}
	
	public void setSendingFacility(String sendingFac) {
		PIXConfiguration.sendingFacility = sendingFac;
	}
	
	public String getSendingApplication() {
		return sendingApplication;
	}
	
	public void setSendingApplication(String sendingApp) {
		PIXConfiguration.sendingApplication = sendingApp;
	}
	
	public String getReceivingFacility() {
		return receivingFacility;
	}
	
	public void setReceivingFacility(String receivingFac) {
		PIXConfiguration.receivingFacility = receivingFac;
	}
	
	public String getReceivingApplication() {
		return receivingApplication;
	}
	
	public void setReceivingApplication(String receivingApp) {
		PIXConfiguration.receivingApplication = receivingApp;
	}	

	public String getHl7Version() {
		return hl7Version;
	}
	
	public void setHl7Version(String version) {
		PIXConfiguration.hl7Version = version;
	}
}
