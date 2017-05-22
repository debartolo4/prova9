package models;


import java.io.Serializable;

/**
 * @author G5 lab group
 * The Class Clinic.
 */
public class Clinic implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The clinic id. */
	private int cID;
	
	/** The clinic name. */
	private String cName;
	
	/** The clinic location. */
	private String cLocation; 
	
	
	  
	/**
	 * Instantiates a new clinic.
	 */
	public Clinic()
	{
		super();
	}
	
	/**
	 * Instantiates a new clinic.
	 *
	 * @param cID the clinic id
	 * @param cName the clinic name
	 * @param cLocation the clinic location
	 */
	public Clinic(int cID,String cName,String cLocation)
	{
		super();
		this.cID = cID;
		this.setcName(cName);
		this.cLocation = cLocation;
		
		
	}

	/**
	 * Gets the clinic id.
	 *
	 * @return the clinic id
	 */
	public int getcID() {
		return cID;
	}

	/**
	 * Sets the clinic id.
	 *
	 * @param cID the new clinic id
	 */
	public void setcID(int cID) {
		this.cID = cID;
	}

	/**
	 * Gets the clinic location.
	 *
	 * @return the clinic location
	 */
	public String getcLocation() {
		return cLocation;
	}

	/**
	 * Sets the clinic location.
	 *
	 * @param cLocation the new clinic location
	 */
	public void setcLocation(String cLocation) {
		this.cLocation = cLocation;
	}

	/**
	 * Gets the clinic name.
	 *
	 * @return the clinic name
	 */
	public String getcName() {
		return cName;
	}

	/**
	 * Sets the clinic name.
	 *
	 * @param cName the new clinic name
	 */
	public void setcName(String cName) {
		this.cName = cName;
	}

	@Override
	public String toString() {
		return "[" + cID + "] " + cName + ", Location=" + cLocation + "";
	}
	
	
	
}
