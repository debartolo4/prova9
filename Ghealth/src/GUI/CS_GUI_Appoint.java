package GUI;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.LoginControl;

/**
 * @author G5 lab group The Class costumer service of adding new appointment.
 */
public class CS_GUI_Appoint extends LoggingOut {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public CS_GUI_Appoint() {
		setResizable(false);
		setTitle("G-Health");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoctorGUI.class.getResource("/images/logo2.PNG")));
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		JLabel label = new JLabel("New label");
		label.setBounds(0, 0, 0, 0);
		contentPane.add(label);
		JLabel lblLogo;
		if (LoginControl.getUser_full_name() == null)
			lblLogo = new JLabel("Welcome CS!");
		else
			lblLogo = new JLabel("Hi " + LoginControl.getUser_full_name() + "!");

		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);

		btnCrtAppoint = new JButton("CREATE APPOINTMENT");
		btnCrtAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrtAppoint.setBounds(138, 214, 245, 68);
		contentPane.add(btnCrtAppoint);

		btnCancelAppoint = new JButton("CANCEL APPOINTMENT");
		btnCancelAppoint.setBounds(138, 293, 245, 68);
		contentPane.add(btnCancelAppoint);

		SearchPatient = new JButton("SEARCH ANOTHER PATIENT");
		SearchPatient.setBounds(138, 135, 245, 68);
		contentPane.add(SearchPatient);

		LogOut = new JButton("Log Out");
		LogOut.setBounds(138, 451, 245, 68);
		LogOut.addActionListener(new LogOutListener());
		contentPane.add(LogOut);

		btnUncreatePatient = new JButton("Uncreate Patient");
		btnUncreatePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUncreatePatient.setBounds(138, 372, 245, 68);
		contentPane.add(btnUncreatePatient);

		setLocationRelativeTo(null);

		setVisible(true);

	}

	/**
	 * Sets the warning message visible true.
	 */
	public void setWarningMessageVisibleTrue() {
		lblwarningMessage.setVisible(true);
	}

	/**
	 * Sets the warning message visible true.
	 *
	 * @param st
	 *            the new warning message visible true
	 */
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setBounds(10, 165, 245, 30);
		lblwarningMessage.setVisible(true);

	}

	/**
	 * Undisplay warning message.
	 */
	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);

	}

	/**
	 * Creates the appoint action listener.
	 *
	 * @param e
	 *            the event
	 */
	public void createAppointActionListener(ActionListener e) {
		btnCrtAppoint.addActionListener(e);
	}

	/**
	 * Log out action listener.
	 *
	 * @param e
	 *            the event
	 */
	public void LogOutActionListener(ActionListener e) {
		LogOut.addActionListener(e);
	}

	/**
	 * Search patient action listener.
	 *
	 * @param e
	 *            the event
	 */
	public void SearchPatientActionListener(ActionListener e) {
		SearchPatient.addActionListener(e);
	}

	/**
	 * Uncreate patient action listener.
	 *
	 * @param e
	 *            the event
	 */
	public void UncreatePatientActionListener(ActionListener e) {
		btnUncreatePatient.addActionListener(e);
	}

	/**
	 * Cancel appoint action listener.
	 *
	 * @param e
	 *            the event
	 */
	public void cancelAppointActionListener(ActionListener e) {
		btnCancelAppoint.addActionListener(e);
	}

	/**
	 * Sets the patient.
	 *
	 * @param pt
	 *            the patient
	 */
	
}
