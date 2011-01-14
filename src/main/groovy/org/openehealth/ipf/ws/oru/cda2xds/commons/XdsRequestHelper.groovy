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

import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.activation.DataHandler
import javax.xml.bind.JAXBElement
import javax.xml.namespace.QName
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.ProvideAndRegisterDocumentSetRequestType
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.lcm.SubmitObjectsRequest
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rim.*
import org.openehealth.ipf.ws.oru.cda2xds.beans.MetadataBean
import org.openehealth.ipf.ws.oru.cda2xds.mdmtoxds.PropertyBuilder

/**
 * Helper class for creating ebXML request
 * @author Sanjiv Venugopal
 * @author Swapnil Sharma
 * 
 * History of Changes:
 * 1/31/2010: Sanjiv Venugopal
 * Updated DocumentUniqueId creation from Metadata DocUniqueId field
 *
 * 02/25/2010 Anu Jandhyala
 * Add Document title to extrinsicObjectType
 * 
 */
public class XdsRequestHelper{
	
	private ObjectFactory rimFactory = new ObjectFactory();
	private static Log log = LogFactory.getLog(XdsRequestHelper.class)
	private static String RIM_URN = "urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0"
	//Swapnil - need to check this for other HITSP documents
	private static InternationalStringType strPhysical = Ebrs30Helper.createInternationalString("Physical");
	private static InternationalStringType strDescription =  Ebrs30Helper.createInternationalString("Annual physical");
	private MetadataBean mBean = null;
	
	/*
	 * Constructor for the class accepts MetadataBean
	 */
	public XdsRequestHelper(MetadataBean metaBean){
		this.mBean = metaBean
	}
	
