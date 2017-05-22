package Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import enums.DoctorSpeciallity;
import enums.Status;
import enums.task;
import models.AppointmentSettings;
import models.Clinic;
import models.Doctor;
import models.Envelope;

/**
 * @author G5 lab group
 * The Class SCdocAppointment.
 */
public class SCdocAppointment extends SCsuperclassA {
	
	PreparedStatement stmt = null; 
	ResultSet result = null;
	
	/**
	 * Gets the recorded appointments.
	 *
	 * @param ptID the pt id
	 * @return the envelope
	 */
	public static Envelope GetRecordedAppointments(String ptID)
	{
		
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		Envelope en = new Envelope();
		AppointmentSettings as;
		Doctor doctor;
		
		querystr="SELECT  apsID,apsPtID,apsDate,apsTime,apsCreateDate,apsCreateTime,apsStatus,apsDocID,uFirstName,uLastName,cID,cName,cLocation,dSpeciality,apsSummery "
				+ "FROM appointmentsettings,user,clinic,doctor "
				+ "WHERE apsPtID= ? AND apsStatus= ? AND uID= ? AND cID= ? AND dID= ?"
						+ " ORDER BY apsDate DESC; ";
		
		try 
		{
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr);
			String[] app = { "apsDocID", "ucID", "uID" };
			result = getSql(querystr, ptID, Status.ARRIVED.toString(), app[0], app[1], app[2]);
			en.setStatus(Status.NOT_EXIST);
			while (result.next())
            {
				
				
				
				Status st =  Status.valueOf(result.getString(7));
				as = new AppointmentSettings(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),
						result.getString(5),result.getString(6),st,result.getString("apsDocID"));
				
				as.setApsSummery(result.getString(15));
				
				Clinic clinic = new Clinic(result.getInt("cID"),result.getString("cName"),result.getString("cLocation"));
				DoctorSpeciallity ds = DoctorSpeciallity.valueOf(result.getString("dSpeciality"));
				doctor = new Doctor(result.getString("apsDocID"),result.getString("uFirstName"),result.getString("uLastName"),clinic,ds);
				as.setDoctor(doctor);
				en.addobjList(as);
				
				en.setStatus(Status.EXIST);
            }   
			
			en.setType(task.GET_ARRIVED_APPOINTMENTS);
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
           en.setStatus(Status.FAILED_EXCEPTION);
          return en;
        }
		
		return en;

	}
		
	/**
	 * Gets the current appointment.
	 *
	 * @param ptID the pt id
	 * @param apsDocID the aps doc id
	 * @return the envelope
	 */
	public static Envelope GetCurrentAppointment(String ptID, String apsDocID)
	{
		
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		Envelope en = new Envelope();
	
		
		
		querystr="SELECT apsID"
				+ " FROM appointmentsettings"
				+ " WHERE apsPtID= ? AND apsStatus= ? AND apsDocID= ?";
		
		try 
		{
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr);
			
			result = getSql(querystr, ptID, Status.SCHEDUELD.toString(), apsDocID);
			en.setStatus(Status.NOT_EXIST);
			
			while (result.next())
            {
				int appointementID = result.getInt(1);
				
				en.addobjList(appointementID);
				
				en.setStatus(Status.EXIST);
            }   
			
			en.setType(task.GET_CURRENT_APPOINTMENT_ID);
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          en.setStatus(Status.FAILED_EXCEPTION);
          return en;
        }
		
		return en;

	}

	/**
	 * Gets the p doc mail.
	 *
	 * @param nt the nt
	 * @return the p doc mail
	 */
	public static Notification getPDocMail(Notification nt){
		
		String querystr,querystr2;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		
		querystr= ""
				+ "SELECT pd.PersonalDoctorEmail "
				+ "FROM ghealth.personaldoctor pd, ghealth.patient p "
				+ "WHERE p.ptID = ? AND p.ptDoctorID = ? ";
		querystr2= ""
				+ "SELECT pd.PersonalDoctorName "
				+ "FROM ghealth.personaldoctor pd, ghealth.patient p "
				+ "WHERE p.ptID = ? AND p.ptDoctorID = ? ";
		
		try {
			String[] app = { "pd.PersonalDoctorID" };
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr);
			result = getSql(querystr, app[0]);
			result.next();
			nt.mail = result.getString(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr2);
			String[] app = { "pd.PersonalDoctorID" };
			result = getSql(querystr2, app[0]);
			result.next();
			nt.docName = result.getString(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return nt;
	}
	
	
	/**
	 * Record appointment.
	 *
	 * @param apsID the aps id
	 * @param summery the summery
	 * @return the status
	 */
	public static Status RecordAppointment(String apsID, String summery)
	{	
		
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;	
		
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		String createdHour = hourFormat.format(new Date());
		
		querystr="UPDATE appointmentsettings "
				+ "SET apsStatus= ?, apsSummery = ?, apsStartTime = ? "
				+ "WHERE apsID= ? ";
		
		try 
		{
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr);
			
			setSql(querystr, Status.ARRIVED.toString(), summery, createdHour, apsID);
		
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          return Status.FAILED_EXCEPTION;
        }
		
		return Status.ARRIVED;

	}

}
