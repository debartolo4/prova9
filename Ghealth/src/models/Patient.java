package models;

import java.io.Serializable;

/**
 * @author G5 lab group
 * The Class model: Patient.
 */
public class Patient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The patient id. */
	private String ptID;
	
	/** The patient first name. */
	private String ptFirstName;
	
	/** The patient last name. */
	private String ptLastName;
	
	/** The patient email. */
	private String ptEmail;
	
	/** The patient phone. */
	private String ptPhone;
	
	/** The patient private clinic. */
	private String ptPrivateClinic;
	
	/** The patient personal doctor id. */
	private String ptpersonalDoctorID; 
	//TODO Medical History History Missing
	
	

	/**
	 * This is the default constructor.
	 */
	
	public Patient()
	{
		super();
	}
	
	/**
	 * Instantiates a new patient.
	 *
	 * @param ptID the patient id
	 */
	public Patient(String ptID)
	{
		super();
		this.ptID = ptID;
	}
	
	/**
	 * Instantiates a new patient.
	 *
	 * @param ptID the patient id
	 * @param ptFirstName the patient first name
	 * @param ptLastName the patient last name
	 * @param ptEmail the patient email
	 * @param ptPhone the patient phone
	 * @param ptPrivateClinic the patient private clinic
	 * @param ptpersonalDoctorID the patient personal doctor id
	 */


	public Patient(String ptID, String ptFirstName, String ptLastName, String ptEmail, String ptPhone,
			String ptPrivateClinic, String ptpersonalDoctorID) {
		super();
		this.ptID = ptID;
		this.ptFirstName = ptFirstName;
		this.ptLastName = ptLastName;
		this.ptEmail = ptEmail;
		this.ptPhone = ptPhone;
		this.ptPrivateClinic = ptPrivateClinic;
		this.ptpersonalDoctorID = ptpersonalDoctorID;
	}


	
	/**
	 * Gets the patient id.
	 *
	 * @return the patient id
	 */
	public String getpID() {
		return ptID;
	}
	
	
	/**
	 * Sets the patient id.
	 *
	 * @param ptID the new patient id
	 */
	public void setpID(String ptID) {
		this.ptID = ptID;
	}



	/**
	 * Gets the patient first name.
	 *
	 * @return the patient first name
	 */
	public String getpFirstName() {
		return ptFirstName;
	}



	/**
	 * Sets the patient first name.
	 *
	 * @param pFirstName the new patient first name
	 */
	public void setpFirstName(String pFirstName) {
		this.ptFirstName = pFirstName;
	}



	/**
	 * Gets the patient last name.
	 *
	 * @return the patient last name
	 */
	public String getpLastName() {
		return ptLastName;
	}



	/**
	 * Sets the patient last name.
	 *
	 * @param pLastName the new patient last name
	 */
	public void setpLastName(String pLastName) {
		this.ptLastName = pLastName;
	}



	/**
	 * Gets the patient email.
	 *
	 * @return the patient email
	 */
	public String getPtEmail() {
		return ptEmail;
	}



	/**
	 * Sets the patient email.
	 *
	 * @param ptEmail the new patient email
	 */
	public void setPtEmail(String ptEmail) {
		this.ptEmail = ptEmail;
	}



	/**
	 * Gets the patient phone.
	 *
	 * @return the patient phone
	 */
	public String getPtPhone() {
		return ptPhone;
	}



	/**
	 * Sets the patient phone.
	 *
	 * @param ptPhone the new patient phone
	 */
	public void setPtPhone(String ptPhone) {
		this.ptPhone = ptPhone;
	}



	/**
	 * Gets the patient private clinic.
	 *
	 * @return the patient private clinic
	 */
	public String getPtPrivateClinic() {
		return ptPrivateClinic;
	}



	/**
	 * Sets the patient private clinic.
	 *
	 * @param ptPrivateClinic the new patient private clinic
	 */
	public void setPtPrivateClinic(String ptPrivateClinic) {
		this.ptPrivateClinic = ptPrivateClinic;
	}



	/**
	 * Gets the patient personal Doctor.
	 *
	 * @return the patient personal Doctor
	 */
	public String getPd() {
		return this.ptpersonalDoctorID;
	}



	/**
	 * Sets the patient personal Doctor.
	 *
	 * @param pd the new patient personal Doctor
	 */
	public void setptpersonalDoctorID(String pd) {
		this.ptpersonalDoctorID = pd;
	}



	/**
	 * toString - for printing the patient.
	 */
	@Override
	public String toString() {
		return "Patient [Patient ID: " + ptID + " , Name: " + ptFirstName +" "+ ptLastName + ", Email: "
				+ ptEmail + ", Phone=" + ptPhone + ", Private Clinic:" + ptPrivateClinic + ", Personal Doctor ID:" + ptpersonalDoctorID + "]";
	}


}
