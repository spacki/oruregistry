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
 
package org.openehealth.ipf.ws.oru.cda2xds.oru

import java.text.DateFormat
import java.text.SimpleDateFormat
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.openehealth.ipf.ws.oru.cda2xds.commons.MessageStrategyAbs
import org.openehealth.ipf.ws.oru.cda2xds.beans.MetadataBean
import org.openehealth.ipf.ws.oru.cda2xds.pix.PIXConfiguration
import org.openehealth.ipf.ws.oru.cda2xds.exception.CcgRuntimeException
import org.openehealth.ipf.ws.oru.cda2xds.mdmtoxds.PropertyBuilder

/**
 * @author Swapnil Sharma
 * Implementation for CDA message handling in ORU message route
 * 
 * History of Changes:
 * 01-26-2010 Anu Jandhyala
 * Added fields that are needed to perform PIX query
 * 
 * History of Changes:
 * 1/31/2010: Sanjiv Venugopal
 * Added DocumentUniqueId creation from OID + Current time for CDA document types.
 * 
 * 2/25/2010 Anu Jandhyala
 * Setter for Document title for CDA document
 * 
 */
public class CdaMessageStrategy extends MessageStrategyAbs {
	def clinicalDocument
	def String strMessage
	
	private final static Log log = LogFactory.getLog(CdaMessageStrategy.class)
	
	/* 
	 * Constructor for the class accepts CDA message as String
	 */	
	public CdaMessageStrategy(String strMsg) {
		
		strMessage = strMsg
	}
	
