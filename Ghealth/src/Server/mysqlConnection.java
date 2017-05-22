/**
 * TODO This is the class description
 */


package Server;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import utility.DBManager;



// TODO: Auto-generated Javadoc
/**
 * The Class mysqlConnection.
 *
 * @author G5 lab group
 * MySQL Connection Class
 */
public class mysqlConnection {
	
	/** The auto Connection. */
	public static DBManager conn,autoConn; //TODO - will be changed (maby) to private	
	
	/** The Server view. */
	private ServerGui ServerView;
	
	/** The server log view. */
	private serverLogGui serverLogView;
	
	/** The user log. */
	private  ArrayList<String> userLog;
	
	/** The user name db. */
	private static String userNameDB;
	
	/** The password db. */
	private static String passwordDB;
	
	/** The Defport. */
	private static String Defport = "5555";
	
	/** The Scheama. */
	private String Scheam = "jdbc:mysql://localhost/ghealth";
	
	/** The Server. */
	public Server sv;
	
	/** The Automation. */
	public Automation auto = null;
	
	static {
		conn = DBManager.getInstance();
		autoConn = DBManager.getInstance();
		userNameDB = "";
		passwordDB = "";
	}
	
	/**
	 * Default constructor, sets conn DB connector.
	 */
	public mysqlConnection() 
	{
		mysqlConnection.conn = DBManager.getInstance();
   	}
	
	/**
	 * constructor.
	 *
	 * @param SerGui is the start gui that open first when we open
	 * the server - need to field port user name and password of workbench
	 * @param servLog show when client connect or disconnect to server
	 */

	public mysqlConnection(ServerGui SerGui,serverLogGui servLog) 
	{
		
		ServerView = SerGui;
		serverLogView = servLog;
		ServerView.setTextFieldPass(passwordDB);
		ServerView.setTextFieldUser(userNameDB);
		ServerView.setTextFieldPort(Defport);
		ServerView.setTextFieldscheam(Scheam);
		userLog = new ArrayList<String>();
		
		ServerView.addLoginActionListener(new LoginListener());
		serverLogView.addDisconnectedBottonActionListener(new DisconnectedListener());

   	}
	
	  /**
  	 * Inner class that handles when Button Logout Pressed, implements ActiontListener.
  	 *
  	 * @see DisconnectedEvent
  	 */
	class DisconnectedListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		System.exit(0);
			
		}
	
	}
	
	/**
	 *  Inner class that handles when Button Login Pressed, implements ActiontListener.
	 *
	 * @see LoginEvent
	 */
	class LoginListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			userNameDB = ServerView.getTextUserName();
			passwordDB = ServerView.getTextPassword();
			Defport = ServerView.getTextPort();

			Scheam = ServerView.getTextScheam();
			
			if(openConnectionDB()){
				
				 //sv = new Server(port);
				 
				   try 
				    {
					  //sv.start();
				      ServerView.dispose();
				      serverLogView.setVisible(true);
				      
				    } 
				    catch (Exception ex) 
				    {
				    	 ServerView.setWarningMessageVisibleTrue("ERROR - Could not listen for clients!");
				    }
				
			}
			
			
			
		}
	
	}
	
	
	/**
	 * openConnectionDB is method that check if the open Connection to DB.
	 *
	 * @return boolean
	 */
	  public boolean openConnectionDB(){
		  
          /** Automated machine connector**/
          mysqlConnection.autoConn = DBManager.getInstance();
          
            /** Automated machine Start**/
        	auto = new Automation();
    		auto.run();
    		
          return mysqlConnection.autoConn != null ? true : false;
		  
	}
		
		/**
		 * set the new log of Employee that connect or disconnect to server.
		 *
		 * @param e1 the e1
		 * @param Task the task
		 */
	  public void SetLog(String e1, String Task){
			ZonedDateTime zonedDateTime = ZonedDateTime.now();
	        if(Task.equals("login")){
	        	userLog.add(e1);
	        	serverLogView.getTextArea().setForeground(Color.green);
	        	serverLogView.getTextArea().append(zonedDateTime.toLocalTime()+" : " +" User " + e1 + "- connected\n");	
	        }
	        if(Task.equals("loginTry")){
	        	userLog.add(e1);
	        	serverLogView.getTextArea().setForeground(Color.red);
	        	serverLogView.getTextArea().append(zonedDateTime.toLocalTime()+" : " +" User " + e1 + "- Tried to log in\n");	
	        }
		
	        
	        if(Task.equals("Register")){
	        	serverLogView.getTextArea().setForeground(Color.red);
	        	serverLogView.getTextArea().append(zonedDateTime.toLocalTime()+" : " +e1 + "\n");	
	        	userLog.remove(e1);
	        
	        }	
	        
	        
	        if(Task.equals("logout")){
	        	serverLogView.getTextArea().setForeground(Color.red);
	        	serverLogView.getTextArea().append(zonedDateTime.toLocalTime()+" : " +" User " + e1 + "- Disconnected\n");	
	        	userLog.remove(e1);
	        
	        }	
			
		}
	
	
	/**
	 * **********************************************Getters and setters**************************************.
	 *
	 * @return the server view
	 */
	public ServerGui getServerView() {
		return ServerView;
	}

	/**
	 * Sets the server view.
	 *
	 * @param serverView the new server view
	 */
	public void setServerView(ServerGui serverView) {
		ServerView = serverView;
	}

	/**
	 * Gets the server log view.
	 *
	 * @return the server log view
	 */
	public serverLogGui getServerLogView() {
		return serverLogView;
	}

	/**
	 * Sets the server log view.
	 *
	 * @param serverLogView the new server log view
	 */
	public void setServerLogView(serverLogGui serverLogView) {
		this.serverLogView = serverLogView;
	}
	
	 /**
 	 * Sets the password db.
 	 *
 	 * @param password1 the new password db
 	 */
 	public void setPasswordDB(String password1) {
			mysqlConnection.passwordDB = password1;
		}
	
	/**
	 * Sets the user name db.
	 *
	 * @param userName the new user name db
	 */
	public void setUserNameDB(String userName) {
		mysqlConnection.userNameDB = userName;
	}
	

}


