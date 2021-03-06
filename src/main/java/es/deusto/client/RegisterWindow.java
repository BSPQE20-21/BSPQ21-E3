package es.deusto.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.apache.log4j.Logger;

import es.deusto.serialization.UserData;
/** This window allows users to introduce all the data for their registration\n
 * In order to create a user the following info is needed: \n
 * emailField, carNumberField, ageField, expenseLimitField
 * 
 */
public class RegisterWindow extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private JFrame frame; 
	private JButton buttonRegister, buttonEnd; 
	private JTextField emailField, carNumberField, ageField, expenseLimitField;  
	private JPasswordField passwordField; 
	private Thread thread;
	private final AtomicBoolean running = new AtomicBoolean(false);
	private ExampleClient client;

	static Logger logger = Logger.getLogger(RegisterWindow.class.getName());

	/**
	 * The constructor of the register window. \n
	 * @param client
	 */
	public RegisterWindow(ExampleClient client) {
		logger.info("Entering constructor");
		
		this.client = client;
		
		JPanel registerPanel = new JPanel();
		registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
		// EMAIL 
		registerPanel.add(new JLabel(client.getResourceBundle().getString("email")));
		this.emailField = new JTextField(20);
		registerPanel.add(this.emailField);
		// PASSWORD 
		registerPanel.add(new JLabel(client.getResourceBundle().getString("password")));
		this.passwordField = new JPasswordField(10);
		registerPanel.add(this.passwordField);
		// CARD NUMBER
		registerPanel.add(new JLabel(client.getResourceBundle().getString("cardNumber")));
		this.carNumberField = new JTextField(10);
		registerPanel.add(this.carNumberField);
		// AGE
		registerPanel.add(new JLabel(client.getResourceBundle().getString("age")));
		this.ageField = new JTextField(10);
		registerPanel.add(this.ageField);
		// EXPENSE LIMIT
		registerPanel.add(new JLabel(client.getResourceBundle().getString("expenseLimit"))); 
		this.expenseLimitField = new JTextField(10);
		registerPanel.add(this.expenseLimitField);


		this.buttonRegister = new JButton(client.getResourceBundle().getString("register"));
		registerPanel.add(this.buttonRegister); 
		
		buttonRegister.addActionListener(this);

		this.frame = new JFrame("Registration"); 
		this.frame.setTitle(client.getResourceBundle().getString("register"));

		this.frame.setSize(600,500);
		this.frame.getContentPane().add(registerPanel,"North"); 

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//WindowManager.middleLeft(this.frame); Is a class of the project
		this.frame.setVisible(true);

		thread = new Thread();
		thread.start();
	
	
	}
	/**
	 * This ACTION EVENT is connected to the REGISTER JButton\n
	 * Once the user introduces all the data a new OBJECT will be created (USER DATA)
	 * and the method es.desuto.client.ExampleClient.registerUser is called\n
	 * After that the user is sent to a new window es.deusto.client.AddExpenseWindow, so he/she can use all the fuctionalities of the system
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		
		if (target == this.buttonRegister) {
			try {
				logger.info("Entering Action Listener of register button");
				String login = this.emailField.getText(); 
				String password = String.valueOf(this.passwordField.getPassword()); 
				String cardNumber = this.carNumberField.getText(); 
				int age = Integer.parseInt(this.ageField.getText());
				double expenseLimit = Double.parseDouble(this.expenseLimitField.getText()); 
				
				UserData userData = new UserData();
				userData.setLogin(login);
				userData.setPassword(password);
				userData.setCardNumber(cardNumber);
				userData.setAge(age); 
				userData.setExpenseLimit(expenseLimit);
				logger.info("Storing the user ... "); 
				client.registerUser(userData);
				this.frame.setVisible(false);
				logger.info("Opening the AddExpenseWindow");
				AddExpenseWindow aew = new AddExpenseWindow(userData, client);
				//aew.setVisible(true);
				
			} catch (NumberFormatException exc) {
				// TODO 
			}
		}
	}
	
}
