package GUI;


import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import client.LoginControl;


/**
 * @author G5 lab group
 * The Class of the Doctor create lab windows GUI.
 */
public class Doctor_Create_Lab_GUI extends LoggingOut {


	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2258527420854925774L;
	
	/** The content pane. */
	
	private JPanel contentPane;
	
	/** The text rec pane. */
	private JTextPane textRecPane;
	
	/** The btn cancel. */
	private JButton btnCancel;
	
	/** The Create lab ref. */
	private JButton CreateLabRef;
	
	/** The lblwarning message. */
	//private JButton btnCrtPt;
	private JLabel lblwarningMessage = null;
	
	/** The Search patient. */
	JButton SearchPatient;
	
	/** The Log out. */
	JButton LogOut;
	
	/**
	 * Create the frame.
	 */
	public Doctor_Create_Lab_GUI() {
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
		
		CreateLabRef = new JButton("CREATE LAB REF");
		CreateLabRef.setBounds(188, 467, 140, 23);
		contentPane.add(CreateLabRef);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(386, 467, 140, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new CancelListener());
		
		textRecPane = new JTextPane();
		textRecPane.setText("Please fill your requirements for the lab worker.");
		textRecPane.setBounds(202, 143, 273, 228);
		contentPane.add(textRecPane);
		
		
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
	 * Creates the new lab ref action listener.
	 *
	 * @param e the event
	 */
	public void CreateNewLabRefActionListener(ActionListener e)
	{
		CreateLabRef.addActionListener(e);
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
	 * Gets the record field.
	 *
	 * @return the record field
	 */
	public String getRecordField() 
	{
		return textRecPane.getText();
	}


	/**
	* Cancell listener of the button.
	*/
	public class CancelListener implements ActionListener 
    {
    	
		/** 
	     * closes the current frame of the class
	     */
	    @Override
    	public void actionPerformed(ActionEvent e)
    	{
    		dispose();
    	}	
    }//CancelListener
}
