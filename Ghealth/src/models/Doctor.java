package models;

import java.io.Serializable;
import enums.*;

/**
 * @author G5 lab group
 * The Class Doctor.
 */
public class Doctor extends User implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3874467060459550851L;
	
	/** The Doctor Speciallity. */
	private DoctorSpeciallity dSpeciality;
	
	/**
	 * Instantiates a new doctor.
	 *
	 * @param uID the u id
	 * @param uPassword the Doctor password
	 * @param uFirstName the Doctor first name
	 * @param uLastName the Doctor last name
	 * @param uRole the Doctor role
	 * @param uEmail the Doctor email
	 * @param uClinic the Doctor clinic
	 * @param dSpeciality the Doctor speciality
	 */
	public Doctor(String uID, String uPassword, String uFirstName, String uLastName, Roles uRole, String uEmail,
			Clinic uClinic,DoctorSpeciallity dSpeciality) 
	{
		super(uID, uPassword, uFirstName, uLastName, uRole, uEmail, uClinic);
		this.setdSpeciality(dSpeciality);
	}
	
	/**
	 * Instantiates a new doctor.
	 *
	 * @param uID the Doctor id
	 * @param uPassword the Doctor password
	 * @param uFirstName the Doctor first name
	 * @param uLastName the Doctor last name
	 * @param uRole the Doctor role
	 * @param uEmail the Doctor email
	 * @param uClinic the Doctor clinic
	 */
	public Doctor(String uID, String uPassword, String uFirstName, String uLastName, Roles uRole, String uEmail,
			Clinic uClinic) 
	{
		super(uID, uPassword, uFirstName, uLastName, uRole, uEmail, uClinic);
	}
	
	/**
	 * Instantiates a new doctor.
	 * (Without Password & Roll).
	 *
	 * @param uID the Doctor id
	 * @param uFirstName the Doctor first name
	 * @param uLastName the Doctor last name
	 * @param uClinic the Doctor clinic
	 * @param dSpeciality the Doctor speciality
	 */
	public Doctor(String uID,String uFirstName,String uLastName,Clinic uClinic,DoctorSpeciallity dSpeciality) 
	{
		super(uID,"****",uFirstName,uLastName,Roles.DOCTOR,"",uClinic);
		this.setdSpeciality(dSpeciality);
	}

	/**
	 * Gets the Doctor speciality.
	 *
	 * @return the Doctor speciality
	 */
	public DoctorSpeciallity getdSpeciality() {
		return dSpeciality;
	}

	/**
	 * Sets the Doctor speciality.
	 *
	 * @param dSpeciality the new Doctor speciality
	 */
	public void setdSpeciality(DoctorSpeciallity dSpeciality) {
		this.dSpeciality = dSpeciality;
	}

	

}
