/**
 * TODO This is the class description
 */


package Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import enums.DoctorSpeciallity;
import enums.Status;
import enums.task;
import models.AppointmentSettings;
import models.Clinic;
import models.Doctor;
import models.Envelope;
import models.User;

/**
 * @author G5 lab group
 * The Class SCappointment.
 */
public class SCappointment {

	/**
	 * Gets the clinic doctor list.
	 *
	 * @param pt the patient
	 * @param sp the Specialty
	 * @return the envelope
	 */
	
	public static Envelope GetClinicDoctorList(String pt,String sp)
	{
		
		
		String querystr_a,querystr_b,querystr_c;
		User us = null;
		Envelope en = new Envelope();
		
		/* Return patient row if exist */
		
		
		
		
		querystr_a="CREATE OR REPLACE VIEW AMIR AS"
				+ " SELECT * "
				+ " FROM appointmentsettings a "
				+ " WHERE a.apsPtID= ? AND a.apsStatus= ? ;";
		
		querystr_b="SELECT COUNT(*) AS COUNT"
				+ " FROM appointmentsettings a,doctor d"
				+ " WHERE a.apsPtID = ? AND a.apsStatus= ? AND d.dSpeciality= ? AND d.dID = ? ;";
		
		querystr_c="SELECT DISTINCT uID,uFirstName,uLastName,cLocation,cName "
				+ " FROM user,clinic,doctor LEFT JOIN AMIR on AMIR.apsDocID = ? "
				+ " WHERE dSpeciality= ? AND uID = ? AND cID = ? "
				+ " ORDER BY apsDate DESC; ";
	
		PreparedStatement stmt = null; 
		try 
		{
			ResultSet result = null;
			String[] app = { "a.apsDocID", "doctor.dID", "dID", "ucID"};
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr_a);
			setSql(querystr_a, pt, Status.ARRIVED.toString());
			
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr_b);
			result = getSql(querystr_b, pt, Status.SCHEDUELD.toString(), sp, app[0]);
			
			result.next();
			if(result.getInt("COUNT") > 0)
			{
				en.setStatus(Status.SCHEDUELD);
			}
			else {
				
				stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr_a);
				result = getSql(querystr_c, app[1], sp, app[2], app[3]);
				
				while (result.next())
	            {
					/* Get & Create the exist user from DB */
					us = new User();
					Clinic cl = new Clinic();
					us.setuID(result.getString("uID"));
					us.setuFirstName(result.getString("uFirstName"));
					us.setuLastName(result.getString("uLastName"));
					cl.setcLocation(result.getString("cLocation"));
					cl.setcName(result.getString("cName"));
					us.setuClinic(cl);
					
					en.addobjList(us);
					
				}
				
