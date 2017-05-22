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
 *@author G5 lab group
 * The Class of the Clinic manager GUI.
 */
public class CM_GUI extends LoggingOut {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8152476922277860117L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The lblwarning message. */
	private JLabel lblwarningMessage = null;
	
	/** The btn monthly report. */
	private JButton btnMonthlyReport;
	
	/** The weekly btn. */
	private JButton weeklyBtn;
	
	/**
	 * Create the frame.
	 */
	public CM_GUI() {
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
			lblLogo = new JLabel("Welcome Clinic Manager!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		weeklyBtn = new JButton("Show weekly report");
		
		weeklyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		weeklyBtn.setBounds(138, 146, 173, 68);
		contentPane.add(weeklyBtn);
		
		
		
		JButton LogOut = new JButton("Log Out");
		LogOut.setBounds(138, 400, 245, 68);
		LogOut.addActionListener(new LogOutListener());
		contentPane.add(LogOut);
		
		btnMonthlyReport = new JButton("Show monthly report");
		btnMonthlyReport.setBounds(138, 253, 173, 68);
		contentPane.add(btnMonthlyReport);
		
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
	 * Weekly action listener.
	 *
	 * @param e the event
	 */
	public void weeklyActionListener(ActionListener e)
	{
		weeklyBtn.addActionListener(e);
	}
	
	/**
	 * Monthly action listener.
	 *
	 * @param e the event
	 */
	public void monthlyActionListener(ActionListener e)
	{
		btnMonthlyReport.addActionListener(e);
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
