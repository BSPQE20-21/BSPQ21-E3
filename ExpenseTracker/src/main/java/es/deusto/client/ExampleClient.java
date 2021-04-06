package es.deusto.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.deusto.serialization.DirectedMessage;
import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;
import es.deusto.server.jdo.Expense;

// This class is the client side of the arquitecture. There is nothing else on this side but we are going to need:
// GUI - windows
// Add more patterns ? 
// - Controlles 
// - service locator 
// We have this done for the other project EasyBooking it should not be hard to replicate


// Methods we will need: 
// register user (already done but we need more parameters)
// login 
// include expense

public class ExampleClient implements ActionListener, Runnable  {
	
	private JFrame frame; 
	private JButton buttonRegister, buttonEnd; 
	private JTextField emailField, carNumberField, ageField, expenseLimitField;  
	private JPasswordField passwordField; 
	private Thread thread;
	private final AtomicBoolean running = new AtomicBoolean(false);

	private Client client;
	private WebTarget webTarget;

	public ExampleClient(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/server", hostname, port));
	
		JPanel registerPanel = new JPanel();
		registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
		// EMAIL 
		registerPanel.add(new JLabel("Email: "));
		this.emailField = new JTextField(20);
		registerPanel.add(this.emailField);
		// PASSWORD 
		registerPanel.add(new JLabel("Password: "));
		this.passwordField = new JPasswordField(10);
		registerPanel.add(this.passwordField);
		// CARD NUMBER
		registerPanel.add(new JLabel("Card Number: "));
		this.carNumberField = new JTextField(10);
		registerPanel.add(this.carNumberField);
		// AGE
		registerPanel.add(new JLabel("Age: "));
		this.ageField = new JTextField(10);
		registerPanel.add(this.ageField);
		// EXPENSE LIMIT
		registerPanel.add(new JLabel("Expense Limit: ")); 
		this.expenseLimitField = new JTextField(10);
		registerPanel.add(this.expenseLimitField);


		this.buttonRegister = new JButton("Register");
		registerPanel.add(this.buttonRegister); 
		this.buttonEnd = new JButton("End");
		registerPanel.add(this.buttonEnd); 
		buttonRegister.addActionListener(this);

		this.frame = new JFrame("Registration"); 
		this.frame.setTitle("REGISTRATION");

		this.frame.setSize(600,500);
		this.frame.getContentPane().add(registerPanel,"North"); 

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//WindowManager.middleLeft(this.frame); Is a class of the project
		this.frame.setVisible(true);

		thread = new Thread(this);
		thread.start();
	
	
	}

	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		if (target == this.buttonEnd) {
			this.stop();
			System.exit(0);
		}
		if (target == this.buttonRegister) {
			try {
				System.out.println("entra");
				String login = this.emailField.getText(); 
				String password = String.valueOf(this.passwordField.getPassword()); 
				String cardNumber = this.carNumberField.getText(); 
				int age = Integer.parseInt(this.ageField.getText());
				double expenseLimit = Double.parseDouble(this.expenseLimitField.getText()); 
				this.registerUser(login, password, cardNumber, age, expenseLimit);
			} catch (NumberFormatException exc) {
				// TODO 
			}
		}
	}
	public void registerUser(String login, String password, String cardNumber, int age, double expenseLimit) {
		WebTarget registerUserWebTarget = webTarget.path("register");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		UserData userData = new UserData();
		userData.setLogin(login);
		userData.setPassword(password);
		userData.setCardNumber(cardNumber);
		userData.setAge(age); 
		userData.setExpenseLimit(expenseLimit);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("User correctly registered");
		}
	}

	/*
	 * public void sayMessage(String login, String password, String message) {
	 * WebTarget sayHelloWebTarget = webTarget.path("sayMessage");
	 * Invocation.Builder invocationBuilder =
	 * sayHelloWebTarget.request(MediaType.APPLICATION_JSON);
	 * 
	 * DirectedMessage directedMessage = new DirectedMessage(); UserData userData =
	 * new UserData(); userData.setLogin(login); userData.setPassword(password);
	 * 
	 * directedMessage.setUserData(userData);
	 * 
	 * MessageData messageData = new MessageData(); messageData.setMessage(message);
	 * directedMessage.setMessageData(messageData);
	 * 
	 * Response response = invocationBuilder.post(Entity.entity(directedMessage,
	 * MediaType.APPLICATION_JSON)); if (response.getStatus() !=
	 * Status.OK.getStatusCode()) {
	 * System.out.println("Error connecting with the server. Code: " +
	 * response.getStatus()); } else { String responseMessage =
	 * response.readEntity(String.class);
	 * System.out.println("* Message coming from the server: '" + responseMessage +
	 * "'"); } }
	 */
	
	public void sayMessage(String login, String password, Expense expense) {
		WebTarget sayHelloWebTarget = webTarget.path("sayMessage");
		Invocation.Builder invocationBuilder = sayHelloWebTarget.request(MediaType.APPLICATION_JSON);

		DirectedMessage directedMessage = new DirectedMessage();
		UserData userData = new UserData();
		userData.setLogin(login);
		userData.setPassword(password);

		directedMessage.setUserData(userData);

		ExpenseData expenseData = new ExpenseData();
		expenseData.setText(expense.getText());
		expenseData.setAmount(expense.getAmount());
		expenseData.setCategory(expense.getCategory());
		
		directedMessage.setExpenseData(expenseData);

		Response response = invocationBuilder.post(Entity.entity(directedMessage, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			String responseMessage = response.readEntity(String.class);
			System.out.println("* Message coming from the server: '" + responseMessage + "'");
		}
	}
	
	public void storeExpense(String login, String password, Expense expense) {
		WebTarget storeExpenseWebTarget = webTarget.path("register");
		Invocation.Builder invocationBuilder = storeExpenseWebTarget.request(MediaType.APPLICATION_JSON);
		
		DirectedMessage directedMessage = new DirectedMessage();
		UserData userData = new UserData();
		userData.setLogin(login);
		userData.setPassword(password);

		directedMessage.setUserData(userData);

		ExpenseData expenseData = new ExpenseData();
		expenseData.setText(expense.getText());
		expenseData.setAmount(expense.getAmount());
		expenseData.setCategory(expense.getCategory());
		
		directedMessage.setExpenseData(expenseData);

		Response response = invocationBuilder.post(Entity.entity(directedMessage, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			String responseMessage = response.readEntity(String.class);
			System.out.println("* Message coming from the server: '" + responseMessage + "'");
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];

		
		ExampleClient client = new ExampleClient(hostname, port);

		/*
		exampleClient.registerUser("dipina", "dipina", "1111111111111111", 32,1000);
		Expense expense = new Expense("This is a test!...", 0.0, Category.OTHERS);
		exampleClient.sayMessage("dipina", "dipina", expense);
		*/
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//running.set(true);
		running.set(true);
		while(running.get()) {
			try { 
				Thread.sleep(2000);
				System.out.println("Obtaining data from server...");
				try {
					//TODO 
					System.out.println("TODO");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
            } catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
		}
	}
		
	
	public void stop() {
		this.running.set(false);
	}
}