	/*
	 * Creates SubmitObjectsRequest as part of ebXML request
	 */
	public SubmitObjectsRequest createSubmitObjectsRequest(String comment) {
		
		//Creating Extrinsic object
		ExtrinsicObjectType extrinsicObjectType = createExtrinsicObject();
		JAXBElement<IdentifiableType> extrinsicElement = new JAXBElement<IdentifiableType>(
				new QName(RIM_URN, "ExtrinsicObject", "rim"),
						(Class)ExtrinsicObjectType.class,
						extrinsicObjectType); 
		
		log.debug("XdsRequestHelper>ExtrinsicObjectType:" + extrinsicObjectType)
		
		//Creating Submission Set
		RegistryPackageType registryPackageType = createSubmissionSet();
		JAXBElement<IdentifiableType> submissionsetElement = new JAXBElement<IdentifiableType>(
				new QName(RIM_URN, "RegistryPackage", "rim"),
						(Class)RegistryPackageType.class,
						registryPackageType); 
		
		log.debug("XdsRequestHelper>RegistryPackageType:" + registryPackageType)
		
		//Creating Classification node (Submission Set)
		ClassificationType subsetClassificationType = 
				Ebrs30Helper.createClassification("SubmissionSet01", Vocabulary.SUBMISSION_SET_CLASS_NODE);
		String uuidSubmissionSet = 'urn:uuid:' + UUID.randomUUID()
		subsetClassificationType.setId(uuidSubmissionSet)
		
		log.debug("XdsRequestHelper>ClassificationType:" + subsetClassificationType)
		
		JAXBElement<IdentifiableType> classificationElement = new JAXBElement<IdentifiableType>(
				new QName(RIM_URN, "Classification", "rim"),
						(Class)ClassificationType.class,
						subsetClassificationType); 
		
		log.debug("XdsRequestHelper>classificationElement:" + classificationElement)
		
		//Creating Association type (Submission Set 'hasmember' Document)
		AssociationType1 associationType = createAssociation("SubmissionSet01", "Document01");
		String uuidAssociation = 'urn:uuid:' + UUID.randomUUID()
		associationType.setId(uuidAssociation)
		
		log.debug("XdsRequestHelper>associationType:" + associationType)
		
		JAXBElement<IdentifiableType> associationElement = new JAXBElement<IdentifiableType>(
				new QName(RIM_URN, "Association", "rim"),
						(Class)AssociationType1.class,
						associationType); 
		
		//Adding the elements to the RegistryObjectList
		RegistryObjectListType registryObjectListType = new RegistryObjectListType();
		
		registryObjectListType.getIdentifiable().add(extrinsicElement);
		registryObjectListType.getIdentifiable().add(submissionsetElement);
		registryObjectListType.getIdentifiable().add(classificationElement);
		registryObjectListType.getIdentifiable().add(associationElement);
		
		SubmitObjectsRequest submitObjectRequest = new SubmitObjectsRequest();
		
		submitObjectRequest.setRegistryObjectList(registryObjectListType);
		submitObjectRequest.setComment(comment);
		
		return submitObjectRequest;
	}
	
	
	private ExtrinsicObjectType createExtrinsicObject(){
		
		ExtrinsicObjectType extrinsicObjectType = rimFactory.createExtrinsicObjectType();
		extrinsicObjectType.setId("Document01");
		extrinsicObjectType.setMimeType(mBean.getMimeType())
		extrinsicObjectType.setObjectType("urn:uuid:7edca82f-054d-47f2-a032-9b2a5b5186c1");
		
		extrinsicObjectType.setName(Ebrs30Helper.createInternationalString(mBean.getDocumentTitle()));
		
		List<SlotType1> slots =new ArrayList<SlotType1>();
		slots = extrinsicObjectType.getSlot();
		
		Ebrs30Helper.addSlot(slots,"creationTime",mBean.getCreationTime());
		Ebrs30Helper.addSlot(slots,"languageCode",mBean.getLanguageCode());
		Ebrs30Helper.addSlot(slots,"serviceStartTime",mBean.getServiceStartTime());
		//Ebrs30Helper.addSlot(slots,"repositoryUniqueId",PropertyBuilder.repositoryUniqueId);
		Ebrs30Helper.addSlot(slots,"serviceStopTime",mBean.getServiceEndTime());
		//Ebrs30Helper.addSlot(slots,"URI",mBean.getUri());
		Ebrs30Helper.addSlot(slots,"sourcePatientId",
				mBean.getSourcePatientId()+"^^^&"+mBean.getSourcePatientOId()+"&ISO");
		// patient info details
		String[] strPatientInfo = new String[5];
		strPatientInfo[0] = "PID-3|" + 
			mBean.getSourcePatientId()+ "^^^&" + mBean.getSourcePatientOId()+"&ISO";
		strPatientInfo[1] = "PID-5|" + 
			mBean.getSourcePatientName();
		strPatientInfo[2] = "PID-7|"+
			mBean.getSourcePatientBirthTime();
		strPatientInfo[3] = "PID-8|"+
			mBean.getSourcePatientGender();
		strPatientInfo[4] = "PID-11|"+
			mBean.getSourcePatientAddress();
		Ebrs30Helper.addSlot(slots,"sourcePatientInfo",strPatientInfo);
		
		//	Creating classification (Author)
		ClassificationType authorClassification = 
			Ebrs30Helper.createClassificationType("urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d");
		authorClassification.setClassifiedObject("Document01");
		String uuidAuthor = 'urn:uuid:' + UUID.randomUUID()
		authorClassification.setId(uuidAuthor)
		addClassificationSchema(authorClassification);
		extrinsicObjectType.getClassification().add(authorClassification);
		
		//	Creating classification (Class code)
		ClassificationType classcodeClassification = 
			Ebrs30Helper.createClassificationType("urn:uuid:41a5887f-8865-4c09-adf7-e362475b143a");
		classcodeClassification.setClassifiedObject("Document01");
		String uuidClassCode = 'urn:uuid:' + UUID.randomUUID()
		classcodeClassification.setId(uuidClassCode)
		addClassificationSchema(classcodeClassification);
		extrinsicObjectType.getClassification().add(classcodeClassification);
		
		//	Creating classification (Confidentiality code)
		ClassificationType confcodeClassification = 
			Ebrs30Helper.createClassificationType("urn:uuid:f4f85eac-e6cb-4883-b524-f2705394840f");
		confcodeClassification.setClassifiedObject("Document01");
		String uuidConfCode = 'urn:uuid:' + UUID.randomUUID()
		confcodeClassification.setId(uuidConfCode)
		addClassificationSchema(confcodeClassification);
		extrinsicObjectType.getClassification().add(confcodeClassification);
		
		//Creating Classification for eventCodeList (Optional per IHE spec)
		log.debug("Event code starts ")
		log.debug(PropertyBuilder.getEventCodeDisplayName())
		log.debug(PropertyBuilder.getEventCodeNodeRepresentation())
		log.debug(PropertyBuilder.getEventCodeCodingScheme())
				
		ClassificationType eventCodeClassification = 
			Ebrs30Helper.createClassificationType("urn:uuid:2c6b8cb7-8b2a-4051-b291-b1ae6a575ef4");
		eventCodeClassification.setClassifiedObject("Document01");
		eventCodeClassification.setNodeRepresentation(PropertyBuilder.getEventCodeNodeRepresentation());
		String uuidEventCode = 'urn:uuid:' + UUID.randomUUID()
		eventCodeClassification.setId(uuidEventCode)
		addClassificationSchema(eventCodeClassification);
		extrinsicObjectType.getClassification().add(eventCodeClassification);
		
		//	Creating classification (Format code)
		ClassificationType formatcodeClassification = 
			Ebrs30Helper.createClassificationType("urn:uuid:a09d5840-386c-46f2-b5ad-9c3699a4309d");
		formatcodeClassification.setClassifiedObject("Document01");
		String uuidFormatCode = 'urn:uuid:' + UUID.randomUUID()
		formatcodeClassification.setId(uuidFormatCode)
		addClassificationSchema(formatcodeClassification);
		extrinsicObjectType.getClassification().add(formatcodeClassification);
		
		//	Creating classification (Healthcare Facility Type code)
		ClassificationType facilitycodeClassification = 
			Ebrs30Helper.createClassificationType("urn:uuid:f33fb8ac-18af-42cc-ae0e-ed0b0bdb91e1");
		facilitycodeClassification.setClassifiedObject("Document01");
		String uuidFacilityTypeCode = 'urn:uuid:' + UUID.randomUUID()
		facilitycodeClassification.setId(uuidFacilityTypeCode)
		addClassificationSchema(facilitycodeClassification);
		extrinsicObjectType.getClassification().add(facilitycodeClassification);
		
		//	Creating classification (Practice Setting code)
		ClassificationType practiceSettingClassification = 
			Ebrs30Helper.createClassificationType("urn:uuid:cccf5598-8b07-4b77-a05e-ae952c785ead");
		practiceSettingClassification.setClassifiedObject("Document01");
		String uuidPracticeSettingCode = 'urn:uuid:' + UUID.randomUUID()
		practiceSettingClassification.setId(uuidPracticeSettingCode)
		addClassificationSchema(practiceSettingClassification);
		extrinsicObjectType.getClassification().add(practiceSettingClassification);
		
		//	Creating classification (Type code)
		ClassificationType typecodeClassification = 
			Ebrs30Helper.createClassificationType("urn:uuid:f0306f51-975f-434e-a61c-c59651d33983");
		typecodeClassification.setClassifiedObject("Document01");
		String uuidTypeCode = 'urn:uuid:' + UUID.randomUUID()
		typecodeClassification.setId(uuidTypeCode)
		addClassificationSchema(typecodeClassification);
		extrinsicObjectType.getClassification().add(typecodeClassification);
		
		//	Creating External Identifiers (XDSDocumentEntry.patientId)
		String patIdExtidentifier = 
			mBean.getSourcePatientId()+ "^^^&"+
			mBean.getSourcePatientOId()+"&ISO";
		ExternalIdentifierType externalidPatientId = 
			Ebrs30Helper.createExternalIdentifier(Vocabulary.DOC_ENTRY_PATIENT_ID_EXTERNAL_ID ,patIdExtidentifier);
		externalidPatientId.setName(Ebrs30Helper.createInternationalString(Vocabulary.DOC_ENTRY_LOCALIZED_STRING_PATIENT_ID));
		
		String uuidPatientId = 'urn:uuid:' + UUID.randomUUID() 
		externalidPatientId.setId(uuidPatientId)
		externalidPatientId.setRegistryObject("Document01")
		extrinsicObjectType.getExternalIdentifier().add(externalidPatientId);
		
		//Swapnil - Need to check the incoming field
		//String currTime = (String)System.currentTimeMillis();
		//String uniqueId = mBean.getSourcePatientOId()+ ".0." + currTime
//Check			//MdmToXdsTranslator.msg.TXA[12][1]?.value;
		
		//	Creating External Identifiers (XDSDocumentEntry.uniqueId)
		String uniqueId = mBean.getDocUniqueId()
		ExternalIdentifierType externalidUniqueId = 
			Ebrs30Helper.createExternalIdentifier(Vocabulary.DOC_ENTRY_UNIQUE_ID_EXTERNAL_ID ,uniqueId);
		externalidUniqueId.setName(Ebrs30Helper.createInternationalString(Vocabulary.DOC_ENTRY_LOCALIZED_STRING_UNIQUE_ID));
				
		String uuidUniqueId = 'urn:uuid:' + UUID.randomUUID() 
		externalidUniqueId.setId(uuidUniqueId)
		externalidUniqueId.setRegistryObject("Document01")
		extrinsicObjectType.getExternalIdentifier().add(externalidUniqueId);
		
		return extrinsicObjectType;
			
	}
	
