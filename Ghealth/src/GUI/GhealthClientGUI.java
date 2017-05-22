package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author G5 lab group
 * The Class GhealthClientGUI to choose which server connect to.
 */
public class GhealthClientGUI extends LoggingOut{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2806545028733372660L;
	
	
	/** The Label port. */
	private JLabel LabelPort = null;
	
	/** The Label host. */
	private JLabel LabelHost = null;
	
	/** The Text host. */
	private JTextField TextHost = null;
	
	/** The Text port. */
	private JTextField TextPort = null;
	
	/** The Button ok. */
	private JButton ButtonOk = null;
	
	/** The Button cancel. */
	private JButton ButtonCancel = null;

	/**
	 * Instantiates a new ghealth client gui.
	 */
	public GhealthClientGUI() {
		getContentPane().setLayout(null);
		
		LabelPort = new JLabel("Port");
		LabelPort.setBounds(10, 40, 46, 14);
		getContentPane().add(LabelPort);
		
		LabelHost = new JLabel("Host");
		LabelHost.setBounds(10, 11, 46, 14);
		getContentPane().add(LabelHost);
		
		getContentPane().add(getTextHost());
		getContentPane().add(getTextPort());
		
		ButtonOk = new JButton("OK");
		ButtonOk.setBounds(23, 180, 89, 23);
		getContentPane().add(ButtonOk);
		
		ButtonCancel = new JButton("Cancel");
		ButtonCancel.setBounds(122, 180, 89, 23);
		getContentPane().add(ButtonCancel);
		ButtonCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		
		this.setTitle("GHealth Connection to Server");
		this.setBounds(500, 200, 300, 250);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	/**
	 * Gets the text host.
	 *
	 * @return the text host
	 */
	private JTextField getTextHost(){
		if(TextHost == null){
			TextHost = new JTextField();
			TextHost.setBounds(51, 9, 160, 20);
			TextHost.setColumns(10);
			TextHost.setText("localhost");
		}
		return TextHost;
	}

	/**
	 * Gets the text port.
	 *
	 * @return the text port
	 */
	private JTextField getTextPort(){
		if(TextPort == null){
			TextPort = new JTextField();
			TextPort.setBounds(51, 37, 160, 20);
			TextPort.setColumns(10);
			TextPort.setText("5555");
		}
		return TextPort;
	}
	
	/**
	 * Clear fields.
	 */
	public void clearFields(){
		TextHost.setText("");
		TextPort.setText("");
	}
	
	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public String getHost(){
		return TextHost.getText();
	}
	
	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort(){
		if(TextPort.getText().equals(""))
			return 0;
		return Integer.parseInt(TextPort.getText());
	}

	/**
	 * Adds the ok action listener.
	 *
	 * @param listener the listener
	 */
	public void addOKActionListener(ActionListener listener){
		ButtonOk.addActionListener(listener);
	}

	/**
	 * Adds the cancel action listener.
	 *
	 * @param listener the listener
	 */
	public void addCancelActionListener(ActionListener listener){
		ButtonCancel.addActionListener(listener);
	}
	
	/**
	 * Display warnning message.
	 *
	 * @param msg the msg
	 */
	public void displayWarnningMessage(String msg)
	{
		JOptionPane.showMessageDialog(this, msg);
	}
	
}
