package Server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * @author G5 lab group
 * this class is Graphic user interface of Server log.
 *
 */
public class serverLogGui extends AbstractGuiServer{
	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 1L;
	/**all the buttons, labels, panels and text fields  */
	private JPanel CustomerReportPanel = null ;
	private JScrollPane scroller = null;
	private JTextArea textArea = null;
	private String textDetail = "";
	/**
	 * constructor 
	 * this constructor create new instance of panel
	 */
	public serverLogGui(){
		super();
		CustomerReportPanel = new JPanel();
		initReport();
	}
	/**
	 * Initialize report log
	 */
	
	
	public void setLog(String str){
		this.textArea.append(str + "\n");
	}
	
	
	public void initReport(){
		textArea = new JTextArea("Server log",23,38);
		textArea.setFont(new Font("Serif",Font.BOLD,15));
		textArea.setEditable(false);
		setTitle(" Server log ");
		//setBounds(150, 150, 300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,550);
		setContentPane(getCustomerReportPanel());	
		
	}
	/**
	 * get Customer Report Panel and create new JScrollPane
	 * @return JPanel
	 */
	public JPanel getCustomerReportPanel() {
		setLayout(null);
        textArea.setText(textDetail);
        if(scroller == null){
        scroller = new JScrollPane(textArea);
        CustomerReportPanel.add(scroller);
        CustomerReportPanel.add(getBackB());
        CustomerReportPanel.setBackground(Color.lightGray);
        }
        setSize(500, 600);
       setLocationRelativeTo ( null );
        setVisible(true);
		return CustomerReportPanel;
	}

	public JButton getBackB(){
		if(backButton == null){
			backButton = new JButton("LogOut");
			backButton.setBounds(80, 300, 80, 20);
		}
		return backButton;
	}
	public void setCustomerReportPanel(JPanel customerReportPanel) {
		CustomerReportPanel = customerReportPanel;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public String getStr() {
		return textDetail;
	}

	public void setStr(String str) {
		this.textDetail = str;
	}

	public JScrollPane getScroller() {
		return scroller;
	}
	public void addDisconnectedBottonActionListener(ActionListener listener){
		backButton.addActionListener(listener);

	} 


}