	private AssociationType1 createAssociation(String sourceObject, String targetObject) {
		AssociationType1 associationType = Ebrs30Helper.createAssociation(sourceObject, targetObject);
		
		List<SlotType1> slots =new ArrayList<SlotType1>();
		slots = associationType.getSlot();
		Ebrs30Helper.addSlot(slots,"SubmissionSetStatus","Original");
				
		return associationType;
	}	
	

	private RegistryPackageType createSubmissionSet(){
		
		RegistryPackageType registryPackageType = rimFactory.createRegistryPackageType();
		registryPackageType.setId("SubmissionSet01");
		registryPackageType.setName(strPhysical);
		//registryPackageType.setDescription(strDescription);
		
		// Submission Time - current datetime
		List<SlotType1> slots =new ArrayList<SlotType1>();
		slots = registryPackageType.getSlot();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	    Date date = new Date();
	    Ebrs30Helper.addSlot(slots,"submissionTime",dateFormat.format(date));
	    
		//	Creating classification (Author)
		ClassificationType authorClassification = 
			Ebrs30Helper.createClassificationType("urn:uuid:a7058bb9-b4e4-4307-ba5b-e3f0ab85e12d");
		authorClassification.setClassifiedObject("SubmissionSet01");
		String uuidAuthor = 'urn:uuid:' + UUID.randomUUID()
		authorClassification.setId(uuidAuthor)
		addClassificationSchema(authorClassification);
		registryPackageType.getClassification().add(authorClassification);
		
		//	Creating classification (Content Type code)
		ClassificationType contentTypeCodeClassification = 
			Ebrs30Helper.createClassificationType("urn:uuid:aa543740-bdda-424e-8c96-df4873be8500");
		contentTypeCodeClassification.setClassifiedObject("SubmissionSet01");
		String uuidTypeCode = 'urn:uuid:' + UUID.randomUUID()
		contentTypeCodeClassification.setId(uuidTypeCode)
		addClassificationSchema(contentTypeCodeClassification);
		registryPackageType.getClassification().add(contentTypeCodeClassification);
		
		//String currTime = (String)System.currentTimeMillis();
		//Swapnil - Need to check the value
		//String uniqueId = mBean.getSourcePatientOId()+ ".1.2." + currTime
//Check		//MdmToXdsTranslator.msg.TXA[12][1]?.value + currTime;
		String uniqueId = mBean.getDocUniqueId() + ".1"
		
		
		//	Creating External Identifiers (XDSSubmissionSet.uniqueId)
		ExternalIdentifierType externalidUniqueId = 
			Ebrs30Helper.createExternalIdentifier(Vocabulary.SUBMISSION_SET_UNIQUE_ID_EXTERNAL_ID ,uniqueId);
		externalidUniqueId.setName(Ebrs30Helper.createInternationalString(Vocabulary.SUBMISSION_SET_LOCALIZED_STRING_UNIQUE_ID));
		String uuidUniqueId = 'urn:uuid:' + UUID.randomUUID()
		externalidUniqueId.setId(uuidUniqueId)
		externalidUniqueId.setRegistryObject("SubmissionSet01")
		registryPackageType.getExternalIdentifier().add(externalidUniqueId);
		
		//	Creating External Identifiers (XDSSubmissionSet.sourceId)
		ExternalIdentifierType externalidSourceId = 
			Ebrs30Helper.createExternalIdentifier(Vocabulary.SUBMISSION_SET_SOURCE_ID_EXTERNAL_ID ,
												  mBean.getSourceId());
		externalidSourceId.setName(Ebrs30Helper.createInternationalString(Vocabulary.SUBMISSION_SET_LOCALIZED_STRING_SOURCE_ID));
		String uuidSourceId = 'urn:uuid:' + UUID.randomUUID()
		externalidSourceId.setId(uuidSourceId)
		externalidSourceId.setRegistryObject("SubmissionSet01")
		registryPackageType.getExternalIdentifier().add(externalidSourceId);
		
		//	Creating External Identifiers (XDSSubmissionSet.patientId)
		String patIdExtidentifier = 
			mBean.getSourcePatientId()+ "^^^&"+
			mBean.getSourcePatientOId()+"&ISO";
				
		ExternalIdentifierType externalidPatientId = 
			Ebrs30Helper.createExternalIdentifier(Vocabulary.SUBMISSION_SET_PATIENT_ID_EXTERNAL_ID ,patIdExtidentifier);
		externalidPatientId.setName(Ebrs30Helper.createInternationalString(Vocabulary.SUBMISSION_SET_LOCALIZED_STRING_PATIENT_ID));
		String uuidPatientId = 'urn:uuid:' + UUID.randomUUID()
		externalidPatientId.setId(uuidPatientId)
		externalidPatientId.setRegistryObject("SubmissionSet01")
		registryPackageType.getExternalIdentifier().add(externalidPatientId);
		
		return registryPackageType;
	}	
	

