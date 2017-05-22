package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Games.Breakout.Breakout;
import Games.Pacman.Pacman;
import Games.Snake.Snake;
import Games.SpaceInvaders.SpaceInvaders;
import Games.Tetris.Tetris;


/**
 * @author G5 lab group
 * The Class LoginGUI.
 */
public class LoginGUI extends LoggingOut {

	
	/** serialVersion. */
	private static final long serialVersionUID = -510417858888515993L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The user field. */
	private JTextField userField;
	
	/** The password field. */
	private JPasswordField passwordField;
	
	/** The btn cancel. */
	//private JTextField passwordField;
	private JButton btnCancel;
	
	/** The btn login. */
	private JButton btnLogin;
	
	/** The lblwarning message. */
	private JLabel lblwarningMessage = null;




	/**
	 * Create the frame of the user log in GUI.
	 */
	public LoginGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginGUI.class.getResource("/images/logo2.png")));
		setTitle("Ghealth Login");
		setResizable(false);

		setBounds(200, 200, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel label = new JLabel("");
		label.setBounds(10, 30, 1200, 200);
		label.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/logo2.png")));
		contentPane.add(label);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 16));
		lblUsername.setBounds(200, 300, 87, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 16));
		lblPassword.setBounds(200, 330, 87, 16);
		contentPane.add(lblPassword);
		
		userField = new JTextField();
		userField.setBounds(290, 300, 139, 22);
		contentPane.add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(290, 330, 139, 22);
		contentPane.add(passwordField);
		
		btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/login.PNG")));
		btnLogin.setBounds(210, 370, 97, 25);
		contentPane.add(btnLogin);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/cancel.PNG")));
		btnCancel.setBounds(350, 370, 97, 25);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		
		
		JButton btnSecret = new JButton("Ghealth Entertainment Generator");
		btnSecret.setBounds(441, 437, 243, 23);
		contentPane.add(btnSecret);
		
		btnSecret.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				

				
				int x = ThreadLocalRandom.current().nextInt(1, 5 + 1);
				
				//x is now 1-5
				
				switch(x){
				
				case 1:
					
					   EventQueue.invokeLater(new Runnable() {
				            @Override
				            public void run() {                
				                JFrame ex = new Snake();
				                ex.setVisible(true);                
				            }
				        });
					
				
					break;
					
				case 2:
					  EventQueue.invokeLater(new Runnable() {

				            @Override
				            public void run() {
				                Pacman ex = new Pacman();
				                ex.setVisible(true);
				            }
				        });
					
					break;
				
				case 3:
					
					new SpaceInvaders();
					break;
				case 4:
					
					Tetris game = new Tetris();
			        game.setLocationRelativeTo(null);
			        game.setVisible(true);
			        
					break;
				case 5:
					
					EventQueue.invokeLater(new Runnable() {
			            @Override
			            public void run() {                
			                Breakout game = new Breakout();
			                game.setVisible(true);                
			            }
			        });
					
					break;
					
				default:
					break;
				
				}
				 
			}
		});
		
		
		setLocationRelativeTo(null);
		
		setVisible(true);
	
	}
	// Action Listeners
	
	
	/**
	 * Gets the lblwarning message.
	 *
	 * @return the lblwarning message
	 */
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("user name or password is wrong");
			lblwarningMessage.setForeground(Color.RED);
			lblwarningMessage.setBounds(10, 165, 200, 30);
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
	 * Adds the login action listener.
	 *
	 * @param e the event
	 */
	public void addLoginActionListener(ActionListener e)
	{
		btnLogin.addActionListener(e);
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
	
	// Getters
	
	/**
	 * Gets the user field.
	 *
	 * @return the user field
	 */
	public String getUserField() {
		return userField.getText();
	}

	/**
	 * Gets the password field.
	 *
	 * @return the password field
	 */
	public String getPasswordField() {
		String pass = String.copyValueOf(passwordField.getPassword());
		return pass;
	}
}


