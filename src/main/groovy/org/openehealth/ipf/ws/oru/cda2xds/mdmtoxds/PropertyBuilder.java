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
package org.openehealth.ipf.ws.oru.cda2xds.mdmtoxds;

/**
 * @author Sanjiv Venugopal
 * 
 * History of Changes:
 */
public class PropertyBuilder {
    //public static String logserver;
    public static String sourceOID;
    public static String repositoryUniqueId;
    public static String uri;
    public static String sourceID;

    //public static String globalPatientID;
    public static String enableCDAParsing;
    public static String languageCode;
    public static String c32_ClassCodeDisplayName;
    public static String c32_ClassCodeNodeRepresentation;
    public static String c32_ClassCodeCodingScheme;
    public static String c48_ClassCodeDisplayName;
    public static String c48_ClassCodeNodeRepresentation;
    public static String c48_ClassCodeCodingScheme;
    public static String xdssd_ClassCodeDisplayName;
    public static String xdssd_ClassCodeNodeRepresentation;
    public static String xdssd_ClassCodeCodingScheme;
   
    public static String classCodeDisplayName;
    public static String classCodeNodeRepresentation;
    public static String classCodeCodingScheme;
        
    public static String confidentialityCodeDisplayName;
    public static String confidentialityCodeNodeRepresentation;
    public static String confidentialityCodeCodingScheme;
    
    public static String cdaR2_FormatCodeDisplayName;
    public static String cdaR2_FormatCodeNodeRepresentation;
    public static String cdaR2_FormatCodeCodingScheme;
    public static String formatCodeDisplayName;
    public static String formatCodeNodeRepresentation;
    public static String formatCodeCodingScheme;
    
    public static String healthcareFacilityCodeDisplayName;
    public static String healthcareFacilityCodeNodeRepresentation;
    public static String healthcareFacilityCodeCodingScheme;
    
    public static String practiceCodeDisplayName;
    public static String practiceCodeNodeRepresentation;
    public static String practiceCodeCodingScheme;

    public static String c32_ContentTypeCodeDisplayName;
    public static String c32_ContentTypeCodeNodeRepresentation;
    public static String c32_ContentTypeCodeCodingScheme;
    public static String c48_ContentTypeCodeDisplayName;
    public static String c48_ContentTypeCodeNodeRepresentation;
    public static String c48_ContentTypeCodeCodingScheme;
    public static String xdssd_ContentTypeCodeDisplayName;
    public static String xdssd_ContentTypeCodeNodeRepresentation;
    public static String xdssd_ContentTypeCodeCodingScheme;
   
    public static String contentTypeCodeDisplayName;
    public static String contentTypeCodeNodeRepresentation;
    public static String contentTypeCodeCodingScheme;
    
    public static String c32_TypeCodeDisplayName;
    public static String c32_TypeCodeNodeRepresentation;
    public static String c32_TypeCodeCodingScheme;
    public static String c48_TypeCodeDisplayName;
    public static String c48_TypeCodeNodeRepresentation;
    public static String c48_TypeCodeCodingScheme;
    public static String xdssd_TypeCodeDisplayName;
    public static String xdssd_TypeCodeNodeRepresentation;
    public static String xdssd_TypeCodeCodingScheme;
    
    public static String typeCodeDisplayName;
    public static String typeCodeNodeRepresentation;
    public static String typeCodeCodingScheme;
    
	/*New props added for ORU - LDS Document*/
	public static String authorRole;
	public static String authorSpeciality;
	public static String ldsTemplateId;
	//Class Codes for LDS
	public static String lds_ClassCodeDisplayName;
	public static String lds_ClassCodeNodeRepresentation;
	public static String lds_ClassCodeCodingScheme;
	//Content Type Codes for LDS
	public static String lds_ContentTypeCodeDisplayName;
	public static String lds_ContentTypeCodeNodeRepresentation;
	public static String lds_ContentTypeCodeCodingScheme;	
	//Format Codes for LDS
	public static String lds_FormatCodeDisplayName;
	public static String lds_FormatCodeNodeRepresentation;
	public static String lds_FormatCodeCodingScheme;
	//Type Codes for LDS
	public static String lds_TypeCodeDisplayName;
	public static String lds_TypeCodeNodeRepresentation;
	public static String lds_TypeCodeCodingScheme;
	//Common Event codes 
	public static String eventCodeDisplayName;
	public static String eventCodeNodeRepresentation;
	public static String eventCodeCodingScheme;	
	

	
    public void setSourceOID(String sourceOID) {
    	PropertyBuilder.sourceOID = sourceOID;
        
    }
    public String getSourceOID() {
        return sourceOID;
    }
   