	private ClassificationType addClassificationSchema(ClassificationType classType){
		// Manage codes
		//1. Author code
		//2. Class code
		//3. Confidentiality code
		//4. Format code
		//5. Healthcare Facility code
		//6. Practice Setting code
		//7. Type code
		switch(classType.getClassificationScheme()){
		//Author code
		case "urn:uuid:a7058bb9-b4e4-4307-ba5b-e3f0ab85e12d":
		case "urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d":
			classType.setNodeRepresentation("");
			List<SlotType1> slots =new ArrayList<SlotType1>();
			slots = classType.getSlot();
			
			Ebrs30Helper.addSlot(slots,"authorPerson",mBean.getAuthorPerson());
			Ebrs30Helper.addSlot(slots,"authorInstitution",mBean.getAuthorInstitution());
			Ebrs30Helper.addSlot(slots,"authorRole",mBean.getAuthorRole());
			Ebrs30Helper.addSlot(slots,"authorSpecialty",mBean.getAuthorSpecialty());
			break;
		// Class code
		case "urn:uuid:41a5887f-8865-4c09-adf7-e362475b143a":
			classType.setNodeRepresentation(mBean.getClassCodeNodeRepresentation());
			InternationalStringType strName = 
				Ebrs30Helper.createInternationalString(mBean.getClassCodeDisplayName());
			classType.setName(strName);
			List<SlotType1> slots =new ArrayList<SlotType1>();
			slots = classType.getSlot();
			Ebrs30Helper.addSlot(slots,"codingScheme",mBean.getClassCodeCodingScheme());
			break;
		// Confidentiality code
		case "urn:uuid:f4f85eac-e6cb-4883-b524-f2705394840f":
			classType.setNodeRepresentation(mBean.getConfidentialityCodeNodeRepresentation());
			InternationalStringType strName = 
				Ebrs30Helper.createInternationalString(mBean.getConfidentialityCodeDisplayName());
			classType.setName(strName);
			List<SlotType1> slots =new ArrayList<SlotType1>();
			slots = classType.getSlot();
			Ebrs30Helper.addSlot(slots,"codingScheme",mBean.getConfidentialityCodeCodingScheme());
			break;
		// Format code
		case "urn:uuid:a09d5840-386c-46f2-b5ad-9c3699a4309d":
			classType.setNodeRepresentation(mBean.getFormatCodeNodeRepresentation());
			InternationalStringType strName = 
				Ebrs30Helper.createInternationalString(mBean.getFormatCodeDisplayName());
			classType.setName(strName);
			List<SlotType1> slots =new ArrayList<SlotType1>();
			slots = classType.getSlot();
			Ebrs30Helper.addSlot(slots,"codingScheme",mBean.getFormatCodeCodingScheme());
			break;
		// Healthcare Facility code
		case "urn:uuid:f33fb8ac-18af-42cc-ae0e-ed0b0bdb91e1":
			classType.setNodeRepresentation(mBean.getHealthcareFacilityCodeNodeRepresentation());
			InternationalStringType strName = 
				Ebrs30Helper.createInternationalString(mBean.getHealthcareFacilityCodeDisplayName());
			classType.setName(strName);
			List<SlotType1> slots =new ArrayList<SlotType1>();
			slots = classType.getSlot();
			Ebrs30Helper.addSlot(slots,"codingScheme",mBean.getHealthcareFacilityCodeCodingScheme());
			break;
		// Practice Setting code
		case "urn:uuid:cccf5598-8b07-4b77-a05e-ae952c785ead":
			classType.setNodeRepresentation(mBean.getPracticeCodeNodeRepresentation());
			InternationalStringType strName = 
				Ebrs30Helper.createInternationalString(mBean.getPracticeCodeDisplayName());
			classType.setName(strName);
			List<SlotType1> slots =new ArrayList<SlotType1>();
			slots = classType.getSlot();
			Ebrs30Helper.addSlot(slots,"codingScheme",mBean.getPracticeCodeCodingScheme());
			break;
		// Type code
		case "urn:uuid:f0306f51-975f-434e-a61c-c59651d33983":
			classType.setNodeRepresentation(mBean.getTypeCodeNodeRepresentation());
			InternationalStringType strName = 
				Ebrs30Helper.createInternationalString(mBean.getTypeCodeDisplayName());
			classType.setName(strName);
			List<SlotType1> slots =new ArrayList<SlotType1>();
			slots = classType.getSlot();
			Ebrs30Helper.addSlot(slots,"codingScheme",mBean.getTypeCodeCodingScheme());
			break;
		// Content Type code
		case "urn:uuid:aa543740-bdda-424e-8c96-df4873be8500":
			classType.setNodeRepresentation(mBean.getContentTypeCodeNodeRepresentation());
			InternationalStringType strName = 
				Ebrs30Helper.createInternationalString(mBean.getContentTypeCodeDisplayName());
			classType.setName(strName);
			List<SlotType1> slots =new ArrayList<SlotType1>();
			slots = classType.getSlot();
			Ebrs30Helper.addSlot(slots,"codingScheme",mBean.getContentTypeCodeCodingScheme());
			break;
			
		//Event code list (Optional field)
		case "urn:uuid:2c6b8cb7-8b2a-4051-b291-b1ae6a575ef4":
			InternationalStringType strName = 
				Ebrs30Helper.createInternationalString(PropertyBuilder.getEventCodeDisplayName());
			classType.setName(strName);
			List<SlotType1> slots =new ArrayList<SlotType1>();
			slots = classType.getSlot();
			Ebrs30Helper.addSlot(slots,"codingScheme",PropertyBuilder.getEventCodeCodingScheme());
			break;				
		}
		
		return classType;
	}	
	
	public ProvideAndRegisterDocumentSetRequestType.Document createDocumentContent(String id, String data) {
		
		ProvideAndRegisterDocumentSetRequestType.Document documentContent = 
			new ProvideAndRegisterDocumentSetRequestType.Document()
		documentContent.id = id
		documentContent.value = new DataHandler(data, mBean.getMimeType())
		documentContent       
	}	
	
}
