package Server;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 

/**
 * @author G5 lab group
 * Sends the notification mail to the patient
 */
public class Email {
 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	static {
		mailServerProperties = System.getProperties();
		getMailSession = Session.getInstance(mailServerProperties, new GMailAuthenticator("braudehospital", "maabadag5"));
		generateMailMessage = new MimeMessage(getMailSession);		
	}
	
	Address[] addresses = new Address[10]; //for future needs
 
	//------------------------------------------------------------------------------------------------
	/**
	 * Main for testing
	 * @param args
	 * @throws AddressException
	 * @throws MessagingException

	public static void main(String args[]) throws AddressException, MessagingException {
		generateAndSendEmail();
		System.out.println("\n\n ===> The System has just sent an Email successfully. Check your email..");
	}
		 */
	
	//------------------------------------------------------------------------------------------------
	/**
	 * This function will be available for outside class interfacing 
	 * here it is only for testing
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void generateAndSendEmail(Notification nt) throws AddressException, MessagingException {
		/**
		 *  Step1: Setting mail Server Properties
		 */
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
		//------------------------------------------------------------------------------------------------
		/**
		 *  Step2: getting mail session, using the GMailAuthenticator class
		 */
		getMailSession = Session.getInstance(mailServerProperties, new GMailAuthenticator("braudehospital", "maabadag5"));
		
		//------------------------------------------------------------------------------------------------
		/**
		 * another Authenticator if we'll need it
		 */
		/*
		getMailSession = Session.getInstance(mailServerProperties, new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("", "");
		    }
		});
		*/
		
		//NO Authentication session
		//getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		
		//------------------------------------------------------------------------------------------------
		/**
		 * MIME and recipients
		 */
		generateMailMessage = new MimeMessage(getMailSession);
		
		//one recipient
		//generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("pinto.com@gmail.com"));
		
		//multiple recipient
		generateMailMessage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(nt.mail));
		
		//if we want CC
		//generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@cc.com"));
		
		
		//------------------------------------------------------------------------------------------------
		/**
		 * Message itself
		 */
		String strVar;
		if(nt.appSummery.compareTo("none")==0){
			
			generateMailMessage.setSubject("You have medical appointment scheduled for tomorrow");	
			
			strVar="";
			strVar += "<html>";
			strVar += "<head>";
			strVar += "<//head>";
			strVar += "<body>";
			strVar += "<h2><span style=\"font-size:20px;\">Dear ";
			strVar += nt.ptName;
			strVar += " ,<//span><//h2>";
			strVar += "";
			strVar += "<p><em style=\"box-sizing: border-box; color: rgb(76, 76, 76); font-family: 'Open Sans', sans-serif; font-size: 16px; line-height: 23.2px; background-color: rgb(255, 255, 255);\">You have an appointment with <strong><span style=\"background-color:#AFEEEE;\">" + nt.docName + "<//span><//strong>&nbsp;tomorrow (<strong>"+nt.sdate+"<//strong>) at <strong>"+nt.time+" "+nt.location+"<//strong>. <//em><//p>";
			strVar += "";
			strVar += "<p><em style=\"box-sizing: border-box; color: rgb(76, 76, 76); font-family: 'Open Sans', sans-serif; font-size: 16px; line-height: 23.2px; background-color: rgb(255, 255, 255);\">Please feel free to&nbsp;call us at &lt;04-9901911&gt; &nbsp;if you have any questions.<//em><//p>";
			strVar += "";
			strVar += "<p>&nbsp;<//p>";
			strVar += "";
			strVar += "<p><span style=\"font-size: 20px;\">Braude Hospital<span style=\"font-size:12px;\">&nbsp;- Always there fo you!<//span><//span><//p>";
			strVar += "<p><span style=\"font-size: 20px;\"><span style=\"font-size:12px;\"><img alt=\"\" src=\"http://www.maloclinic.co.il/wp-content/uploads/2012/07/malo-clinc-team1.jpg\" style=\"width: 940px; height: 320px;\" //><//span><//span><//p>";
			strVar += "";
			strVar += "<//body>";
			strVar += "<//html>";
			strVar += "";
		}
		
		else{
			generateMailMessage.setSubject("New Appointment summery of one of your patients");	
			
			strVar="";
			strVar += "<html>";
			strVar += "<head>";
			strVar += "<//head>";
			strVar += "<body>";
			strVar += "<h2><span style=\"font-size:20px;\">Dear ";
			strVar += nt.docName;
			strVar += " ,<//span><//h2>";
			strVar += "";
			strVar += "<p><em style=\"box-sizing: border-box; color: rgb(76, 76, 76); font-family: 'Open Sans', sans-serif; font-size: 16px; line-height: 23.2px; background-color: rgb(255, 255, 255);\">Your patient (" + nt.patient.getpFirstName() + " " + nt.patient.getpLastName() + ") had an appointment today at our hospital.  <//em><//p>";
			strVar += "";
			strVar += "<p><em style=\"box-sizing: border-box; color: rgb(76, 76, 76); font-family: 'Open Sans', sans-serif; font-size: 16px; line-height: 23.2px; background-color: rgb(255, 255, 255);\">This is the appointment summery:<//em><//p>";
			strVar += "";
			strVar += "<p><em style=\"box-sizing: border-box; color: rgb(76, 76, 76); font-family: 'Open Sans', sans-serif; font-size: 16px; line-height: 23.2px; background-color: rgb(255, 255, 255);\">" + nt.appSummery + "<//em><//p>";
			strVar += "";
			strVar += "<p><em style=\"box-sizing: border-box; color: rgb(76, 76, 76); font-family: 'Open Sans', sans-serif; font-size: 16px; line-height: 23.2px; background-color: rgb(255, 255, 255);\">Please feel free to&nbsp;call us at &lt;04-9901911&gt; &nbsp;if you have any questions.<//em><//p>";
			strVar += "";
			strVar += "<p>&nbsp;<//p>";
			strVar += "";
			strVar += "<p><span style=\"font-size: 20px;\">Braude Hospital<span style=\"font-size:12px;\">&nbsp;- Always there fo you!<//span><//span><//p>";
			strVar += "<p><span style=\"font-size: 20px;\"><span style=\"font-size:12px;\"><img alt=\"\" src=\"http://www.maloclinic.co.il/wp-content/uploads/2012/07/malo-clinc-team1.jpg\" style=\"width: 940px; height: 320px;\" //><//span><//span><//p>";
			strVar += "";
			strVar += "<//body>";
			strVar += "<//html>";
			strVar += "";
		}
			

		String emailBody = strVar;
		generateMailMessage.setContent(emailBody, "text/html");
		//------------------------------------------------------------------------------------------------
		/**
		 *  Step3: Choose transport type
		 */
		Transport transport = getMailSession.getTransport("smtp");
		//------------------------------------------------------------------------------------------------
		/**
		 *  Step 4: Connection
		 */
		transport.connect("smtp.gmail.com", "braudehospital", "maabadag5");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}
		//------------------------------------------------------------------------------------------------

/**
 * Authenticator class
 * Made for Gmail ass Security system 
 *
 * @author Pinto.com
 */
class GMailAuthenticator extends Authenticator {
    String user;
    String pw;
    public GMailAuthenticator (String username, String password)
    {
       super();
       this.user = username;
       this.pw = password;
    }
   public PasswordAuthentication getPasswordAuthentication()
   {
      return new PasswordAuthentication("braudehospital", "maabadag5");
   }
}