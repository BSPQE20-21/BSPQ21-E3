package es.deusto.client;

import org.junit.Test;
import org.junit.Assert; 
import es.deusto.serialization.UserData;

public class ExampleClientTest {
	ExampleClient exampleClient;
	UserData userExpected; 
	String login;
	String password;

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
	public void testRegisterUser() throws Exception {

	}

	@Test
	public void testSayMessage() throws Exception {

	}

	@Test
	public void testStoreExpense() throws Exception {

	}

	@Test
	public void testValidateUser() throws Exception {
		UserData userDB = exampleClient.validateUser(login, password);
    	Assert.assertEquals(userExpected, userDB);

	}

	@Test
	public void testShowExpenses() throws Exception {

	}



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
    
    
  
    
    
    	
    	
    	
    


}
