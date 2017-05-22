package client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import GUI.LabGUI;
import GUI.Lab_Rec_GUI;
import enums.Status;
import enums.task;
import models.Envelope;
import models.LabSettings;
import models.Patient;

/**
 * The Class LabController.
 * @author G5 lab group
 */
public class LabController {
      
	/** The lab gui. */
	private LabGUI labGUI;
	
	/** The pt. */
	private Patient pt;
	
	/** The Lab worker id. */
	private String LabWorkerID;
	
	/** The obj list_str. */
	private List<Object> objList_str;
	
	/** The lb. */
	private LabSettings lb;
	
	/** The rec. */
	private Lab_Rec_GUI rec;
	
	/** The Upload file. */
	private boolean UploadFile=false;
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   GUI Constractors ~~~~~~~~~~~~~~~~~~~~~~~~  */

	
	
	/**
	 * constractor for the find patient screen GUI.
	 *
	 * @param lab the lab
	 * @param labworker the labworker
	 */
	
	public LabController(LabGUI lab,String labworker)
	{
		LabWorkerID = labworker;
		labGUI = lab;
		labGUI.findPatientActionListener(new findPatientListener());
		labGUI.getbtnChooseLab().addActionListener(new ChooseLabListener());

	}
	
	


	/**
	 * The listener interface for receiving findPatient events.
	 * find the patient in the database.
	 */
	class findPatientListener  implements ActionListener 
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		if(labGUI.getPtID().equals("") || labGUI.getPtID().equals("Insert ID here...")) 		//If fields are empty , show error message
       	 {
       		 JOptionPane.showMessageDialog(null,"Please insert Patient ID!","Error", JOptionPane.INFORMATION_MESSAGE);    
       		 return;	//return to the find patient window
       	 }//if
		
			// This is new verison of client controller func call:
			pt = new Patient(labGUI.getPtID());
			Envelope en = Controller.Control(pt,task.GET_PATIENT);

			/* if Patient exist */
			if (en.getStatus() == Status.EXIST)
			{
				pt = (Patient)en.getSingleObject();
				///pt.getpID()
				List<String> objList = GET_LAB_HISTORY(pt.getpID());
				if(objList == null)
				{
					JOptionPane.showMessageDialog(null,"There is no SCHEDUELD lab for patient: "+pt.getpFirstName()+" "+pt.getpLastName()
					,"Can't find open lab reference", JOptionPane.INFORMATION_MESSAGE);
					return;	//return to the find patient window
				}
				else 
				{
					labGUI.getLabHistoryComboBox().setVisible(true);
					labGUI.getbtnChooseLab().setVisible(true);
					labGUI.getLabHistoryComboBox().setModel(new DefaultComboBoxModel(objList.toArray()));
				}
				
			}
			else{
				JOptionPane.showMessageDialog(null, "The Patient '"+pt.getpID()+"' Patient NOT Exists!" + "\n","Confirm",JOptionPane.OK_OPTION);	
			}
			
		}
	}

		
	/**
	 * The listener interface for receiving browseFile events.
	 * find the file to upload to server side and add it's path to db
	 *
	 */
	class BrowseFileListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(UploadFile == true)
			{
				JOptionPane.showMessageDialog(null,
                        "You already upload file, can't load more files! ",
                         "Upload more files.",
                         JOptionPane.OK_OPTION);
				return;
			}
			//rec.getFileChooser().showOpenDialog(this)
			JFileChooser fc = new JFileChooser();
			int val = fc.showOpenDialog(rec);
			String extension;
			try 
			{
				File unfiltered_picture = fc.getSelectedFile();
				extension=unfiltered_picture.getPath();
		        int index=extension.indexOf(".");
		        //get the extension of the file
		        extension=extension.substring(index+1, extension.length());
			       if(val==JFileChooser.APPROVE_OPTION) 
			       {
			           
			           //if the file is not jpg, png, or jpeg, reject it and send a message to the user.
			           if(!extension.matches("[jJ][pP][gG]") && !extension.matches("[pP][nN][gG]") && !extension.matches("[jJ][pP][eE][gG]")) 
			           {
			              JOptionPane.showMessageDialog(null,
			                                            "cannot load file. File must be of type png, jpeg, or jpg. \n Your file is of type " + extension,
			                                             "Error: improper file",
			                                             JOptionPane.OK_OPTION);
			              return;
			            //if the file is of the proper type, display it to the user on the img JLabel.
			           }
			       }
					int response = JOptionPane.showConfirmDialog(null,unfiltered_picture+" This is the file you choose\n"
							+ "Upload this file to lab record?","Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    responseOptions(extension, unfiltered_picture, response);
			} catch(Exception f)
			{
				JOptionPane.showMessageDialog(null,f.getMessage());
			}
		}

		private void responseOptions(String extension, File unfiltered_picture, int response) {
			if (response == JOptionPane.NO_OPTION)   
			{
				//return;	//return
			} 
			else if (response == JOptionPane.YES_OPTION) 
			{
				lb.setFilePath(unfiltered_picture.toString());
				lb.setFileExt(extension);
				Controller.Control(lb,task.UPLOAD_FILE_TO_LAB_RECORD);
				UploadFile=true;
			} 
			else if (response == JOptionPane.CLOSED_OPTION) {
				//do something
			}
		}
	}
	
		/**
		 * The listener interface for receiving chooseLab events.
		 * choose the lab foe the patient
		 */
		class ChooseLabListener  implements ActionListener 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{

				labGUI.getLabHistoryComboBox().setVisible(false);
				labGUI.getbtnChooseLab().setVisible(false);
				lb = (LabSettings)objList_str.get(labGUI.getLabHistoryComboBox().getSelectedIndex());
				lb.toStringOpenLabs();
				rec = new Lab_Rec_GUI(lb);
				rec.RecordLabActionListener(new RecordLab());
				rec.getBrowseButton().addActionListener(new BrowseFileListener());
				rec.SetPatient(pt);
				
				
				
				
			}
			
		}
		
		
		/**
		 * The Class jpgFilter.
		 */
		class jpgFilter  implements FileFilter 
		{

			@Override
			public boolean accept(File f) {
	            return f.getName().endsWith(".jpg");
			}
			

			
		}
		
		/**
		 * The Class RecordLab.
		 */
		class RecordLab  implements ActionListener 
		{

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				if(rec.getRecordField().equals("Add your lab record here..."))
				{
					JOptionPane.showMessageDialog(null,"Pleae fill the lab record!"
					,"Can't find open lab reference", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				else
				{
					lb.setLabWorkerSummery(rec.getRecordField());
					lb.setLabWorkerID(LoginControl.getUserId());
					Controller.Control(lb,task.UPDATE_LAB_RECORD);
					JOptionPane.showMessageDialog(null,"Lab record was updated!"
							,"Done.", JOptionPane.INFORMATION_MESSAGE);

					UploadFile=false;
					rec.dispose();
				}
			}
			
		}
		
		/**
		 * Gets the lab history.
		 *
		 * @param ptID the patient id
		 * @return the list
		 */
		private List<String> GET_LAB_HISTORY(String ptID) 
		{
			
			Envelope en = Controller.Control(new Patient(ptID),task.GET_SCHEDUELD_LAB);
			List<String> strList = new ArrayList<String>();
			objList_str = en.getobjList();
			
			if(en.getStatus() == Status.NOT_EXIST)
			{
				return null;
			}
			for (Object obj : en.getobjList())
			{
				strList.add(((LabSettings)obj).toStringOpenLabs());
			}
					
			return strList;
		}
	
	
} //LabControl

