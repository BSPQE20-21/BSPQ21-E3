package es.deusto.server;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import es.deusto.server.jdo.User;
import es.deusto.server.jdo.Expense;
import es.deusto.serialization.DirectedMessage;
import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.UserData;
import es.deusto.serialization.LoginData;
import es.deusto.serialization.ExpenseList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.*;
import javax.jdo.Extent;
import es.deusto.log.LoggerFile;
import java.util.logging.Level;

/**
 * This class is the most important part os the server side\n It is filled with
 * methods that access and store or stract values from the DB\n To acces the DB
 * we use Jenkins\n The es.deusto.client.ExampleClient methods usually are
 * connected to the ones in this class
 */

@Path("/server")
@Produces(MediaType.APPLICATION_JSON)
public class Server {

	private int cont = 0;
	private PersistenceManager pm = null;
	private Transaction tx = null;

	/**
	 * Constructor. \n
	 * The PersistenceManager is initialized as well as the Transaction
	 */
	public Server() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}

	/**
	 * STORE EXPENSE\n
	 * The idea is to store the expense and the relationship between the user\n 
	 * This method is called from the es.deusto.client.ExampleClient.storeExpense class which receives the info from the es.deusto.client.AddExpenseWindow\n
	 * @param directedExpense - is the object that stores both the user and the expense
	 * @return RESPONSE (OK or NOT OK)
	 */
	@POST
	@Path("/store")
	public Response storeExpense(DirectedMessage directedExpense) {
		User user = null;
		try {
			tx.begin();

			Query<User> q = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE login == \""
					+ directedExpense.getUserData().getLogin() + "\" &&  password == \""
					+ directedExpense.getUserData().getPassword() + "\"");
			q.setUnique(true);
			user = (User)q.execute();
			
			//System.out.println("User retreived: " + user);
			LoggerFile.log(Level.INFO,  "User retreived: " + user);
			if (user != null)  {
				Expense expense = new Expense(directedExpense.getExpenseData().getText(), directedExpense.getExpenseData().getAmount(), directedExpense.getExpenseData().getCategory());
				user.getMessages().add(expense);
				pm.makePersistent(user);
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}

		if (user != null) {
			cont++;
			// System.out.println(" * Client number: " + cont);
			LoggerFile.log(Level.INFO, " * Client number: " + cont);
			ExpenseData expenseData = new ExpenseData();
			expenseData.setText(directedExpense.getExpenseData().getText());
			expenseData.setAmount(directedExpense.getExpenseData().getAmount());
			expenseData.setCategory(directedExpense.getExpenseData().getCategory());

			return Response.ok(expenseData).build();
		} else {
			return Response.status(Status.BAD_REQUEST)
					.entity("Login details supplied for message delivery are not correct").build();
		}
	}

	/**
	 * VALIDATE USER.\n
	 * This method access the DB and searches if the given user is already stored\n
	 * This method is called from the client side class es.deusto.client.ExampleClient 
	 * and the data is received from the es.deusto.client.LoginWindow\n
	 * 
	 * @param loginData - user and password of the user
	 * @return - USER DATA it will return the actual user if it is already registred
	 */
	@POST
	@Path("/validate")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response validateUser(LoginData loginData) {
		try {
			tx.begin();
			User user = null;
			UserData userData = new UserData();
			try {
				user = pm.getObjectById(User.class, loginData.getLogin());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				
				LoggerFile.log(Level.SEVERE, jonfe.getMessage());
			}
			System.out.println("User: " + user);
			if (user != null) {
				if ((loginData.getPassword()).equals(user.getPassword())) {
				
					userData.setLogin(user.getLogin());
					userData.setPassword(user.getPassword());
					userData.setCardNumber(user.getCardNumber());
					userData.setAge(user.getAge());
					userData.setExpenseLimit(user.getExpenseLimit());
				}

			} else {
				LoggerFile.log(Level.INFO, "User not registred");
				

			}
			tx.commit();

			return Response.ok().entity(userData).build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
	}

	/**
	 * REGISTER USER.\n
	 * This method stores a new user inside the DB\n
	 * This method is called in the es.deusto.client.ExampleClient class and the data of the user is obtained in the es.deusto.client.RegisterWindow\n
	 * @param userData
	 * @return (OK or not OK)
	 */

	@POST
	@Path("/register")
	public Response registerUser(UserData userData) {
		try {
			tx.begin();
			System.out.println("Checking whether the user already exits or not: '" + userData.getLogin() + "'");
			User user = null;
			try {
				user = pm.getObjectById(User.class, userData.getLogin());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {

				LoggerFile.log(Level.SEVERE, jonfe.getMessage());
			}
			System.out.println("User: " + user);
			if (user != null) {
		
				LoggerFile.log(Level.INFO, "Setting password user: " + user);
				user.setPassword(userData.getPassword());
	
			} else {
				
				user = new User(userData.getLogin(), userData.getPassword(), userData.getCardNumber(),
						userData.getAge(), userData.getExpenseLimit());
				pm.makePersistent(user);
				LoggerFile.log(Level.INFO, "User created: " + user);
				
			}
			tx.commit();
			return Response.ok().build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
	}

	/**
	 * SHOW EXPENSE.\n This method returns all the expenses related to a concrete
	 * user. This method is called from the es.deusto.client.ExampleClient class and
	 * the user is the one that is logged in\n It should return ALL the expenses
	 * related to one user and in order to do that a SET<ExpenseData> is created so
	 * we can return all the info.
	 * 
	 * @param userData - the user that is logged in
	 * @return it will return a set of expenses
	 */
	@POST
	@Path("/showExpenses")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response showExpenses(UserData userData) {

		try {
			tx.begin();
			// ExpenseList expenses = new ExpenseList();
			ExpenseList expenseList = new ExpenseList();
			Set<ExpenseData> expenses = new HashSet<ExpenseData>();
			User user = null;

			try {

				user = pm.getObjectById(User.class, userData.getLogin());
				if (user != null) {
					for (Expense e : user.getMessages()) {
						ExpenseData exp = new ExpenseData();
						exp.setText(e.getText());
						exp.setAmount(e.getAmount());
						exp.setCategory(e.getCategory());
						expenses.add(exp);
					}
					expenseList.setExpenseList(expenses);
				}

			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				LoggerFile.log(Level.SEVERE, jonfe.getMessage());
				// System.out.println("Exception launched: " + jonfe.getMessage());
			}

			tx.commit();
			return Response.ok().entity(expenseList).build();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}

	}
	//TODO
	@POST
	@Path("/delete")
	public Response deleteExpense(ExpenseData expenseData) {
		try {
			tx.begin();
			Query<Expense> q = pm.newQuery("SELECT FROM " + Expense.class.getName() + " WHERE text == \"" + expenseData.getText() + "\" && amount == \"" + expenseData.getAmount() + "\"");
			q.setUnique(true);
			Expense expense = (Expense)q.execute();
			System.out.println(q);
			if (expense != null)  {

				pm.deletePersistent(expense);
	
			}
			tx.commit();
			return Response.ok().build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}

	/**
	 * UPDATE THE INFO OF A USER\n
	 * This method makes possible for the user to update some of the information and update it inside the BD
	 * 
	 * @param userData -> the user that is logged in after the changes made in the window
	 * @return the suer with the changes
	 */

	@POST
	@Path("/update")
	@Produces("application/json")
	public Response updateuser(UserData userData) {
		try {
			tx.begin();
			User user = null;
			
			try {
				user = pm.getObjectById(User.class, userData.getLogin());
				pm.retrieve(user);
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				
				LoggerFile.log(Level.SEVERE, jonfe.getMessage());
			}
			
			if (user != null) {
			
				user.setLogin(userData.getLogin());
				user.setPassword(userData.getPassword());
				user.setCardNumber(userData.getCardNumber());
				user.setAge(userData.getAge());
				user.setExpenseLimit(userData.getExpenseLimit());
				pm.makePersistent(user); 
				tx.commit();

			} 
		
			return Response.ok().entity(userData).build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
	}

	/**
	 * This method erases from the DB a user and all his or her information
	 * @param userData
	 * @return
	 */
	@POST
	@Path("/deleteUser")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteUser(UserData userData) {
		try {
			tx.begin();
			User user = null;
			user = pm.getObjectById(User.class, userData.getLogin());
			pm.deletePersistentAll(user);
			tx.commit();
			
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return Response.ok().build();
	}

	


}
