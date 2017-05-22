package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.LoginControl;


/**
 * @author G5 lab group
 * The Class of doctor view history GUI.
 */
public class Doctor_History_GUI extends LoggingOut {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4721311421959450478L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The btn cancel. */
	private JButton btnCancel;
	
	/** The lblwarning message. */
	private JLabel lblwarningMessage = null;
	
	/** The Appointment box. */
	private JComboBox<?> AppointmentBox;
	
	/** The Lab res box. */
	private JComboBox<?> LabResBox;
	
	/** The editor pane. */
	private JEditorPane editorPane;
	
	/** The image pan. */
	private JPanel imagePan;
	
	/** The imglabel. */
	private JLabel imglabel;
	
	/** The btnLabHistory. */
	private JButton btnLabHistory;
	
	/** The btnAppointmentHistory. */
    private JButton btnAppointmentHistory;
    
    private JLabel LaborAppTitle;
	/**
	 * Create the frame.
	 */
	public Doctor_History_GUI() {
		setResizable(false);
		setTitle("G-Health");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoctorGUI.class.getResource("/images/logo2.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(630, 523, 140, 23);
		contentPane.add(btnCancel);
		
		AppointmentBox = new JComboBox<Object>();
		AppointmentBox.setBounds(10, 209, 465, 20);
		contentPane.add(AppointmentBox);
		
		LaborAppTitle = new JLabel("Appointment History");
		LaborAppTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		LaborAppTitle.setBounds(10, 184, 140, 14);
		contentPane.add(LaborAppTitle);

		/*
		JLabel lblLabResultHistory = new JLabel("Lab Result History");
		lblLabResultHistory.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLabResultHistory.setBounds(10, 184, 140, 14);
		contentPane.add(lblLabResultHistory);
		*/
		
		LabResBox = new JComboBox<Object>();
		LabResBox.setBounds(10, 209, 465, 20);
		contentPane.add(LabResBox);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(10, 274, 164, 286);
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);

		contentPane.add(editorPane);
		
		imagePan = new JPanel();
		imagePan.setLocation(175, 271);
		imagePan.setSize(300, 300);
		imagePan.setVisible(true);
    	contentPane.add(imagePan);
    	imglabel = new JLabel();
        imagePan.add(imglabel);
        
        
        btnAppointmentHistory = new JButton("Appointment History");
        btnAppointmentHistory.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAppointmentHistory.setBounds(43, 119, 176, 54);
        contentPane.add(btnAppointmentHistory);
        
        btnLabHistory = new JButton("Lab History");
        btnLabHistory.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLabHistory.setBounds(249, 119, 176, 54);
        contentPane.add(btnLabHistory);
        
        JLabel Summerylabel = new JLabel("Summery:");
        Summerylabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        Summerylabel.setBounds(10, 251, 140, 14);
        contentPane.add(Summerylabel);
    	
    	
		btnCancel.addActionListener(new CancelListener());
		
		
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
	 * Adds the cancel action listener.
	 *
	 * @param e the event
	 */
	public void addCancelActionListener(ActionListener e)
	{
		btnCancel.addActionListener(e);
	}
	
	/**
	 * Sets the summery.
	 *
	 * @param summery the summery
	 */
	public void SetSummery(String summery) {
		editorPane.setText(summery);
	}

	/**
	 * Gets the appointment history box.
	 *
	 * @return the appointment history box
	 */
	public JComboBox<?> getAppointmentHistoryBox() {
		return AppointmentBox;
	}
	
	/**
	 * Gets the lab result box.
	 *
	 * @return the lab result box
	 */
	public JComboBox<?> getLabResultBox() {
		return LabResBox;
	}
	
	/**
	 * Appointment history box action listener.
	 *
	 * @param e the event
	 */
	public void AppointmentHistoryBoxActionListener(ActionListener e){
		
		AppointmentBox.addActionListener(e);
	}
	
	/**
	 * Lab result box action listener.
	 *
	 * @param e the event
	 */
	public void LabResultBoxActionListener(ActionListener e){
		
		LabResBox.addActionListener(e);
	}
	
	/**
	 * Gets the editor pane.
	 *
	 * @return the editor pane
	 */
	public  JEditorPane geteditorPane()
	{
			return editorPane;
	}

	/**
	 * Gets the image pan.
	 *
	 * @return the image pan
	 */
	public JPanel getimagePan()
	{
		return imagePan;
	}
	
	/**
	 * Gets the Lab History Button.
	 *
	 * @return  Lab History Button
	 */
	public JButton getLabHistoryButton()
	{
        return btnLabHistory;
	}
	
	/**
	 * Gets the Appointment History Button.
	 *
	 * @return  Appointment History Button
	 */
	public JButton getAppHistoryButton()
	{
        return btnAppointmentHistory;
	}
	
	/**
	 * Sets the adds the to image pan.
	 *
	 * @param Path the new adds the to image pan
	 */
	public void setAddToImagePan(String Path)
	{
		Image image = null;
        try {
        	File sourceimage = new File(Path);
        	image = ImageIO.read(sourceimage);
        	image = getScaledImage(image,300,300);
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        imglabel.setIcon(new ImageIcon(image));
              
	}
	
	/**
	 * get the title of combobox.
	 *
	 * @return LaborAppTitle
	 */
	public JLabel getTitleLabel()
	{
		return LaborAppTitle;
	}
	
	
	/**
	 * Gets the scaled image.
	 *
	 * @param srcImg the src img
	 * @param w the width
	 * @param h the hight
	 * @return the scaled image
	 */
	private static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
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
