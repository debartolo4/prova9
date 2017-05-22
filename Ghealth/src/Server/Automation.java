package Server;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;

import enums.DoctorSpeciallity;
import enums.Status;
import models.AppointmentSettings;
import models.Clinic;
import models.Doctor;
import models.Patient;

/**
 * @author G5 lab group
 * 	Controls the automated tasks.
 */
public class Automation extends TimerTask{
	
	/** The timer. */
	private Timer timer = new Timer();
	
	/** The timer2. */
	private Timer timer2 = new Timer();
	
	/** The not lst. */
	//public static Email mail= new Email();
	public static List<Notification> notLst = new ArrayList<Notification>();
	
	/** The result. */
	public ResultSet result = null;
	
	/** The result2. */
	public ResultSet result2 = null;
	
	/** The stmt. */
	public PreparedStatement stmt; 
	
	/** The querystr. */
	String querystr;
	
	/** The as. */
	AppointmentSettings as;
	
	/** The doctor. */
	Doctor doctor;
	
	/** The c. */
	Calendar c = new GregorianCalendar();
	
	/** The cal. */
	Calendar cal = new GregorianCalendar();
	
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	public void run(){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			/** 
			 * here we can enter a starting date to schedule mailing at a certain hour
			 * if there is no future date it will start immediately and repeat with the given value
			 */
			Date startDate = dateFormatter.parse("2016-06-04 08:00:00");
			/**Execute automatic periodical notifications*/
			timer.schedule(new PeriodicNotification(),startDate, 24 * 60 * 60 * 1000);  //Every 24 hours at 8AM
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		/**
		 * Execute automatic periodical reports
		 */
			timer2.schedule(new PeriodicReport(), 0);
	}
	
