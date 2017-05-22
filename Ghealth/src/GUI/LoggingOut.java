package GUI;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.Controller;
import client.LoginControl;
import enums.task;
import models.Patient;
import models.User;

/**
 * @author G5 lab group The Class LoggingOut.
 */
public class LoggingOut extends JFrame {

	/**
	 * A father class which all GUI classes inherit from. Controls the window X
	 * button and logging out function
	 */
	private static final long serialVersionUID = 270872694035967708L;

	/** The content pane. */
	protected JPanel contentPane;

	/** The btn cancel appoint. */
	protected JButton btnCancelAppoint;

	/** The btn crt appoint. */
	protected JButton btnCrtAppoint;

	/** The lblwarning message. */
	protected JLabel lblwarningMessage = null;

	/** The patient details. */
	protected JPanel patientDetails;

	/** The Search patient. */
	protected JButton SearchPatient;

	/** The Log out. */
	protected JButton LogOut;

	/** The btn uncreate patient. */
	protected JButton btnUncreatePatient;

	/**
	 * Setting X button to do nothing and adds the listener.
	 */
	public LoggingOut() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(exitListener);
	}

	public void SetPatient(Patient pt) {

		JLabel lblPatientDetails = new JLabel("Patient Details:");
		lblPatientDetails.setBounds(541, 135, 107, 20);
		contentPane.add(lblPatientDetails);
		patientDetails = new JPanel();
		patientDetails.setBounds(497, 170, 273, 232);
		contentPane.add(patientDetails);
		patientDetails.setLayout(null);

		extracted();

		extractedSetPatient(pt);

		patientDetails.setVisible(true);

	}

	private void extracted() {
		Label label_1 = new Label("Patient ID");
		label_1.setBounds(5, 5, 62, 22);
		patientDetails.add(label_1);

		Label label_2 = new Label("First Name");
		label_2.setBounds(5, 35, 62, 22);
		patientDetails.add(label_2);

		Label label_3 = new Label("Last Name");
		label_3.setBounds(5, 65, 62, 22);
		patientDetails.add(label_3);

		Label label_4 = new Label("Email");
		label_4.setBounds(5, 95, 62, 22);
		patientDetails.add(label_4);

		extracted2();
	}

	private void extracted2() {
		Label label_5 = new Label("Phone");
		label_5.setBounds(5, 125, 62, 22);
		patientDetails.add(label_5);

		Label label_6 = new Label("Private Clinic");
		label_6.setBounds(5, 155, 82, 22);
		patientDetails.add(label_6);

		Label label_7 = new Label("Doctor ID");
		label_7.setBounds(5, 185, 62, 22);
		patientDetails.add(label_7);
	}

	private void extractedSetPatient(Patient pt) {
		JLabel PationID = new JLabel(pt.getpID());
		PationID.setBounds(95, 5, 300, 22);
		patientDetails.add(PationID);

		JLabel fName = new JLabel(pt.getpFirstName());
		fName.setBounds(95, 35, 300, 22);
		patientDetails.add(fName);

		JLabel lName = new JLabel(pt.getpLastName());
		lName.setBounds(95, 65, 300, 22);
		patientDetails.add(lName);

		JLabel eMail = new JLabel(pt.getPtEmail());
		eMail.setBounds(95, 95, 300, 22);
		patientDetails.add(eMail);

		extractedSetPatient2(pt);
	}

	private void extractedSetPatient2(Patient pt) {
		JLabel phone = new JLabel(pt.getPtPhone());
		phone.setBounds(95, 125, 300, 22);
		patientDetails.add(phone);

		JLabel pClinic = new JLabel(pt.getPtPrivateClinic());
		pClinic.setBounds(95, 155, 90, 22);
		patientDetails.add(pClinic);

		JLabel doctorID = new JLabel(pt.getPd());
		doctorID.setBounds(95, 185, 90, 22);
		patientDetails.add(doctorID);
	}

	/** The exit listener. */
	WindowListener exitListener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent event) {
			int confirm = JOptionPane.showOptionDialog(null, "Are You Sure you want to close and log out?",
					"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == 0) {
				Controller.Control(new User(LoginControl.getUserId()), task.LOG_OUT);
				dispose();
				LoginControl userctrl = new LoginControl(new LoginGUI());
				System.exit(0);
			}
		}
	};

	/**
	 * Logging out listener.
	 *
	 * 
	 */
	class LogOutListener implements ActionListener {

		/**
		 * @param e
		 *            the event
		 *
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			Controller.Control(new User(LoginControl.getUserId()), task.LOG_OUT);
			dispose();
			LoginControl userctrl = new LoginControl(new LoginGUI());

		}

	}// LogOutListener

	// logout
}
