package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JOptionPane;

import enums.Status;
import enums.task;
import models.AppointmentSettings;
import models.Clinic;
import models.Envelope;
import models.LabSettings;
import models.Patient;
import models.User;


/**
 * @author G5 lab group
 * Main server class.
 * includes mainly the server socket connections and the tasks switch case
 */
public class Server extends Thread
{

    /** The server socket. */
    private ServerSocket serverSocket = null;
    
    /** The conn. */
    public Connection conn;
    
    /** The status. */
    public Status status;
    
    /** The pt. */
    public Patient pt = null;
    
    /** The us. */
    public User us = null;
    
    /** The as. */
    public AppointmentSettings as = null;
    
    /** The filename. */
    public String filename;
    
    /** The session list. */
    public static List<String> sessionList = new ArrayList<String>();
    
    /** The ls. */
    public LabSettings ls = null;
    
    /** The clinic. */
    public Clinic clinic = null;
	
	/** The nt. */
	private Notification nt;
    
    
    
    
    /**
     * Starting server socket with given port.
     *
     * @param port the port
     */
    public Server(int port) 
    {
    	try 
    	{
			serverSocket = new ServerSocket(port);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    	
    }
    
    
    /**
     * Waiting for connections.
     */
    public void run()
    {
    	while(true)
	    	try {
				Socket clientSocket = serverSocket.accept();
				communicate(clientSocket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
        
    }

    
    /**
     * Taking care of network transportations and tasks switch case.
     *
     * @param cs the cs
     */
    public void communicate(Socket cs) {
    	
        //System.out.println("Server-> Start - Before Socket Coonnection.");
        try {
        	Envelope env;
        	ObjectInputStream inputStream;
        	/* getting object */
        	inputStream = new ObjectInputStream(cs.getInputStream());
            
           // System.out.println("Object is = " + inStream);
            
            /* parsing and switching are needed here */
            env  = (Envelope)inputStream.readObject();
            
         
            /*---      User Tasks:    ---*/
            env = serverB(env);
            	
            	
            /*---     Patient Tasks:   ---*/
            env = serverC(env);
            	
            	
            
            env = serverD(env);
            	
            	
            	
            env = serverE(env);
            	
            	
            	
            env = serverF(env);
            	
            	
            	/*--- Doctor flow Tasks  ----*/
            	
            env = serverG(env);
            	
           
            	
            env = serverF(cs, env);
            /* Geting file from client */
            	
				
            env = serverH(env);
            	
            	
            	
            env = serverI(env);

    			
            	
            serverL(env);
                
                	
                	
                	
            serverM(env);
                	
                                                         
            
            /* if the task is not to send FILE to client */
            serverZ(cs, env);
           //serverSocket.close();
           //cs.close();

        } catch (SocketException se) {
        	JOptionPane.showMessageDialog(null, se.getMessage());
            System.exit(0);
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (ClassNotFoundException cn) {
        	JOptionPane.showMessageDialog(null, cn.getMessage());
       }
        
        
        
    }


	private void serverZ(Socket cs, Envelope env) throws IOException {
		ObjectOutputStream outputStream;
		if(env.getType() != task.UPLOAD_FILE_TO_LAB_RECORD && env.getType() != task.SEND_FILE_TO_CLIENT)
		{
		    /* Sending data back to client */
		    outputStream = new ObjectOutputStream(cs.getOutputStream());
		    outputStream.writeObject(env);
		    
		}
	}


	private void serverM(Envelope env) {
		if(env.getType() == task.LOG_OUT) {
			/* client is logging out */
			us = (User)env.getSingleObject();
			
			removeSession(us.getuID());
		    
		}
	}


	private void serverL(Envelope env) {
		if(env.getType() == task.SEND_PERSONAL_DOC_MAIL) {
			/* Sending the patient's personal doctor mail with app details. */
			nt = (Notification)env.getSingleObject();
			nt = SCdocAppointment.getPDocMail(nt); //scda = null;
			try {
				Email.generateAndSendEmail(nt);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}


	private Envelope serverI(Envelope env) {
		if(env.getType() == task.GET_CLINIC_WEEKLY_REPORT) {
			clinic = (Clinic)env.getSingleObject();

			SCweeklyReports rep = SCweeklyReports.getInstance();
			env = rep.getClinicWeeklyReport(clinic.getcID()); 

		}
			            	
			
		if(env.getType() == task.GET_CLINIC_MONTHLY_REPORT) {
			clinic = (Clinic)env.getSingleObject();

			SCmonthlyReports report = SCmonthlyReports.getInstance();
			env = report.getClinicMonthlyReport(clinic.getcID()); 

		}
 
			
		if(env.getType() == task.GET_CLINIC_CLUSTER_MONTHLY_REPORT) {
			SCmonthlyClusterReports reports = SCmonthlyClusterReports.getInstance();
			env = reports.getClinicMonthlyClusterReport(env.getobjList());
			
		}
		return env;
	}


	private Envelope serverH(Envelope env) {
		if(env.getType() == task.GET_ARRIVED_LABS) {
			pt = (Patient)env.getSingleObject();
			env = SClab.Get_ARRIVED_labs(pt.getpID());
			
		}
			
		if(env.getType() == task.GET_SCHEDUELD_LAB) {
			pt = (Patient)env.getSingleObject();
			env = SClab.Get_SCHEDUELD_labs(pt.getpID());
			
		}
			
			
		if(env.getType() == task.UPDATE_LAB_RECORD) {
			ls = (LabSettings)env.getSingleObject();
			SClab.UpdateLabRecord(ls.getLabID(),ls.getLabWorkerSummery(),ls.getLabWorkerID());
			
		}
		return env;
	}


	private Envelope serverF(Socket cs, Envelope env) throws IOException {
		if(env.getType() == task.GET_ARRIVED_APPOINTMENTS) {
			pt = (Patient)env.getSingleObject();
			env = SCdocAppointment.GetRecordedAppointments(pt.getpID());
			
		}
		
		/*---     Lab-Ref Tasks:   ---*/
		if(env.getType() == task.SEND_FILE_TO_CLIENT) {
			ls = (LabSettings)env.getSingleObject();
			sendFile(ls.getFilePath(),cs);
			
		}
		/* Sending file to client */
			/* TODO: SQL query returns filename as string */
			
		
			
		if(env.getType() == task.UPLOAD_FILE_TO_LAB_RECORD) {
			ls = (LabSettings)env.getSingleObject();
			filename="src//Server//files//"+Integer.toString(ls.getLabID())+"."+ls.getFileExt();
			saveFile(filename,cs);
			SClab.UpdateLabFilePath(filename,ls.getLabID());
			
		}
		return env;
	}


	private Envelope serverG(Envelope env) {
		if(env.getType() == task.CREATE_LAB_REF) {
			ls = (LabSettings)env.getSingleObject();
			status=SClab.CreaetLabRef(ls);
			env.setStatus(status);

		}
			
       
		if(env.getType() == task.GET_CURRENT_APPOINTMENT_ID) {
			String[] patiend_doc =(String[])env.getSingleObject();
			env = SCdocAppointment.GetCurrentAppointment(patiend_doc[0], patiend_doc[1]);
			
		}
			
		if(env.getType() == task.SET_APPOINTMENT_RECORD) {
			String[] appID_appRec =(String[])env.getSingleObject();
			SCdocAppointment.RecordAppointment(appID_appRec[0], appID_appRec[1]);
			
		}
		return env;
	}


	private Envelope serverF(Envelope env) {
		List<Object> objList;
		if(env.getType() == task.GET_AVAILIBLE_DOCTOR_HOURS) {
			objList = env.getobjList();
			env = SCappointment.GetAvailibleDoctorHours(objList.get(0).toString(),objList.get(1).toString());
			
		}
			            	
			
		if(env.getType() == task.GET_OPEN_APPOINTMENTS) {
			pt = (Patient)env.getSingleObject();
			env = SCappointment.GetSCHEDUELDAppointments(pt.getpID());
			
		}
			
			
		if(env.getType() == task.CANCEL_APPOINTMENT_FROM_DB) {
			as = (AppointmentSettings) env.getSingleObject();
			env.setStatus(SCappointment.CancelAppointment(as.getApsID()));
			
		}
		return env;
	}


	private Envelope serverE(Envelope env) {
		List<Object> objList;
		if(env.getType() == task.RECOVER_PATIENT_REGISTRATION) {
			pt = (Patient)env.getSingleObject();
			status=SCpatient.RecoverPatient(pt);           	
			env.setStatus(status);
			
		}

			
			
			
			
			
		if(env.getType() == task.CREATE_NEW_APPOINTMENT) {
			as = (AppointmentSettings) env.getSingleObject();
			status=SCappointment.CreateAppointment(as);
			env.setStatus(status);
			
		}
			
       
			
		if(env.getType() == task.GET_DOCTORS_IN_CLINIC_BY_TYPE) {
			objList = env.getobjList();
			env = SCappointment.GetClinicDoctorList(objList.get(0).toString(),objList.get(1).toString());
			
		}
		return env;
	}


	private Envelope serverD(Envelope env) {
		if(env.getType() == task.GET_CLINIC_LIST) {
			env = SCclinic.GetOurClinicList();
		}
			
		
			
		/*---   Appointment Tasks: ---*/  
		if(env.getType() == task.CANCEL_PATIENT_REGISTRATION) {
			pt = (Patient)env.getSingleObject();
			status=SCpatient.UncreatePatient(pt);           	
			env.setStatus(status);
			
		}
			
			
			
		if(env.getType() == task.CANCEL_ALL_PATIENT_APPOINTMENTS) {
			pt = (Patient)env.getSingleObject();
			status=SCpatient.CANCEL_ALL_APPOINTMENTS(pt);           	
			env.setStatus(status);
			
		}
		return env;
	}


	private Envelope serverC(Envelope env) {
		if(env.getType() == task.ADD_PATIENT) {
			pt = (Patient)env.getSingleObject();
		    status=SCpatient.CreatePatient(pt);
		    env.setStatus(status);
		}
		
			
		if(env.getType() == task.GET_PATIENT) {
			pt = (Patient)env.getSingleObject();
			env=SCpatient.GetExistPatient(pt.getpID());
			
			
		}
			
		if(env.getType() == task.GET_PRIVATE_CLINIC_LIST) {
			env = SCpatient.GetClinicList();
		}
		return env;
	}


	private Envelope serverB(Envelope env) {
		if( env.getType() == task.GET_USER ) {
			us = (User)env.getSingleObject();
			
			if(searchUserSession(us.getuID())==true){
				
				env.setStatus(Status.IN_SESSION);
			}
			else{
				env=SCuser.GetExistUser(us.getuID());
				serverA(env);
		  
			}
			
		}
		return env;
	}


	private void serverA(Envelope env) {
		if(env.getStatus() == Status.EXIST && ((User)(env.getSingleObject())).getuPassword().equals(us.getuPassword()))
			sessionList.add(us.getuID());
	}
    
    
    /**
     * Removing user session from the active sessions list.
     *
     * @param getuID the getu id
     */
    public void removeSession(String getuID) {
		sessionList.remove(getuID);
	}



	/**
	 * Searching user session in active sessions list.
	 *
	 * @param uid the uid
	 * @return true, if successful
	 */
	public boolean searchUserSession(String uid){
    	for(String str: sessionList) {
    	    if(str.trim().equals(uid))
    	       return true;
    	}
    	return false;
    }
    
    /**
     * Sending file.
     *
     * @param filename the filename
     * @param s the s
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void sendFile(String flname,Socket s) throws IOException {
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		
		String extension;
		extension=flname;
	    int index=extension.indexOf(".");
	    //get the extension of the file
	    extension=extension.substring(index+1, extension.length());
		
		FileInputStream fis = new FileInputStream(flname);
		byte[] buffer = new byte[4096];
		
		while (fis.read(buffer) > 0) {
			dos.write(buffer);
		}
		
		fis.close();
		dos.close();	
	}
    
    
    /**
     * Saving file in server storage.
     *
     * @param filename the filename
     * @param clientSock the client sock
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void saveFile(String flname,Socket clientSock) throws IOException {
		DataInputStream dis = new DataInputStream(clientSock.getInputStream());
		FileOutputStream fos = new FileOutputStream(flname);
		byte[] buffer = new byte[16*1024]; // 16 kb
		
		int filesize = 2097152; // 2mb files - Send file size in separate msg
		int read = 0;
		int remaining = filesize;
		while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
			remaining -= read;
			fos.write(buffer, 0, read);
		}
		
		fos.close();
		dis.close();
	}
    
   
}