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

public class ExampleClient {

	private Client client;
	private WebTarget webTarget;
	private LoginWindow lw;

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
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			//System.out.println("User correctly registered");
			LoggerFile.log(Level.INFO, LoggerFile.class.getName()); 
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
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			//String responseMessage = response.readEntity(String.class);
			LoggerFile.log(Level.INFO, LoggerFile.class.getName()); 

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
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			//String responseMessage = response.readEntity(String.class);
			//System.out.println("* Message coming from the server: '" + responseMessage + "'");
			LoggerFile.log(Level.INFO, LoggerFile.class.getName()); 
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
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
					
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
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
					
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
		
		ExampleClient client = new ExampleClient(hostname, port);

		/*
		exampleClient.registerUser("dipina", "dipina", "1111111111111111", 32,1000);
=======
		ExampleClient exampleClient = new ExampleClient(hostname, port);
		exampleClient.registerUser("dipina", "dipina", "1111111111111111", 32, 1000);
>>>>>>> 83ccbaa010ca5229c38104e4ad1917d880d1417f
		Expense expense = new Expense("This is a test!...", 0.0, Category.OTHERS);
		exampleClient.sayMessage("dipina", "dipina", expense);
		*/
	}

}