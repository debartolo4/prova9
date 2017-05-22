package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import GUI.CS_GUI_Appoint;
import GUI.CS_GUI_cancelAppoint;
import GUI.CS_GUI_findPatient;
import GUI.CS_GUI_newAppoint;
import enums.DoctorSpeciallity;
import enums.Status;
import enums.task;
import models.AppointmentSettings;
import models.Envelope;
import models.Patient;
import models.User;

/**
 * 
 * The Class AppointmentControl.
 * @author G5 lab group
 */
public class AppointmentControl {

//	private CS_GUI_findPatient csGUI_findPt;	
	private CS_GUI_Appoint csGUI_Appoint;	
	private CS_GUI_newAppoint csGUI_CreateNewAppoint;	
	private CS_GUI_cancelAppoint csGUI_cancelAppoint;	
	private Patient pt;	
	private AppointmentSettings as;	
//	private PatientControl ptCtrl;
	
	private List<Object> objList_str;
	
	/**
	 * Instantiates a new appointment control.
	 *
	 * @param cs the costumer service GUI windows
	 * @param pt the patients referance.
	 */
	public AppointmentControl(CS_GUI_newAppoint cs,Patient pt)
	{
		this.pt=pt;
		as = new AppointmentSettings();
		as.setApsPtID(pt.getpID());
		as.setApsStatus(Status.SCHEDUELD);
		csGUI_CreateNewAppoint = cs;
		csGUI_CreateNewAppoint.SetPatient(pt);
		csGUI_CreateNewAppoint.SelectDocTypeActionListener(new SelectDoctorTypeListener());
		csGUI_CreateNewAppoint.DoctorBoxActionListener(new SelectClnicAndDoctor());
		csGUI_CreateNewAppoint.DoctorHoursBoxActionListener(new SelectHour());
		csGUI_CreateNewAppoint.getChoosenDateOK().addActionListener(new SelectDateListener());
		csGUI_CreateNewAppoint.getbtnCrtAppoint().addActionListener(new InsertNewAppointToDBListener());
		
		
	}
	
	/**
	 * constractor for the find patient screen GUI.
	 *
	 * @param cs the costumer service GUI windows
	 * @param pt the patients referance.
	 */
	public AppointmentControl(CS_GUI_Appoint cs,Patient pt)
	{
		this.pt = pt;
		
		csGUI_Appoint = cs;
		csGUI_Appoint.SetPatient(pt);
		csGUI_Appoint.createAppointActionListener(new createNewAppointListener());
		csGUI_Appoint.SearchPatientActionListener(new SearchPatientListener());
		csGUI_Appoint.cancelAppointActionListener(new CancelAppointListener());
		csGUI_Appoint.UncreatePatientActionListener(new UncreatePatientListener());
	}
	
	
	

	/**
	 * Gets the open appointments.
	 *
	 * @param ptID the patient id
	 * @return the list
	 */
	public List<String> GET_OPEN_APPOINTMENTS(String ptID)
	{
		
		Envelope en = Controller.Control(new Patient(ptID),task.GET_OPEN_APPOINTMENTS);
		List<String> strList = new ArrayList<String>();
		objList_str = en.getobjList();
		
		if(en.getStatus() == Status.NOT_EXIST)
		{
			return null;
		}
		for (Object obj : en.getobjList())
		{
			strList.add(((AppointmentSettings)obj).toStringCancelAppoint());
		}
				
		return strList;
	}
	
	/**
	 * Gets the doctor clinic.
	 *
	 * @param ptID the patient id
	 * @param ds the patient
	 * @return the list
	 */
	public List<String> GET_DOCTOR_CLINIC(String ptID,DoctorSpeciallity ds)
	{
		/* GET_DOCTORS_IN_CLINIC_BY_TYPE */

		List<Object> objList = new ArrayList<Object>();
		objList_str = new ArrayList<Object>();
		
		objList.add(ptID);
		objList.add(ds);
		Envelope en = Controller.Control(objList,task.GET_DOCTORS_IN_CLINIC_BY_TYPE);
		if(en.getStatus() == Status.SCHEDUELD)
		{
			return null;
		}
		List<String> strList = new ArrayList<String>();
		for (Object obj : en.getobjList())
		{
			objList_str.add(((User)obj).getuID());
			strList.add(((User)obj).toStringClinicList());
		}
		
		return strList;
	}
	
