package models;

import java.io.Serializable;

import enums.Status;

/**
 * @author G5 lab group
 * The Class model - LabSettings,
 * Here we will create lab ref and record the patient lab.
 */
public class LabSettings implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The lab id. */
	private int labID;
	
	/** The lab patient id. */
	private String labPtID;
	
	/** The Create date. */
	private String CreateDate;
	
	/** The Create time. */
	private String CreateTime;
	
	/** The lab status. */
	private Status labStatus;
	
	/** The lab doctor id. */
	private String labDocID;
	
	/** The lab worker id. */
	private String labWorkerID;
	
	/** The lab doctor req. */
	private String labDoctorReq;
	
	/** The lab worker summery. */
	private String labWorkerSummery;
	
	/** The lab photo path. */
	private String labPhotoPath;
	
	/** The lab worker. */
	private User labWorker;
	
	/** The file path. */
	private String filePath;
	
	/** The file extension. */
	private String fileExt;
	
	
	/**
	 * Instantiates a new lab settings.
	 */
	public LabSettings()
	{
		super();
		
	}
	
	/**
	 * Instantiates a new lab settings.
	 *
	 * @param labID the lab id
	 * @param labPtID the lab patient id
	 * @param createDate the create date
	 * @param createTime the create time
	 * @param labStatus the lab status
	 * @param labDocID the lab doc id
	 * @param labDoctorReq the lab doctor req
	 */
	public LabSettings(int labID,String labPtID, String createDate, String createTime, Status labStatus, String labDocID,
			String labDoctorReq) {
		super();
		this.labID = labID;
		this.setLabPtID(labPtID);
		CreateDate = createDate;
		setCreateTime(createTime);
		this.setLabStatus(labStatus);
		this.setLabDocID(labDocID);
		this.setLabDoctorReq(labDoctorReq);
	}
	
	/**
	 * Instantiates a new lab settings.
	 * (Without lab ID)
	 *
	 * @param labPtID the lab patient id
	 * @param createDate the create date
	 * @param createTime the create time
	 * @param labStatus the lab status
	 * @param labDocID the lab doc id
	 * @param labDoctorReq the lab doctor req
	 */
	public LabSettings(String labPtID, String createDate, String createTime, Status labStatus, String labDocID,
			String labDoctorReq) {
		super();
		this.setLabPtID(labPtID);
		CreateDate = createDate;
		setCreateTime(createTime);
		this.setLabStatus(labStatus);
		this.setLabDocID(labDocID);
		this.setLabDoctorReq(labDoctorReq);
	}

	
	/**
	 * Gets the lab id.
	 *
	 * @return the lab id
	 */
	public int getLabID()
	{
		return labID;
	}
	
	/**
	 * Gets the lab worker.
	 *
	 * @return the lab worker
	 */
	public User getLabWorker() {
		return labWorker;
	}

	/**
	 * Sets the lab worker.
	 *
	 * @param labWorker the new lab worker
	 */
	public void setLabWorker(User labWorker) {
		this.labWorker = labWorker;
	}
	
	
	/**
	 * To string open labs.
	 *
	 * @return the string
	 */
	public String toStringOpenLabs()
	{
		return "Lab ID:"+labID+" | Doctor Name: "+labWorker.getuFirstName()+" "+labWorker.getuLastName();
	}

	/**
	 * Gets the lab doctor req.
	 *
	 * @return the lab doctor req
	 */
	public String getLabDoctorReq() {
		return labDoctorReq;
	}

	/**
	 * Sets the lab doctor req.
	 *
	 * @param labDoctorReq the new lab doctor req
	 */
	public void setLabDoctorReq(String labDoctorReq) {
		this.labDoctorReq = labDoctorReq;
	}

	/**
	 * Gets the lab worker summery.
	 *
	 * @return the lab worker summery
	 */
	public String getLabWorkerSummery() {
		return labWorkerSummery;
	}

	/**
	 * Sets the lab worker summery.
	 *
	 * @param labWorkerSummery the new lab worker summery
	 */
	public void setLabWorkerSummery(String labWorkerSummery) {
		this.labWorkerSummery = labWorkerSummery;
	}

	/**
	 * Gets the file path.
	 *
	 * @return the file path
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Sets the file path.
	 *
	 * @param filePath the new file path
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Gets the file ext.
	 *
	 * @return the file ext
	 */
	public String getFileExt() {
		return fileExt;
	}

	/**
	 * Sets the file ext.
	 *
	 * @param fileExt the new file ext
	 */
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	
	/**
	 * Tostring show labs.
	 *
	 * @return the string
	 */
	public String tostringShowLabs() {
		return "Date= "+CreateDate+",Laborant "+labWorker.getuFirstName()+" "+labWorker.getuLastName()
		+",Clinic="+labWorker.getuClinic().getcName()+"("+labWorker.getuClinic().getcLocation()+")";
	}

	/**
	 * Gets the lab worker id.
	 *
	 * @return the lab worker id
	 */
	public String getLabWorkerID() {
		return labWorkerID;
	}

	/**
	 * Sets the lab worker id.
	 *
	 * @param labWorkerID the new lab worker id
	 */
	public void setLabWorkerID(String labWorkerID) {
		this.labWorkerID = labWorkerID;
	}

	/**
	 * Gets the lab status.
	 *
	 * @return the lab status
	 */
	public Status getLabStatus() {
		return labStatus;
	}

	/**
	 * Sets the lab status.
	 *
	 * @param labStatus the new lab status
	 */
	public void setLabStatus(Status labStatus) {
		this.labStatus = labStatus;
	}

	/**
	 * Gets the lab pt id.
	 *
	 * @return the lab pt id
	 */
	public String getLabPtID() {
		return labPtID;
	}

	/**
	 * Sets the lab pt id.
	 *
	 * @param labPtID the new lab pt id
	 */
	public void setLabPtID(String labPtID) {
		this.labPtID = labPtID;
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
	 * Sets the creates the time.
	 *
	 * @param createTime the new creates the time
	 */
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	/**
	 * Gets the lab photo path.
	 *
	 * @return the lab photo path
	 */
	public String getLabPhotoPath() {
		return labPhotoPath;
	}

	/**
	 * Sets the lab photo path.
	 *
	 * @param labPhotoPath the new lab photo path
	 */
	public void setLabPhotoPath(String labPhotoPath) {
		this.labPhotoPath = labPhotoPath;
	}

	/**
	 * Gets the lab doc id.
	 *
	 * @return the lab doc id
	 */
	public String getLabDocID() {
		return labDocID;
	}

	/**
	 * Sets the lab doc id.
	 *
	 * @param labDocID the new lab doc id
	 */
	public void setLabDocID(String labDocID) {
		this.labDocID = labDocID;
	}
	
	
}
