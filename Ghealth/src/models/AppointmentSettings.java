/**
 * TODO This is the class description
 */


package models;

import java.io.Serializable;

import enums.Status;




/**
 * @author G5 lab group
 * The Class AppointmentSettings.
 */
public class AppointmentSettings  implements Serializable {
	
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7716496157001890423L;
	
	  /**
  	 * Constructs an instance of the ClientConsole UI.
  	 *
  	 */

	/** The appointment id. */

	private int apsID;
	
	/** The aps pt id. */
	private String apsPtID;
	
	/** The aps date. */
	private String apsDate;
	
	/** The aps time. */
	private String apsTime;
	
	/** The Create date. */
	private String CreateDate;
	
	/** The Create time. */
	private String CreateTime;
	
	/** The aps status. */
	private Status apsStatus;
	
	/** The aps doc id. */
	private String apsDocID;
	
	/** The aps summery. */
	private String apsSummery;
	
	/** The doctor. */
	private Doctor doctor;
	
	/**
	 * Instantiates a new appointment settings.
	 */
	public AppointmentSettings()
	{
		super();
	}


	/**
	 * Instantiates a new appointment settings.
	 *
	 * @param apsPtID the appointment Patient ID
	 * @param apsDate the appointment date
	 * @param apsTime the appointment time
	 * @param createTimeDate the appointment create date
	 * @param createTime the appointment create time
	 * @param apsStatus the appointment status
	 * @param apsDocID the appointment doctor ID
	 */
	public AppointmentSettings(String apsPtID, String apsDate, String apsTime, String createTimeDate, String createTime,
			Status apsStatus, String apsDocID) {
		super();
		this.apsPtID = apsPtID;
		this.apsDate = apsDate;
		this.apsTime = apsTime;
		CreateDate = createTimeDate;
		CreateTime = createTime;
		this.apsStatus = apsStatus;
		this.apsDocID = apsDocID;
	}


	/**
	 * Instantiates a new appointment settings.
	 *
	 * @param apsID the appointment id
	 * @param apsPtID the appointment patient id
	 * @param apsDate the appointment date
	 * @param apsTime the appointment time
	 * @param createTimeDate the appointment create date
	 * @param createTime the appointment create time
	 * @param apsStatus the appointment status
	 * @param apsDocID the appointment doc id
	 */
	public AppointmentSettings(int apsID, String apsPtID, String apsDate, String apsTime, String createTimeDate,
			String createTime, Status apsStatus, String apsDocID) {
		super();
		this.apsID = apsID;
		this.apsPtID = apsPtID;
		this.apsDate = apsDate;
		this.apsTime = apsTime;
		CreateDate = createTimeDate;
		CreateTime = createTime;
		this.apsStatus = apsStatus;
		this.apsDocID = apsDocID;
	}
	
	
	/**
	 * Sets the aps id.
	 *
	 * @param apsID the new aps id
	 */
	public void setApsID(int apsID) {
		this.apsID = apsID;
	}
	
	/**
	 * Sets the aps pt id.
	 *
	 * @param apsPtID the new aps pt id
	 */
	public void setApsPtID(String apsPtID) {
		this.apsPtID = apsPtID;
	}
	
	/**
	 * Sets the aps date.
	 *
	 * @param apsDate the new aps date
	 */
	public void setApsDate(String apsDate) {
		this.apsDate = apsDate;
	}
	
	/**
	 * Sets the aps time.
	 *
	 * @param apsTime the new aps time
	 */
	public void setApsTime(String apsTime) {
		this.apsTime = apsTime;
	}
	
	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	
	/**
	 * Sets the creates the time.
	 *
	 * @param createTime the new creates the time
	 */
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	
	/**
	 * Sets the aps status.
	 *
	 * @param apsStatus the new aps status
	 */
	public void setApsStatus(Status apsStatus) {
		this.apsStatus = apsStatus;
	}
	
	/**
	 * Sets the aps doc id.
	 *
	 * @param apsDocID the new aps doc id
	 */
	public void setApsDocID(String apsDocID) {
		this.apsDocID = apsDocID;
	}

	/**
	 * Gets the aps id.
	 *
	 * @return the aps id
	 */
	public int getApsID() {
		return apsID;
	}

	/**
	 * Gets the aps pt id.
	 *
	 * @return the aps pt id
	 */
	public String getApsPtID() {
		return apsPtID;
	}

	/**
	 * Gets the aps date.
	 *
	 * @return the aps date
	 */
	public String getApsDate() {
		return apsDate;
	}

	/**
	 * Gets the aps time.
	 *
	 * @return the aps time
	 */
	public String getApsTime() {
		return apsTime;
	}

	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	public String getCreateDate() {
		return CreateDate;
	}

	/**
	 * Gets the creates the time.
	 *
	 * @return the creates the time
	 */
	public String getCreateTime() {
		return CreateTime;
	}

	/**
	 * Gets the aps status.
	 *
	 * @return the aps status
	 */
	public Status getApsStatus() {
		return apsStatus;
	}

	/**
	 * Gets the aps doc id.
	 *
	 * @return the aps doc id
	 */
	public String getApsDocID() {
		return apsDocID;
	}



	/**
	 * Gets the doctor.
	 *
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}


	/**
	 * Sets the doctor.
	 *
	 * @param doctor the new doctor
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
	/**
	 * Print The appointment
	 */
	@Override
	public String toString() {
		
		return "AppointmentSettings [apsPtID=" + apsPtID + ", apsDate=" + apsDate + ", apsTime=" + apsTime
				+ ", CreateDate=" + CreateDate + ", CreateTime=" + CreateTime + ", apsStatus=" + apsStatus
				+ ", apsDocID=" + apsDocID + "]";
	}


	/**
	 * To string cancel appoint.
	 *
	 * @return the string
	 */
	public String toStringCancelAppoint() {
		return "("+doctor.getdSpeciality()+") Date=" + apsDate + ", Doctor "+
				doctor.getuLastName()+",Clinic="+doctor.getuClinic().getcName()+" ("+doctor.getuClinic().getcLocation()+")";
	}


	/**
	 * Gets the aps summery.
	 *
	 * @return the aps summery
	 */
	public String getApsSummery() {
		return apsSummery;
	}


	/**
	 * Sets the aps summery.
	 *
	 * @param apsSummery the new aps summery
	 */
	public void setApsSummery(String apsSummery) {
		this.apsSummery = apsSummery;
	}
	
}
