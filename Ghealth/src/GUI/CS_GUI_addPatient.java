package GUI;

import java.awt.Color;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import client.Controller;
import client.LoginControl;
import enums.task;
import models.Envelope;
import models.PrivateClinic;


/**
 * @author G5 lab group
 * The Class of the costumer service add new patient GUI.
 */
public class CS_GUI_addPatient extends LoggingOut {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4516583930581651307L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The btn cancel. */
	private JButton btnCancel;
	
	/** The btn add patient. */
	private JButton btnAddPatient;
	
	/** The lblwarning message. */
	private JLabel lblwarningMessage = null;
	
	/** The name. */
	private JTextField fName;
	
	/** The l name. */
	private JTextField lName;
	
	/** The e mail. */
	private JTextField eMail;
	
	/** The phone. */
	private JTextField phone;
	
	/** The Pation id. */
	private JTextField PationID;
	
	/** The doctor id. */
	private JTextField doctorID;
	
	/** The Clinic box. */
	private JComboBox ClinicBox;
	
	/** The e mailcombo box. */
	private JComboBox eMailcomboBox;
	


	/**
	 * Create the frame.
	 */
	public CS_GUI_addPatient() {
		setResizable(false);
		setTitle("G-Health");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoctorGUI.class.getResource("/images/logo2.PNG")));
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

		JLabel label = new JLabel("New label");
		label.setBounds(0, 0, 0, 0);
		contentPane.add(label);

		JLabel lblLogo;
		if(LoginControl.getUser_full_name() == null)
			lblLogo = new JLabel("Welcome CS!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		
		
		
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		btnAddPatient = new JButton("ADD PATIENT");
		btnAddPatient.setBounds(220, 444, 109, 23);
		contentPane.add(btnAddPatient);
		
		fName = new JTextField();
		fName.setBounds(372, 163, 86, 20);
		contentPane.add(fName);
		fName.setColumns(10);
		
		lName = new JTextField();
		lName.setBounds(372, 205, 86, 20);
		contentPane.add(lName);
		lName.setColumns(10);
		
		eMail = new JTextField();
		eMail.setBounds(372, 242, 86, 20);
		contentPane.add(eMail);
		eMail.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(372, 285, 86, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		
		PationID = new JTextField();
		PationID.setBounds(372, 123, 86, 20);
		contentPane.add(PationID);
		PationID.setColumns(10);
		
		doctorID = new JTextField();
		doctorID.setBounds(372, 368, 86, 20);
		contentPane.add(doctorID);
		doctorID.setColumns(10);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(392, 444, 109, 23);
		contentPane.add(btnCancel);
		
		Label at = new Label("@");
		at.setBounds(464, 240, 15, 22);
		contentPane.add(at);
		
		Label label_2 = new Label("First Name");
		label_2.setBounds(252, 161, 62, 22);
		contentPane.add(label_2);
		
		Label label_3 = new Label("Last Name");
		label_3.setBounds(252, 203, 62, 22);
		contentPane.add(label_3);
		
		Label label_4 = new Label("Email");
		label_4.setBounds(252, 242, 62, 22);
		contentPane.add(label_4);
		
		Label label_5 = new Label("Phone");
		label_5.setBounds(252, 283, 62, 22);
		contentPane.add(label_5);
		
		Label label_6 = new Label("Private Clinic");
		label_6.setBounds(252, 324, 77, 22);
		contentPane.add(label_6);
		
		Label label_7 = new Label("Doctor ID");
		label_7.setBounds(252, 366, 62, 22);
		contentPane.add(label_7);
		
		Envelope en = Controller.Control(null, task.GET_PRIVATE_CLINIC_LIST);
		String [] str = new String[en.getobjList().size()];
		int i=0;
		for(Object obj : en.getobjList())
		{
			PrivateClinic pc;
			pc = (PrivateClinic)obj;
			str[i++] = (pc.getPrivateClinicName());
		}
		
		ClinicBox = new JComboBox<Object>(str);
		ClinicBox.setBounds(372, 324, 115, 20);
		contentPane.add(ClinicBox);
		
		String [] eMailList = {"gmail.com","hotmail.com","braude.ac.il","yahoo.com","walla.co.il","iol.com"};
		eMailcomboBox = new JComboBox<Object>(eMailList);
		eMailcomboBox.setBounds(485, 242, 115, 20);
		contentPane.add(eMailcomboBox);
		
		Label label_8 = new Label("Pation ID");
		label_8.setBounds(267, 123, 62, 22);
		contentPane.add(label_8);
		
		
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
	 * @param st the new warning message visible true
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
	 * Adds the patient action listener.
	 *
	 * @param e the event
	 */
	public void addPatientActionListener(ActionListener e)
	{
		btnAddPatient.addActionListener(e);
	}
	
	/**
	 * Adds the cancel action listener.
	 *
	 * @param e the event
	 */
	public void addCancelActionListener(ActionListener e)
	{
		btnCancel.addActionListener(e);
	}

	
	/**
	 * Gets the first  name.
	 *
	 * @return the first name
	 */
	public String getfName() {
		return fName.getText();
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getlName() {
		return lName.getText();
	}

	/**
	 * Gets the e mail.
	 *
	 * @return the e mail
	 */
	public String geteMail() {
		return eMail.getText();
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone.getText();
	}


	/**
	 * Sets the pation id.
	 *
	 * @param id the new pation id
	 */
	public void setPationID(String id){
		PationID.setText(id);
	}
	
	/**
	 * Gets the pation id.
	 *
	 * @return the pation id
	 */
	public String getPationID() {
		return PationID.getText();
	}

	/**
	 * Gets the doctor id.
	 *
	 * @return the doctor id
	 */
	public String getDoctorID() {
		return doctorID.getText();
	}
	
	/**
	 * Gets the clinic box.
	 *
	 * @return the clinic box
	 */
	public JComboBox getClinicBox()
	{
		return ClinicBox;
	}
	
	/**
	 * Gets the e mail box.
	 *
	 * @return the e mail box
	 */
	public JComboBox geteMailBox()
	{
		return eMailcomboBox;
	}
}
