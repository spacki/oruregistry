package org.openehealth.ipf.ws.oru.cda2xds.beans;

/**
 * Bean for holding information for a given Event Code.  An Event Code may be part of an eventCodeList as 
 * is the case in the XDS Document Metadata.
 * @author Rodolfo Chavez
 *
 */
public class EventCodeBean {
	private String displayName;
	private String nodeRepresentation;
	private String codingScheme;
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getNodeRepresentation() {
		return nodeRepresentation;
	}
	public void setNodeRepresentation(String nodeRepresentation) {
		this.nodeRepresentation = nodeRepresentation;
	}
	public String getCodingScheme() {
		return codingScheme;
	}
	public void setCodingScheme(String codingScheme) {
		this.codingScheme = codingScheme;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		builder.append("eventCodeDislayName: " + this.displayName + ", ");
		builder.append("eventCodeNodeRepresentation: " + this.nodeRepresentation + ", ");
		builder.append("eventCodeCodingScheme: " + this.codingScheme + " ");
		
		return builder.toString();
	}
}
