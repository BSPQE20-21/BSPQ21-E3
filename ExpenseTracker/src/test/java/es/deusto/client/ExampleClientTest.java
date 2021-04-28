package es.deusto.client;

import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Assert;

import es.deusto.serialization.UserData;

public class ExampleClientTest {
	ExampleClient exampleClient;
	UserData userExpected; 

	@Before
	public void setUp() throws Exception {
    	
    	exampleClient = new ExampleClient("127.0.0.1", "8080");
    	
    	userExpected = new UserData();
    	userExpected.setLogin("user");
    	userExpected.setPassword("12345");
    	userExpected.setAge(20);
    	userExpected.setCardNumber("123456789");
    	userExpected.setExpenseLimit(2000);
    	
    }

	@Test
	public void testRegisterUser() throws Exception {
		//UserData mockedUser = mock(UserData.class);
		//verify(exampleClient.registerUser(mockedUser));
		
		//ExampleClient exampleClient2 = Mockito.mock(ExampleClient.class);
		ExampleClient exampleClient2 = Mockito.spy(new ExampleClient("127.0.0.1", "8080"));
		verify(exampleClient2, times(1)).registerUser(userExpected);
	
	}

	@Test
	public void testSayMessage() throws Exception {

	}

	@Test
	public void testStoreExpense() throws Exception {

	}

	@Test
	public void testValidateUser() throws Exception {
		UserData userDB = exampleClient.validateUser("user", "12345");
    	Assert.assertEquals(userDB.getLogin(), userExpected.getLogin());
    	Assert.assertEquals(userDB.getPassword(), userExpected.getPassword());
    	Assert.assertEquals(userDB.getCardNumber(), userExpected.getCardNumber());
    	Assert.assertEquals(userDB.getAge(), userExpected.getAge());
    	Assert.assertEquals(userDB.getExpenseLimit(), userExpected.getExpenseLimit(), 0);
    	//Assert.assertSame(userExpected, userDB);

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