    public void setSourceID(String sourceID) {
    	PropertyBuilder.sourceID = sourceID;
        
    }
    public String getSourceID() {
        return sourceID;
    }

    public void setRepositoryUniqueId(String repositoryUniqueId) {
    	PropertyBuilder.repositoryUniqueId = repositoryUniqueId;
        
    }
    public String getRepositoryUniqueId() {
        return repositoryUniqueId;
    }
    
    public void setUri(String uri) {
    	PropertyBuilder.uri = uri;
        
    }
    public String getUri() {
        return uri;
    }
   
    public void setEnableCDAParsing(String enableCDAParsing) {
    	PropertyBuilder.enableCDAParsing = enableCDAParsing;
        
    }
    public String getEnableCDAParsing() {
        return enableCDAParsing;
    }
    
    public void setLanguageCode(String languageCode) {
    	PropertyBuilder.languageCode = languageCode;
        
    }
    public String getLanguageCode() {
        return languageCode;
    }
    
    public void setC32_ClassCodeDisplayName(String c32_ClassCodeDisplayName) {
    	PropertyBuilder.c32_ClassCodeDisplayName = c32_ClassCodeDisplayName;
        
    }
    public String getC32_ClassCodeDisplayName() {
        return c32_ClassCodeDisplayName;
    }
    
    public void setC32_ClassCodeNodeRepresentation(String c32_ClassCodeNodeRepresentation) {
    	PropertyBuilder.c32_ClassCodeNodeRepresentation = c32_ClassCodeNodeRepresentation;
        
    }
    public String getC32_ClassCodeNodeRepresentation() {
        return c32_ClassCodeNodeRepresentation;
    }
    
    public void setC32_ClassCodeCodingScheme(String c32_ClassCodeCodingScheme) {
    	PropertyBuilder.c32_ClassCodeCodingScheme = c32_ClassCodeCodingScheme;
        
    }
    public String getC32_ClassCodeCodingScheme() {
        return c32_ClassCodeCodingScheme;
    }
    
    public void setC48_ClassCodeDisplayName(String c48_ClassCodeDisplayName) {
    	PropertyBuilder.c48_ClassCodeDisplayName = c48_ClassCodeDisplayName;
        
    }
    public String getC48_ClassCodeDisplayName() {
        return c48_ClassCodeDisplayName;
    }
    
    public void setC48_ClassCodeNodeRepresentation(String c48_ClassCodeNodeRepresentation) {
    	PropertyBuilder.c48_ClassCodeNodeRepresentation = c48_ClassCodeNodeRepresentation;
        
    }
    public String getC48_ClassCodeNodeRepresentation() {
        return c48_ClassCodeNodeRepresentation;
    }
    
    public void setC48_ClassCodeCodingScheme(String c48_ClassCodeCodingScheme) {
    	PropertyBuilder.c48_ClassCodeCodingScheme = c48_ClassCodeCodingScheme;
        
    }
    public String getC48_ClassCodeCodingScheme() {
        return c48_ClassCodeCodingScheme;
    }
    
    public void setXdssd_ClassCodeDisplayName(String xdssd_ClassCodeDisplayName) {
    	PropertyBuilder.xdssd_ClassCodeDisplayName = xdssd_ClassCodeDisplayName;
        
    }
    public String getXdssd_ClassCodeDisplayName() {
        return xdssd_ClassCodeDisplayName;
    }
    
    public void setXdssd_ClassCodeNodeRepresentation(String xdssd_ClassCodeNodeRepresentation) {
    	PropertyBuilder.xdssd_ClassCodeNodeRepresentation = xdssd_ClassCodeNodeRepresentation;
        
    }
    public String getXdssd_ClassCodeNodeRepresentation() {
        return xdssd_ClassCodeNodeRepresentation;
    }
    
