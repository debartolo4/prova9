package Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import enums.Status;
import models.Envelope;
import models.Patient;
import models.PrivateClinic;

/**
 * @author G5 lab group
 * The Class SCpatient.
 */
public class SCpatient extends SCsuperclassA {


	PreparedStatement stmt; 
	ResultSet result = null;

	/**
	 * Gets the exist patient.
	 *
	 * @param ptID the pt id
	 * @return the envelope
	 */
	public static Envelope GetExistPatient(String ptID)
	{
		int rowCount=0;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		String querystr;
		Patient pt = new Patient();
		Envelope en = new Envelope();
		
		/* Return patient row if exist */
		querystr="SELECT * FROM patient "
				+ "WHERE ptID = ? ;";
		
		try 
		{
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			
			result = getSql(querystr, ptID);
			result.last();
			rowCount = result.getRow();
			result.first();
			
			if(rowCount == 0)
			{
				en.setStatus(Status.NOT_EXIST);
				//en.addobjList(pt);
			}
			else if(result.getString(8).equals("NOT_REG"))
			{
				en.setStatus(Status.NOT_REG);
				/* Get & Create the patient from DB */
				
				pt.setpID(result.getString("ptID"));
				pt.setpFirstName(result.getString("ptFirstName"));
				pt.setpLastName(result.getString("ptLastName"));
				pt.setPtEmail(result.getString("ptEmail"));
				pt.setPtPhone(result.getString("ptPhone"));
				pt.setPtPrivateClinic(result.getString("ptPrivateClinic"));
				String ptdid = result.getString("ptDoctorID");
				pt.setptpersonalDoctorID(ptdid);
				en.addobjList(pt);
			}
			else
			{
				en.setStatus(Status.EXIST);
				/* Get & Create the patient from DB */
				
				pt.setpID(result.getString("ptID"));
				pt.setpFirstName(result.getString("ptFirstName"));
				pt.setpLastName(result.getString("ptLastName"));
				pt.setPtEmail(result.getString("ptEmail"));
				pt.setPtPhone(result.getString("ptPhone"));
				pt.setPtPrivateClinic(result.getString("ptPrivateClinic"));
				String ptdid = result.getString("ptDoctorID");
				pt.setptpersonalDoctorID(ptdid);
				en.addobjList(pt);
			}
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          return null;
        }
		
		return en;
	}
	
	/**
	 * Creates the patient.
	 *
	 * @param pt the pt
	 * @return the status
	 */
	public static Status CreatePatient(Patient pt)
	{
		PreparedStatement stmt = null; 
		ResultSet result = null;
		String querystr;
		querystr="INSERT INTO patient " 
		+ " VALUES ('"+pt.getpID()+"','"+pt.getpFirstName()+
		"','"+pt.getpLastName()+"', '"+pt.getPtEmail()+"', '"+
		pt.getPtPhone()+"', '"+pt.getPtPrivateClinic()+"', '"+pt.getPd()+"', 'IS_REG',null)";
		
		try 
		{
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			
			setSql(querystr);
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          return Status.FAILED_EXCEPTION;
        }
		
		return Status.CREATED;

	}
	
	
	/**
	 * Gets the clinic list.
	 *
	 * @return the envelope
	 */
	public static Envelope GetClinicList()
	{
		Envelope en = new Envelope();
		
		String querystr;
		ResultSet result = null;
		
		querystr="SELECT * "
				+ "FROM privateclniic";
		
		PreparedStatement stmt = null; 
		
		try 
		{
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			result = getSql(querystr);
			while (result.next())
            {
				en.addobjList(new PrivateClinic(result.getString(1),result.getString(2)));
				
            }   
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
         
        }
		return en;
		

	}
	
	/**
	 * methood chages the registration status of the patient and
	 * canceles his SCHEDUALED Appointments.
	 *
	 * @param pt the pt
	 * @return the status
	 */
	
	public static Status UncreatePatient(Patient pt)
	{
		
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String leftDate = formatter.format(new Date());

		
		querystr="UPDATE patient "
				+ "SET ptIsRegistered= ? ,ptLeaveDate= ? "
				+ "WHERE ptID= ? ";
		
		try 
		{
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			setSql(querystr, Status.NOT_REG.toString(), leftDate, pt.getpID());
		
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          return Status.FAILED_EXCEPTION;
        }
		
		return Status.NOT_REG;

	}
	
	/**
	 * Recover patient.
	 *
	 * @param pt the pt
	 * @return the status
	 */
	public static Status RecoverPatient(Patient pt)
	{
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		
		querystr="UPDATE patient "
				+ "SET ptIsRegistered= ? "
				+ "WHERE ptID= ? ";
		
		try 
		{
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			setSql(querystr, Status.IS_REG.toString(), pt.getpID());
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          return Status.FAILED_EXCEPTION;
        }
		
		return Status.IS_REG;

	}
	
	/**
	 * Cancel all appointments.
	 *
	 * @param pt the pt
	 * @return the status
	 */
	public static Status CANCEL_ALL_APPOINTMENTS(Patient pt)
	{
		
		String querystr,querystr_b;
		PreparedStatement stmt = null; 
		ResultSet result = null;
			
		querystr="UPDATE appointmentsettings "
				+ "SET apsStatus= ? "
				+ "WHERE apsPtID= ? AND apsStatus= ? ";
		
		querystr_b="UPDATE labsettings "
				+ "SET labStatus= ? "
				+ "WHERE labPtID= ? AND labStatus= ? ";
		
		try 
		{
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			setSql(querystr, Status.CANCELED.toString(), pt.getpID(), Status.SCHEDUELD.toString());
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr_b);
			setSql(querystr_b, Status.CANCELED.toString(), pt.getpID(), Status.SCHEDUELD.toString());
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          return Status.FAILED_EXCEPTION;
        }
		
		return Status.CANCEL_ALL;

	}

}
