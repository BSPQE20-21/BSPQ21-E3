package es.deusto.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.deusto.serialization.UserData;

public class ExampleClientTest {

	ExampleClient exampleClient;
	UserData userExpected; 
	String login;
	String password;

	/*
	 * @Before public void setUp() throws Exception { exampleClient = new
	 * ExampleClient(hostname, port); exampleClient.storeExpense(userData,
	 * expenseData); }
	 * 
	 * @Test public void testRegisterUser() { ExpenseList expenses = new
	 * ExpenseList();
	 * 
	 * 
	 * assertEquals(expenses, exampleClient.showExpenses(userData)); }
	 */
    
    @Before
    public void setUp() throws Exception {
    	
    	exampleClient = new ExampleClient("127.0.0.1", "8080");
    	login = "user";
    	password = "12345";
    	
    	userExpected = new UserData();
    	userExpected.setLogin("user");
    	userExpected.setPassword("12345");
    	userExpected.setAge(20);
    	userExpected.setCardNumber("123456789");
    	userExpected.setExpenseLimit(2000);
    	
    }
    
    @Test
    public void testRegisterUser() {
    	
    	UserData userDB = exampleClient.validateUser(login, password);
    	assertEquals(userExpected, userDB);
    	
    }

}