    public void setXdssd_ClassCodeCodingScheme(String xdssd_ClassCodeCodingScheme) {
    	PropertyBuilder.xdssd_ClassCodeCodingScheme = xdssd_ClassCodeCodingScheme;
        
    }
    public String getXdssd_ClassCodeCodingScheme() {
        return xdssd_ClassCodeCodingScheme;
    }
    
    public void setclassCodeDisplayName(String classCodeDisplayName) {
    	PropertyBuilder.classCodeDisplayName = classCodeDisplayName;
        
    }
    public String getClassCodeDisplayName() {
        return classCodeDisplayName;
    }
    
    public void setClassCodeNodeRepresentation(String classCodeNodeRepresentation) {
    	PropertyBuilder.classCodeNodeRepresentation = classCodeNodeRepresentation;
        
    }
    public String getClassCodeNodeRepresentation() {
        return classCodeNodeRepresentation;
    }
    
    public void setClassCodeCodingScheme(String classCodeCodingScheme) {
    	PropertyBuilder.classCodeCodingScheme = classCodeCodingScheme;
        
    }
    public String getClassCodeCodingScheme() {
        return classCodeCodingScheme;
    }

    
    public void setConfidentialityCodeDisplayName(String confidentialityCodeDisplayName) {
    	PropertyBuilder.confidentialityCodeDisplayName = confidentialityCodeDisplayName;
        
    }
    public String getConfidentialityCodeDisplayName() {
        return confidentialityCodeDisplayName;
    }
    public void setConfidentialityCodeNodeRepresentation(String confidentialityCodeNodeRepresentation) {
    	PropertyBuilder.confidentialityCodeNodeRepresentation = confidentialityCodeNodeRepresentation;
        
    }
    public String getConfidentialityCodeNodeRepresentation() {
        return confidentialityCodeNodeRepresentation;
    }
    
    public void setConfidentialityCodeCodingScheme(String confidentialityCodeCodingScheme) {
    	PropertyBuilder.confidentialityCodeCodingScheme = confidentialityCodeCodingScheme;
        
    }
    public String getConfidentialityCodeCodingScheme() {
        return confidentialityCodeCodingScheme;
    }
    
    public void setCdaR2_FormatCodeDisplayName(String cdaR2_FormatCodeDisplayName) {
    	PropertyBuilder.cdaR2_FormatCodeDisplayName = cdaR2_FormatCodeDisplayName;
        
    }
    public String getCdaR2_FormatCodeDisplayName() {
        return cdaR2_FormatCodeDisplayName;
    }
    
    public void setCdaR2_FormatCodeNodeRepresentation(String cdaR2_FormatCodeNodeRepresentation) {
    	PropertyBuilder.cdaR2_FormatCodeNodeRepresentation = cdaR2_FormatCodeNodeRepresentation;
        
    }
    public String getCdaR2_FormatCodeNodeRepresentation() {
        return cdaR2_FormatCodeNodeRepresentation;
    }
    
    public void setCdaR2_FormatCodeCodingScheme(String cdaR2_FormatCodeCodingScheme) {
    	PropertyBuilder.cdaR2_FormatCodeCodingScheme = cdaR2_FormatCodeCodingScheme;
        
    }
    public String getCdaR2_FormatCodeCodingScheme() {
        return cdaR2_FormatCodeCodingScheme;
    }
    
    
    public void setFormatCodeDisplayName(String formatCodeDisplayName) {
    	PropertyBuilder.formatCodeDisplayName = formatCodeDisplayName;
        
    }
    public String getFormatCodeDisplayName() {
        return formatCodeDisplayName;
    }
    
    public void setFormatCodeNodeRepresentation(String formatCodeNodeRepresentation) {
    	PropertyBuilder.formatCodeNodeRepresentation = formatCodeNodeRepresentation;
        
    }
    public String getFormatCodeNodeRepresentation() {
        return formatCodeNodeRepresentation;
    }
    
