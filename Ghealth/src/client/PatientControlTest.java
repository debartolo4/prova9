package client;

import org.junit.Test;

import enums.Status;
import enums.task;
import junit.framework.Assert;
import junit.framework.TestCase;
import models.Envelope;
import models.Patient;

public class PatientControlTest extends TestCase {

	public Patient exsitedPT ;
	public Patient notExsitedPT;
	public Patient recoverPT;
	public Patient actaulPTFromDB;
	
	
	
	
	@Test
	public void testExistedPatient() {

		this.exsitedPT = new Patient(); // (1)
		this.exsitedPT.setpID("1010");
		

		this.actaulPTFromDB = new Patient();
		Envelope en = Controller.Control(this.exsitedPT,task.GET_PATIENT);
		
		this.actaulPTFromDB = (Patient)en.getSingleObject();
		String result = this.actaulPTFromDB.getpID();
		String expected = this.exsitedPT.getpID();
		Assert.assertTrue(expected.equals(result));
	
	

}

	public void testNotExistedPatient() {
		
		this.notExsitedPT = new Patient(); // (1)
		this.notExsitedPT.setpID("2323232");
		

		Envelope en = Controller.Control(this.notExsitedPT,task.GET_PATIENT);
		
		
		String result = en.getStatus().toString();
		String expected = Status.NOT_EXIST.toString();
		Assert.assertTrue(expected.equals(result));
	
	
 }
	

	public void testRecoverPatient() {

		this.recoverPT = new Patient(); // (1)
		this.recoverPT.setpID("1002");
		

		Envelope en = Controller.Control(this.recoverPT,task.GET_PATIENT);
		
		
		String result = en.getStatus().toString();
		String expected = Status.NOT_REG.toString();
		Assert.assertTrue(expected.equals(result));
	
	 }

}
