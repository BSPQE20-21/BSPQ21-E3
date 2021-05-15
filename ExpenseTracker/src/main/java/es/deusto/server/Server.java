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
 * This class is the most important part os the server side\n
 * It is filled with methods that access and store or stract values from the DB\n
 * To acces the DB we use Jenkins\n
 * The es.deusto.client.ExampleClient methods usually are connected to the ones in this class
 * 
 */

@Path("/server")
@Produces(MediaType.APPLICATION_JSON)
public class Server {

	private int cont = 0;
	private PersistenceManager pm=null;
	private Transaction tx=null;

	/**
	 * Constructor. \n
	 * The persiscanceManager is initialices as well as the Transaction
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
	 * @return RESPONSE (OK or NOT)
	 */
	@POST
	@Path("/store")
	public Response storeExpense(DirectedMessage directedExpense) {
		User user = null;
		try{
			tx.begin();
			
			
			Query<User> q = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE login == \"" + directedExpense.getUserData().getLogin() + "\" &&  password == \"" + directedExpense.getUserData().getPassword() + "\"");
			q.setUnique(true);
			user = (User)q.execute();
			
			//System.out.println("User retrieved: " + user);
			LoggerFile.log(Level.INFO,  "User retrieved: " + user);
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
			//System.out.println(" * Client number: " + cont);
			LoggerFile.log(Level.INFO,  " * Client number: " + cont);
			ExpenseData expenseData = new ExpenseData();
			expenseData.setText(directedExpense.getExpenseData().getText());
			expenseData.setAmount(directedExpense.getExpenseData().getAmount());
			expenseData.setCategory(directedExpense.getExpenseData().getCategory());
			
			return Response.ok(expenseData).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Login details supplied for message delivery are not correct").build();
		}
	}
	/**
	 * VALIDATE USER.\n
	 * This method access the DB and searches if the given user is already stored\n
	 * This method is called from the client side class es.deusto.client.ExampleClient 
	 * and the data is recieved from the es.deusto.client.LoginWindow\n
	 * 
	 * @param loginData - user and password of the user
	 * @return - USER DATA it will return the actual user if it is already registred 
	 */
	@POST
	@Path("/validate")
	@Produces({MediaType.APPLICATION_JSON})
	 public Response validateUser(LoginData loginData) {
		try
        {	
            tx.begin();
			User user = null;
			UserData userData = new UserData(); 
			try {
				user = pm.getObjectById(User.class, loginData.getLogin());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				//System.out.println("Exception launched: " + jonfe.getMessage());
				LoggerFile.log(Level.SEVERE,  jonfe.getMessage());
			}
			System.out.println("User: " + user);
			if (user != null) {
				if((loginData.getPassword()).equals(user.getPassword())){
					//System.out.println("Hello: " + user);
					
					userData.setLogin(user.getLogin()); 
					userData.setPassword(user.getPassword()); 
					userData.setCardNumber(user.getCardNumber()); 
					userData.setAge(user.getAge()); 
					userData.setExpenseLimit(user.getExpenseLimit()); 
				}
		
			} else {
				LoggerFile.log(Level.INFO,  "User not registred");
				//System.out.println("User not registred");
				
			}
			tx.commit();

			return Response.ok().entity(userData).build();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
		}
	}

	/**
	 * REGISTER USER.\n
	 * This method stores a new user inside the DB\n
	 * This method is called in the es.deusto.client.ExampleClient class and the data of the user is obtain in the es.deusto.client.RegisterWindow\n
	 * 
	 * @param userData
	 * @return (Just and OK or not OK)
	 */

	@POST
	@Path("/register")
	public Response registerUser(UserData userData) {
		try
        {	
            tx.begin();
            System.out.println("Checking whether the user already exits or not: '" + userData.getLogin() +"'");
			User user = null;
			try {
				user = pm.getObjectById(User.class, userData.getLogin());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				//System.out.println("Exception launched: " + jonfe.getMessage());
				LoggerFile.log(Level.SEVERE,  jonfe.getMessage());
			}
			System.out.println("User: " + user);
			if (user != null) {
				//System.out.println("Setting password user: " + user);
				LoggerFile.log(Level.INFO,  "Setting password user: " + user);	
				user.setPassword(userData.getPassword());
				//System.out.println("Password set user: " + user);
			} else {
				//System.out.println("Creating user: " + user);
				user = new User(userData.getLogin(), userData.getPassword(), userData.getCardNumber(), userData.getAge(),userData.getExpenseLimit());
				pm.makePersistent(user);	
				LoggerFile.log(Level.INFO,  "User created: " + user);				 
				//System.out.println("User created: " + user);
			}
			tx.commit();
			return Response.ok().build();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
		}
	}
	/**
	 * SHOW EXPENSE.\n
	 * This method returns all the expenses related to a concrete user.
	 * This method is called from the es.deusto.client.ExampleClient class and the user is the one that is logged in\n
	 * It should return ALL the expenses related to one user and in order to do that a SET<ExpenseData> is created so we can return all the info.
	 * 
	 * @param userData - the user that is logged in
	 * @return it will return a set of expenses
	 */
	@POST
	@Path("/showExpenses")
	@Produces({MediaType.APPLICATION_JSON})
	public Response showExpenses(UserData userData) 
	{
		
		try
        {	
            tx.begin();
			//ExpenseList expenses = new ExpenseList(); 
			ExpenseList expenseList = new ExpenseList(); 
 			Set<ExpenseData> expenses = new HashSet<ExpenseData>(); 
			User user = null;
			
			try {
				
				//System.out.println("Creating query ...");
			
				//Query<User> q = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE login == \"" + userData.getLogin() + "\" &&  password == \"" + userData.getPassword() + "\"");
				//q.setUnique(true);
				//user = (User)q.execute();
				user = pm.getObjectById(User.class, userData.getLogin());
				//System.out.println("User retrieved: " + user);
				if (user != null){	
					for (Expense e: user.getMessages()){
						ExpenseData exp = new ExpenseData(); 
						exp.setText(e.getText()); 
						exp.setAmount(e.getAmount()); 
						exp.setCategory(e.getCategory()); 
						expenses.add(exp); 
					}
					expenseList.setExpenseList(expenses); 
				}
		

			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				LoggerFile.log(Level.SEVERE,  jonfe.getMessage());
				//System.out.println("Exception launched: " + jonfe.getMessage());
			}	
			
			tx.commit();
			return Response.ok().entity(expenseList).build();
			
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
		}
		
	}
	


}