	/**
	 * Gets the doctor hours.
	 *
	 * @param ptID the pt id
	 * @param date the date
	 * @return the list
	 */
	public List<String> GET_DOCTOR_HOURS(String ptID,String date)
	{
		/* GET_AVAILIBLE_DOCTOR_HOURS */
		Envelope en;
		List<Object> objList = new ArrayList<Object>();
		objList.add(date);
		objList.add(ptID);
		en = Controller.Control(objList,task.GET_AVAILIBLE_DOCTOR_HOURS);
		List<String> strList = new ArrayList<String>();
		for (Object obj : en.getobjList())
		{
			strList.add(obj.toString());
		}
		
		return strList;
	}
	
	
	/**
	 * The listener interface for receiving createNewAppoint events.
	 * The class that is interested in processing a createNewAppoint
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addcreateNewAppointListener<code> method. When
	 * the createNewAppoint event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see createNewAppointEvent
	 */
	class createNewAppointListener  implements ActionListener 
	{

		/**
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			CS_GUI_newAppoint newApp_gui = new CS_GUI_newAppoint();
			AppointmentControl newApp_ctrl = new AppointmentControl(newApp_gui,pt);
			

		}
		
	}
	
	/**
	 *
	 */
	class InsertNewAppointToDBListener  implements ActionListener 
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			if(as.getApsDocID() == null)
			{
				JOptionPane.showMessageDialog(null,"Please select doctor before you try to create appointment!!","Can't create the appointment!", JOptionPane.INFORMATION_MESSAGE);
				return;	//return to the find patient window
			}
			else if(as.getApsDate() == null)
			{
				JOptionPane.showMessageDialog(null,"Please select date before you try to create appointment!!","Can't create the appointment!", JOptionPane.INFORMATION_MESSAGE);
				return;	//return to the find patient window
			}
			if(as.getApsTime() == null)
			{
				JOptionPane.showMessageDialog(null,"Please select hour before you try to create appointment!!","Can't create the appointment!", JOptionPane.INFORMATION_MESSAGE);
				return;	//return to the find patient window
			}
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
			String createdDate = formatter.format(new Date());
			String createdHour = hourFormat.format(new Date());
			
			
			as.setCreateDate(createdDate);
			as.setCreateTime(createdHour);
			
			Envelope en = Controller.Control(as,task.CREATE_NEW_APPOINTMENT);
			if(en.getStatus() == Status.CREATED)
				JOptionPane.showMessageDialog(null,"Appointment was created succesfully!"
						+ "\nAppointment Date & Time: "+as.getApsDate()+" "+as.getApsTime()
						+ "\nPress OK to close new appointment window","Appointment Created!", JOptionPane.INFORMATION_MESSAGE);

