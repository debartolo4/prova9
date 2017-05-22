package models;

import java.io.Serializable;

/**
 * @author G5 lab group
 * The Class PersonalDoctor.
 */
public class PersonalDoctor implements Serializable{

	private static final long serialVersionUID = -4471079822624266319L;
	
	/** The Personal Doctor id. */
	private int PersonalDoctorID;
	
	/** The Personal doctor name. */
	private String PersonalDoctorName;
	
	/** The Personal doctor email. */
	private String PersonalDoctorEmail;
	
	/**
	 * Instantiates a new personal doctor with ID only.
	 *
	 * @param idonly the doctor id
	 */
	public PersonalDoctor(int idonly)
	{
		super();
		this.PersonalDoctorID = idonly;
	}
	
	/**
	 * Instantiates a new personal doctor with all attributes.
	 *
	 * @param PersonalDoctorID the personal doctor id
	 * @param PersonalDoctorName the personal doctor name
	 * @param PersonalDoctorEmail the personal doctor email
	 */
	public PersonalDoctor(int PersonalDoctorID,String PersonalDoctorName, String PersonalDoctorEmail) {
		super();
		this.setPersonalDoctorID(PersonalDoctorID);
		this.PersonalDoctorName = PersonalDoctorName;
		this.PersonalDoctorEmail = PersonalDoctorEmail;
	}
	
	/**
	 * Gets the personal doctor name.
	 *
	 * @return the personal doctor name
	 */
	public String getPersonalDoctorName() {
		return PersonalDoctorName;
	}
	
	/**
	 * Sets the personal doctor name.
	 *
	 * @param personalDoctorName the new personal doctor name
	 */
	public void setPersonalDoctorName(String personalDoctorName) {
		PersonalDoctorName = personalDoctorName;
	}
	
	/**
	 * Gets the personal doctor email.
	 *
	 * @return the personal doctor email
	 */
	public String getPersonalDoctorEmail() {
		return PersonalDoctorEmail;
	}
	
	/**
	 * Sets the personal doctor email.
	 *
	 * @param personalDoctorEmail the new personal doctor email
	 */
	public void setPersonalDoctorEmail(String personalDoctorEmail) {
		PersonalDoctorEmail = personalDoctorEmail;
	}

	/**
	 * Gets the personal doctor id.
	 *
	 * @return the personal doctor id
	 */
	public int getPersonalDoctorID() {
		return PersonalDoctorID;
	}

	/**
	 * Sets the personal doctor id.
	 *
	 * @param personalDoctorID the new personal doctor id
	 */
	public void setPersonalDoctorID(int personalDoctorID) {
		PersonalDoctorID = personalDoctorID;
	}
	
	
}