				en.setStatus(Status.ARRIVED);
			}
			en.setType(task.GET_DOCTORS_IN_CLINIC_BY_TYPE);
			//mysqlConnection.autoConn.close();
			//XXX LOL!!!!!!
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          return null;
        }
		
		
		return en;
	}
	
	
	/**
	 * Gets the availible doctor hours.
	 *
	 * @param date the date
	 * @param uID the user id
	 * @return the envelope
	 */
	public static Envelope GetAvailibleDoctorHours(String date,String uID)
	{
		ResultSet result = null;
		String querystr;
		Envelope en = new Envelope();
		
		String []hours = new String[]{"8:00:00","8:30:00",
						"9:00:00","9:30:00",
						"10:00:00","10:30:00",
						"11:00:00","11:30:00",
						"12:00:00","12:30:00",
						"13:00:00","13:30:00",
						"14:00:00","14:30:00",
						"15:00:00","15:30:00",
						"16:00:00","16:30:00"};
		
		
		List<String> hoursList =  new ArrayList<String>();
	    Collections.addAll(hoursList, hours); 
	    
		querystr="SELECT apsTime FROM appointmentsettings"
				+ " WHERE apsDocID = ? AND apsDate= ? ;";
	
		PreparedStatement stmt = null; 
		
		try 
		{
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr);
			result = getSql(querystr, uID, date);
			while (result.next())
            {
				
				hoursList.remove(result.getString("apsTime"));

			}
			
			List<Object> timeList = new ArrayList<Object>(hoursList);

			en.setobjList(timeList);
			en.setType(task.GET_AVAILIBLE_DOCTOR_HOURS);
			
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          return null;
        }
		
		
		return en;
	}
	
	
	/**
	 * Creates the appointment.
	 *
	 * @param as the Appointment Settings
	 * @return the status
	 */
	public static Status CreateAppointment(AppointmentSettings as)
	{
	
		String querystr;
		
		querystr="INSERT INTO appointmentsettings " + " (apsPtID,apsDate,apsTime,apsCreateDate,apsCreateTime,apsStatus,apsDocID) "
				+ "VALUES ('"+as.getApsPtID()+"','"+as.getApsDate()+"','"+as.getApsTime()+"', '"
		+as.getCreateDate()+"', '"+as.getCreateTime()+"', '"+as.getApsStatus().toString()+"', '"+as.getApsDocID()+"')";
		
		PreparedStatement stmt = null; 
		
		try 
		{
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr);
			
			setSql(querystr);
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          return Status.FAILED_EXCEPTION;
        }
		
		return Status.CREATED;

	}
	
	
	/**
	 * Gets the scheduled appointments.
	 *
	 * @param ptID the patient id
	 * @return the envelope
	 */
	public static Envelope GetSCHEDUELDAppointments(String ptID)
	{
		
		String querystr;
		ResultSet result;
		PreparedStatement stmt = null; 
		Envelope en = new Envelope();
		AppointmentSettings as;
		Doctor doctor;
		
		querystr="SELECT  apsID,apsPtID,apsDate,apsTime,apsCreateDate,apsCreateTime,apsStatus,apsDocID,uFirstName,uLastName,cID,cName,cLocation,dSpeciality "
				+ "FROM appointmentsettings,user,clinic,doctor "
				+ "WHERE apsPtID= ? AND apsStatus= ? AND uID= ? AND cID= ? AND dID= ?";
		
		try 
		{
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr);
			
			String[] app = { "apsDocID", "ucID", "uID" };
			result = getSql(querystr, ptID, Status.SCHEDUELD.toString(), app[0], app[1], app[2]);
			en.setStatus(Status.NOT_EXIST);
			while (result.next())
            {
				Status st =  Status.valueOf(result.getString(7));
				as = new AppointmentSettings(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),
						result.getString(5),result.getString(6),st,result.getString("apsDocID"));
				
				
				Clinic clinic = new Clinic(result.getInt("cID"),result.getString("cName"),result.getString("cLocation"));
				DoctorSpeciallity ds = DoctorSpeciallity.valueOf(result.getString("dSpeciality"));
				doctor = new Doctor(result.getString("apsDocID"),result.getString("uFirstName"),result.getString("uLastName"),clinic,ds);
				as.setDoctor(doctor);
				en.addobjList(as);
				
				en.setStatus(Status.EXIST);
            }   
			
			en.setType(task.GET_OPEN_APPOINTMENTS);
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          en.setStatus(Status.FAILED_EXCEPTION);
          return en;
        }
		
		return en;

	}
	
		
	/**
	 * Cancel appointment.
	 *
	 * @param apsID the Appointment id
	 * @return the status
	 */
	public static Status CancelAppointment(int apsID)
	{
		
		PreparedStatement stmt;
		String querystr;
		
		querystr="UPDATE appointmentsettings "
				+ "SET apsStatus=? "
				+ "WHERE apsID=?";
		
		try 
		{
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr);
			stmt.setString(1, Status.CANCELED.toString());
			stmt.setInt(2, apsID);
			stmt.executeUpdate();
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          return Status.FAILED_EXCEPTION;
        }
		
		return Status.CANCELED;

	}
	
	/**
	 * Assegna i valori passati come parametri allo statements.
	 * 
	 * @param statement
	 * @param fields
	 * @throws SQLException
	 */
	private static void assignValues(PreparedStatement statement, Object... fields) throws SQLException {
		int index = 0;
		for (Object obj : fields) {
			statement.setObject(++index, obj);
		}
	}
	

	/**
	 * Getting SQL query results back from DB
	 * 
	 * @param query
	 */
	public static ResultSet getSql(String query) {
		// Statement stmt;
		ResultSet result = null;
		PreparedStatement stmt = null; 
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			result = stmt.executeQuery();
			// mysqlConnection.autoConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return result;
	}

	/**
	 * Getting SQL query results back from DB with params
	 * 
	 * @param query
	 */
	public static ResultSet getSql(String query, Object... fields) {
		// Statement stmt;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			assignValues(stmt, fields);
			result = stmt.executeQuery();
			// mysqlConnection.autoConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return result;
	}

	/**
	 * Sending SQL query.
	 *
	 * @param query
	 *            the new SQL query
	 */
	public static void setSql(String query) {
		// Statement stmt;
		PreparedStatement stmt = null; 
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			stmt.executeUpdate();
			// mysqlConnection.autoConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	/**
	 * Sending SQL query with params.
	 *
	 * @param query
	 *            the new SQL query
	 */
	public static void setSql(String query, Object...fields) {
		// Statement stmt;
		PreparedStatement stmt = null; 
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			assignValues(stmt, fields);
			stmt.executeUpdate();
			// mysqlConnection.autoConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
}
