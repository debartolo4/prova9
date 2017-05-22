package client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import GUI.CS_GUI_Appoint;
import GUI.CS_GUI_addPatient;
import GUI.CS_GUI_findPatient;
import enums.Status;
import enums.task;
import models.Envelope;
import models.Patient;


/**
 * 
 * The Class PatientControl.
 * @author G5 lab group
 */
public class PatientControl {
      
	/** The cs gu i_add patient. */
	private CS_GUI_addPatient csGUI_addPatient;
	
	/** The cs gu i_find patient. */
	private CS_GUI_findPatient csGUI_findPatient;
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   GUI Constractors ~~~~~~~~~~~~~~~~~~~~~~~~  */

	/**
	 * constractor for the Adding patient screen GUI.
	 *
	 * @param cs the cs
	 * @param pID the id
	 */
	public PatientControl(CS_GUI_addPatient cs,String pID)
	{
		csGUI_addPatient = cs;
		csGUI_addPatient.addPatientActionListener(new AddPatientListener());
		csGUI_addPatient.setPationID(pID);
		csGUI_addPatient.addCancelActionListener(new cancelListener());
	}
	
	/**
	 * constractor for the find patient screen GUI.
	 *
	 * @param cs the cs
	 */
	public PatientControl(CS_GUI_findPatient cs)
	{
		csGUI_findPatient = cs;
		csGUI_findPatient.findPatientActionListener(new findPatientListener());
	}
	
	
  

	/**
	 * The listener interface for receiving addPatient events.
	 * filling the form when cs want to add patient.
	 */
	class AddPatientListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			if(csGUI_addPatient.getPationID().equals("") ||
				csGUI_addPatient.getfName().equals("") ||
				csGUI_addPatient.getlName().equals("") ||
				csGUI_addPatient.geteMail().equals(""))
			{
				JOptionPane.showMessageDialog(null,"There is Empty Fields!","Error", JOptionPane.INFORMATION_MESSAGE);    
				return;	//return to the find patient window
				
			}
			else if(csGUI_addPatient.getPhone().length() < 7)
			{
				JOptionPane.showMessageDialog(null,"Please enter real phone number ( Numbers > 7)","Error", JOptionPane.INFORMATION_MESSAGE); 
				return;	//return to the find patient window
			}
			
			Patient newpt = new Patient(csGUI_addPatient.getPationID(),
										csGUI_addPatient.getfName(),
										csGUI_addPatient.getlName(),
										csGUI_addPatient.geteMail()+"@"+csGUI_addPatient.geteMailBox().getSelectedItem(),
										csGUI_addPatient.getPhone(),
										(String)csGUI_addPatient.getClinicBox().getSelectedItem(),
										csGUI_addPatient.getDoctorID()	);
			
			Controller.Control(newpt,task.ADD_PATIENT); 
			JOptionPane.showMessageDialog(null,"The Patient: "+newpt.getpFirstName()+" "+newpt.getpLastName()
												+" Was successfully added!","Error", JOptionPane.INFORMATION_MESSAGE);
			
			csGUI_addPatient.dispose();
			CS_GUI_Appoint appoint = new CS_GUI_Appoint();
			AppointmentControl pt_appoint = new AppointmentControl(appoint,newpt);
			
		}
		
	}
	
	/**
	 * The listener interface for receiving findPatient events.
	 * when trying to find the patient in the database
	 */
	class findPatientListener  implements ActionListener 
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		if(csGUI_findPatient.getPtID().equals("") || csGUI_findPatient.getPtID().equals("Insert ID here...")) 		//If fields are empty , show error message
       	 {
       		 JOptionPane.showMessageDialog(null,"Please insert Patient ID!","Error", JOptionPane.INFORMATION_MESSAGE);    
       		 return;	//return to the find patient window
       	 }//if
		
			// This is new verison of client controller func call:
			Patient findpt = new Patient(csGUI_findPatient.getPtID());
			Envelope en = Controller.Control(findpt,task.GET_PATIENT);
			
			
			/* if Patient exist */
			if (en.getStatus() == Status.EXIST)
			{
				findpt = (Patient)en.getSingleObject();
				csGUI_findPatient.dispose();
				CS_GUI_Appoint appoint = new CS_GUI_Appoint();
				AppointmentControl pt_appoint = new AppointmentControl(appoint,findpt);
			}else if(en.getStatus() == Status.NOT_REG){		
				patientNotReg(findpt, en);	
			}
			else{
				responseMethod(findpt);
			}
			
		}

		private void responseMethod(Patient findpt) {
			int response = JOptionPane.showConfirmDialog(null, "The Patient '"+findpt.getpID()+"' Patient NOT Exists! "
					+ "\nWould you like to create new pation?","Confirm",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			    if (response == JOptionPane.NO_OPTION)   {
			    	//do something
			    } 
			    else if (response == JOptionPane.YES_OPTION) {
					csGUI_findPatient.dispose();
					PatientControl addpt_CL = new PatientControl(new CS_GUI_addPatient(),findpt.getpID());
			    } 
			    else if (response == JOptionPane.CLOSED_OPTION) {
			    	//do something
			    }
		}

		private void patientNotReg(Patient findpt, Envelope en) {
			int response = JOptionPane.showConfirmDialog(null, "The Patient '"+findpt.getpID()+"' is canceled his account! "
					+ "\nWould you like to restore?","Confirm",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			    if (response == JOptionPane.NO_OPTION)   {
			    	//do something
			    } 
			    else if (response == JOptionPane.YES_OPTION) {
					
					findpt = (Patient)en.getSingleObject();
					csGUI_findPatient.dispose();
					CS_GUI_Appoint appoint = new CS_GUI_Appoint();
					AppointmentControl pt_appoint = new AppointmentControl(appoint,findpt);
					
					en = Controller.Control(findpt,task.RECOVER_PATIENT_REGISTRATION);
			    } 
			    else if (response == JOptionPane.CLOSED_OPTION) {
			    	//do something
			    }
		}
		
	}
	
	
	
	/**
	 * The listener interface for receiving cancel events.
	 * closing the current windows.
	 */
	class cancelListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			csGUI_addPatient.dispose();
			CS_GUI_findPatient cs = new CS_GUI_findPatient();
	   		PatientControl pt = new PatientControl(cs);
		}
		
	}
	
	
} //PationControl

