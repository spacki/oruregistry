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

package org.openehealth.ipf.ws.oru.cda2xds.beans;

/**
 * Bean class for storing data elements extracted from
 * incoming message by sending applications - CDA or HL7 etc 
 * @author Swapnil Sharma
 *
 * History of Changes:
 * 1/26/2010: Anu Jandhyala
 * Added PIX related data and setters/getters for it. 
 * 
 * 1/31/2010: Sanjiv Venugopal
 * Added DocumentUniqueId and setters/getters for it.
 * 
 * 2/23/2010: Anu Jandhyala
 * Setters and getters for document Title
 */
public class MetadataBean {
	private String mimeType;
	private String creationTime;
	private String languageCode;
	private String serviceStartTime;
	private String serviceEndTime;
	private String sourcePatientId;
	private String sourcePatientOId;
	private String sourcePatientName;
	private String sourcePatientBirthTime;
	private String sourcePatientGender;
	private String sourcePatientAddress;
	private String authorPerson;
	private String authorInstitution;
	private String providerAssignedPersonName;
	private String authorRole;
	private String authorSpecialty;
	private String classCodeDisplayName;
	private String classCodeNodeRepresentation;
	private String classCodeCodingScheme;
	private String typeCodeDisplayName;
	private String typeCodeNodeRepresentation;
	private String typeCodeCodingScheme;
	private String contentTypeCodeDisplayName;
	private String contentTypeCodeNodeRepresentation;
	private String contentTypeCodeCodingScheme;
	private String formatCodeDisplayName;
	private String formatCodeNodeRepresentation;
	private String formatCodeCodingScheme;
	private String uri;
	private String sourceId;
	private String docUniqueId;
	//Confidentiality codes 
	private String confidentialityCodeDisplayName;
	private String confidentialityCodeNodeRepresentation;
	private String confidentialityCodeCodingScheme;
	// Healthcare Facility Type codes 
	private String healthcareFacilityCodeDisplayName;
	private String healthcareFacilityCodeNodeRepresentation;
	private String healthcareFacilityCodeCodingScheme;
	// Practice Setting codes 
	private String practiceCodeDisplayName;
	private String practiceCodeNodeRepresentation;
	private String practiceCodeCodingScheme;
	// values that might be used for PIX query
	private String encodingChars;
	private String sendingFacility;
	private String sendingApplication;
	private String receivingFacility;
	private String receivingApplication;
    private String hl7Version;
    private String docTitle;
    // values used for registry query
    private EventCodeList eventCodes = new EventCodeList();
	
	
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getServiceStartTime() {
		return serviceStartTime;
	}
	public void setServiceStartTime(String serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}
	public String getServiceEndTime() {
		return serviceEndTime;
	}
	public void setServiceEndTime(String serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}
	public String getSourcePatientId() {
		return sourcePatientId;
	}
	public void setSourcePatientId(String sourcePatientId) {
		this.sourcePatientId = sourcePatientId;
	}
	public String getSourcePatientOId() {
		return sourcePatientOId;
	}
	public void setSourcePatientOId(String sourcePatientOId) {
		this.sourcePatientOId = sourcePatientOId;
	}
	public String getSourcePatientName() {
		return sourcePatientName;
	}
	public void setSourcePatientName(String sourcePatientName) {
		this.sourcePatientName = sourcePatientName;
	}
	public String getSourcePatientBirthTime() {
		return sourcePatientBirthTime;
	}
	public void setSourcePatientBirthTime(String sourcePatientBirthTime) {
		this.sourcePatientBirthTime = sourcePatientBirthTime;
	}
	public String getSourcePatientGender() {
		return sourcePatientGender;
	}
	public void setSourcePatientGender(String sourcePatientGender) {
		this.sourcePatientGender = sourcePatientGender;
	}
	public String getSourcePatientAddress() {
		return sourcePatientAddress;
	}
	public void setSourcePatientAddress(String patientAddress) {
		this.sourcePatientAddress = patientAddress;
	}
	public String getAuthorPerson() {
		return authorPerson;
	}
	public void setAuthorPerson(String authorPerson) {
		this.authorPerson = authorPerson;
	}
	public String getAuthorInstitution() {
		return authorInstitution;
	}
	public void setAuthorInstitution(String authorInstitution) {
		this.authorInstitution = authorInstitution;
	}
	public String getProviderAssignedPersonName() {
		return providerAssignedPersonName;
	}
	public void setProviderAssignedPersonName(String providerAssignedPersonName) {
		this.providerAssignedPersonName = providerAssignedPersonName;
	}
	public String getAuthorRole() {
		return authorRole;
	}
	public void setAuthorRole(String authorRole) {
		this.authorRole = authorRole;
	}
	public String getAuthorSpecialty() {
		return authorSpecialty;
	}
	public void setAuthorSpecialty(String authorSpecialty) {
		this.authorSpecialty = authorSpecialty;
	}
	public String getClassCodeDisplayName() {
		return classCodeDisplayName;
	}
	public void setClassCodeDisplayName(String classCodeDisplayName) {
		this.classCodeDisplayName = classCodeDisplayName;
	}
	public String getClassCodeNodeRepresentation() {
		return classCodeNodeRepresentation;
	}
	public void setClassCodeNodeRepresentation(String classCodeNodeRepresentation) {
		this.classCodeNodeRepresentation = classCodeNodeRepresentation;
	}
	public String getClassCodeCodingScheme() {
		return classCodeCodingScheme;
	}
	public void setClassCodeCodingScheme(String classCodeCodingScheme) {
		this.classCodeCodingScheme = classCodeCodingScheme;
	}
	public String getTypeCodeDisplayName() {
		return typeCodeDisplayName;
	}
	public void setTypeCodeDisplayName(String typeCodeDisplayName) {
		this.typeCodeDisplayName = typeCodeDisplayName;
	}
	public String getTypeCodeNodeRepresentation() {
		return typeCodeNodeRepresentation;
	}
	public void setTypeCodeNodeRepresentation(String typeCodeNodeRepresentation) {
		this.typeCodeNodeRepresentation = typeCodeNodeRepresentation;
	}
	public String getTypeCodeCodingScheme() {
		return typeCodeCodingScheme;
	}
	public void setTypeCodeCodingScheme(String typeCodeCodingScheme) {
		this.typeCodeCodingScheme = typeCodeCodingScheme;
	}
	public String getContentTypeCodeDisplayName() {
		return contentTypeCodeDisplayName;
	}
	public void setContentTypeCodeDisplayName(String contentTypeCodeDisplayName) {
		this.contentTypeCodeDisplayName = contentTypeCodeDisplayName;
	}
	public String getContentTypeCodeNodeRepresentation() {
		return contentTypeCodeNodeRepresentation;
	}
	public void setContentTypeCodeNodeRepresentation(
			String contentTypeCodeNodeRepresentation) {
		this.contentTypeCodeNodeRepresentation = contentTypeCodeNodeRepresentation;
	}
	public String getContentTypeCodeCodingScheme() {
		return contentTypeCodeCodingScheme;
	}
	public void setContentTypeCodeCodingScheme(String contentTypeCodeCodingScheme) {
		this.contentTypeCodeCodingScheme = contentTypeCodeCodingScheme;
	}
	public String getFormatCodeDisplayName() {
		return formatCodeDisplayName;
	}
	public void setFormatCodeDisplayName(String formatCodeDisplayName) {
		this.formatCodeDisplayName = formatCodeDisplayName;
	}
	public String getFormatCodeNodeRepresentation() {
		return formatCodeNodeRepresentation;
	}
	public void setFormatCodeNodeRepresentation(String formatCodeNodeRepresentation) {
		this.formatCodeNodeRepresentation = formatCodeNodeRepresentation;
	}
	public String getFormatCodeCodingScheme() {
		return formatCodeCodingScheme;
	}
	public void setFormatCodeCodingScheme(String formatCodeCodingScheme) {
		this.formatCodeCodingScheme = formatCodeCodingScheme;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getDocUniqueId() {
		return docUniqueId;
	}
	public void setDocUniqueId(String docUniqueId) {
		this.docUniqueId = docUniqueId;
	}
	public String getConfidentialityCodeDisplayName() {
		return confidentialityCodeDisplayName;
	}
	public void setConfidentialityCodeDisplayName(
			String confidentialityCodeDisplayName) {
		this.confidentialityCodeDisplayName = confidentialityCodeDisplayName;
	}
	public String getConfidentialityCodeNodeRepresentation() {
		return confidentialityCodeNodeRepresentation;
	}
	public void setConfidentialityCodeNodeRepresentation(
			String confidentialityCodeNodeRepresentation) {
		this.confidentialityCodeNodeRepresentation = confidentialityCodeNodeRepresentation;
	}
	public String getConfidentialityCodeCodingScheme() {
		return confidentialityCodeCodingScheme;
	}
	public void setConfidentialityCodeCodingScheme(
			String confidentialityCodeCodingScheme) {
		this.confidentialityCodeCodingScheme = confidentialityCodeCodingScheme;
	}
	public String getHealthcareFacilityCodeDisplayName() {
		return healthcareFacilityCodeDisplayName;
	}
	public void setHealthcareFacilityCodeDisplayName(
			String healthcareFacilityCodeDisplayName) {
		this.healthcareFacilityCodeDisplayName = healthcareFacilityCodeDisplayName;
	}
	public String getHealthcareFacilityCodeNodeRepresentation() {
		return healthcareFacilityCodeNodeRepresentation;
	}
	public void setHealthcareFacilityCodeNodeRepresentation(
			String healthcareFacilityCodeNodeRepresentation) {
		this.healthcareFacilityCodeNodeRepresentation = healthcareFacilityCodeNodeRepresentation;
	}
	public String getHealthcareFacilityCodeCodingScheme() {
		return healthcareFacilityCodeCodingScheme;
	}
	public void setHealthcareFacilityCodeCodingScheme(
			String healthcareFacilityCodeCodingScheme) {
		this.healthcareFacilityCodeCodingScheme = healthcareFacilityCodeCodingScheme;
	}
	public String getPracticeCodeDisplayName() {
		return practiceCodeDisplayName;
	}
	public void setPracticeCodeDisplayName(String practiceCodeDisplayName) {
		this.practiceCodeDisplayName = practiceCodeDisplayName;
	}
	public String getPracticeCodeNodeRepresentation() {
		return practiceCodeNodeRepresentation;
	}
	public void setPracticeCodeNodeRepresentation(
			String practiceCodeNodeRepresentation) {
		this.practiceCodeNodeRepresentation = practiceCodeNodeRepresentation;
	}
	public String getPracticeCodeCodingScheme() {
		return practiceCodeCodingScheme;
	}
	public void setPracticeCodeCodingScheme(String practiceCodeCodingScheme) {
		this.practiceCodeCodingScheme = practiceCodeCodingScheme;
	}
	   
	public String getEncodingChars() {
		return encodingChars;
	}
	
	public void setEncodingChars(String EncodingChars) {
		encodingChars = EncodingChars;
	}
	
	public String getSendingFacility() {
		return sendingFacility;
	}
	
	public void setSendingFacility(String sendingFac) {
		sendingFacility = sendingFac;
	}
	
	public String getSendingApplication() {
		return sendingApplication;
	}
	
	public void setSendingApplication(String sendingApp) {
		sendingApplication = sendingApp;
	}
	
	public String getReceivingFacility() {
		return receivingFacility;
	}
	
	public void setReceivingFacility(String receivingFac) {
		receivingFacility = receivingFac;
	}
	
	public String getReceivingApplication() {
		return receivingApplication;
	}
	
	public void setReceivingApplication(String receivingApp) {
		receivingApplication = receivingApp;
	}	

	public String getHl7Version() {
		return hl7Version;
	}
	
	public void setHl7Version(String version) {
		hl7Version = version;
	}
	
	public String getDocumentTitle() {
		return docTitle;
	}
	public void setDocumentTitle(String title) {
		this.docTitle = title;
	}	
	
	public EventCodeList getEventCodes() {
		return eventCodes;
	}

	/* 
	 * Overrides the method to print the content of the bean
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("===========MetaData=================" + "\n");
		strBuff.append("docUniqueId:" + docUniqueId + "\n");
		strBuff.append("mimeType:" + mimeType + "\n");
		strBuff.append("creationTime:" + creationTime + "\n");
		strBuff.append("languageCode:" + languageCode + "\n");
		strBuff.append("serviceStartTime:" + serviceStartTime + "\n");
		strBuff.append("serviceEndTime:" + serviceEndTime + "\n");
		strBuff.append("sourcePatientId:" + sourcePatientId + "\n");
		strBuff.append("sourcePatientOId:" + sourcePatientOId + "\n");
		strBuff.append("sourcePatientName:" + sourcePatientName + "\n");
		strBuff.append("sourcePatientBirthTime:" + sourcePatientBirthTime + "\n");
		strBuff.append("sourcePatientGender:" + sourcePatientGender + "\n");
		strBuff.append("sourcePatientAddress:" + sourcePatientAddress + "\n");
		strBuff.append("authorPerson:" + authorPerson + "\n");
		strBuff.append("authorInstitution:" + authorInstitution + "\n");
		strBuff.append("providerAssignedPersonName:" + providerAssignedPersonName + "\n");
		strBuff.append("authorRole:" + authorRole + "\n");
		strBuff.append("authorSpecialty:" + authorSpecialty + "\n");
		strBuff.append("classCodeDisplayName:" + classCodeDisplayName + "\n");
		strBuff.append("classCodeNodeRepresentation:" + classCodeNodeRepresentation + "\n");
		strBuff.append("classCodeCodingScheme:" + classCodeCodingScheme + "\n");
		strBuff.append("typeCodeDisplayName:" + typeCodeDisplayName + "\n");
		strBuff.append("typeCodeNodeRepresentation:" + typeCodeNodeRepresentation + "\n");
		strBuff.append("typeCodeCodingScheme:" + typeCodeCodingScheme + "\n");
		strBuff.append("contentTypeCodeDisplayName:" + contentTypeCodeDisplayName + "\n");
		strBuff.append("contentTypeCodeNodeRepresentation:" + contentTypeCodeNodeRepresentation + "\n");
		strBuff.append("contentTypeCodeCodingScheme:" + contentTypeCodeCodingScheme + "\n");
		strBuff.append("formatCodeDisplayName:" + formatCodeDisplayName + "\n");
		strBuff.append("formatCodeNodeRepresentation:" + formatCodeNodeRepresentation + "\n");
		strBuff.append("formatCodeCodingScheme:" + formatCodeCodingScheme + "\n");
		strBuff.append("uri:" + uri + "\n");
		strBuff.append("sourceId:" + sourceId + "\n");
		//Confidentiality codes 
		strBuff.append("confidentialityCodeDisplayName:" + confidentialityCodeDisplayName + "\n");
		strBuff.append("confidentialityCodeNodeRepresentation:" + confidentialityCodeNodeRepresentation + "\n");
		strBuff.append("confidentialityCodeCodingScheme:" + confidentialityCodeCodingScheme + "\n");
		// Healthcare Facility Type codes 
		strBuff.append("healthcareFacilityCodeDisplayName:" + healthcareFacilityCodeDisplayName + "\n");
		strBuff.append("healthcareFacilityCodeNodeRepresentation:" + healthcareFacilityCodeNodeRepresentation + "\n");
		strBuff.append("healthcareFacilityCodeCodingScheme:" + healthcareFacilityCodeCodingScheme + "\n");
		// Practice Setting codes 
		strBuff.append("practiceCodeDisplayName:" + practiceCodeDisplayName + "\n");
		strBuff.append("practiceCodeNodeRepresentation:" + practiceCodeNodeRepresentation + "\n");
		strBuff.append("practiceCodeCodingScheme:" + practiceCodeCodingScheme + "\n");
		//Event Code List
		strBuff.append(eventCodes.toString());
		
		strBuff.append("Document Title:" + docTitle + "\n");
		strBuff.append("===========End MetaData=================" + "\n");
		
		return strBuff.toString();
	}
	
	
	/**
	 * Implementation for Visitor pattern
	 * Visitor updates the PID after performing a 
	 * PIX query and changes the value in the required field  
	 * @param beanvisitor
	 * 
	 */
	public void accept(BeanVisitor beanvisitor){
		beanvisitor.UpdateBean(this);
	}
	
}
