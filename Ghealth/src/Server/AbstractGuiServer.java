package Server;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * @author G5 lab group
 * this class is Abstract GUI Server that extends JFrame.
 */
public abstract class AbstractGuiServer extends JFrame
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4109991313549908385L;
	
	/** The back button. */
	protected JButton backButton=null;

	
	
	/**
	 * Display info message.
	 *
	 * @param message the message
	 * @param title the title
	 * @param messageType the message type
	 */
	public void displayInfoMessage(String message,String title,int messageType)
	{
		JOptionPane.showMessageDialog(this, message, title,messageType);
	}
	
	/**
	 * This method adds an action listener to back to menu button.
	 *
	 * @param listener the listener
	 */
	public void addBackActionListener(ActionListener listener){
		backButton.addActionListener(listener);
	}
}
