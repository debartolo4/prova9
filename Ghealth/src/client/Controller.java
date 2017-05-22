package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JOptionPane;

import enums.task;
import models.Envelope;
import models.LabSettings;

/**
 * 
 * Taking care of all connection and transportation in client side
 * including file management
 * @author G5 lab group
 */
public class Controller {
	 	private static Socket socket = new Socket();
//	    private static boolean isConnected = false;
	    private static Envelope En = new Envelope();
	    private static Envelope GetEn = new Envelope();
	    
	    /**
	     * Encapsulate in Envelope struct type
	     * @param obj
	     * @param ts
	     * @return
	     */
	    public static Envelope Control(Object obj,task ts)
	    {
	    	Envelope En = new Envelope();
	    	
	    	if(obj instanceof List<?>)
	    	{
	    		/* This case is for sending list and not object. */
	    		List<Object> objList = (List<Object>) obj;
	    		En.setobjList(objList);
	    	}
	    	else En.addobjList(obj);
	        En.setType(ts);
	        En = communicate(En);
	    	return En;
	    }

	   /**
	    * Sending the envelope
	    * @param envel
	    * @return
	    */
	public static Envelope communicate(Envelope envel) {
	    	
	    	String ip = "127.0.0.1";
	    	
	    	ObjectInputStream inputStream = null;
		    ObjectOutputStream outputStream = null;
		    boolean isConnected = false;
	    	
	        while (!isConnected) { //loop not used, for future purposes
	            try {
	            	
	            	/* Connection details + socket creation */
	            	socket = new Socket(ip,5555);
	                
	                /* Output stream creation and related object sending */
	                outputStream = new ObjectOutputStream(socket.getOutputStream());
	                
	                
	                /* Object sending */
	                outputStream.writeObject(envel);
	                isConnected = true;
	                

	                
	         //   } catch (SocketException se) {
	         //       se.printStackTrace();
	                // System.exit(0);
	        //    } catch (IOException e) {
	          //      e.printStackTrace();
	           // }
	            
	            /* Receiving response from server */
	            //try {
	            	
	                /* Choose if get file or object */
	                if(envel.getType() == task.SEND_FILE_TO_CLIENT)
	                {
	                	LabSettings ls = (LabSettings)envel.getSingleObject();
	                	saveFile(ls.getFileExt());
	                }
	                else if(envel.getType() == task.UPLOAD_FILE_TO_LAB_RECORD)
	                	sendFile(((LabSettings)envel.getSingleObject()).getFilePath());
	                	//sendFile("src//client//files//afasdf.jpg");
	                else
	                {
		                inputStream = new ObjectInputStream(socket.getInputStream());
		                try {
		                	
		                	GetEn  = (Envelope) inputStream.readObject();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e.getMessage());
						}	     
	                }
	            
	                /* Flushing and closing stream */
	                outputStream.flush();
	                outputStream.close();
	                
	            } catch (SocketException se) {
	            	JOptionPane.showMessageDialog(null,se.getMessage());
	                // System.exit(0);
	            } catch (IOException e) {
	            	JOptionPane.showMessageDialog(null,e.getMessage());
	            }
	        }//end while
	        
	        isConnected = false; 
	        return GetEn;
	        
	        
	    }//end function

	    
	    /**
	     * Sending the file
	     * @param filename
	     * @throws IOException
	     */
	    public static void sendFile(String filename) throws IOException 
	    {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			FileInputStream fis = new FileInputStream(filename);
			byte[] buffer = new byte[16*1024]; //16 kb buffer

			int filesize = 2097152; // Send file up to 2 mb size in separate msg
			int read = 0;
			int totalRead = 0;
			int remaining = filesize;
			while((read = fis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
				totalRead += read;
				remaining -= read;
				dos.write(buffer, 0, read);
			}
	        
	        /*
			while (fis.read(buffer) > 0) {
				dos.write(buffer);
			}
			*/
			
			
			fis.close(); //Git Test
			dos.close();	
			
			
			
		}
		
		
		/**
		 * Saving file in client storage
		 * @throws IOException
		 */
		private static void saveFile(String ext) throws IOException {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			FileOutputStream fos = new FileOutputStream("src//images//lab_file."+ext);
			byte[] buffer = new byte[16*1024]; // 16 kb buffer
			
			int filesize = 2097152; // Send file up to 2mb size in separate msg
			int read = 0;
			int totalRead = 0;
			int remaining = filesize;
			while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
				totalRead += read;
				remaining -= read;
				fos.write(buffer, 0, read);
			}
			
			fos.close();
			dis.close();
		} 
	    
	    
}
