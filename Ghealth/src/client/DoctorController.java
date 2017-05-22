package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import GUI.DoctorGUI;
import GUI.Doctor_Create_Lab_GUI;
import GUI.Doctor_History_GUI;
import GUI.Doctor_Pt_GUI;
import GUI.Doctor_rec_GUI;
//import client.AppointmentControl.cancelAppointmentFromDB;
import Server.Notification;
import enums.Status;
import enums.task;
import models.AppointmentSettings;
import models.Envelope;
import models.LabSettings;
import models.Patient;

/**
 * 
 * The Class DoctorController.
 * 
 * @author G5 lab group
 */
public class DoctorController {

	/** The doc gui. */
	private DoctorGUI docGUI;

	/** The doc pt gui. */
	private Doctor_Pt_GUI docPtGUI;

	/** The doc lab gui. */
	private Doctor_Create_Lab_GUI docLabGUI;

	/** The pt. */
	private Patient pt;

	/** The doc_rec gui. */
	private Doctor_rec_GUI doc_recGUI;

	/** The doc_hist_ gui. */
	private Doctor_History_GUI doc_hist_GUI;

	/** The Doctor id. */
	private String DoctorID;

	/** The as. */
	private AppointmentSettings as;

	/** The obj list_stra. */
	private List<Object> objList_stra;

	/** The obj list_strb. */
	private List<Object> objList_strb;

	/** The appointment list. */
	private List<String> appointmentList;

	/** The lab list. */
	private List<String> labList;

	/* ~~~~~~~~~~~~~~~~~~~~~~~~ GUI Constractors ~~~~~~~~~~~~~~~~~~~~~~~~ */

	/**
	 * constractor for the find patient screen GUI.
	 *
	 * @param doc
	 *            the doc
	 * @param docID
	 *            the doc id
	 */
	public DoctorController(DoctorGUI doc, String docID) {
		DoctorID = docID;
		docGUI = doc;
		docGUI.findPatientActionListener(new findPatientListener());

	}

	/**
	 * Instantiates a new doctor controller.
	 *
	 * @param doc_pt
	 *            the doc_pt
	 * @param pt
	 *            the pt
	 * @param docID
	 *            the doc id
	 */
	public DoctorController(Doctor_Pt_GUI doc_pt, Patient pt, String docID) {
		this.pt = pt;
		this.DoctorID = docID;
		docPtGUI = doc_pt;
		docPtGUI.SetPatient(pt);
		docPtGUI.RecordAppointActionListener(new RecAppointListener());
		docPtGUI.ViewHistoryActionListener(new ViewHistoryListener());
		docPtGUI.CreateLabActionListener(new CreateLabListener());
		docPtGUI.SearchPatientActionListener(new SearchAnotherPatientListener());
	}

	/**
	 * Instantiates a new doctor controller.
	 *
	 * @param docRec
	 *            the doc rec
	 * @param pt
	 *            the pt
	 * @param docID
	 *            the doc id
	 */
	public DoctorController(Doctor_rec_GUI docRec, Patient pt, String docID) {
		this.pt = pt;
		this.DoctorID = docID;
		doc_recGUI = docRec;
		doc_recGUI.SetPatient(pt);
		doc_recGUI.RecordPatientActionListener(new RecPatientListener());
	}
	/* ~~~~~~~~~~~~~~~~~~~~~~~~ Controller Function ~~~~~~~~~~~~~~~~~~~~~~~~ */

	/**
	 * Gets the current appointment.
	 *
	 * @param ptID
	 *            the pt id
	 * @param docID
	 *            the doc id
	 * @return the int
	 */
	public int GET_CURRENT_APPOINTMENT(String ptID, String docID) {
		String[] patientID_doctorID = { ptID, docID };
		Envelope en = Controller.Control(patientID_doctorID, task.GET_CURRENT_APPOINTMENT_ID);

		if (en.getStatus() == Status.NOT_EXIST) {
			return 0;
		} else {

			int apptID = (int) en.getSingleObject();

			return apptID;
		}
	}

