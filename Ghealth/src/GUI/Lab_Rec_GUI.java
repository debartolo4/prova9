package GUI;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client.LoginControl;
import models.LabSettings;


/**
 * @author G5 lab group
 * The Class of the lab worker record GUI.
 */
public class Lab_Rec_GUI extends LoggingOut {


	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5798215983453009657L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The text rec pane. */
	private JTextPane textRecPane;
	
	/** The btn cancel. */
	private JButton btnCancel;
	
	/** The save rec. */
	private JButton saveRec;
	
	/** The lblwarning message. */
	//private JButton btnCrtPt;
	private JLabel lblwarningMessage = null;
	
	/** The btn upload file. */
	private JButton btnUploadFile;
	
	/** The Browse. */
	private JButton Browse;
	
	/** The fc. */
	private JFileChooser fc;
	
	/**
	 * Create the frame.
	 *
	 * @param lb the lb
	 */
	public Lab_Rec_GUI(LabSettings lb) 
	{

		setResizable(false);
		setTitle("G-Health");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoctorGUI.class.getResource("/images/logo2.PNG")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			lblLogo = new JLabel("Welcome Lab-Worker!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		saveRec = new JButton("SAVE RECORD");
		saveRec.setBounds(202, 503, 140, 23);
		contentPane.add(saveRec);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(374, 503, 140, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new CancelListener());
		
		textRecPane = new JTextPane();
		textRecPane.setText("Add your lab record here...");
		textRecPane.setBounds(202, 203, 273, 228);
		contentPane.add(textRecPane);
		
		JLabel lblDoctor = new JLabel("Doctor Requirements:");
		lblDoctor.setBounds(202, 102, 178, 14);
		contentPane.add(lblDoctor);
		
		JLabel DoctorReq = new JLabel(lb.getLabDoctorReq());
		DoctorReq.setVerticalAlignment(SwingConstants.TOP);
		DoctorReq.setBounds(202, 127, 288, 50);
		contentPane.add(DoctorReq);
		
		
		
		
		
		Browse = new JButton("Upload File");
		Browse.setBounds(202, 455, 140, 23);
		contentPane.add(Browse);
		
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
	 * Record lab action listener.
	 *
	 * @param e the e
	 */
	public void RecordLabActionListener(ActionListener e)
	{
		saveRec.addActionListener(e);
	}
	
	/**
	 * Adds the cancel action listener.
	 *
	 * @param e the e
	 */
	public void addCancelActionListener(ActionListener e)
	{
		btnCancel.addActionListener(e);
	}
		
	/**
	 * Gets the upload file button.
	 *
	 * @return the upload file button
	 */
	public JButton getUploadFileButton()
	{
		return btnUploadFile;
	}

	/**
	 * Gets the record field.
	 *
	 * @return the record field
	 */
	public String getRecordField() {
		return textRecPane.getText();
	}
	
	/**
	 * Gets the browse button.
	 *
	 * @return the browse button
	 */
	public JButton getBrowseButton()
	{
		return Browse;
	}
	
	/**
	 * Gets the file chooser.
	 *
	 * @return the file chooser
	 */
	public JFileChooser getFileChooser()
	{
		return fc;
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
