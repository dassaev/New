package gov.solar.permit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceResponse", propOrder = {
	    "applicationId",
	    "structuralPermitStatus",
	    "electricPermitStatus"
	})
@XmlRootElement(name="ServiceResponse")
public class ServiceResponse implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required = true)
	private String applicationId;
	@XmlElement(required = true)
	private String structuralPermitStatus;
	@XmlElement(required = true)
	private String electricPermitStatus;
	
	
	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getStructuralPermitStatus() {
		return structuralPermitStatus;
	}

	public void setStructuralPermitStatus(String structuralPermitStatus) {
		this.structuralPermitStatus = structuralPermitStatus;
	}

	public String getElectricPermitStatus() {
		return electricPermitStatus;
	}

	public void setElectricPermitStatus(String electricPermitStatus) {
		this.electricPermitStatus = electricPermitStatus;
	}

}
