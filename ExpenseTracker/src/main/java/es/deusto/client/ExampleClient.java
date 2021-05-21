package es.deusto.client;

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

import org.apache.log4j.Logger;
/**
 * The main CLIENT SIDE class\n
 * The purpose of this class is to create the client and the WEBTARGET as well as to initialize the first window\n
 * All the methods defined in this class are a bridge to the server side where the real actions are performed, none of this methods
 * acces the DB they call the server side methods that acces them.\n
 * This class has a resourceBoundle that detects the lenguage of the computer and stores the information inside the logger accordly
 * 
 * 
 */

public class ExampleClient {

	private Client client;
	private WebTarget webTarget;
	private LoginWindow lw;
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault()); 
	static Logger logger = Logger.getLogger(ExampleClient.class.getName());


	
	/**
	 * Constructor of the CLIENT 
	 * Here is called the @see es.deusto.client.LoginWindow so the first window where the user can loged in is opened
	 * @param hostname this information is stored insite the pom.xml and will be asigned in the main with an arg
	 * @param port this information is stored insite the pom.xml and will be asigned in the main with an arg
	 */
	
	public ExampleClient(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/server", hostname, port));
		lw = new LoginWindow(this);
		//resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());

	}

	/**
	 * This method allows a user to be registered insite the DB 
	 * It calls the server side method @see es.deusto.server.Server (registerUser)
	 * @param UserData userData -  the information of the user that sould be register is passed
	 * @return nothing should be returned in this class 
	 * 
	 */
	public void registerUser(UserData userData) {
		WebTarget registerUserWebTarget = webTarget.path("register");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			//LoggerFile.log(Level.INFO, resourceBundle.getString("error_connecting"));
			logger.info(resourceBundle.getString("error_connecting")); 
			//System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			//LoggerFile.log(Level.INFO, resourceBundle.getString("persisting_user"));
			logger.info(resourceBundle.getString("persisting_user"));
			//System.out.println("User correctly registered");

		}
	}
	
	
	/**
	 * This method is called when the user introduces a new EXPENSE and desired to store it into the DB
	 * Both userData and ExpenseData are needed so the relationship between who buys who is stored
	 * @param userData - the loged user
	 * @param expenseData - the expese that he/she desired to store
	 * 
	 */

	public void storeExpense(UserData userData, ExpenseData expenseData) {
		WebTarget storeExpenseWebTarget = webTarget.path("store");
		Invocation.Builder invocationBuilder = storeExpenseWebTarget.request(MediaType.APPLICATION_JSON);

		DirectedMessage directedMessage = new DirectedMessage();

		directedMessage.setUserData(userData);

		directedMessage.setExpenseData(expenseData);

		Response response = invocationBuilder.post(Entity.entity(directedMessage, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			//LoggerFile.log(Level.INFO, resourceBundle.getString("error_connecting")); 
			//System.out.println("Error connecting with the server. Code: " + response.getStatus());
			logger.info(resourceBundle.getString("error_connecting"));
		} else {
			//String responseMessage = response.readEntity(String.class);
			//System.out.println("* Message coming from the server: '" + responseMessage + "'");
			//LoggerFile.log(Level.INFO, resourceBundle.getString("store_expense")); 
			logger.info(resourceBundle.getString("store_expense"));
		}
	}
	/**
	 * This method validated if the user that is trying to access the site is already loged in or not
	 * This will call the @see es.deusto.server.Server (validateUser) which will access the BD and check if the user is registred
	 * @param login - email
	 * @param password 
	 * @return UserData - it will return the actual loged in user
	 */
	public UserData validateUser(String login, String password){
		WebTarget storeExpenseWebTarget = webTarget.path("validate");
		Invocation.Builder invocationBuilder = storeExpenseWebTarget.request(MediaType.APPLICATION_JSON);


		LoginData loginData = new LoginData(); 
		loginData.setLogin(login); 
		loginData.setPassword(password); 
		Response response = invocationBuilder.post(Entity.entity(loginData, MediaType.APPLICATION_JSON));
		UserData userData = response.readEntity(UserData.class);
		if (response.getStatus() != Status.OK.getStatusCode()) {
			//LoggerFile.log(Level.INFO, resourceBundle.getString("error_connecting")); 
			//System.out.println("Error connecting with the server. Code: " + response.getStatus());
			logger.info(resourceBundle.getString("error_connecting"));

		} else {

			//LoggerFile.log(Level.INFO, resourceBundle.getString("validate_user")); 


			logger.info(resourceBundle.getString("validate_user"));

			
		}
		return userData; 

	}

	/**
	 * This method looks inside the DB for all the expenses of a concrete user
	 * This method will call the @see es.deusto.server.Server (showExpense) that will be the one accesing the BD
	 * This method is called inside the @see es.deusto.client.AddExpenseWindow
	 * @param userData - the user that is logged in
	 * @return Set<ExpenseData> a set of all the expenses of the concrete user
	 * 
	 */

	public 	Set<ExpenseData> showExpenses(UserData userData){
		WebTarget storeExpenseWebTarget = webTarget.path("showExpenses");
		Invocation.Builder invocationBuilder = storeExpenseWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		//ExpenseList expenses = response.readEntity(ExpenseList.class);
		ExpenseList expenseList = response.readEntity(ExpenseList.class); 


		if (response.getStatus() != Status.OK.getStatusCode()) {
			//LoggerFile.log(Level.INFO, resourceBundle.getString("error_connecting")); 
			//System.out.println("Error connecting with the server. Code: " + response.getStatus());
			logger.info(resourceBundle.getString("error_connecting"));
		} else {
			//LoggerFile.log(Level.INFO, resourceBundle.getString("show_expenses")); 
			logger.info(resourceBundle.getString("show_expenses"));
		}

		Set<ExpenseData> expenses = expenseList.getExpenseList(); 
		//System.out.println(expenses.getExpenseList());
		return expenses; 

	}
	

	/**
	 * UPDATE USER\n
	 * This method connects with the server part and request to update the info of the DB
	 * @param userData - the updated user
	 * @return user - the updated user so it can be passed trought the windows
	 */
	public UserData updateUser(UserData userData){
		WebTarget storeExpenseWebTarget = webTarget.path("update");
		Invocation.Builder invocationBuilder = storeExpenseWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		UserData uData = response.readEntity(UserData.class);
		if (response.getStatus() != Status.OK.getStatusCode()) {
	
			logger.info(resourceBundle.getString("error_connecting"));

		} else {
			logger.info(resourceBundle.getString("validate_user"));
		}
		System.out.println(uData);
		return uData; 
	}

	/**
	 * DELETE USER\n
	 * This method connects with the server part and request to delete the info of the user inse theDB
	 * @param userData - the updated user
	 * 
	 */
	public void deleteUser(UserData userData){
		WebTarget storeExpenseWebTarget = webTarget.path("deleteUser");
		Invocation.Builder invocationBuilder = storeExpenseWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.info(resourceBundle.getString("error_connecting"));

		} else {
			logger.info(resourceBundle.getString("delete_user"));
		}
	}



	public ResourceBundle getResourceBundle(){
		return resourceBundle; 
	}

	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];
		
		//resourceBundle = ResourceBundle.getBundle("SystemMessages",	Locale.forLanguageTag("es"));

		logger.info(resourceBundle.getString("starting_msg"));
		logger.info(resourceBundle.getString("app_title"));
        logger.info(resourceBundle.getString("app_underline"));
		//LoggerFile.log(Level.INFO, resourceBundle.getString("starting_msg")); 
		//LoggerFile.log(Level.INFO, resourceBundle.getString("app_title")); 
		//LoggerFile.log(Level.INFO, resourceBundle.getString("app_underline")); 

		ExampleClient client = new ExampleClient(hostname, port);

	}

}