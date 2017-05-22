package Server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * The Class ServerGui.
 *
 * @author G5 lab group
 * this class is Graphic user interface of Server which extends AbstractGuiServer
 * this class is the GUI we need to fill scheme, user name and password to workbench 
 */
public class ServerGui extends AbstractGuiServer {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5003284014897183840L;

	//private JPanel contentPane;
	/** all the buttons, labels and text fields. */
	private JButton loginBut = null;
	
	/** The lbl user name. */
//	private JLabel lblUserName = null;
    
    /** The lblwarning message. */
    private JLabel lblwarningMessage = null;
	
	/** The lbl password. */
//	private JLabel lblPassword = null;
	
	/** The lbl port. */
//	private JLabel lblPort = null;
	
	/** The lbl scheam. */
//	private JLabel lblScheam = null;
	
	/** The text field user. */
	private JTextField textFieldUser;
	
	/** The text field pass. */
	private JTextField textFieldPass;
	
	/** The text field port. */
	private JTextField textFieldPort;
	
	/** The text field scheam. */
	private JTextField textFieldScheam;
	
	
	/** The my font. */
	private Font myFont;
	
	/**
	 * constructor 
	 * this constructor add all the Label, Button,TextField to the Frame.
	 */
	public ServerGui() {
		myFont = new Font("Serif",Font.BOLD,17);
		getContentPane().setLayout(null);
		getContentPane().add(getLogin());
		getContentPane().add(getLblwarningMessage());
		getContentPane().add(getLblUserName());
		getContentPane().add(getLblPassword());
		getContentPane().add(getTextFieldUser());
		getContentPane().add(getTextFieldPass());
		getContentPane().add(getTextFieldport());
		getContentPane().add(getLblPort());
		getContentPane().add(getLblScheam());
		getContentPane().add(getTextFieldScheam());
		
		this.setTitle("GHealth Server Login");
		this.setBounds(500, 200, 400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	
	}
	
/**
 * Gets the lblwarning message.
 *
 * @return the lblwarning message
 */
//Getters and setter, etc.
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("Can't login to server");
			lblwarningMessage.setForeground(Color.RED);
			lblwarningMessage.setBounds(10, 20, 200, 30);
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
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
		lblwarningMessage.setBounds(15, 10, 245, 30);
		lblwarningMessage.setVisible(true);	
		
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public JButton getLogin() {
		
		if(loginBut == null ) {
			loginBut = new JButton("Login");
			loginBut.setBounds(150, 200, 80, 20);
		}
		return loginBut;
	}

	
	/**
	 * Gets the lbl user name.
	 *
	 * @return the lbl user name
	 */
	public JLabel getLblUserName() {
		JLabel lblUserName = null;
		
		if(lblUserName == null) {
			lblUserName = new JLabel("user name:");
			lblUserName.setBounds(15, 70, 90, 20);
			lblUserName.setFont(myFont);
		}
		return lblUserName;
	}
	
	/**
	 * Gets the lbl port.
	 *
	 * @return the lbl port
	 */
	public JLabel getLblPort() {
		JLabel lblPort = null;
		
		if(lblPort == null) {
			lblPort = new JLabel("port: ");
			lblPort.setBounds(15, 142, 90, 20);
			lblPort.setFont(myFont);
		}
		return lblPort;
	}

	/**
	 * Gets the lbl password.
	 *
	 * @return the lbl password
	 */
	public JLabel getLblPassword() {
		JLabel lblPassword = null;
		
		if(lblPassword == null){
			lblPassword = new JLabel("password :");
			lblPassword.setBounds(15, 105, 90, 20);
			lblPassword.setFont(myFont);
		}
		return lblPassword;
	}
	
	/**
	 * Gets the lbl scheam.
	 *
	 * @return the lbl scheam
	 */
	public JLabel getLblScheam() {
		JLabel lblScheam = null;
		
		if(lblScheam == null){
			lblScheam = new JLabel("Scheam:");
			lblScheam.setBounds(15, 40, 90, 20);
			lblScheam.setFont(myFont);
		}
		return lblScheam;
	}

	/**
	 * Gets the text field user.
	 *
	 * @return the text field user
	 */
	public JTextField getTextFieldUser() {
		if(textFieldUser == null) {
			textFieldUser = new JTextField();
			textFieldUser.setBounds(105, 73, 170, 20);
			textFieldUser.setColumns(10);
		}
		return textFieldUser;
	}

	/**
	 * Gets the text field pass.
	 *
	 * @return the text field pass
	 */
	public JTextField getTextFieldPass() {
		if(textFieldPass == null) {
			textFieldPass = new JTextField();
			textFieldPass.setBounds(105, 107, 170, 20);
			textFieldPass.setColumns(10);
		}
		return textFieldPass;
	}
	
	/**
	 * Gets the text fieldport.
	 *
	 * @return the text fieldport
	 */
	public JTextField getTextFieldport() {
		if(textFieldPort == null) {
			textFieldPort = new JTextField();
			textFieldPort.setBounds(105, 143, 170, 20);
			textFieldPort.setColumns(10);
		}
		return textFieldPort;
	}
	
	/**
	 * Gets the text field scheam.
	 *
	 * @return the text field scheam
	 */
	public JTextField getTextFieldScheam() {
		if(textFieldScheam == null) {
			textFieldScheam = new JTextField();
			textFieldScheam.setBounds(105, 43, 170, 20);
			textFieldScheam.setColumns(10);
		}
		return textFieldScheam;
	}
	
	/**
	 * Sets the text field pass.
	 *
	 * @param str the new text field pass
	 */
	public void setTextFieldPass(String str) {
		textFieldPass.setText(str);
	}
	
	/**
	 * Sets the text field user.
	 *
	 * @param str the new text field user
	 */
	public void setTextFieldUser(String str) {
		textFieldUser.setText(str);
	}
	
	/**
	 * Sets the text field port.
	 *
	 * @param str the new text field port
	 */
	public void setTextFieldPort(String str) {
		textFieldPort.setText(str);
	}
	
	/**
	 * Sets the text fieldscheam.
	 *
	 * @param str the new text fieldscheam
	 */
	public void setTextFieldscheam(String str) {
		textFieldScheam.setText(str);
	}
	
	
	
	/**
	 * Clear text.
	 */
	public void ClearText(){
		textFieldUser.setText("");
		textFieldPass.setText("");
		textFieldPort.setText("");
		textFieldScheam.setText("");
	}

	/**
	 * Gets the text user name.
	 *
	 * @return the text user name
	 */
	public String getTextUserName() {
		return textFieldUser.getText();
	}
	
	/**
	 * Gets the text password.
	 *
	 * @return the text password
	 */
	public String getTextPassword() {
		
		return textFieldPass.getText();
	}
	
	/**
	 * Gets the text port.
	 *
	 * @return the text port
	 */
	public String getTextPort() {
		
		return textFieldPort.getText();
	}
	
	/**
	 * Gets the text scheam.
	 *
	 * @return the text scheam
	 */
	public String getTextScheam() {
		
		return textFieldScheam.getText();
	}
	
	/**
	 * add to login button ActionListener.
	 *
	 * @param listener the listener
	 */
	public void addLoginActionListener(ActionListener listener){
		loginBut.addActionListener(listener);
	}

	/**
	 * Undisplay warning message.
	 */
	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);
		
	}
	
	
}