	//-------------------------------------------------------------------------------------
	/**
	 * Automatic periodical reports
	 */
	public class PeriodicReport extends Automation{
		public void run(){
			
			/** Setting all the patients that didn't show up to noshow status */
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar yesterday = Calendar.getInstance();
			yesterday.add(Calendar.DATE, -1);
			String yesterday_s = formatter.format(yesterday.getTime());
			String querystr = ""
					+ "SELECT apsID FROM ghealth.appointmentsettings "
					+ "WHERE apsDate= ? AND apsStatus= ? ";
			result2=getSql(querystr,yesterday_s, Status.SCHEDUELD.toString());

				try {
					while (result2.next())
					{
						String updatestr = ""
								+ "UPDATE appointmentsettings SET apsStatus= ? "
								+ "WHERE apsID= ? ";
						setSql(updatestr, Status.NOSHOW.toString(), result2.getString(1));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			/*                                                                */
			/** Getting weekly reports ready */	
			SCweeklyReports rep = SCweeklyReports.getInstance();
			rep.createAllClinicsWeeklyReports();
			
			timer.schedule(new PeriodicReport(), 24 * 60 * 60 * 1000); // every day for the past week
		}
	}
	
	//-------------------------------------------------------------------------------------
	
	/**
	 * Automatic periodical notifications
	 */
	public class PeriodicNotification extends Automation{
		public void run(){
			/* TODO Checks for changed status appointment notifications */

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar tommorow = Calendar.getInstance();
			tommorow.add(Calendar.DATE, 1);
			String tommorow_b = formatter.format(tommorow.getTime());

			//-------------------------------------------------------------------------------------
			
			String[] app = { "patient.ptID", "apsDocID", "ucID", "uID" };
			
			querystr = "SELECT  apsID,apsPtID,apsDate,apsTime,apsCreateDate,apsCreateTime,apsStatus,apsDocID,uFirstName,uLastName,cID,cName,cLocation,dSpeciality,ptEmail,ptFirstName,ptLastName "
					+ "FROM appointmentsettings,user,clinic,doctor,patient "
					+ "WHERE apsPtID= ? AND apsStatus= ? AND uID= ? AND cID= ? AND dID= ? AND apsDate= ? ";//SELECT mother fucker appointments";

			result=getSql(querystr, app[0], Status.SCHEDUELD.toString(), app[1], app[2], app[3], tommorow_b);
			
			/*  -------------parsing---------------  */
			try {
				while (result.next())
				{
					/* -------------parsing tuple--------------- */ 
					Status st =  Status.valueOf(result.getString(7));
					as = new AppointmentSettings(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),
					result.getString(5),result.getString(6),st,result.getString("apsDocID"));
					Patient pt= new Patient();
					pt.setPtEmail(result.getString("ptEmail"));
					Clinic clinic = new Clinic(result.getInt("cID"),result.getString("cName"),result.getString("cLocation"));
					DoctorSpeciallity ds = DoctorSpeciallity.valueOf(result.getString("dSpeciality"));
					doctor = new Doctor(result.getString("apsDocID"),result.getString("uFirstName"),result.getString("uLastName"),clinic,ds);
					as.setDoctor(doctor);
					/* -------------end of parsing--------------- */ 
					
					/* Preparing notification object */
					Notification nt = new Notification();
					Date dt = formatter.parse(as.getApsDate());
					nt.date = dt;
					nt.ptName = result.getString("ptFirstName") +" "+ result.getString("ptLastName");
					nt.sdate = as.getApsDate();
					nt.time = as.getApsTime();
					nt.docName="Dr. " + doctor.getuLastName() + " " + doctor.getuFirstName();
					nt.location=clinic.getcLocation();
					nt.mail=pt.getPtEmail();
					nt.appSummery = "none";
					/* Preparing notification object - end */
					
					//-------------------------------------------------------------------------------------
					/* checking if this is todays notification */
					/*
					//c.getTime();
					cal.setTime(dt);
					int diffrence = cal.get(Calendar.DAY_OF_MONTH)-c.get(Calendar.DAY_OF_MONTH);
					System.out.println("diff: "+diffrence);
					if(diffrence <= 1 && diffrence >=0)
					*/
					//-------------------------------------------------------------------------------------
					
					/** Sending mail **/
					sendMail(nt);
					/** Sending mail **/
				}
			} catch (SQLException | ParseException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			
			//-------------------------------------------------------------------------------------
			/*  trash that we'll maybe need later
			Date startDate;
			try {
				startDate = dateFormatter.parse("2016-06-04 19:14:30");
			//	timer.schedule(new PeriodicNotification(), startDate); // Every 24 hours at 8AM
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			//-------------------------------------------------------------------------------------
		}
		/** 
		 * Sending mail function 
		 * @param nt
		 */
		private void sendMail(Notification nt) {
			// TODO Auto-generated method stub
			try {
				Email.generateAndSendEmail(nt);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		

		//-------------------------------------------------------------------------------------
		
		/**
		 * Searching if user in session
		 * @param mail
		 * @return
		 */
		public boolean searchUserSession(String mail){
	    	for(Notification notf: notLst) {
	    	    if(notf.mail.equals(mail))
	    	       return true;
	    	}
	    	return false;
	    }
	}
	
	
	//-------------------------------------------------------------------------------------

	/**
	 * Assegna i valori passati come parametri allo statements.
	 * 
	 * @param statement
	 * @param fields
	 * @throws SQLException
	 */
	private void assignValues(PreparedStatement statement, Object... fields) throws SQLException {
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
	public ResultSet getSql(String query) {
		// Statement stmt;
		ResultSet result = null;
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			result = stmt.executeQuery();
			// mysqlConnection.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Getting SQL query results back from DB with params
	 * 
	 * @param query
	 */
	public ResultSet getSql(String query, Object... fields) {
		// Statement stmt;
		ResultSet result = null;
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			assignValues(stmt, fields);
			result = stmt.executeQuery();
			// mysqlConnection.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Sending SQL query.
	 *
	 * @param query
	 *            the new SQL query
	 */
	public void setSql(String query) {
		// Statement stmt;
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			stmt.executeUpdate();
			// mysqlConnection.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	
	/**
	 * Sending SQL query with params.
	 *
	 * @param query
	 *            the new SQL query
	 */
	public void setSql(String query, Object...fields) {
		// Statement stmt;
		try {
			stmt = mysqlConnection.autoConn.getDbConnession().prepareStatement(query);
			assignValues(stmt, fields);
			stmt.executeUpdate();
			// mysqlConnection.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
}
