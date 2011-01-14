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

import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rim.*;

import java.util.List;

/**
 * @author Sanjiv Venugopal
 *
 */
public class Ebrs30Helper {

	private final static ObjectFactory rimFactory = new ObjectFactory(); 

	public static void addSlot(List<SlotType1> slots, String slotName, String... slotValues) {
		
    //notNull(slots, "slots cannot be null");
    if (slotValues == null || slotValues.length == 0) {
        return;
    }
    
    ValueListType valueList = rimFactory.createValueListType();
    List<String> values = valueList.getValue();
    for (String slotValue : slotValues) {
        if (slotValue != null) {
            values.add(slotValue);
        }
    }

    if (!values.isEmpty()) {
        SlotType1 slot = rimFactory.createSlotType1();
        slot.setName(slotName);
        slot.setValueList(valueList);
        slots.add(slot);
        
    }
	}
	
	   public static InternationalStringType createInternationalString(String strValue) {
	        if (strValue == null) {
	            return null;
	        }
	        	               
	    	InternationalStringType intStrType = rimFactory.createInternationalStringType();
			LocalizedStringType locStrType = rimFactory.createLocalizedStringType();
			locStrType.setValue(strValue);
			locStrType.setCharset(null);
			locStrType.setLang(null);
			intStrType.getLocalizedString().add(locStrType);
			
			return intStrType;
	    }

	   public static ExternalIdentifierType createExternalIdentifier(String identificationScheme, String value) {
		   ExternalIdentifierType externalIdentifer = rimFactory.createExternalIdentifierType();
		   externalIdentifer.setIdentificationScheme(identificationScheme);
		   externalIdentifer.setValue(value);
	        return externalIdentifer;
	    }
	   
	   public static ClassificationType createClassificationType(String scheme) {
	        ClassificationType classification = rimFactory.createClassificationType();
	        classification.setClassificationScheme(scheme);
	        classification.setNodeRepresentation("");
	        return classification;
	    }
	     
	   
	   public static ClassificationType createClassification(String classObject, String classNode) {
	        ClassificationType classification = rimFactory.createClassificationType();
	        classification.setClassifiedObject(classObject);
	        classification.setClassificationNode(classNode);
	        return classification;
	    }

	   public static AssociationType1 createAssociation(String sourceObject, String targetObject) {
		   AssociationType1 associationType = rimFactory.createAssociationType1();
		   associationType.setSourceObject(sourceObject);
		   associationType.setTargetObject(targetObject);
		   //ebRIM 3.0 specification
		   associationType.setAssociationType("urn:oasis:names:tc:ebxml-regrep:AssociationType:HasMember");
		   return associationType;
	    }


}
