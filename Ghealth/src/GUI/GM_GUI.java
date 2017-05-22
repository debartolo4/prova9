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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import client.Controller;
import client.LoginControl;
import enums.task;
import models.Clinic;
import models.Envelope;



/**
 * @author G5 lab group
 * The Class of the general manager windows GUI.
 */
public class GM_GUI extends LoggingOut {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1583234608338407492L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The lblwarning message. */
	private JLabel lblwarningMessage = null;
	
	/** The weekly btn. */
	private JButton weeklyBtn;
	
	/** The monthly btn. */
	private JButton OKmonthBtn;
	
	/** The date picker from. */
	private JDatePickerImpl datePickerFrom;
	
	/** The cal_from. */
	private Panel cal_from;
	
	/** The btn choose date from. */
	private JButton btnChooseDateFrom;
	
	/** The MonthBox choose date from. */
	private JComboBox MonthBox;
	
	/** The date picker to. */
	private JDatePickerImpl datePickerTo;
	
	/** The cal_to. */
	private Panel cal_to;
	
	/** The btn choose date to. */
	private JButton btnChooseDateTo;
	
	/** The lbl select date from. */
	private JLabel lblSelectDateFrom;
	
	/** The lbl select date to. */
	private JLabel lblSelectDateTo;
	
	/** The separator. */
	private JSeparator separator;
	
	/** The separator_1. */
	private JSeparator separator_1;
	
	/** The separator_2. */
	private JSeparator separator_2;
	
	private JList<String> list;
	
	
	
	/**
	 * Create the frame.
	 */
	public GM_GUI() {
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
			lblLogo = new JLabel("Welcome General Manager!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		weeklyBtn = new JButton("Show report with this date range");
		
		weeklyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		weeklyBtn.setBounds(237, 352, 240, 35);
		contentPane.add(weeklyBtn);
		
		
		
		JButton LogOut = new JButton("Log Out");
		LogOut.setBounds(617, 493, 100, 30);
		LogOut.addActionListener(new LogOutListener());
		contentPane.add(LogOut);
		
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
		
        
        UtilDateModel model_b = new UtilDateModel();
		Calendar calendar_b = Calendar.getInstance();
		int day_b = calendar_b.get(Calendar.DAY_OF_MONTH);
		int month_b = calendar_b.get(Calendar.MONTH); 
		int year_b = calendar_b.get(Calendar.YEAR); 
		model_b.setDate(year, month, day);
		model_b.setSelected(true);
		Properties p_b = new Properties();
		p_b.put("text.today", "Today");
		p_b.put("text.month", "Month");
		p_b.put("text.year", "Year");
		JDatePanelImpl datePanela = new JDatePanelImpl(model,p);
		JDatePanelImpl datePanelb = new JDatePanelImpl(model_b,p_b);
		
		datePickerFrom = new JDatePickerImpl(datePanela, new DateLabelFormatter());
		datePickerFrom.setVisible(true);
		cal_from = new Panel();
		cal_from.add(datePickerFrom);
		cal_from.setBounds(33, 302, 278, 33);
		cal_from.setVisible(true);
		contentPane.add(cal_from,BorderLayout.WEST);		
		btnChooseDateFrom = new JButton("OK");
		cal_from.add(btnChooseDateFrom);		

		datePickerTo = new JDatePickerImpl(datePanelb, new DateLabelFormatter());
		datePickerTo.setVisible(true);
		cal_to = new Panel();
		cal_to.add(datePickerTo);
		cal_to.setBounds(439, 302, 278, 33);
		cal_to.setVisible(true);
		contentPane.add(cal_to,BorderLayout.WEST);		
		btnChooseDateTo = new JButton("OK");
		btnChooseDateTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cal_to.add(btnChooseDateTo);		
		
		lblSelectDateFrom = new JLabel("Select date From");
		lblSelectDateFrom.setBounds(33, 262, 278, 33);
		contentPane.add(lblSelectDateFrom);
		
		lblSelectDateTo = new JLabel("Select date To");
		lblSelectDateTo.setBounds(439, 262, 278, 33);
		contentPane.add(lblSelectDateTo);
		
		MonthBox = new JComboBox();
		MonthBox.addItem(1);MonthBox.addItem(2);MonthBox.addItem(3);
		MonthBox.addItem(4);MonthBox.addItem(5);MonthBox.addItem(6);
		MonthBox.addItem(7);MonthBox.addItem(8);MonthBox.addItem(9);
		MonthBox.addItem(10);MonthBox.addItem(11);MonthBox.addItem(12);
		MonthBox.setBounds(33, 447, 55, 20);
		MonthBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(MonthBox);
		
		JLabel lblLastMonthsAmount = new JLabel("Last months amount");
		lblLastMonthsAmount.setBounds(33, 407, 130, 14);
		contentPane.add(lblLastMonthsAmount);
		
		OKmonthBtn = new JButton("Show reports with this number of months back");
		OKmonthBtn.setBounds(203, 440, 310, 35);
		contentPane.add(OKmonthBtn);
		
		separator = new JSeparator();
		separator.setBounds(0, 398, 795, 2);
		contentPane.add(separator);
		
		
		Envelope en = Controller.Control(null, task.GET_CLINIC_LIST);
		String [] str = new String[en.getobjList().size()];
		int i=0;
		for(Object obj : en.getobjList())
		{
			Clinic cl = new Clinic();
			cl = (Clinic)obj;
			str[i++] = cl.toString();
		}
		
		
		list = new JList<String>(str); //JList list = new JList();//
		//list.setToolTipText("Use 'CTRL' to select more than one item");
		//System.out.println(list.getSelectedValuesList());

		list.setBounds(237, 111, 240, 140);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("Please choose the clinics you want reports on");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(223, 86, 270, 14);
		contentPane.add(lblNewLabel);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 263, 795, 2);
		contentPane.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(0, 480, 795, 2);
		contentPane.add(separator_2);
		
		setLocationRelativeTo(null);
		
		setVisible(true);
		
	
	}
	
	
	/**
	 * Gets the clinic selection.
	 *
	 * @return the clinic selection
	 */
	public String getClinicSel(){
		if(!list.getSelectedValuesList().isEmpty())
			return list.getSelectedValuesList().get(0);
		return null;
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
	 * @param e the e
	 */
	public void weeklyActionListener(ActionListener e)
	{
		weeklyBtn.addActionListener(e);
	}
	
	public int getMonthBoxIndex()
	{
		return MonthBox.getSelectedIndex();
	}
	
	/**
	 * Monthly action listener.
	 *
	 * @param e the event
	 */
	public void monthlyActionListener(ActionListener e)
	{
		OKmonthBtn.addActionListener(e);
	}
	
	/**
	 * Gets the calendar.
	 *
	 * @return the calendar
	 */
	public Panel getCal() {
		return cal_from;
	}	
	
	/**
	 * Gets the date picker.
	 *
	 * @return the date picker
	 */
	public JDatePickerImpl getDatePickerFrom() {
		return datePickerFrom;
	}
	
	public JDatePickerImpl getDatePickerTo() {
		return datePickerTo;
	}
	
	/**
	 * Gets the choosen date ok.
	 *
	 * @return the choosen date ok
	 */
	public JButton getChoosenDateFrom() {
		return btnChooseDateFrom;
	}
	public JButton getChoosenDateTo() {
		return btnChooseDateTo;
	}


	/**
	* Cancel listener of the button.
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