    public void setFormatCodeCodingScheme(String formatCodeCodingScheme) {
    	PropertyBuilder.formatCodeCodingScheme = formatCodeCodingScheme;
        
    }
    public String getFormatCodeCodingScheme() {
        return formatCodeCodingScheme;
    }
    
    public void setHealthcareFacilityCodeDisplayName(String healthcareFacilityCodeDisplayName) {
    	PropertyBuilder.healthcareFacilityCodeDisplayName = healthcareFacilityCodeDisplayName;
        
    }
    public String getHealthcareFacilityCodeDisplayName() {
        return healthcareFacilityCodeDisplayName;
    }
    
    public void setHealthcareFacilityCodeNodeRepresentation(String healthcareFacilityCodeNodeRepresentation) {
    	PropertyBuilder.healthcareFacilityCodeNodeRepresentation = healthcareFacilityCodeNodeRepresentation;
        
    }
    public String getHealthcareFacilityCodeNodeRepresentation() {
        return healthcareFacilityCodeNodeRepresentation;
    }
    
    public void setHealthcareFacilityCodeCodingScheme(String healthcareFacilityCodeCodingScheme) {
    	PropertyBuilder.healthcareFacilityCodeCodingScheme = healthcareFacilityCodeCodingScheme;
        
    }
    public String getHealthcareFacilityCodeCodingScheme() {
        return healthcareFacilityCodeCodingScheme;
    }
    
    public void setPracticeCodeDisplayName(String practiceCodeDisplayName) {
    	PropertyBuilder.practiceCodeDisplayName = practiceCodeDisplayName;
        
    }
    public String getPracticeCodeDisplayName() {
        return practiceCodeDisplayName;
    }
    
    public void setPracticeCodeNodeRepresentation(String practiceCodeNodeRepresentation) {
    	PropertyBuilder.practiceCodeNodeRepresentation = practiceCodeNodeRepresentation;
        
    }
    public String getPracticeCodeNodeRepresentation() {
        return practiceCodeNodeRepresentation;
    }
    
    public void setPracticeCodeCodingScheme(String practiceCodeCodingScheme) {
    	PropertyBuilder.practiceCodeCodingScheme = practiceCodeCodingScheme;
        
    }
    public String getPracticeCodeCodingScheme() {
        return practiceCodeCodingScheme;
    }
    

    public void setC32_ContentTypeCodeDisplayName(String c32_ContentTypeCodeDisplayName) {
    	PropertyBuilder.c32_ContentTypeCodeDisplayName = c32_ContentTypeCodeDisplayName;
        
    }
    public String getC32_ContentTypeCodeDisplayName() {
        return c32_ContentTypeCodeDisplayName;
    }
    
    public void setC32_ContentTypeCodeNodeRepresentation(String c32_ContentTypeCodeNodeRepresentation) {
    	PropertyBuilder.c32_ContentTypeCodeNodeRepresentation = c32_ContentTypeCodeNodeRepresentation;
        
    }
    public String getC32_ContentTypeCodeNodeRepresentation() {
        return c32_ContentTypeCodeNodeRepresentation;
    }
    
    public void setC32_ContentTypeCodeCodingScheme(String c32_ContentTypeCodeCodingScheme) {
    	PropertyBuilder.c32_ContentTypeCodeCodingScheme = c32_ContentTypeCodeCodingScheme;
        
    }
    public String getC32_ContentTypeCodeCodingScheme() {
        return c32_ContentTypeCodeCodingScheme;
    }
    
    public void setC48_ContentTypeCodeDisplayName(String c48_ContentTypeCodeDisplayName) {
    	PropertyBuilder.c48_ContentTypeCodeDisplayName = c48_ContentTypeCodeDisplayName;
        
    }
    public String getC48_ContentTypeCodeDisplayName() {
        return c48_ContentTypeCodeDisplayName;
    }
    
    public void setC48_ContentTypeCodeNodeRepresentation(String c48_ContentTypeCodeNodeRepresentation) {
    	PropertyBuilder.c48_ContentTypeCodeNodeRepresentation = c48_ContentTypeCodeNodeRepresentation;
        
    }
    public String getC48_ContentTypeCodeNodeRepresentation() {
        return c48_ContentTypeCodeNodeRepresentation;
    }
    
