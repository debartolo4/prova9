package models;

import java.io.Serializable;

/**
 * @author G5 lab group
 * The Class Private Clinic.
 */
public class PrivateClinic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The Private clinic name. */
	private String PrivateClinicName;
	
	/** The Private clinic email. */
	private String PrivateClinicEmail;
	
	
	/**
	 * Instantiates a new private clinic.
	 */
	public PrivateClinic()
	{
		super();
	}
	
	/**
	 * Instantiates a new private clinic.
	 *
	 * @param privateClinicName the private clinic name
	 * @param privateClinicEmail the private clinic email
	 */
	public PrivateClinic(String privateClinicName, String privateClinicEmail) {
		super();
		setPrivateClinicName(privateClinicName);
		setPrivateClinicEmail(privateClinicEmail);
	}


	/**
	 * Gets the private clinic name.
	 *
	 * @return the private clinic name
	 */
	public String getPrivateClinicName() {
		return PrivateClinicName;
	}


	/**
	 * Sets the private clinic name.
	 *
	 * @param privateClinicName the new private clinic name
	 */
	public void setPrivateClinicName(String privateClinicName) {
		PrivateClinicName = privateClinicName;
	}


	/**
	 * Gets the private clinic email.
	 *
	 * @return the private clinic email
	 */
	public String getPrivateClinicEmail() {
		return PrivateClinicEmail;
	}


	/**
	 * Sets the private clinic email.
	 *
	 * @param privateClinicEmail the new private clinic email
	 */
	public void setPrivateClinicEmail(String privateClinicEmail) {
		PrivateClinicEmail = privateClinicEmail;
	}

	
	
}
