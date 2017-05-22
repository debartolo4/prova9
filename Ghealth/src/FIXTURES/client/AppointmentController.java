
package FIXTURES.client;

import models.*;
import enums.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import fit.ActionFixture;
import GUI.CS_GUI_newAppoint;
import client.*;


public class AppointmentController extends ActionFixture {

	

	public AppointmentControl newApp_ctrl;
	public AppointmentSettings as;	
	public Patient pt;
	
	public List<String> doctorsAndClinics;

	public boolean isDoctorTypeAvail;
	public boolean isValidDate;
	public boolean apptCreated;
	

	public void startCreateNewAppointment() {

		this.pt = new Patient();
		this.pt.setpID("1010");
		
		as = new AppointmentSettings();
		as.setApsPtID(pt.getpID());
		as.setApsStatus(Status.SCHEDUELD);
		this.apptCreated = false; //initialize 

		newApp_ctrl = new AppointmentControl(new CS_GUI_newAppoint(), this.pt);
	}
	
	public void doctorType(String docType) {

		this.doctorsAndClinics = newApp_ctrl.GET_DOCTOR_CLINIC(pt.getpID(),DoctorSpeciallity.valueOf(docType));
		
	}
	
	public void selectDoctorType() {

		if (this.doctorsAndClinics == null) {
			
			this.isDoctorTypeAvail = false;
			
		} else {
			
			this.isDoctorTypeAvail = true;
		}
	}
	
	public boolean doctorTypeAvailableForPatient() {
		
		return this.isDoctorTypeAvail;
	}


	public void clinicIDAndDoctor(int clIDAndDoctor) {
		
		as.setApsDocID(doctorsAndClinics.get(clIDAndDoctor).toString());
		
	}

	
	public void date(String dt) {
		
		this.as.setApsDate(dt);
	}

	

	public void chooseDate() {
		Date todayDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date apsDate =null;
		
		try {
			apsDate = (Date)formatter.parse(this.as.getApsDate());
		} catch (ParseException e) {
			this.isValidDate = false;
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	
		if(apsDate.compareTo(todayDate)<0) 
		{
			this.isValidDate = false; 
			this.apptCreated = false; 
			
		
		}
		else{
			this.isValidDate = true;
			
		}
	}


	public boolean validDate() {

		return this.isValidDate;
	}


	public void hour(String hr) {
		this.as.setApsTime(hr);
		
	}

	public void createAppointment() {

		if(this.isValidDate == false || this.isDoctorTypeAvail == false)
		{ 
			this.apptCreated =false; 
		
		}else{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		
		as.setCreateDate(formatter.format(new Date()));
		as.setCreateTime(hourFormat.format(new Date()));
		
		
		Envelope en = Controller.Control(as,task.CREATE_NEW_APPOINTMENT);
		
		
		if(en != null ){
			this.apptCreated = true;
			
			
		}else {this.apptCreated = false;}
		
	}
		}

	
	public boolean appointmentCreated() {

		return this.apptCreated;
	}
	

	

	


}