    public void setC48_ContentTypeCodeCodingScheme(String c48_ContentTypeCodeCodingScheme) {
    	PropertyBuilder.c48_ContentTypeCodeCodingScheme = c48_ContentTypeCodeCodingScheme;
        
    }
    public String getC48_ContentTypeCodeCodingScheme() {
        return c48_ContentTypeCodeCodingScheme;
    }

    public void setXdssd_ContentTypeCodeDisplayName(String xdssd_ContentTypeCodeDisplayName) {
    	PropertyBuilder.xdssd_ContentTypeCodeDisplayName = xdssd_ContentTypeCodeDisplayName;
        
    }
    public String getXdssd_ContentTypeCodeDisplayName() {
        return xdssd_ContentTypeCodeDisplayName;
    }
    
    public void setXdssd_ContentTypeCodeNodeRepresentation(String xdssd_ContentTypeCodeNodeRepresentation) {
    	PropertyBuilder.xdssd_ContentTypeCodeNodeRepresentation = xdssd_ContentTypeCodeNodeRepresentation;
        
    }
    public String getXdssd_ContentTypeCodeNodeRepresentation() {
        return xdssd_ContentTypeCodeNodeRepresentation;
    }
    
    public void setXdssd_ContentTypeCodeCodingScheme(String xdssd_ContentTypeCodeCodingScheme) {
    	PropertyBuilder.xdssd_ContentTypeCodeCodingScheme = xdssd_ContentTypeCodeCodingScheme;
        
    }
    public String getXdssd_ContentTypeCodeCodingScheme() {
        return xdssd_ContentTypeCodeCodingScheme;
    }
    
    public void setContentTypeCodeDisplayName(String contentTypeCodeDisplayName) {
    	PropertyBuilder.contentTypeCodeDisplayName = contentTypeCodeDisplayName;
        
    }
    public String getContentTypeCodeDisplayName() {
        return contentTypeCodeDisplayName;
    }
    
    public void setContentTypeCodeNodeRepresentation(String contentTypeCodeNodeRepresentation) {
    	PropertyBuilder.contentTypeCodeNodeRepresentation = contentTypeCodeNodeRepresentation;
        
    }
    public String getContentTypeCodeNodeRepresentation() {
        return contentTypeCodeNodeRepresentation;
    }
    
    public void setContentTypeCodeCodingScheme(String contentTypeCodeCodingScheme) {
    	PropertyBuilder.contentTypeCodeCodingScheme = contentTypeCodeCodingScheme;
        
    }
    public String getContentTypeCodeCodingScheme() {
        return contentTypeCodeCodingScheme;
    }
    
    public void setC32_TypeCodeDisplayName(String c32_TypeCodeDisplayName) {
    	PropertyBuilder.c32_TypeCodeDisplayName = c32_TypeCodeDisplayName;
        
    }
    public String getC32_TypeCodeDisplayName() {
        return c32_TypeCodeDisplayName;
    }
    
    public void setC32_TypeCodeNodeRepresentation(String c32_TypeCodeNodeRepresentation) {
    	PropertyBuilder.c32_TypeCodeNodeRepresentation = c32_TypeCodeNodeRepresentation;
        
    }
    public String getC32_TypeCodeNodeRepresentation() {
        return c32_TypeCodeNodeRepresentation;
    }
    
    public void setC32_TypeCodeCodingScheme(String c32_TypeCodeCodingScheme) {
    	PropertyBuilder.c32_TypeCodeCodingScheme = c32_TypeCodeCodingScheme;
        
    }
    public String getC32_TypeCodeCodingScheme() {
        return c32_TypeCodeCodingScheme;
    }
  
    public void setC48_TypeCodeDisplayName(String c48_TypeCodeDisplayName) {
    	PropertyBuilder.c48_TypeCodeDisplayName = c48_TypeCodeDisplayName;
        
    }
    public String getC48_TypeCodeDisplayName() {
        return c48_TypeCodeDisplayName;
    }
    
