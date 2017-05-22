
package Server;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import enums.Status;
import enums.task;
import models.Envelope;


/**
 * @author G5 lab group
 * The Class SCmonthlyReports.
 */
public class SCmonthlyReports extends SCsuperclass{

	
	static PreparedStatement stmt; 
	static ResultSet result;
	
	
	/** The instance. */
	private static SCmonthlyReports instance;
	
	static {
		try {
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		result = getSql("");
		instance = new SCmonthlyReports();
	}
	
	/** The Report to env. */
	private List<Object> ReportToEnv;
	
	
	
	/**
	 * Instantiates a new s cmonthly reports.
	 */
	private SCmonthlyReports(){	
		// Exists only to defeat instantiation.
	}
	
	
	 /**
 	 * Gets the single instance of SCmonthlyReports.
 	 *
 	 * @return single instance of SCmonthlyReports
 	 */
 	public static SCmonthlyReports getInstance() {
	      if(instance == null) {
	         instance = new SCmonthlyReports();
	      }
	      return instance;
	   }
	
	 

	
	
	/**
	 * Creates the report.
	 *
	 * @param clinicID the clinic id
	 */
	private void createReport(int clinicID){
		
		
		this.ReportToEnv =  new ArrayList<Object>();
		
		

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		
		
		
		String to_date_str = formatter.format(today.getTime());
		today.add(Calendar.DATE, -29);
		
		String from_date_str = formatter.format(today.getTime());
		
		


		String query1 = ""
				+ "Create or replace view TrypA as "
				+ "(SELECT * "
				+ "  from appointmentsettings A "
				+ "  where A.apsstatus = ? );";


				String query2 = ""
				+ "Create or replace view TrypB as "
				+ "(SELECT idweeks AS weekNumar, "
				+ "        Count(DISTINCT A.apsptid) AS NumOfPatients, "
				+ "		AVG(DATEDIFF(A.apsDate,A.apsCreateDate)) as AvgProcessTime, "
				+ "		AVG(timediff(A.apsStartTime, A.apsTime)/60) as AvgWaitingTime "
				+ "FROM   TrypA A RIGHT OUTER JOIN Weeks ON WEEK(A.apsdate) = ? "
				+ "WHERE  weeks.idweeks>= week( ? ) AND "
				+ "       weeks.idweeks<=week( ? ) AND "
				+ "	   A.apsstatus IS NULL OR (A.apsstatus = ? AND "
				+ "       A.apsdate >= ? AND "
				+ "       A.apsdate <= ? ) and "
				+ "       A.apsDocID in (SELECT doc.uID FROM user doc where doc.ucID= ? ) "
				+ "       GROUP BY idweeks "
				+ "       order by idweeks);";


				String query3 = ""
				+ "Create or replace view TryLEAVEa as "
				+ "(SELECT * "
				+ "	FROM patient P "
				+ "    where P.ptIsRegistered = ? and P.ptLeaveDate IS NOT NULL);";


				String query4 = ""
				+ "Create or replace view TryLEAVEb as "
				+ "(SELECT idweeks AS weekNuml, "
				+ "   Count(Pa.ptLeaveDate) AS LeaveClients "
				+ "   FROM   TryLEAVEa Pa  RIGHT OUTER JOIN Weeks ON WEEK(Pa.ptLeaveDate) = ? "
				+ "   WHERE  weeks.idweeks>= week( ? ) AND "
				+ "       weeks.idweeks<=week( ? ) and Pa.ptLeaveDate is NULL "
				+ "   OR (Pa.ptIsRegistered = ? AND "
				+ "      Pa.ptLeaveDate >= ? AND "
				+ "      Pa.ptLeaveDate <= ? ) "
				+ "      group by idweeks "
				+ "      order by idweeks "
				+ ");";


				String query5 = ""
				+ "Create or replace view TryNOSHOWa as "
				+ "(SELECT * "
				+ "from appointmentsettings A "
				+ "where A.apsStatus =? "
				+ ");";


				String query6 = ""
				+ "Create or replace view TryNOSHOWb as "
				+ "(SELECT idweeks AS weekNumns, "
				+ "       Count(DISTINCT A.apsptid) AS NumOfNoshows "
				+ "FROM   TryNOSHOWa A RIGHT OUTER JOIN Weeks ON WEEK(A.apsdate) = ? "
				+ "WHERE  weeks.idweeks>= week( ? ) AND "
				+ "       weeks.idweeks<=week( ? ) AND "
				+ "	   A.apsstatus IS NULL OR "
				+ "       (A.apsstatus = ? AND "
				+ "       A.apsdate >= ? AND "
				+ "       A.apsdate <= ? and "
				+ "       A.apsDocID in (SELECT doc.uID FROM user doc where doc.ucID= ? )) "
				+ "GROUP BY idweeks);";


				String query7 = ""
				+ "Create or replace view NMonthlyView as "
				+ "(SELECT * "
				+ "FROM   TrypB AR,TryNOSHOWb NS, TryLEAVEb L "
				+ "WHERE  NS.weekNumns = ? AND L.weekNuml = ? AND NS.weekNumns = ? "
				+ " "
				+ ");";


				/*String query8 = ""
				+ "SELECT * FROM ghealth.NMonthlyView;";
				*/
				String query8 = ""
						+ "SELECT weekNumar as weekNum,NumOfPatients,AvgProcessTime,AvgWaitingTime,NumOfNoshows,LeaveClients "
						+ "	   FROM nmonthlyview "
						+ "       UNION "
						+ "       SELECT "
						+ "       'Min' as Op, "
						+ "       Min(NumOfPatients) as NumOfPatients, "
						+ "       MIN(AvgProcessTime) as AvgProcessTime, "
						+ "       MIN(AvgWaitingTime) as AvgWaitingTime , "
						+ "       MIN(NumOfNoshows) as NumOfNoshows, "
						+ "       MIN(LeaveClients) as LeaveClients "
						+ "	   FROM nmonthlyview "
						+ "       UNION "
						+ "       SELECT "
						+ "       'Max' as Op, "
						+ "       Max(NumOfPatients) , "
						+ "       Max(AvgProcessTime), "
						+ "       Max(AvgWaitingTime), "
						+ "	   MAX(NumOfNoshows) as NumOfNoshows, "
						+ "       MAX(LeaveClients) as LeaveClients "
						+ "       FROM nmonthlyview "
						+ "       UNION "
						+ "       SELECT "
						+ "       'Avg' as Op, "
						+ "       AVG(NumOfPatients)  , "
						+ "       AVG(AvgProcessTime), "
						+ "       AVG(AvgWaitingTime), "
						+ "	   AVG(NumOfNoshows) as NumOfNoshows, "
						+ "       AVG(LeaveClients) as LeaveClients "
						+ "       FROM nmonthlyview "
						+ "       UNION "
						+ "       SELECT "
						+ "       'SD' as Op, "
						+ "       STDDEV(NumOfPatients) , "
						+ "       STDDEV(AvgProcessTime), "
						+ "       STDDEV(AvgWaitingTime), "
						+ "	   STDDEV(NumOfNoshows) as NumOfNoshows, "
						+ "       STDDEV(LeaveClients) as LeaveClients "
						+ "       FROM nmonthlyview";
		try {
			String app = "weeks.idweeks";
			String[] app2 = { "L.weekNuml", "AR.weekNumar", "AR.weekNumar" };
			
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(query1);
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(query2);
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(query3);
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(query4);
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(query5);
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(query6);
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(query7);
			stmt = mysqlConnection.conn.getDbConnession().prepareStatement(query8);
			
			setSql(query1, Status.ARRIVED.toString());
			setSql(query2, app, from_date_str, to_date_str, Status.ARRIVED.toString(),
					from_date_str, to_date_str, clinicID);
			setSql(query3, Status.NOT_REG.toString());
			setSql(query4, app, from_date_str, to_date_str, Status.NOT_REG.toString(),
					from_date_str, to_date_str);
			setSql(query5, Status.NOSHOW.toString());
			setSql(query6, app, from_date_str, to_date_str, Status.NOSHOW.toString(),
					from_date_str, to_date_str, clinicID);
			setSql(query7, app2[0], app2[1], app2[2]);
			result = getSql(query8);
			
			
			String WN,MN;
			/*-- for each day of the past week -- */
			while(result.next())
			{
				Calendar tempDay = Calendar.getInstance();
				WN = result.getString(1);
				if(!WN.equals("Min") && !WN.equals("Max") && !WN.equals("Avg") && !WN.equals("SD"))
				{	
					tempDay.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(WN));
					int week =(Integer.parseInt(result.getString(1))%4);
					int month = ((1+Integer.parseInt(result.getString(1)))/4);
					MN = getMonthName(month);
					WN = String.valueOf(week);
				}
				else MN = " ";
				
				
				
				
				this.ReportToEnv.add(new String[]{MN,result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6) });	
				
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
	}//end of createReport function 
	
	
	
	/**
	 * Gets the clinic monthly report.
	 *
	 * @param cID the c id
	 * @return the clinic monthly report
	 */
	public Envelope getClinicMonthlyReport(int cID){
	    
		Envelope en = new Envelope();    
	
	           
        this.createReport(cID);
        
		try {
			en.setobjList(this.ReportToEnv);
			en.setStatus(Status.EXIST);		
			en.setType(task.GET_CLINIC_MONTHLY_REPORT);
		
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		    en.setStatus(Status.FAILED_EXCEPTION);
		}
		
		return en;
             
	}//end of getClinicMonthlyReport

	
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
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			result = stmt.executeQuery();
			// mysqlConnection.conn.close();
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
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			assignValues(stmt, fields);
			result = stmt.executeQuery();
			// mysqlConnection.conn.close();
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
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			stmt.executeUpdate();
			// mysqlConnection.conn.close();
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
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			assignValues(stmt, fields);
			stmt.executeUpdate();
			// mysqlConnection.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}



