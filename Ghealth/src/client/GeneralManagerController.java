package client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import GUI.GM_GUI;
import enums.task;
import models.Envelope;


/**
 *
 * The Class GeneralManagerController.
 * @author G5 lab group
 */
public class GeneralManagerController {
      
	/** The General_ m_ gui. */
	private GM_GUI General_M_GUI;
	
	/** The gm_ id. */
	private String gm_ID;
	
	/** The choosen date from. */
	public String choosenDateFrom = null;
	
	/** The choosen date to. */
	public String choosenDateTo = null;
	
	/** The date from. */
	public Date dateFrom = null;
	
	/** The date to. */
	public Date dateTo = null;
	
	/** The clinic chosen. */
	public String clinicChosen = null;
	
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   GUI Constractors ~~~~~~~~~~~~~~~~~~~~~~~~  */

	/**
	 * Contractor for the general manager screen GUI controller.
	 *
	 * @param gm the gm GUI instance
	 * @param uID the u id
	 */
	public GeneralManagerController(GM_GUI gm,String uID)
	{
		General_M_GUI = gm;
		this.gm_ID = uID;
		General_M_GUI.weeklyActionListener(new showWeeklyListener());
		General_M_GUI.monthlyActionListener(new showMonthlyListener());
		General_M_GUI.getChoosenDateFrom().addActionListener(new SelectDateListenerFrom());
		General_M_GUI.getChoosenDateTo().addActionListener(new SelectDateListenerTo());
	}
	

	
	
	/**
	 * The Class SelectDateListenerFrom.
	 */
	class SelectDateListenerFrom implements ActionListener 
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			dateFrom = (Date)General_M_GUI.getDatePickerFrom().getModel().getValue();
			JOptionPane.showMessageDialog(null,"You chose date from: " + (dateFrom),"", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * The Class SelectDateListenerTo.
	 */
	class SelectDateListenerTo implements ActionListener 
	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dateTo = (Date)General_M_GUI.getDatePickerTo().getModel().getValue();
			JOptionPane.showMessageDialog(null,"You chose date to: " + (dateTo),"", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * The Class SendDateRange.
	 */
	class SendDateRange implements ActionListener 
	{
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			choosenDateFrom = formatter.format(dateFrom);
			choosenDateTo = formatter.format(dateTo);	
		}
	}
	
	
	/**
	 * The listener interface for receiving tables by events.
	 * selecting all the right filds
	 * befor sending request to db
	 * 
	 */
	class showWeeklyListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			clinicChosen = General_M_GUI.getClinicSel();
			if(clinicChosen==null){
				JOptionPane.showMessageDialog(null,"Please select clinic before!","", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(dateFrom==null || dateTo==null){
				JOptionPane.showMessageDialog(null,"Please select both dates!","", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(dateFrom.after(dateTo)){
				JOptionPane.showMessageDialog(null,"Please select positive range!","", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			choosenDateFrom = formatter.format(dateFrom);
			choosenDateTo = formatter.format(dateTo);	
			SendAndShow(clinicChosen,choosenDateFrom,choosenDateTo);
			
		}
	}
	
	/**
	 * The listener interface for the general manager.
	 * for choosing and filtering the table he want's to see
	 */
	class showMonthlyListener  implements ActionListener 
	{
		
		public void actionPerformed(ActionEvent e) {
			
			clinicChosen = General_M_GUI.getClinicSel();
			if(clinicChosen==null){
				JOptionPane.showMessageDialog(null,"Please select clinic before!","", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			int monthBack = (int)General_M_GUI.getMonthBoxIndex()+1;
			Calendar fromDay = Calendar.getInstance();
			Calendar toDay = Calendar.getInstance();
			fromDay.set(Calendar.MONTH,toDay.get(Calendar.MONTH)-monthBack);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			dateTo = toDay.getTime();
			dateFrom = fromDay.getTime();
			choosenDateFrom = formatter.format(dateFrom);
			choosenDateTo = formatter.format(dateTo);	
			SendAndShow(clinicChosen,choosenDateFrom,choosenDateTo);
		}
	}
	
	/**
	 * Send and show.
	 *
	 * @param clinic the clinic
	 * @param from the from
	 * @param to the to
	 */
	public void SendAndShow(String clinic , String from, String to){

		List<Object> strings_to_server = new ArrayList<Object>();
		
		strings_to_server.add(from);
		strings_to_server.add(to);
		strings_to_server.add(Character.toString(clinic.charAt(1))); //clinic id 
		
		/*-- call server --*/
		Envelope en = Controller.Control(strings_to_server,task.GET_CLINIC_CLUSTER_MONTHLY_REPORT);

		List<Object> listObj =  en.getobjList();
		
		@SuppressWarnings("unused")
		showClusterReports showRepo = new showClusterReports(listObj);
	}
	
} //PationControl