    public void setC48_TypeCodeNodeRepresentation(String c48_TypeCodeNodeRepresentation) {
    	PropertyBuilder.c48_TypeCodeNodeRepresentation = c48_TypeCodeNodeRepresentation;
        
    }
    public String getC48_TypeCodeNodeRepresentation() {
        return c48_TypeCodeNodeRepresentation;
    }
    
    public void setC48_TypeCodeCodingScheme(String c48_TypeCodeCodingScheme) {
    	PropertyBuilder.c48_TypeCodeCodingScheme = c48_TypeCodeCodingScheme;
        
    }
    public String getC48_TypeCodeCodingScheme() {
        return c48_TypeCodeCodingScheme;
    }
   
    public void setXdssd_TypeCodeDisplayName(String xdssd_TypeCodeDisplayName) {
    	PropertyBuilder.xdssd_TypeCodeDisplayName = xdssd_TypeCodeDisplayName;
        
    }
    public String getXdssd_TypeCodeDisplayName() {
        return xdssd_TypeCodeDisplayName;
    }
    
    public void setXdssd_TypeCodeNodeRepresentation(String xdssd_TypeCodeNodeRepresentation) {
    	PropertyBuilder.xdssd_TypeCodeNodeRepresentation = xdssd_TypeCodeNodeRepresentation;
        
    }
    public String getXdssd_TypeCodeNodeRepresentation() {
        return xdssd_TypeCodeNodeRepresentation;
    }
    
    public void setXdssd_TypeCodeCodingScheme(String xdssd_TypeCodeCodingScheme) {
    	PropertyBuilder.xdssd_TypeCodeCodingScheme = xdssd_TypeCodeCodingScheme;
        
    }
    public String getXdssd_TypeCodeCodingScheme() {
        return xdssd_TypeCodeCodingScheme;
    }
    
    public void setTypeCodeDisplayName(String typeCodeDisplayName) {
    	PropertyBuilder.typeCodeDisplayName = typeCodeDisplayName;
        
    }
    public String getTypeCodeDisplayName() {
        return typeCodeDisplayName;
    }
    
    public void setTypeCodeNodeRepresentation(String typeCodeNodeRepresentation) {
    	PropertyBuilder.typeCodeNodeRepresentation = typeCodeNodeRepresentation;
        
    }
    public String getTypeCodeNodeRepresentation() {
        return typeCodeNodeRepresentation;
    }
    
    public void setTypeCodeCodingScheme(String typeCodeCodingScheme) {
    	PropertyBuilder.typeCodeCodingScheme = typeCodeCodingScheme;
        
    }
    public String getTypeCodeCodingScheme() {
        return typeCodeCodingScheme;
    }
    
