package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * The Class SCweeklyReports.
 */
public class SCweeklyReports {
	
	/** The instance. */
	private SCweeklyReports instance = null;
	
	/** The all reports. */
	private List<Object> allReports;

	
	
	/**
	 * Instantiates a new s cweekly reports.
	 */
	private SCweeklyReports(){	
		// Exists only to defeat instantiation.
	}
	
	
	 /**
 	 * Gets the single instance of SCweeklyReports.
 	 *
 	 * @return single instance of SCweeklyReports
 	 */
 	public static SCweeklyReports getInstance() {
 		SCweeklyReports instance = null;  
 		if(instance == null) {
	         instance = new SCweeklyReports();
	      }
	      return instance;
	   }
	
	 
	 
	/**
	 * Creates the all clinics weekly reports.
	 */
	//for automation 
	@SuppressWarnings("static-access")
	public void createAllClinicsWeeklyReports(){
	
		this.allReports = new ArrayList<Object>();
		ResultSet result = null;
		Statement stmt;
		String querystr = "SELECT C.cID FROM clinic C"; //all clinics 
		int cID;
		
		try {
			mysqlConnection ms = new mysqlConnection();
			stmt = ms.conn.getDbConnession().createStatement();

			result = stmt.executeQuery(querystr);
			
			/*-- create weekly report for every clinic --*/
			while(result.next())
			{
				cID = result.getInt(1);
				createReport(cID);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
			
		
	} //end of createAllWeeklyReports
	
	
	/**
	 * Creates the report.
	 *
	 * @param clinicID the clinic id
	 */
	@SuppressWarnings("static-access")
	private void createReport(int clinicID){
		
		List<String[]> weeklyData = new ArrayList<String[]>();
		ResultSet result = null;
		Statement stmt;
		

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		

		today.add(Calendar.DATE, -1);
		String to_date_str = formatter.format(today.getTime());
		today.add(Calendar.DATE, -7);
		String from_date_str = formatter.format(today.getTime());
		
		String query1 = ""
				+ "Create or replace view TrypA as "
				+ "(SELECT * "
				+ "  from appointmentsettings A "
				+ "  where A.apsstatus = 'ARRIVED');";
		
		String query2 = ""
				+ "DROP TABLE IF EXISTS calendar; ";
		String query3 = ""
				+ "CREATE TABLE IF NOT EXISTS calendar(calendar_date DATE NOT NULL PRIMARY KEY); ";
		String query4 = ""
				+ "CALL filldates('"+from_date_str+"','"+to_date_str+"');";
		
		String query5 = ""
				+ "Create or replace view Vweeklyrep as "
				+ "(SELECT calendar_date AS apsDate, "
				+ "		AVG(DATEDIFF(A.apsDate,A.apsCreateDate)) as AvgProcessTime, "
				+ "		AVG(ltrim(timediff(A.apsStartTime, A.apsTime))) as AvgWaitingTime, "
				+ "        Count(DISTINCT A.apsptid) AS NumOfPatients "
				+ "FROM   TrypA A RIGHT OUTER JOIN calendar ON A.apsdate = calendar.calendar_date "
				+ "WHERE  A.apsstatus IS NULL OR (A.apsstatus = 'ARRIVED' AND "
				+ "       A.apsdate >= '"+from_date_str+"' AND "
				+ "       A.apsdate <= '"+to_date_str+"' and "
				+ "       A.apsDocID in (SELECT doc.uID FROM user doc where doc.ucID='" + clinicID
				+ "')) "
				+ "       group by (calendar.calendar_date));";
		

		String query6 = ""
					+ "SELECT * FROM vweeklyrep "
					+ "       UNION "
					+ "       SELECT 'Min' as Op, "
					+ "       MIN(AvgProcessTime) as AvgProcessTime, "
					+ "       MIN(AvgWaitingTime) as AvgWaitingTime, "
					+ "       Min(NumOfPatients) as NumOfPatients "
					+ "	   FROM vweeklyrep "
					+ "       UNION "
					+ "       SELECT  'Max' as Op, "
					+ "       Max(AvgProcessTime), "
					+ "       Max(AvgWaitingTime), "
					+ "       Max(NumOfPatients) "
					+ "       FROM vweeklyrep "
					+ "       UNION "
					+ "       SELECT  'Avg' as Op, "
					+ "       AVG(AvgProcessTime), "
					+ "       AVG(AvgWaitingTime), "
					+ "       AVG(NumOfPatients) "
					+ "       FROM vweeklyrep "
					+ "       UNION "
					+ "       SELECT  'SD' as Op, "
					+ "       STDDEV(AvgProcessTime), "
					+ "       STDDEV(AvgWaitingTime), "
					+ "       STDDEV(NumOfPatients) "
					+ "       FROM vweeklyrep";
		
		
		/*
		String query = "CREATE OR REPLACE VIEW TABLEWEEK AS "
				+ "SELECT  A.apsDate, "
					+ "		AVG(DATEDIFF(A.apsDate,A.apsCreateDate)) as AvgProcessTime, "
					+ "		AVG(timediff(A.apsStartTime, A.apsTime)/60) as AvgWaitingTime, "
				+ "        Count(DISTINCT A.apsptid) AS NumOfPatients "
				+ "FROM   appointmentsettings A "
				+ "WHERE   A.apsstatus = 'ARRIVED' "
				+ "       AND A.apsDate <= '" + to_date_str 
				+ "' AND A.apsDate >= '" + from_date_str
				+ "' "
				+ "       AND A.apsDocID in (SELECT doc.uID FROM user doc WHERE doc.ucID='" + clinicID
				+ "') "
				+ "GROUP BY A.apsDate; ";
		
		String query2 = ""
				+ "SELECT * FROM TABLEWEEK "
				+ "UNION "
				+ "SELECT 'Min' as Op, "
				+ "MIN(AvgProcessTime) as AvgProcessTime,MIN(AvgWaitingTime) as AvgWaitingTime,Min(NumOfPatients) as NumOfPatients "
				+ " FROM TABLEWEEK "
				+ "UNION "
				+ "SELECT  'Max' as Op, "
				+ "Max(AvgProcessTime),Max(AvgWaitingTime),Max(NumOfPatients) "
				+ " FROM TABLEWEEK "
				+ " UNION "
				+ " SELECT  'Avg' as Op, "
				+ " AVG(AvgProcessTime),AVG(AvgWaitingTime),AVG(NumOfPatients) "
				+ " FROM TABLEWEEK "
				+ " UNION "
				+ " SELECT  'SD' as Op, "
				+ " STDDEV(AvgProcessTime),STDDEV(AvgWaitingTime),STDDEV(NumOfPatients) "
				+ " FROM TABLEWEEK";
		*/
		try {
			mysqlConnection ms = new mysqlConnection();
			stmt = ms.conn.getDbConnession().createStatement();
			
			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);
			stmt.executeUpdate(query3);
			stmt.executeUpdate(query4);
			stmt.executeUpdate(query5);
			result = stmt.executeQuery(query6);
			
			
			/*-- for each day of the past week -- */
			while(result.next())
			{
				/* --get from DB the 4 fields : apsDate, NumOfPatients, AvgProcessTime, AvgWaitingTime ---*/	
				weeklyData.add(new String[]{result.getString(1), result.getString(2), result.getString(3), result.getString(4)});	
			}
			
			
			allReports.add(weeklyData);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
	}//end of createReport function 
	
	
	
	/**
	 * Gets the clinic weekly report.
	 *
	 * @param cID the c id
	 * @return the clinic weekly report
	 */
	@SuppressWarnings("unchecked")
	public Envelope getClinicWeeklyReport(int cID){
	    
		Envelope en = new Envelope();    
        List<Object> list = new ArrayList<Object>();
        
		try {	
			list = (List<Object>) this.allReports.get(cID-1);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		    en.setStatus(Status.FAILED_EXCEPTION);
		}
		
		en.setobjList(list);
		en.setStatus(Status.EXIST);		
		en.setType(task.GET_CLINIC_WEEKLY_REPORT);
		return en;
             
	}//end of getClinicWeeklyReport
	
	

	
}



