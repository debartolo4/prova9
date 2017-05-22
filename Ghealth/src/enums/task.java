package enums;

/**
 * 
 * The Enum tasks for client server communicate.
 * @author G5 lab group
 */
public enum task {
	/** The add patient. */
	ADD_PATIENT,
	
	/** The update patient. */
	UPDATE_PATIENT, 
	
	/** The cancel patient registration. */
	CANCEL_PATIENT_REGISTRATION,
	
	/** The recover patient registration. */
	RECOVER_PATIENT_REGISTRATION,
	
	/** The cancel all patient appointments. */
	CANCEL_ALL_PATIENT_APPOINTMENTS,
	
	/** The get patient. */
	GET_PATIENT,
	
	/** The get user. */
	GET_USER,
	
	/** The get lab ref. */
	GET_LAB_REF,
	
	/** The update lab ref. */
	UPDATE_LAB_REF,
	
	/** The create new appointment. */
	CREATE_NEW_APPOINTMENT,
	/** The get doctors in clinic by type. */
	GET_DOCTORS_IN_CLINIC_BY_TYPE,
	/** The get availible doctor hours. */
	GET_AVAILIBLE_DOCTOR_HOURS,
	/** The get open appointments. */
	GET_OPEN_APPOINTMENTS,
	/** The cancel appointment from db. */
	CANCEL_APPOINTMENT_FROM_DB,
	
	/** The get arrived appointments. */
	GET_ARRIVED_APPOINTMENTS,
	
	/** The get private clinic list. */
	GET_PRIVATE_CLINIC_LIST,
	
	/** The log out. */
	LOG_OUT,
	
	/** The get current appointment id. */
	GET_CURRENT_APPOINTMENT_ID,
	
	/** The set appointment record. */
	SET_APPOINTMENT_RECORD,
	
	/** The get arrived labs. */
	GET_ARRIVED_LABS,
	
	/** The get schedueld lab. */
	GET_SCHEDUELD_LAB,
	
	/** The update lab record. */
	UPDATE_LAB_RECORD,
	
	/** The upload file to lab record. */
	UPLOAD_FILE_TO_LAB_RECORD,
	
	/** The send file to client. */
	SEND_FILE_TO_CLIENT,
	
	/** The create lab ref. */
	CREATE_LAB_REF,
	
	/** The get clinic list. */
	GET_CLINIC_LIST,
	
	/** The get clinic weekly report. */
	GET_CLINIC_WEEKLY_REPORT,
	
	/** The get clinic monthly report. */
	GET_CLINIC_MONTHLY_REPORT,
	
	
	/** The get clinic cluster months report. */
	GET_CLINIC_CLUSTER_MONTHLY_REPORT,
	
	/** The send personal doc mail. */
	SEND_PERSONAL_DOC_MAIL
}
