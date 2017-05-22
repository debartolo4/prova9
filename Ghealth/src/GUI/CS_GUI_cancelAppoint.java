package GUI;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.LoginControl;

/**
 * @author G5 lab group
 * The Class  costumer service of cancel Appointment.
 */
public class CS_GUI_cancelAppoint extends LoggingOut{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1645191120165568000L;

	/** The combo box. */
	private JComboBox comboBox;
	
	/** The btn cancel appointment. */
	private JButton btnCancelAppointment;
	
	/** The btn cancel appoint. */
	private JButton btnCancelAppoint;
	
	/**
	 * Instantiates a new c s_ gu i_cancel appoint.
	 */
	public CS_GUI_cancelAppoint() {
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
		
		comboBox = new JComboBox();
		comboBox.setBounds(58, 147, 443, 24);
		contentPane.add(comboBox);
		
		JLabel lblPleaseSelectAppointment = new JLabel("Please select appointment to cancel");
		lblPleaseSelectAppointment.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPleaseSelectAppointment.setBounds(87, 112, 359, 24);
		contentPane.add(lblPleaseSelectAppointment);
		
		btnCancelAppointment = new JButton("Cancel Selected Appointment");
		btnCancelAppointment.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnCancelAppointment.setBounds(87, 224, 391, 65);
		contentPane.add(btnCancelAppointment);
		JLabel lblLogo;
		if(LoginControl.getUser_full_name() == null)
			lblLogo = new JLabel("Welcome CS!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		
		btnCancelAppoint = new JButton("CLOSE WINDOW");
		btnCancelAppoint.setBounds(202, 470, 172, 68);
		contentPane.add(btnCancelAppoint);
		btnCancelAppoint.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});

		setLocationRelativeTo(null);
		
		setVisible(true);
	}

	/**
	 * Gets the btn cancel appointment.
	 *
	 * @return the btn cancel appointment
	 */
	public JButton getbtnCancelAppointment()
	{
		return btnCancelAppointment;
	}

	/**
	 * Gets the combo box.
	 *
	 * @return the combo box
	 */
	public JComboBox getcomboBox()
	{
		return comboBox;
	}
}
