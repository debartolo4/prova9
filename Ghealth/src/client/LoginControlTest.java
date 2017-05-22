
package client;

import org.junit.Test;

import enums.Status;
import enums.task;
import junit.framework.Assert;
import junit.framework.TestCase;
import models.Envelope;
import models.User;

public class LoginControlTest extends TestCase {

	
	public User exsitedUser ;
	public User notExsitedUser;
	public User actaulUserFromDB;
	

	@Test
	public void testExistedUser() {

		this.exsitedUser = new User(); // (1)
		
		
		String pass = "1234";	//Gets the password the user entered
	   	String userID = "4000";		//Gets the user ID the user entered
	   	this.exsitedUser.setuID(userID);
	   	this.exsitedUser.setuPassword(pass);

		Envelope en = Controller.Control(this.exsitedUser,task.GET_USER);
		User us = (User)en.getSingleObject();
	
		String result = en.getStatus().toString();
		
		String expected = Status.EXIST.toString(); //user already connected from previous test - testExistedUser() 
		String possible = Status.IN_SESSION.toString();
		
		Assert.assertTrue(result.equals(expected) || result.equals(possible));
		//Assert.assertTrue(expected.equals(result));
   		 
	}

	
	
	@Test
	public void testNotExistedUser() {

		this.notExsitedUser = new User(); // (1)
		
		
		 String pass = "1234";	//Gets the password the user entered
	   	 String userID = "456456465";		//Gets the user ID the user entered
	   	this.notExsitedUser.setuID(userID);
	   	this.notExsitedUser.setuPassword(pass);

		   Envelope en = Controller.Control(this.notExsitedUser,task.GET_USER);
		   User us = (User)en.getSingleObject();
		
		
		
		String result = en.getStatus().toString();

		
		String expected = Status.NOT_EXIST.toString();
		
		Assert.assertTrue(expected.equals(result));
	   
   		 

	}

}




