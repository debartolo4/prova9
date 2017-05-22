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
 * @author G5 lab group
 * The Class when the doctor meets the patient menu GUI.
 */
public class Doctor_Pt_GUI extends LoggingOut {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1645191120165568000L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The btn view history. */
	private JButton btnViewHistory;
	
	/** The btn rec appoint. */
	private JButton btnRecAppoint;
	
	/** The lblwarning message. */
	private JLabel lblwarningMessage = null;
	
	/** The Search patient. */
	private JButton SearchPatient;
	
	/** The Log out. */
	private JButton LogOut;
	
	/** The btn create lab ref. */
	private JButton btnCreateLabRef;

 
	/**
	 * Create the frame.
	 */
	public Doctor_Pt_GUI() {
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
		if(LoginControl.getUser_full_name() == null)
			lblLogo = new JLabel("Welcome DOCTOR!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		btnRecAppoint = new JButton("RECORD APPOINTMENT");
		btnRecAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRecAppoint.setBounds(138, 220, 245, 68);
		contentPane.add(btnRecAppoint);
		
		btnViewHistory = new JButton("Medical History");
		btnViewHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnViewHistory.setBounds(138, 310, 245, 68);
		contentPane.add(btnViewHistory);
		

		
		SearchPatient = new JButton("SEARCH ANOTHER PATIENT");
		SearchPatient.setBounds(138, 130, 245, 68);
		contentPane.add(SearchPatient);
		
		LogOut = new JButton("Log Out");
		LogOut.setBounds(138, 492, 245, 68);
		LogOut.addActionListener(new LogOutListener());
		contentPane.add(LogOut);
		
		btnCreateLabRef = new JButton("Create Lab Ref");
		btnCreateLabRef.setBounds(138, 400, 245, 68);
		contentPane.add(btnCreateLabRef);
		
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
	 * Record appoint action listener.
	 *
	 * @param e the event
	 */
	public void RecordAppointActionListener(ActionListener e)
	{
		btnRecAppoint.addActionListener(e);
	}
	
	/**
	 * Log out action listener.
	 *
	 * @param e the event
	 */
	public void LogOutActionListener(ActionListener e)
	{
		LogOut.addActionListener(e);
	}
	
	/**
	 * Search patient action listener.
	 *
	 * @param e the event
	 */
	public void SearchPatientActionListener(ActionListener e)
	{
		SearchPatient.addActionListener(e);
	}
	
	/**
	 * View history action listener.
	 *
	 * @param e the event
	 */
	public void ViewHistoryActionListener(ActionListener e)
	{
		btnViewHistory.addActionListener(e);
	}
	
	
	/**
	 * Creates the lab action listener.
	 *
	 * @param e the event
	 */
	public void CreateLabActionListener(ActionListener e)
	{
		btnCreateLabRef.addActionListener(e);
	}
	
}