	/* 
	 * Implementation of Abstract method
	 * Returns Document content as String
	 */		
	public String getDocContent(){
		return this.strMessage
	}
	
	
	/* 
	 * Implementation of Abstract method.
	 * Populates MetadataBean with values extracted from CDA document
	 * @see MessageStrategyAbs#buildMetaData()
	 */
	protected MetadataBean extractData(){
		log.info("Received Payload in extractData")
		
		MetadataBean mBean = new MetadataBean();
		clinicalDocument = new XmlSlurper().parseText(strMessage)
		try{
			mBean.setDocumentTitle(clinicalDocument.title?.text());
			
			//AuthorInstitution						
			mBean.setAuthorInstitution(clinicalDocument.author?.assignedAuthor?.representedOrganization?.name.text())
			//AuthorPerson
			mBean.setAuthorPerson(clinicalDocument.author?.assignedAuthor?.id.@extension.text() +"^"+
								  clinicalDocument.author?.assignedAuthor?.assignedPerson?.name?.family.text()+"^"+
								  clinicalDocument.author?.assignedAuthor?.assignedPerson?.name?.given[0].text()	+"^"+
								  clinicalDocument.author?.assignedAuthor?.assignedPerson?.name?.given[1].text()	+"^"+
								  clinicalDocument.author?.assignedAuthor?.assignedPerson?.name?.suffix.text()	+"^"+
								  clinicalDocument.author?.assignedAuthor?.assignedPerson?.name?.prefix.text()	+"^"+
								  "^^^&" + clinicalDocument.author?.id?.@root.text()+"&ISO"
								  )		
			//Author Role					  
			mBean.setAuthorRole(PropertyBuilder.getAuthorRole())
			//Author Speciality
			mBean.setAuthorSpecialty(PropertyBuilder.getAuthorSpeciality())
			
			//Check if the document type is Labor and Delivery Summary
			def ldsFormatCodes = 
				clinicalDocument.templateId.findAll {it.@root.text().equals(PropertyBuilder.getLdsTemplateId())}
			//Set the flag if message type is Labor and Delivery Summary
			boolean isLDS = ldsFormatCodes.size()>0
			
			log.debug("LDS Template is:"+isLDS)
			
			//Class Codes
			//clinicalDocument.code.@displayName.text()
			if(isLDS){
				mBean.setClassCodeDisplayName(PropertyBuilder.getLds_ClassCodeDisplayName())
				mBean.setClassCodeNodeRepresentation(PropertyBuilder.getLds_ClassCodeNodeRepresentation())
				mBean.setClassCodeCodingScheme(PropertyBuilder.getLds_ClassCodeCodingScheme())
			}
			
			//Content Type Codes
			log.debug("ContentTypeCode present in inbound CDA:" + clinicalDocument.code.@displayName.text())
			if(isLDS){
				mBean.setContentTypeCodeDisplayName(PropertyBuilder.getLds_ContentTypeCodeDisplayName())
				mBean.setContentTypeCodeNodeRepresentation(PropertyBuilder.getLds_ContentTypeCodeNodeRepresentation())
				mBean.setContentTypeCodeCodingScheme(PropertyBuilder.getLds_ContentTypeCodeCodingScheme())
			}
			
			//Confidentiality codes are common
			mBean.setConfidentialityCodeDisplayName(PropertyBuilder.confidentialityCodeDisplayName)
			mBean.setConfidentialityCodeNodeRepresentation(PropertyBuilder.confidentialityCodeNodeRepresentation)
			mBean.setConfidentialityCodeCodingScheme(PropertyBuilder.confidentialityCodeCodingScheme)
			
			//Creation Time - TODO : Fix conversion to UTC from CDA V3 datetime
			String creationTime = clinicalDocument.effectiveTime.@value.text();
			if(creationTime == null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				java.util.Date date = new java.util.Date();
				//System.out.println("Current Date Time : " + dateFormat.format(date));
				creationTime = dateFormat.format(date);
			}
			//trimming the time zone. ex. 20091001140559-0700 -> 20091001140559
			//this should be made configurable in future
			if(creationTime.indexOf("-") >= 0)
				creationTime = creationTime.substring(0,creationTime.indexOf("-"))
			//Creation time
			mBean.setCreationTime(creationTime)
			
			//Format Codes
			if(isLDS){
				//urn:ihe:pcc:lds:2009
				mBean.setFormatCodeDisplayName(PropertyBuilder.getLds_FormatCodeDisplayName())
				mBean.setFormatCodeNodeRepresentation(PropertyBuilder.getLds_FormatCodeNodeRepresentation())
				mBean.setFormatCodeCodingScheme(PropertyBuilder.getLds_FormatCodeCodingScheme())
			}
			
			// Healthcare Facility Type codes are common
			mBean.setHealthcareFacilityCodeDisplayName(PropertyBuilder.healthcareFacilityCodeDisplayName)
			mBean.setHealthcareFacilityCodeNodeRepresentation(PropertyBuilder.healthcareFacilityCodeNodeRepresentation)
			mBean.setHealthcareFacilityCodeCodingScheme(PropertyBuilder.healthcareFacilityCodeCodingScheme)
			
			//Language code
			mBean.setLanguageCode(clinicalDocument.languageCode.@code.text())
			
			//MimeType
			mBean.setMimeType("text/xml")
			
			//Patient Id
			mBean.setSourcePatientId(clinicalDocument.recordTarget.patientRole.id.@extension.text())
			mBean.setSourcePatientOId(PIXConfiguration.localAssigningAuthority)
			//clinicalDocument.recordTarget.patientRole.id.@root.text()
			mBean.setSourcePatientName(clinicalDocument.recordTarget?.patientRole?.patient?.name?.family.text() + "^" +
									clinicalDocument.recordTarget?.patientRole?.patient?.name?.given.text())
			mBean.setSourcePatientBirthTime(clinicalDocument.recordTarget?.patientRole?.patient?.birthTime?.@value.text())		
			mBean.setSourcePatientGender(clinicalDocument.recordTarget?.patientRole?.patient?.administrativeGenderCode?.@code.text())
			mBean.setSourcePatientAddress(
									clinicalDocument.recordTarget?.patientRole?.addr?.streetAddressLine?.text() +"^"+
									clinicalDocument.recordTarget?.patientRole?.addr?.city?.text() +"^"+
									clinicalDocument.recordTarget?.patientRole?.addr?.state?.text() +"^"+
									clinicalDocument.recordTarget?.patientRole?.addr?.postalCode?.text())
			
			// Practice Setting codes are common
			mBean.setPracticeCodeDisplayName(PropertyBuilder.practiceCodeDisplayName)
			mBean.setPracticeCodeNodeRepresentation(PropertyBuilder.practiceCodeNodeRepresentation)
			mBean.setPracticeCodeCodingScheme(PropertyBuilder.practiceCodeCodingScheme)
			
			//serviceStartTime
			String serviceStartTime = clinicalDocument.documentationOf.serviceEvent.effectiveTime.low.@value.text();
			if((serviceStartTime != null)&& (serviceStartTime.equalsIgnoreCase('UNK')))
				serviceStartTime = creationTime;
			if(serviceStartTime.indexOf("-") >= 0)
				serviceStartTime = serviceStartTime.substring(0,serviceStartTime.indexOf("-"))
			log.debug("serviceStartTime:"+serviceStartTime)
			
			//Set service start time
			//If all other conditions fail, set current time
			if((null==serviceStartTime)|| ("".equals(serviceStartTime))){
				serviceStartTime = creationTime;
			}
			mBean.setServiceStartTime(serviceStartTime)
			
			//serviceStopTime
			String serviceEndTime = clinicalDocument.documentationOf.serviceEvent.effectiveTime.high.@value.text();
			if((serviceEndTime != null)&& (serviceEndTime.equalsIgnoreCase('UNK')))
				serviceEndTime = creationTime;
			if(serviceEndTime.indexOf("-") >= 0)
				serviceEndTime = serviceEndTime.substring(0,serviceEndTime.indexOf("-"))
			//In case ServiceEndTime is missing, set it to ServiceStartTime
			if ("".equals(serviceEndTime))
				serviceEndTime = serviceStartTime
			log.debug("ServiceEndTime:"+serviceEndTime)	
			//Set service end time	
			mBean.setServiceEndTime(serviceEndTime)
			
			//Type code
			//clinicalDocument.code.@displayName.text()
			if (isLDS) {
				mBean.setTypeCodeDisplayName(PropertyBuilder.getLds_TypeCodeDisplayName())
				mBean.setTypeCodeNodeRepresentation(PropertyBuilder.getLds_TypeCodeNodeRepresentation())
				mBean.setTypeCodeCodingScheme(PropertyBuilder.getLds_TypeCodeCodingScheme())
			}
			
			mBean.setProviderAssignedPersonName(clinicalDocument.documentationOf?.serviceEvent[0]?.performer[0]?.assignedEntity?.assignedPerson?.name.text())
			mBean.setUri(PropertyBuilder.uri)
			mBean.setSourceId(PropertyBuilder.sourceID)
			
			// the following data is used to perform a PIX query
			mBean.setEncodingChars(PIXConfiguration.encodingChars)
			mBean.setSendingFacility(PIXConfiguration.sendingFacility)
			mBean.setSendingApplication(PIXConfiguration.sendingApplication)
			mBean.setReceivingFacility(PIXConfiguration.receivingFacility)
			mBean.setReceivingApplication(PIXConfiguration.receivingApplication)
			mBean.setHl7Version(PIXConfiguration.hl7Version)
			
			// Document Unique ID
			String currTime = (String)System.currentTimeMillis();
			String uniqueId = mBean.getSourcePatientOId()+ ".0." + currTime
			mBean.setDocUniqueId(uniqueId)
			
			
		}catch(Exception e){
			log.error(e)
			throw new CcgRuntimeException(e)
		}
		//Output contenets of Metadata bean
		log.debug(mBean.toString())
		
		return mBean;
	}
	
}
