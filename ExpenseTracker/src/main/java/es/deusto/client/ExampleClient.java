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
import es.deusto.serialization.ExpenseList;
import es.deusto.serialization.UserData;
import es.deusto.serialization.LoginData;
import es.deusto.server.jdo.Category;
import es.deusto.server.jdo.Expense;
import es.deusto.server.jdo.User;
import java.util.*; 
import es.deusto.log.LoggerFile; 
import java.util.logging.Level; 
import java.util.logging.Logger;
public class ExampleClient {

	private Client client;
	private WebTarget webTarget;
	private LoginWindow lw;
	private static ResourceBundle resourceBundle; 

	private static final Logger logger = Logger.getLogger("Example Client");

	public ExampleClient(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/server", hostname, port));
		lw = new LoginWindow(this);
	}

	
	public void registerUser(UserData userData) {
		WebTarget registerUserWebTarget = webTarget.path("register");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		

		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			LoggerFile.log(Level.INFO, resourceBundle.getString("error_connecting"));
			//System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			LoggerFile.log(Level.INFO, resourceBundle.getString("persisting_user"));
			//logger.info(resourceBundle.getString("persisting_user"));
			//System.out.println("User correctly registered");
			
		}
	}

	
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
			//System.out.println("Error connecting with the server. Code: " + response.getStatus());
			//logger.info(resourceBundle.getString("error_connecting"));
			LoggerFile.log(Level.INFO, resourceBundle.getString("error_connecting"));
		} else {
			//String responseMessage = response.readEntity(String.class);
			//logger.info(resourceBundle.getString("persisting_user"));
			LoggerFile.log(Level.INFO, resourceBundle.getString("persisting_user"));
			//LoggerFile.log(Level.INFO, "* Message coming from the server: '" + responseMessage + "'"); 
			//System.out.println("* Message coming from the server: '" + responseMessage + "'");
		}
	}
	
	public void storeExpense(UserData userData, ExpenseData expenseData) {
		WebTarget storeExpenseWebTarget = webTarget.path("store");
		Invocation.Builder invocationBuilder = storeExpenseWebTarget.request(MediaType.APPLICATION_JSON);
		
		DirectedMessage directedMessage = new DirectedMessage();

		directedMessage.setUserData(userData);

		directedMessage.setExpenseData(expenseData);

		Response response = invocationBuilder.post(Entity.entity(directedMessage, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			LoggerFile.log(Level.INFO, resourceBundle.getString("error_connecting")); 
			//System.out.println("Error connecting with the server. Code: " + response.getStatus());
			//logger.info(resourceBundle.getString("error_connecting"));
		} else {
			//String responseMessage = response.readEntity(String.class);
			//System.out.println("* Message coming from the server: '" + responseMessage + "'");
			LoggerFile.log(Level.INFO, resourceBundle.getString("store_expense")); 
			//logger.info(resourceBundle.getString("store_expense"));
		}
	}

	public UserData validateUser(String login, String password){
		WebTarget storeExpenseWebTarget = webTarget.path("validate");
		Invocation.Builder invocationBuilder = storeExpenseWebTarget.request(MediaType.APPLICATION_JSON);
		

		LoginData loginData = new LoginData(); 
		loginData.setLogin(login); 
		loginData.setPassword(password); 
		Response response = invocationBuilder.post(Entity.entity(loginData, MediaType.APPLICATION_JSON));
		UserData userData = response.readEntity(UserData.class);
		if (response.getStatus() != Status.OK.getStatusCode()) {
			LoggerFile.log(Level.INFO, resourceBundle.getString("error_connecting")); 
			//System.out.println("Error connecting with the server. Code: " + response.getStatus());
			//logger.info(resourceBundle.getString("error_connecting"));

		} else {
			LoggerFile.log(Level.INFO, resourceBundle.getString("validate_user")); 
			//logger.info(resourceBundle.getString("validate_user"));

					
		}
		return userData; 
	
	}

	public 	Set<ExpenseData> showExpenses(UserData userData){
		WebTarget storeExpenseWebTarget = webTarget.path("showExpenses");
		Invocation.Builder invocationBuilder = storeExpenseWebTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		//ExpenseList expenses = response.readEntity(ExpenseList.class);
		ExpenseList expenseList = response.readEntity(ExpenseList.class); 


		if (response.getStatus() != Status.OK.getStatusCode()) {
			LoggerFile.log(Level.INFO, resourceBundle.getString("error_connecting")); 
			//System.out.println("Error connecting with the server. Code: " + response.getStatus());
			//logger.info(resourceBundle.getString("error_connecting"));
		} else {
			LoggerFile.log(Level.INFO, resourceBundle.getString("show_expenses")); 
			//logger.info(resourceBundle.getString("show_expenses"));
					
		}
		
		Set<ExpenseData> expenses = expenseList.getExpenseList(); 
		//System.out.println(expenses.getExpenseList());
		return expenses; 
	
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];
		resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
		resourceBundle = ResourceBundle.getBundle("SystemMessages",	Locale.forLanguageTag("es"));

		//logger.info(resourceBundle.getString("starting_msg"));
		//logger.info(resourceBundle.getString("app_title"));
        //logger.info(resourceBundle.getString("app_underline"));
		LoggerFile.log(Level.INFO, resourceBundle.getString("starting_msg")); 
		LoggerFile.log(Level.INFO, resourceBundle.getString("app_title")); 
		LoggerFile.log(Level.INFO, resourceBundle.getString("app_underline")); 
		
		ExampleClient client = new ExampleClient(hostname, port);

	}

}