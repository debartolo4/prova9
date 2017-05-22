
package client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* This class represents our client side 
 * of the system communication protocol.
 * the client will be personal for every component in the program
 * and will act as a "control unit"
 */
import java.util.List;

import GUI.CM_GUI;
import enums.task;
import models.Envelope;


/**
 * 
 * The Class ClinicManagerController.
 * @author G5 lab group
 */
public class ClinicManagerController {
      
	/** The Clinic_ m_ gui. */
//	private CM_GUI Clinic_M_GUI;
	
	/** The cm_ id. */
	private String cm_ID;
	
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   GUI Constructors ~~~~~~~~~~~~~~~~~~~~~~~~  */

	/**
	 * Constructor for the Adding patient screen GUI.
	 *
	 * @param cm the cm
	 * @param uID the u id
	 */
	public ClinicManagerController(CM_GUI cm,String uID)
	{
		CM_GUI Clinic_M_GUI;
		
		Clinic_M_GUI = cm;
		Clinic_M_GUI.weeklyActionListener(new showWeeklyListener());
		Clinic_M_GUI.monthlyActionListener(new showMonthlyListener());
		this.cm_ID = uID;
	}
	

	
	

	/**
	 * The listener interface for receiving showWeekly events.
	 * The class that is interested in processing a showWeekly
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addshowWeeklyListener<code> method. When
	 * the showWeekly event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see showWeeklyEvent
	 */
	class showWeeklyListener  implements ActionListener 
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			Envelope en = Controller.Control(LoginControl.getUserClinic(),task.GET_CLINIC_WEEKLY_REPORT);
			List<Object> listObj =  en.getobjList();
			
			@SuppressWarnings("unused")
			ShowWeeklyReports showRepo = new ShowWeeklyReports(listObj);
		}
		
	}
	
	/**
	 * The listener interface for receiving showMonthly events.
	 * The class that is interested in processing a showMonthly
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addshowMonthlyListener<code> method. When
	 * the showMonthly event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see showMonthlyEvent
	 */
	class showMonthlyListener  implements ActionListener 
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			/*-- call server --*/
			Envelope en = Controller.Control(LoginControl.getUserClinic(),task.GET_CLINIC_MONTHLY_REPORT);
			List<Object> listObj =  en.getobjList();
			
			@SuppressWarnings("unused")
			ShowMonthlyReport showRepo = new ShowMonthlyReport(listObj);
			
		
		}
		
	}
	
	
} //ClinicManagerController

