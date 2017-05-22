package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import client.LoginControl;
import enums.DoctorSpeciallity;

/**
 * @author G5 lab group
 * The Class costumer service fill the form for new appointment.
 */
public class CS_GUI_newAppoint extends LoggingOut {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1645191120165568000L;
	
	/** The btn cancel appoint. */
	private JButton btnCancelAppoint;
	
	/** The btn crt appoint. */
	private JButton btnCrtAppoint;
	
	/** The lblwarning message. */
	private JLabel lblwarningMessage = null;
	
	/** The Doctor type box. */
	private JComboBox<?> DoctorTypeBox;
	
	/** The Doctor_and_ clinic box. */
	private JComboBox<?> Doctor_and_ClinicBox;
	
	/** The Doctor hours box. */
	private JComboBox<?> DoctorHoursBox;
	
	/** The doctor hours. */
	private JLabel lblDoctorHours;
	
	/** The  doctor_and_ clinic. */
	private JLabel lblDoctor_and_Clinic;
	
	/** The doctor type. */
	private JLabel lblDoctorType;
	
	/** The date picker. */
	private JDatePickerImpl datePicker;
	
	/** The calendar. */
	private Panel cal;
	
	/** The button choose date. */
	private JButton btnChooseDate;
	
	/**
	 * Create the frame.
	 */
	public CS_GUI_newAppoint() {
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
			lblLogo = new JLabel("Welcome CS!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 109);
		contentPane.add(lblLogo);
		
		btnCrtAppoint = new JButton("CREATE APPOINTMENT");
		btnCrtAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrtAppoint.setBounds(413, 470, 172, 68);
		contentPane.add(btnCrtAppoint);
		
		btnCancelAppoint = new JButton("CANCEL APPOINTMENT");
		btnCancelAppoint.setBounds(202, 470, 172, 68);
		contentPane.add(btnCancelAppoint);
		btnCancelAppoint.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		
		DoctorTypeBox = new JComboBox<Object>(DoctorSpeciallity.values());
		DoctorTypeBox.setBounds(38, 157, 106, 20);
		contentPane.add(DoctorTypeBox);
		
		lblDoctorType = new JLabel("Choose Doctor Type:");
		lblDoctorType.setBounds(38, 138, 183, 14);
		contentPane.add(lblDoctorType);
		
		Doctor_and_ClinicBox = new JComboBox<Object>();
		Doctor_and_ClinicBox.setBounds(38, 217, 328, 20);
		Doctor_and_ClinicBox.setVisible(false);
		contentPane.add(Doctor_and_ClinicBox);
		
		lblDoctor_and_Clinic = new JLabel("Choose Clinic & Doctor:");
		lblDoctor_and_Clinic.setBounds(38, 192, 183, 14);
		lblDoctor_and_Clinic.setVisible(false);
		contentPane.add(lblDoctor_and_Clinic);
		
		DoctorHoursBox = new JComboBox<Object>();
		DoctorHoursBox.setBounds(38, 305, 207, 20);
		DoctorHoursBox.setVisible(false);
		contentPane.add(DoctorHoursBox);
		
		lblDoctorHours = new JLabel("Doctor Hours");
		lblDoctorHours.setBounds(38, 282, 143, 14);
		lblDoctorHours.setVisible(false);
		contentPane.add(lblDoctorHours);
		
		UtilDateModel model = new UtilDateModel();
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH); 
		int year = calendar.get(Calendar.YEAR); 
		model.setDate(year, month, day);
		model.setSelected(true);
		Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setVisible(true);
		cal = new Panel();
		cal.add(datePicker);
		cal.setBounds(26, 243, 278, 33);
		cal.setVisible(false);
		contentPane.add(cal,BorderLayout.WEST);
		
		btnChooseDate = new JButton("OK");
		cal.add(btnChooseDate);
	
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
	 * Creates the new appoint action listener.
	 *
	 * @param e the event
	 */
	public void createNewAppointActionListener(ActionListener e)
	{
		btnCrtAppoint.addActionListener(e);
	}
	
	/**
	 * Cancel new appoint action listener.
	 *
	 * @param e the event
	 */
	public void cancelNewAppointActionListener(ActionListener e)
	{
		btnCancelAppoint.addActionListener(e);
	}
	
	/**
	 * Select doc type action listener.
	 *
	 * @param e the event
	 */
	public void SelectDocTypeActionListener(ActionListener e){
		
		DoctorTypeBox.addActionListener(e);
		
	}
	
	/**
	 * Doctor box action listener.
	 *
	 * @param e the event
	 */
	public void DoctorBoxActionListener(ActionListener e){
		
		Doctor_and_ClinicBox.addActionListener(e);
	}
	
/**
 * Doctor hours box action listener.
 *
 * @param e the event
 */
public void DoctorHoursBoxActionListener(ActionListener e){
		
		DoctorHoursBox.addActionListener(e);
	}
	

	/**
 * Sets the patient id.
 *
 * @param ptIDstr the new patient id
 */
public void setPtID(String ptIDstr) {
		
		JLabel ptID = new JLabel(ptIDstr);
		ptID.setBounds(372, 120, 100, 14);
		contentPane.add(ptID);
	}

	/**
	 * Gets the doctor type box.
	 *
	 * @return the doctor type box
	 */
	public JComboBox<?> getDoctorTypeBox() {
		return DoctorTypeBox;
	}

	/**
	 * Gets the doctor_and_ clinic box.
	 *
	 * @return the doctor_and_ clinic box
	 */
	public JComboBox<?> getDoctor_and_ClinicBox() {
		return Doctor_and_ClinicBox;
	}
	
	/**
	 * Gets the doctor hours box.
	 *
	 * @return the doctor hours box
	 */
	public JComboBox<?> getDoctorHoursBox() {
		return DoctorHoursBox;
	}

	/**
	 * Gets the lbl doctor hours.
	 *
	 * @return the lbl doctor hours
	 */
	public JLabel getLblDoctorHours() {
		return lblDoctorHours;
	}

	/**
	 * Gets the lbl doctor_and_ clinic.
	 *
	 * @return the lbl doctor_and_ clinic
	 */
	public JLabel getLblDoctor_and_Clinic() {
		return lblDoctor_and_Clinic;
	}

	/**
	 * Gets the lbl doctor type.
	 *
	 * @return the lbl doctor type
	 */
	public JLabel getLblDoctorType() {
		return lblDoctorType;
	}

	/**
	 * Gets the cal.
	 *
	 * @return the cal
	 */
	public Panel getCal() {
		return cal;
	}	
	
	/**
	 * Gets the date picker.
	 *
	 * @return the date picker
	 */
	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}	
	
	/**
	 * Gets the choosen date ok.
	 *
	 * @return the choosen date ok
	 */
	public JButton getChoosenDateOK() {
		return btnChooseDate;
	}
	
	/**
	 * Gets the btn crt appoint.
	 *
	 * @return the btn crt appoint
	 */
	public JButton getbtnCrtAppoint() {
		return btnCrtAppoint;
	}
}

