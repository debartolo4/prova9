package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.User;

/**
 * @author G5 lab group
 * The Class MainWindowGUI.
 */
public class MainWindowGUI extends LoggingOut {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8911802687405241262L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The btn home. */
	private JButton btnHome;
	
	/** The btn manage files. */
	private JButton btnManageFiles;
	
	/** The btn manage groups. */
	private JButton btnManageGroups;
	
	/**
	 * Create the frame. This is the Home window.
	 * Launch the application.
	 *
	 * @param user the user
	 */
	public MainWindowGUI(User user) {

		setResizable(false);
		setTitle("G-Health");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindowGUI.class.getResource("/images/logo2.png")));
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 0, 0, 0);
		contentPane.add(label);
		
		JLabel lblLogo = new JLabel("Logo");
		lblLogo.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);

		JLabel lblIPersonalBox = new JLabel("USER "+user.getuID());
		lblIPersonalBox.setForeground(new Color(51, 51, 255));
		lblIPersonalBox.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblIPersonalBox.setBounds(315, 169, 156, 63);
		contentPane.add(lblIPersonalBox);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	} 
	
	

	
	
	// Action Listeners
	
	/**
	 * Addbtn home action listener.
	 *
	 * @param e the event
	 */
	public void addbtnHomeActionListener(ActionListener e)
	{
		btnHome.addActionListener(e);
	}
	
	/**
	 * Addbtn manage files action listener.
	 *
	 * @param e the event
	 */
	public void addbtnManageFilesActionListener(ActionListener e)
	{
		btnManageFiles.addActionListener(e);
	}
	
	/**
	 * Addbtn manage groups action listener.
	 *
	 * @param e the event
	 */
	public void addbtnManageGroupsActionListener(ActionListener e)
	{
		btnManageGroups.addActionListener(e);
	}
}
