package models;
import java.io.Serializable;
import enums.*;


/**
 * @author G5 lab group
 * The Class for any User on our system.
 */
public class User implements Serializable {

	private static final long serialVersionUID = 4147848156651925332L;
	
	/** The user id. */
	private String uID; //worker id 
	
	/** The user password. */
	private String uPassword;
	
	/** The user first name. */
	private String uFirstName;
	
	/** The user last name. */
	private String uLastName;
	
	/** The user role. */
	private Roles uRole; 
	
	/** The user email. */
	private String uEmail;
	
	/** The user clinic. */
	private Clinic uClinic;
	
	/**
	 * This is the default constructor.
	 */
	public User()
	{
		super();
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param uID the user id
	 */
	public User(String uID)
	{
		super();
		this.uID = uID;
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param uID the user id
	 * @param uPassword the user password
	 * @param uFirstName the user first name
	 * @param uLastName the user last name
	 * @param uRole the user role
	 * @param uEmail the user email
	 * @param uClinic the user clinic
	 */
	public User(String uID, String uPassword, String uFirstName, String uLastName, Roles uRole, String uEmail,
			Clinic uClinic) 
	{
		super();
		this.uID = uID;
		this.uPassword = uPassword;
		this.setuFirstName(uFirstName);
		this.setuLastName(uLastName);
		this.uRole = uRole;
		this.uEmail = uEmail;
		this.uClinic = uClinic;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getuID() {
		return uID;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param uID the new user id
	 */
	public void setuID(String uID) {
		this.uID = uID;
	}
	
	/**
	 * Gets the user password.
	 *
	 * @return the user password
	 */
	public String getuPassword() {
		return uPassword;
	}
	
	/**
	 * Sets the user password.
	 *
	 * @param uPassword the new user password
	 */
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	
	/**
	 * Gets the user role.
	 *
	 * @return the user role
	 */
	public Roles getuRole() {
		return uRole;
	}
	
	/**
	 * Sets the user role.
	 *
	 * @param uRole the new user role
	 */
	public void setuRole(Roles uRole) {
		this.uRole = uRole;
	}
	
	/**
	 * Gets the user email.
	 *
	 * @return the user email
	 */
	public String getuEmail() {
		return uEmail;
	}
	
	/**
	 * Sets the user email.
	 *
	 * @param uEmail the new user email
	 */
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	
	/**
	 * Gets the user clinic.
	 *
	 * @return the user clinic
	 */
	public Clinic getuClinic() {
		return uClinic;
	}
	
	/**
	 * Sets the user clinic.
	 *
	 * @param uClinic the new user clinic
	 */
	public void setuClinic(Clinic uClinic) {
		this.uClinic = uClinic;
	}

	/**
	 * Gets the user first name.
	 *
	 * @return the user first name
	 */
	public String getuFirstName() {
		return uFirstName;
	}

	/**
	 * Sets the user first name.
	 *
	 * @param uFirstName the new user first name
	 */
	public void setuFirstName(String uFirstName) {
		this.uFirstName = uFirstName;
	}

	/**
	 * Gets the user last name.
	 *
	 * @return the user last name
	 */
	public String getuLastName() {
		return uLastName;
	}

	/**
	 * Sets the user last name.
	 *
	 * @param uLastName the new user last name
	 */
	public void setuLastName(String uLastName) {
		this.uLastName = uLastName;
	}

	/**
	 * Print the user. 
	 * 
	 * @return user in string
	 */
	@Override
	public String toString() {
		return "User [uID=" + uID + ", uPassword=" + uPassword + ", uFirstName=" + uFirstName + ", uLastName="
				+ uLastName + ", uRole=" + uRole + ", uEmail=" + uEmail + ", Clinic Name=" + uClinic.getcName() + " Location=" + uClinic.getcLocation() +"]";
		
	}
	
	
	/**
	 * To string clinic list.
	 *
	 * @return clinic in string
	 */
	public String toStringClinicList() {
		return "Clinic: '"+uClinic.getcName() + "' " + (uClinic.getcLocation())
				+ "  |  Doctor: " + uFirstName + " " + uLastName;
	}
	
	
}
