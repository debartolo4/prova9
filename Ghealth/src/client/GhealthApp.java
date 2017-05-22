package client;

import GUI.LoggingOut;
import GUI.LoginGUI;

/**
 * The Class GhealthApp.
 *
 * @author G5 lab group
 * The Main Class That starts Ghealth Application.
 * Initializes login screen
 */
public class GhealthApp {

	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		LoggingOut lo = new LoggingOut();
		LoginGUI login1 = new LoginGUI();
		LoginControl userctrl = new LoginControl(login1);
		
	}

}

//logout