			csGUI_CreateNewAppoint.dispose();
			
		}
		
	} //InsertNewAppointToDBListener
	
	
		
	
	
	/**
	 * The listener interface for receiving selectDoctorType events.
	 * The class that is interested in processing a selectDoctorType
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSelectDoctorTypeListener<code> method. When
	 * the selectDoctorType event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SelectDoctorTypeEvent
	 */
	class SelectDoctorTypeListener  implements ActionListener 
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String TypeSelected=csGUI_CreateNewAppoint.getDoctorTypeBox().getSelectedItem().toString();
					
			 
			DoctorSpeciallity ds = DoctorSpeciallity.valueOf(TypeSelected);
			List<String> objList = GET_DOCTOR_CLINIC(pt.getpID(),ds);
			/* if there is SCHEDUELD appointment to this doctor type.*/
			if(objList == null)
			{
				
				/* Hide "Doctor & Clinic Box" Combo box and label */
				csGUI_CreateNewAppoint.getDoctor_and_ClinicBox().setVisible(false);
				csGUI_CreateNewAppoint.getLblDoctor_and_Clinic().setVisible(false);
				/* Hide "Doctor Hours Box" Combo box and label */
				csGUI_CreateNewAppoint.getDoctorHoursBox().setVisible(false);
				csGUI_CreateNewAppoint.getLblDoctorHours().setVisible(false);
				/* Hide Calender */
				csGUI_CreateNewAppoint.getCal().setVisible(false);

				JOptionPane.showMessageDialog(null,"There is SCHEDUELD appointment to "+TypeSelected+" for patient: "+pt.getpFirstName()+" "+pt.getpLastName()
				+",\nPlease choose another type or cancel this appointment before creating new!","Can't make appointment", JOptionPane.INFORMATION_MESSAGE);
				return;	//return to the find patient window
			}
			else
			{
				csGUI_CreateNewAppoint.getDoctor_and_ClinicBox().setModel(new DefaultComboBoxModel(objList.toArray()));

				/* setVisible "Doctor & Clinic Box" Combo box and label */
				csGUI_CreateNewAppoint.getDoctor_and_ClinicBox().setVisible(true);
				csGUI_CreateNewAppoint.getLblDoctor_and_Clinic().setVisible(true);
				/* hide  Hours */
				csGUI_CreateNewAppoint.getDoctorHoursBox().setVisible(false);
				csGUI_CreateNewAppoint.getLblDoctorHours().setVisible(false);
				/* Hide Calender */
				csGUI_CreateNewAppoint.getCal().setVisible(false);
			}
		}
	}//SelectDoctorTypeListener
	
	/**
	 * The Class SelectClnicAndDoctor.
	 */
	class SelectClnicAndDoctor  implements ActionListener 
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			csGUI_CreateNewAppoint.getCal().setVisible(true);
			/* Hide "Doctor Hours Box" Combo box and label */
			csGUI_CreateNewAppoint.getDoctorHoursBox().setVisible(false);
			csGUI_CreateNewAppoint.getLblDoctorHours().setVisible(false);
			
			/* Set Selected Doctor ID to Appointment Settings. */
			int SelectedIndex = csGUI_CreateNewAppoint.getDoctor_and_ClinicBox().getSelectedIndex();
			as.setApsDocID(objList_str.get(SelectedIndex).toString());
			
		}
		
	}//SelectClnicAndDoctor
	
	
	
	
	/**
	 * The listener interface for receiving cancelAppoint events.
	 * The class that is interested in processing a cancelAppoint
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addCancelAppointListener<code> method. When
	 * the cancelAppoint event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see CancelAppointEvent
	 */
	class CancelAppointListener  implements ActionListener 
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{

			List<String> objList = GET_OPEN_APPOINTMENTS(pt.getpID());
			if(objList == null)
			{
				JOptionPane.showMessageDialog(null,"There is no open appointment to cancel for "+pt.getpFirstName()+" "+pt.getpLastName()+"!!","No Open Appointment", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			csGUI_cancelAppoint = new CS_GUI_cancelAppoint();
			csGUI_cancelAppoint.getcomboBox().setModel(new DefaultComboBoxModel(objList.toArray()));
			csGUI_cancelAppoint.SetPatient(pt);
			csGUI_cancelAppoint.getbtnCancelAppointment().addActionListener(new cancelAppointmentFromDB());
		
		}
		
	}//CancelAppintListener
	
	
	/**
	 * The Class cancelAppointmentFromDB.
	 */
	class cancelAppointmentFromDB  implements ActionListener 
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//csGUI_cancelAppoint.getcomboBox().getSelectedItem();
			int selectedIndex = csGUI_cancelAppoint.getcomboBox().getSelectedIndex();
			as = (AppointmentSettings) objList_str.get(selectedIndex);
			Envelope en = Controller.Control(as,task.CANCEL_APPOINTMENT_FROM_DB);
			if(en.getStatus() == Status.CANCELED)
			{
				JOptionPane.showMessageDialog(null,"Appointment is canceled!\n"
						+ as,"Appointment Canceled!", JOptionPane.INFORMATION_MESSAGE);
				csGUI_cancelAppoint.dispose();
			}
		}
		
	}//CancelAppintListener
	
	

	/**
	 * The listener interface for receiving uncreatePatient events.
	 * The class that is interested in processing a uncreatePatient
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addUncreatePatientListener<code> method. When
	 * the uncreatePatient event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @author P34w
	 */
	class UncreatePatientListener  implements ActionListener 
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			CS_GUI_findPatient csGUI_findPt;	
			PatientControl ptCtrl;
			
			JOptionPane.showMessageDialog(null,"You about to cancel all appointments of "+pt.getpFirstName()+" "+pt.getpLastName()+"!!","Attention!", JOptionPane.INFORMATION_MESSAGE);
			
			int confirm = JOptionPane.showOptionDialog(
		             null, "You are about to UnRegister "+pt.getpFirstName()+"\nAre you sure?", 
		             "UnRegistration Confirmation", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) 
		        {
					List<String> objList = GET_OPEN_APPOINTMENTS(pt.getpID());
					Envelope en = Controller.Control(pt,task.CANCEL_PATIENT_REGISTRATION);
					
					if(en.getStatus() == Status.NOT_REG)
					{
						JOptionPane.showMessageDialog(null,"all Appointments are canceled!\n","Attention!", JOptionPane.INFORMATION_MESSAGE);
						
					}
					
					en = Controller.Control(pt,task.CANCEL_ALL_PATIENT_APPOINTMENTS);
					
				
 		   		   	csGUI_findPt = new CS_GUI_findPatient();
 		   		   	ptCtrl = new PatientControl(csGUI_findPt);
					csGUI_Appoint.dispose();
					
		        	
		        }
			
			

		
		}
		
	}//CancelAppintListener
	
	
	/**
	 * The listener interface for receiving searchPatient events.
	 * The class that is interested in processing a searchPatient
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSearchPatientListener<code> method. When
	 * the searchPatient event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SearchPatientEvent
	 */
	class SearchPatientListener  implements ActionListener 
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			csGUI_Appoint.dispose();
	   		PatientControl pt = new PatientControl(new CS_GUI_findPatient());

		}
		
	}//SearchPatientListener
	
	
	/**
	 * The Class SelectHour.
	 */
	class SelectHour implements ActionListener 
    {
    	
	    /* (non-Javadoc)
	     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	     */
	    @Override
    	public void actionPerformed(ActionEvent e)
    	{
    		as.setApsTime(csGUI_CreateNewAppoint.getDoctorHoursBox().getSelectedItem().toString());
    	}	
    }//SelectHour
	

	/**
	 * The listener interface for receiving selectDate events.
	 * The class that is interested in processing a selectDate
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSelectDateListener<code> method. When
	 * the selectDate event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SelectDateEvent
	 */
	class SelectDateListener  implements ActionListener 
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Date date = (Date)csGUI_CreateNewAppoint.getDatePicker().getModel().getValue();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String choosenDate = formatter.format(date);
			
			Date todayDate = new Date();
			
			if(todayDate.compareTo(date)>0)
			{
				JOptionPane.showMessageDialog(null,"The date '"+choosenDate+"' has passed, please select a proper date.","Choose another date!", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			as.setApsDate(choosenDate);
			
			List<String> objList = GET_DOCTOR_HOURS(pt.getpID(),choosenDate);
			csGUI_CreateNewAppoint.getDoctorHoursBox().setModel(new DefaultComboBoxModel(objList.toArray()));
			/* setVisible "Doctor Hours Box" Combo box and label */
			csGUI_CreateNewAppoint.getDoctorHoursBox().setVisible(true);
			csGUI_CreateNewAppoint.getLblDoctorHours().setVisible(true);
			

			
		}
		
		/**
		 * Gets the zero time date.
		 *
		 * @param fecha the fecha
		 * @return the zero time date
		 */
		public Date getZeroTimeDate(Date fecha) {
		    Date res = fecha;
		    Calendar calendar = Calendar.getInstance();

		    calendar.setTime( fecha );
		    calendar.set(Calendar.HOUR_OF_DAY, 0);
		    calendar.set(Calendar.MINUTE, 0);
		    calendar.set(Calendar.SECOND, 0);
		    calendar.set(Calendar.MILLISECOND, 0);

		    res = calendar.getTime();

		    return res;
		}
		
	}
	
	
    /**
     * The listener interface for receiving cancel events.
     * closing the current window
     * 
     */
    class CancelListener implements ActionListener 
    {
    	
	    @Override
    	public void actionPerformed(ActionEvent e)
    	{
    		csGUI_CreateNewAppoint.dispose();
    	}	
    }//action


}
