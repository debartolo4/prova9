package Server;

import java.io.Serializable;
import java.util.Date;

import models.Patient;

/**
 * @author G5 lab group
 * Contains notification details.
 */
public class Notification implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** The date. */
	public Date date = null;
	
	/** The String date. */
	public String sdate = null;
	
	/** The time. */
	public String time = null;
	
	/** The mail address. */
	public String mail = null;
	
	/** The location. */
	public String location = null;
	
	/** The doc name. */
	public String docName = null;	
	
	/** The patient name. */
	public String ptName = null;
	
	/** The appointment summary. */
	public String appSummery = null;
	
	/** The patient. */
	public Patient patient = new Patient();
}