	public static String getAuthorRole() {
		return authorRole;
	}
	public void setAuthorRole(String authorRole) {
		PropertyBuilder.authorRole = authorRole;
	}
	public static String getAuthorSpeciality() {
		return authorSpeciality;
	}
	public void setAuthorSpeciality(String authorSpeciality) {
		PropertyBuilder.authorSpeciality = authorSpeciality;
	}
	public static String getLdsTemplateId() {
		return ldsTemplateId;
	}
	public void setLdsTemplateId(String ldsTemplateId) {
		PropertyBuilder.ldsTemplateId = ldsTemplateId;
	}
	public static String getLds_ClassCodeDisplayName() {
		return lds_ClassCodeDisplayName;
	}
	public void setLds_ClassCodeDisplayName(String lds_ClassCodeDisplayName) {
		PropertyBuilder.lds_ClassCodeDisplayName = lds_ClassCodeDisplayName;
	}
	public static String getLds_ClassCodeNodeRepresentation() {
		return lds_ClassCodeNodeRepresentation;
	}
	public void setLds_ClassCodeNodeRepresentation(
			String lds_ClassCodeNodeRepresentation) {
		PropertyBuilder.lds_ClassCodeNodeRepresentation = lds_ClassCodeNodeRepresentation;
	}
	public static String getLds_ClassCodeCodingScheme() {
		return lds_ClassCodeCodingScheme;
	}
	public void setLds_ClassCodeCodingScheme(String lds_ClassCodeCodingScheme) {
		PropertyBuilder.lds_ClassCodeCodingScheme = lds_ClassCodeCodingScheme;
	}
	public static String getLds_ContentTypeCodeDisplayName() {
		return lds_ContentTypeCodeDisplayName;
	}
	public void setLds_ContentTypeCodeDisplayName(
			String lds_ContentTypeCodeDisplayName) {
		PropertyBuilder.lds_ContentTypeCodeDisplayName = lds_ContentTypeCodeDisplayName;
	}
	public static String getLds_ContentTypeCodeNodeRepresentation() {
		return lds_ContentTypeCodeNodeRepresentation;
	}
	public void setLds_ContentTypeCodeNodeRepresentation(
			String lds_ContentTypeCodeNodeRepresentation) {
		PropertyBuilder.lds_ContentTypeCodeNodeRepresentation = lds_ContentTypeCodeNodeRepresentation;
	}
	public static String getLds_ContentTypeCodeCodingScheme() {
		return lds_ContentTypeCodeCodingScheme;
	}
	public void setLds_ContentTypeCodeCodingScheme(
			String lds_ContentTypeCodeCodingScheme) {
		PropertyBuilder.lds_ContentTypeCodeCodingScheme = lds_ContentTypeCodeCodingScheme;
	}
	public static String getLds_FormatCodeDisplayName() {
		return lds_FormatCodeDisplayName;
	}
	public void setLds_FormatCodeDisplayName(String lds_FormatCodeDisplayName) {
		PropertyBuilder.lds_FormatCodeDisplayName = lds_FormatCodeDisplayName;
	}
	public static String getLds_FormatCodeNodeRepresentation() {
		return lds_FormatCodeNodeRepresentation;
	}
	public void setLds_FormatCodeNodeRepresentation(
			String lds_FormatCodeNodeRepresentation) {
		PropertyBuilder.lds_FormatCodeNodeRepresentation = lds_FormatCodeNodeRepresentation;
	}
	public static String getLds_FormatCodeCodingScheme() {
		return lds_FormatCodeCodingScheme;
	}
	public void setLds_FormatCodeCodingScheme(
			String lds_FormatCodeCodingScheme) {
		PropertyBuilder.lds_FormatCodeCodingScheme = lds_FormatCodeCodingScheme;
	}
	public static String getLds_TypeCodeDisplayName() {
		return lds_TypeCodeDisplayName;
	}
	public void setLds_TypeCodeDisplayName(String lds_TypeCodeDisplayName) {
		PropertyBuilder.lds_TypeCodeDisplayName = lds_TypeCodeDisplayName;
	}
	public static String getLds_TypeCodeNodeRepresentation() {
		return lds_TypeCodeNodeRepresentation;
	}
	public void setLds_TypeCodeNodeRepresentation(
			String lds_TypeCodeNodeRepresentation) {
		PropertyBuilder.lds_TypeCodeNodeRepresentation = lds_TypeCodeNodeRepresentation;
	}
	public static String getLds_TypeCodeCodingScheme() {
		return lds_TypeCodeCodingScheme;
	}
	public void setLds_TypeCodeCodingScheme(String lds_TypeCodeCodingScheme) {
		PropertyBuilder.lds_TypeCodeCodingScheme = lds_TypeCodeCodingScheme;
	}
	
	/*Getter Setter methods for LDS related properties */
	
	/*Getter Setter methods for Event code related properties */
	public static String getEventCodeDisplayName() {
		return eventCodeDisplayName;
	}
	public void setEventCodeDisplayName(String eventCodeDisplayName) {
		PropertyBuilder.eventCodeDisplayName = eventCodeDisplayName;
	}
	
	public static String getEventCodeNodeRepresentation() {
		return eventCodeNodeRepresentation;
	}
	public void setEventCodeNodeRepresentation(String eventCodeNodeRepresentation) {
		PropertyBuilder.eventCodeNodeRepresentation = eventCodeNodeRepresentation;
	}
	
	public static String getEventCodeCodingScheme() {
		return eventCodeCodingScheme;
	}
	public void setEventCodeCodingScheme(String eventCodeCodingScheme) {
		PropertyBuilder.eventCodeCodingScheme = eventCodeCodingScheme;
	}

}
