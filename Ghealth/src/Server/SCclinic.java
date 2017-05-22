/**
 * TODO This is the class description
 */


package Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Clinic;
import models.Envelope;


/**
 * @author G5 lab group
 * The Class SCclinic.
 */
public class SCclinic extends SCsuperclassA {


	PreparedStatement stmt = null; 
	ResultSet result = null;

	/**
	 * Gets the our clinic list.
	 *
	 * @return the envelope
	 */
	public static Envelope GetOurClinicList()
	{
		Envelope en = new Envelope();
		String querystr;
		PreparedStatement stmt = null; 
		ResultSet result = null;
		
		querystr="SELECT * "
				+ "FROM clinic";
		
		try 
		{
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(querystr);
			
			result = getSql(querystr);
			while (result.next())
            {
				en.addobjList(new Clinic(result.getInt(1),result.getString(2),result.getString(3)));
				
            }   
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          
         
        }
		return en;
		
	}

}
