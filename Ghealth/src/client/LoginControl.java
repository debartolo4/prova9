package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import GUI.CM_GUI;
import GUI.CS_GUI_findPatient;
import GUI.DoctorGUI;
import GUI.GM_GUI;
import GUI.LabGUI;
import GUI.LoginGUI;
import enums.Status;
import enums.task;
import models.Clinic;
import models.Envelope;
import models.User;


/**
 * 
 * The Class LoginControl.
 * @author G5 lab group
 */
public class LoginControl {
	
	/** The login g. */
	private LoginGUI loginG;
	
	/** The User login. */
	private User UserLogin;
	
	/** The user_full_name. */
	private static String user_full_name;
	
	/** The u id. */
	private static String uId;
	
	/** The clinic. */
	private static Clinic clinic;
	//private User user;
	//private User U;
	
	static {
		user_full_name = "";
		uId = "";
		clinic = new Clinic();
	}
	
	/**
	 * constractor.
	 *
	 * @param lC the l c
	 */
	public LoginControl (LoginGUI lC )
	{
		loginG = lC;
		UserLogin = new User();
		loginG.addLoginActionListener(new LoginListener());
		//loginG.addCancelActionListener(new CancelListener());	
	}

    /**
     * Gets the user_full_name.
     *
     * @return the user_full_name
     */
    public static String getUser_full_name() {
		return user_full_name;
	}

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public static String getUserId() {
		return uId;
	}
    
    /**
     * Gets the user clinic.
     *
     * @return the user clinic
     */
    public static Clinic getUserClinic() {
		return clinic;
	}

	/**
	 * Sets the user_full_name.
	 *
	 * @param user_full_name the new user_full_name
	 */
	public static void setUser_full_name(String user_full_name) {
		LoginControl.user_full_name = user_full_name;
	}

	/**
	 * The listener interface for receiving cancel events.
	 * closing the windows
	 */
	class CancelListener implements ActionListener 
    {
    	
	    @Override
    	public void actionPerformed(ActionEvent e)
    	{
    	loginG.dispose();	//Closes the login window
    	}	
    }//action



    /**
     * The listener interface for receiving login events.
     * trying to login with the username and password strings
     * 
     */
    class LoginListener implements ActionListener
    {
         
         /* (non-Javadoc)
          * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
          */
         public void actionPerformed(ActionEvent ev)
         {      		
        	 String pass = loginG.getPasswordField();	//Gets the password the user entered
        	 String user = loginG.getUserField();		//Gets the user name the user entered
        	 
        	
        	 if(pass.equals("")|| user.equals("")) 		//If fields are empty , show error message
        	 {
        		 JOptionPane.showMessageDialog(null,"Empty Fields!","Error", JOptionPane.INFORMATION_MESSAGE);    
        		 return;								//return to the login window
        	 }//if
        	 else
        	 {
        		 try
        		 {										 //set the user name and password and send to server
        		   UserLogin.setuPassword(pass);
        		   UserLogin.setuID(user);
        		   
        		   Envelope en = Controller.Control(UserLogin,task.GET_USER);
        		   User us = (User)en.getSingleObject();
        		   
        		   uId = us.getuID();
        		   clinic = us.getuClinic();
        		   
        		   //if(us.getuID() != null && !us.getuID().equals("0"))
        		   if(en.getStatus()!=Status.IN_SESSION)
        		   {
        			   setUser_full_name(us.getuFirstName()+" "+us.getuLastName());
	        		   if(UserLogin.getuPassword().equals(us.getuPassword()))
	        		   {
	        		   	   loginG.dispose();	//Closes the login window
	        		   	   
	        		   	   getRoleMethod(us);
	        		   	   
	        		   }
	        		   else
	        		   { 
	        			   JOptionPane.showMessageDialog(null,"Pass not match!!!!","Error", JOptionPane.INFORMATION_MESSAGE);
	        		   }
        		   }
        		   else if (en.getStatus()==Status.IN_SESSION)
        			   JOptionPane.showMessageDialog(null,"User is in another session!","Error", JOptionPane.INFORMATION_MESSAGE);
        		   else
        			   JOptionPane.showMessageDialog(null,"No such User!!!!","Error", JOptionPane.INFORMATION_MESSAGE); 
        		   
        		 }
        		 catch(Exception e)
        		 {	
        			 JOptionPane.showMessageDialog(null,e.getMessage());
        		}
    	      }//else
          }

		private void getRoleMethod(User us) {
			switch(us.getuRole())
			   {
			   	   case CUSTOMER_SERVICE:
			   		   CS_GUI_findPatient cs = new CS_GUI_findPatient();
			   		   PatientControl pt = new PatientControl(cs);
			   		//TODO: open the next window (menu).
			   		   break;
			   	   case LAB_WORKER:
				   		LabController lb = new LabController(new LabGUI(),us.getuID());
			   		//TODO: open the next window (menu).
			   		   break;
			   	   case CLINIC_MANAGER:
				   		ClinicManagerController CM_ctrl = new ClinicManagerController(new CM_GUI(),us.getuID());
			   		//TODO: open the next window (menu).
			   		   break;
			   	   case DOCTOR:    		   		   
				   		DoctorGUI doc_gui = new DoctorGUI();	        		   		   
				   		DoctorController docCon = new DoctorController(doc_gui,us.getuID());
				   		//TODO: open the next window (menu).
			   	   		break;
			   	   case GENERAL_MANAGER:
				   		GeneralManagerController GeneralCtrl = new GeneralManagerController(new GM_GUI(),us.getuID());
			   		//TODO: open the next window (menu).
			   		   break;
			   		   
			   		default:
			   		//TODO: open the next window (menu).
			   			break;
			   }
		}
    }//action
}
