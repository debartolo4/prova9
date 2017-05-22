/**
 * TODO This is the class description
 */


package Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import enums.Status;
import enums.task;
import models.Clinic;
import models.Envelope;
import models.LabSettings;
import models.User;

/**
 * @author G5 lab group
 * The Class SClab.
 */
public class SClab extends SCsuperclassA {


	PreparedStatement stmt; 
	ResultSet result = null;
	
	/**
	 * Get_ scheduel d_labs.
	 *
	 * @param ptID the pt id
	 * @return the envelope
	 */
	public static Envelope Get_SCHEDUELD_labs(String ptID)
	{
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		Envelope en = new Envelope();
		LabSettings ls;
		User doctor;
		Clinic cl;
		
		querystr="SELECT * "
				+ "FROM labsettings,user,clinic  "
				+ "WHERE labDocID= ? AND labPtID= ? AND labStatus= ? AND cID = ?"
				+ " ORDER BY labCreateDate DESC ";
		
		try 
		{
			String[] app = {"uID","ucID"};
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			
			result = getSql(querystr, app[0], ptID, Status.SCHEDUELD.toString(), app[1]);
			en.setStatus(Status.NOT_EXIST);
			while (result.next())
            {
				Status st =  Status.valueOf(result.getString("labStatus"));
				
				ls = new LabSettings(result.getInt("labID"),result.getString("labPtID"), result.getString("labCreateDate"), result.getString("labCreateTime"), st,
						result.getString("labDocID"), result.getString("labDocReq"));
				
				
				doctor = new User();
				doctor.setuID(result.getString("labDocID"));
				doctor.setuFirstName(result.getString("uFirstName"));
				doctor.setuLastName(result.getString("uLastName"));
				
				cl = new Clinic();
				cl.setcID(result.getInt("cID"));
				cl.setcLocation(result.getString("cLocation"));
				cl.setcName("cName");
				doctor.setuClinic(cl);
				ls.setLabWorker(doctor);
				
				en.addobjList(ls);
				en.setStatus(Status.EXIST);
            }   
			
			en.setType(task.GET_SCHEDUELD_LAB);
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          en.setStatus(Status.FAILED_EXCEPTION);
          return en;
        }
		
		return en;

	}

	
	/**
	 * Get_ arrive d_labs.
	 *
	 * @param ptID the pt id
	 * @return the envelope
	 */
	public static Envelope Get_ARRIVED_labs(String ptID)
	{
	
		String[] app = {"uID","ucID"};
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		Envelope en = new Envelope();
		LabSettings ls;
		User doctor;
		Clinic cl;
		
		querystr="SELECT * "
				+ "FROM labsettings,user,clinic  "
				+ "WHERE labDocID= ? AND labPtID= ? AND labStatus= ? AND cID = ? "
				+ " ORDER BY labCreateDate DESC";
		
		try 
		{
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			result = getSql(querystr, app[0], ptID, Status.ARRIVED.toString(), app[1]);
			en.setStatus(Status.NOT_EXIST);
			while (result.next())
            {
				Status st =  Status.valueOf(result.getString("labStatus"));
				
				ls = new LabSettings(result.getInt("labID"),result.getString("labPtID"), result.getString("labCreateDate"), result.getString("labCreateTime"), st,
						result.getString("labDocID"), result.getString("labDocReq"));
				
				
				doctor = new User();
				doctor.setuID(result.getString("labDocID"));
				doctor.setuFirstName(result.getString("uFirstName"));
				doctor.setuLastName(result.getString("uLastName"));
				
				cl = new Clinic();
				cl.setcID(result.getInt("cID"));
				cl.setcLocation(result.getString("cLocation"));
				cl.setcName("cName");
				doctor.setuClinic(cl);
				ls.setLabWorker(doctor);
				ls.setLabWorkerSummery(result.getString("labWorkerSummery"));
				
				String filePath = result.getString("labPhotoPath");
				ls.setFilePath(filePath);
				if(!filePath.equals("NO FILE"))
				{
					String extension = filePath;
				    int index=extension.indexOf(".");
				    //get the extension of the file
				    extension=extension.substring(index+1, extension.length());
				    ls.setFileExt(extension);
				}
				
				en.addobjList(ls);
				en.setStatus(Status.EXIST);
            }   
			
			en.setType(task.GET_SCHEDUELD_LAB);
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          en.setStatus(Status.FAILED_EXCEPTION);
          return en;
        }
		
		return en;

	}
	
	
	
	
	/**
	 * Update lab record.
	 *
	 * @param labID the lab id
	 * @param record the record
	 * @param labworkerID the labworker id
	 */
	public static void UpdateLabRecord(int labID,String record,String labworkerID)
	{
		
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		
		querystr="UPDATE labsettings "
				+ "SET labStatus= ?,labWorkerSummery= ?,labworkerID= ?"
				+ "WHERE labID= ? ";
		
		try 
		{
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			
			setSql(querystr,Status.ARRIVED.toString(), record, labworkerID, labID);
		
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
        }
		

	}




	/**
	 * Update lab file path.
	 *
	 * @param filename the filename
	 * @param labID the lab id
	 */
	public static void UpdateLabFilePath(String filename,int labID) {
		
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		
		querystr="UPDATE labsettings "
				+ "SET labPhotoPath= ? "
				+ "WHERE labID= ? ";
		
		try 
		{
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			setSql(querystr, filename, labID);
		
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          //return Status.FAILED_EXCEPTION;
        }
		
	}
	
	
	/**
	 * Gets the lab file path.
	 *
	 * @param labID the lab id
	 * @return the string
	 */
	public static String GetLabFilePath(int labID) {
		
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		String filePath = "";
		querystr="SELECT * labsettings "
				+ "WHERE labID= ? ";
		
		try 
		{
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			result = getSql(querystr, labID);
			result.next();
			filePath=result.getString("labPhotoPath");

		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          //return Status.FAILED_EXCEPTION;
        }
		
		return filePath;
	}

	/**
	 * Creaet lab ref.
	 *
	 * @param lb the lb
	 * @return the status
	 */
	public static Status CreaetLabRef(LabSettings lb) {
		
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		String createdDate = formatter.format(new Date());
		String createdHour = hourFormat.format(new Date());
		
		
		querystr="INSERT INTO labsettings " + " (labPtID,labCreateDate,labCreateTime,labStatus,labDocID,labDocReq) "
				+ "VALUES ('"+lb.getLabPtID()+"','"+createdDate+"','"+createdHour+"','SCHEDUELD','"+lb.getLabDocID()+"'"
				+",'"+lb.getLabDoctorReq()+"')";

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
	
}