	/**
	 * Sets the appointment record.
	 *
	 * @param AppID
	 *            the app id
	 * @param AppSummery
	 *            the app summery
	 */
	public void SET_APPOINTMENT_RECORD(String AppID, String AppSummery) {
		String[] AppID_AppSummery = { AppID, AppSummery };
		Envelope en = Controller.Control(AppID_AppSummery, task.SET_APPOINTMENT_RECORD);
		Notification nt = new Notification();
		nt.appSummery = AppSummery;
		nt.patient = pt;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				URL url = null;
				try {
					url = new URL("http://findicons.com/files/icons/42/basic/64/tick.png");// http://www.archisevilla.org/wp-content/themes/archisevilla/images/loading.gif");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				ImageIcon icon = new ImageIcon(url);
				JOptionPane.showMessageDialog(null, "Record saved and mail sent!", "Please wait!",
						JOptionPane.INFORMATION_MESSAGE, icon);
				Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
			}
		});
		t.start();
		Controller.Control(nt, task.SEND_PERSONAL_DOC_MAIL);
	}

	/**
	 * Gets the arrived appointments.
	 *
	 * @param ptID
	 *            the pt id
	 * @return the list
	 */
	public List<String> GET_ARRIVED_APPOINTMENTS(String ptID) {

		Envelope en = Controller.Control(new Patient(ptID), task.GET_ARRIVED_APPOINTMENTS);
		List<String> strList = new ArrayList<String>();
		objList_stra = en.getobjList();

		if (en.getStatus() == Status.NOT_EXIST) {
			return null;
		}
		for (Object obj : en.getobjList()) {
			strList.add(((AppointmentSettings) obj).toStringCancelAppoint());
		}

		return strList;
	}

	/**
	 * Gets the arrived labs.
	 *
	 * @param ptID
	 *            the pt id
	 * @return the list
	 */
	public List<String> GET_ARRIVED_LABS(String ptID) {

		Envelope en = Controller.Control(new Patient(ptID), task.GET_ARRIVED_LABS);
		List<String> strList = new ArrayList<String>();
		objList_strb = en.getobjList();

		if (en.getStatus() == Status.NOT_EXIST) {
			return null;
		}
		for (Object obj : en.getobjList()) {
			strList.add(((LabSettings) obj).tostringShowLabs());
		}

		return strList;
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~ ActionListener Functions
	 * ~~~~~~~~~~~~~~~~~~~~~~~~
	 */

	/**
	 * The listener interface for receiving find Patient events.
	 */
	class findPatientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (docGUI.getPtID().equals("") || docGUI.getPtID().equals("Insert ID here...")) // If
																								// fields
																								// are
																								// empty
																								// ,
																								// show
																								// error
																								// message
			{
				JOptionPane.showMessageDialog(null, "Please insert Patient ID!", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				return; // return to the find patient window
			} // if

			// This is new verison of client controller func call:
			Patient findpt = new Patient(docGUI.getPtID());
			Envelope en = Controller.Control(findpt, task.GET_PATIENT);

			/* if Patient exist */
			if (en.getStatus() == Status.EXIST) {
				findpt = (Patient) en.getSingleObject();
				docGUI.dispose();

				docPtGUI = new Doctor_Pt_GUI();
				DoctorController doc_cnt = new DoctorController(docPtGUI, findpt, DoctorID);

			} else {
				JOptionPane.showMessageDialog(null,
						"The Patient '" + findpt.getpID() + "' Patient is NOT Registered!" + "\n", "Confirm",
						JOptionPane.OK_OPTION);
			}

		}

	}

	/**
	 * The listener interface for receiving record Appointment events.
	 */
	class RecAppointListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (GET_CURRENT_APPOINTMENT(pt.getpID(), DoctorID) != 0) {

				Doctor_rec_GUI docRec = new Doctor_rec_GUI();
				docRec.SetPatient(pt);
				DoctorController doc_rec_cntrl = new DoctorController(docRec, pt, DoctorID);

			} else {
				JOptionPane.showMessageDialog(null,
						"The Patient '" + pt.getpFirstName() + "'HAS NO APPOINTMENT FOR YOU!" + "\n", "Confirm",
						JOptionPane.OK_OPTION);
			}

		}
	}

	/**
	 * The listener interface for receiving record Patient events.
	 */
	class RecPatientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int appID = GET_CURRENT_APPOINTMENT(pt.getpID(), DoctorID);
			String strAppID = appID + "";

			if (doc_recGUI.getRecordField().equals("Please fill appointment record here...")) {
				// TODO - pop up message
				return;
			}

			SET_APPOINTMENT_RECORD(strAppID, doc_recGUI.getRecordField());
			doc_recGUI.dispose();
		}

	}

	/**
	 * The listener interface for receiving view History events.
	 */
	class ViewHistoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			appointmentList = GET_ARRIVED_APPOINTMENTS(pt.getpID());
			labList = GET_ARRIVED_LABS(pt.getpID());

			if (appointmentList == null && labList == null) {
				JOptionPane.showMessageDialog(null,
						"There are no recorded Appointments and Labs to show for " + pt.getpFirstName() + " "
								+ pt.getpLastName() + "!!",
						"No recorded Medical History", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			doc_hist_GUI = new Doctor_History_GUI();
			doc_hist_GUI.SetPatient(pt);
			doc_hist_GUI.LabResultBoxActionListener(new LabResultBoxListener());
			doc_hist_GUI.AppointmentHistoryBoxActionListener(new AppointmentHistoryBoxListener());
			doc_hist_GUI.getAppHistoryButton().addActionListener(new ShowAppointmentHistoryListener());
			;
			doc_hist_GUI.getLabHistoryButton().addActionListener(new ShowLabHistoryListener());
			;
			doc_hist_GUI.getAppointmentHistoryBox().setVisible(false);
			doc_hist_GUI.getLabResultBox().setVisible(false);
			doc_hist_GUI.getTitleLabel().setVisible(false);

			if (labList != null) {
				doc_hist_GUI.AppointmentHistoryBoxActionListener(new AppointmentHistoryBoxListener());
				doc_hist_GUI.getLabResultBox().setModel(new DefaultComboBoxModel(labList.toArray()));
			}
			if (appointmentList != null) {
				doc_hist_GUI.getAppointmentHistoryBox().setModel(new DefaultComboBoxModel(appointmentList.toArray()));
			}
		}

	}

	/**
	 * The listener interface for receiving appointment History Box events.
	 */
	class AppointmentHistoryBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			doc_hist_GUI.getimagePan().setVisible(false);

			int selectedIndex = doc_hist_GUI.getAppointmentHistoryBox().getSelectedIndex();
			AppointmentSettings aaa = (AppointmentSettings) objList_stra.get(selectedIndex);
			doc_hist_GUI.SetSummery(aaa.getApsSummery());

		}

	}

	/**
	 * The listener interface for receiving lab Result Box events.
	 */
	class LabResultBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			doc_hist_GUI.getimagePan().setVisible(false);
			int selectedIndex = doc_hist_GUI.getLabResultBox().getSelectedIndex();
			LabSettings ls = (LabSettings) objList_strb.get(selectedIndex);

			doc_hist_GUI.SetSummery(ls.getLabWorkerSummery());

			Controller.Control(ls, task.SEND_FILE_TO_CLIENT);
			if (!ls.getFilePath().equals("NO FILE")) {
				doc_hist_GUI.setAddToImagePan("src\\images\\lab_file." + ls.getFileExt());
				doc_hist_GUI.getimagePan().setVisible(true);
			} else
				doc_hist_GUI.getimagePan().setVisible(false);

		}

	}

	/**
	 * The listener interface for receiving create Lab events.
	 */
	class CreateLabListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			docLabGUI = new Doctor_Create_Lab_GUI();
			docLabGUI.SetPatient(pt);
			docLabGUI.CreateNewLabRefActionListener(new CreateLabinDBListener());
		}
	}

	/**
	 * The listener interface for receiving create Lab in DB events.
	 */
	class CreateLabinDBListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (docLabGUI.getRecordField().equals("Please fill your requirements for the lab worker.")
					|| docLabGUI.getRecordField().equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill your requirements!", "Can't create lab ref!",
						JOptionPane.INFORMATION_MESSAGE);
				return; // return to the find patient window
			}

			LabSettings ls = new LabSettings();
			ls.setLabPtID(pt.getpID());
			ls.setLabDocID(LoginControl.getUserId());
			ls.setLabDoctorReq(docLabGUI.getRecordField());
			Controller.Control(ls, task.CREATE_LAB_REF);
			JOptionPane.showMessageDialog(null, "Lab Request open success", "Lab request complete",
					JOptionPane.INFORMATION_MESSAGE);
			docLabGUI.dispose();

		}

	}

	/**
	 * The listener interface for receiving search Another Patient events.
	 */
	class SearchAnotherPatientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			docPtGUI.dispose();
			DoctorGUI doc_gui = new DoctorGUI();
			DoctorController docCon = new DoctorController(doc_gui, LoginControl.getUserId());

		}
	}

	/**
	 * The listener interface for receiving Lab History events.
	 * 
	 */
	class ShowLabHistoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (labList != null) {
				doc_hist_GUI.getLabResultBox().setVisible(true);
				doc_hist_GUI.getTitleLabel().setText("Lab Result History");
				doc_hist_GUI.getTitleLabel().setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "There is no Lab Record for this Patient!", "No Lab History!",
						JOptionPane.INFORMATION_MESSAGE);
				doc_hist_GUI.getTitleLabel().setVisible(false);
			}
			doc_hist_GUI.getAppointmentHistoryBox().setVisible(false);
		}
	}

	/**
	 * The listener interface for receiving the patient appointment events.
	 */
	class ShowAppointmentHistoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (appointmentList != null) {
				doc_hist_GUI.getAppointmentHistoryBox().setVisible(true);
				doc_hist_GUI.getTitleLabel().setText("Appointment History");
				doc_hist_GUI.getTitleLabel().setVisible(true);
			} else {
				doc_hist_GUI.getTitleLabel().setVisible(false);
				JOptionPane.showMessageDialog(null, "There is no Appointment Record for this Patient!",
						"No Appointment History!", JOptionPane.INFORMATION_MESSAGE);
			}

			doc_hist_GUI.getimagePan().setVisible(false);
			doc_hist_GUI.getLabResultBox().setVisible(false);
		}
	}
} // PationControl
