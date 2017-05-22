/**
 * TODO This is the class description
 */


package Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enums.Roles;
import enums.Status;
import models.Clinic;
import models.Envelope;
import models.User;

/**
 * @author G5 lab group
 * The Class SCuser.
 */
public class SCuser extends SCsuperclassA {

	PreparedStatement stmt; 
	ResultSet result = null;
	
	/**
	 * Gets the exist user.
	 *
	 * @param uID the u id
	 * @return the envelope
	 */
	public static Envelope GetExistUser(String uID)
	{
		int rowCount=0;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		String querystr;
		User us = null;
		Envelope en = new Envelope();
		Clinic cl = new Clinic();
		/* Return patient row if exist */
		querystr="SELECT *"
				+ "FROM user u,clinic c "
				+ "WHERE u.ucID = c.cID AND u.uID = ?;";
		try 
		{
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(querystr);
			result = getSql(querystr, uID);
			
			result.last();
			rowCount = result.getRow();
			result.first();
			
			if(rowCount == 1)
			{
				/* Get & Create the exist user from DB */
				us = new User();
				us.setuID(result.getString("uID"));
				us.setuPassword(result.getString("uPassword"));
				us.setuFirstName(result.getString("uFirstName"));
				us.setuLastName(result.getString("uLastName"));
				us.setuEmail(result.getString("uEmail"));
				
				cl.setcID(result.getInt("ucID"));
				cl.setcLocation(result.getString("cLocation"));
				cl.setcName(result.getString("cName"));
				us.setuClinic(cl);
				
				String temp124=result.getString("role");
				us.setuRole(Roles.valueOf(temp124));
				
				en.addobjList(us);
				
				en.setStatus(Status.EXIST);
			}else{
				
				en.setStatus(Status.NOT_EXIST);
				us = new User(null,null,null,null,null,null,null);
				en.addobjList(us);
			}
			
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          return null;
        }
		
		return en;
	}
	
}
