package es.deusto.client;

import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Assert;

import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;
import es.deusto.server.jdo.Expense;

public class ExampleClientTest {
	ExampleClient exampleClient;
	UserData userExpected; 
	Expense expenseExpected;
	ExpenseData expenseDataExpected;

	@Before
	public void setUp() throws Exception {
    	
    	exampleClient = new ExampleClient("127.0.0.1", "8080");
    	
    	userExpected = new UserData();
    	userExpected.setLogin("user");
    	userExpected.setPassword("12345");
    	userExpected.setAge(20);
    	userExpected.setCardNumber("123456789");
    	userExpected.setExpenseLimit(2000);
    	
    	expenseExpected = new Expense();
    	expenseExpected.setAmount(1);
    	expenseExpected.setCategory(Category.CLOTHES);
    	expenseExpected.setText("expensiveExpense");
    	
    	expenseDataExpected = new ExpenseData();
    	expenseDataExpected.setAmount(1);
    	expenseDataExpected.setCategory(Category.CLOTHES);
    	expenseDataExpected.setText("expensiveExpense");
    	
    }

	@Test
	public void testRegisterUser() throws Exception {
		UserData mockedUser = mock(UserData.class);
		verify(exampleClient.registerUser(mockedUser));
		
		ExampleClient exampleClient2 = Mockito.mock(ExampleClient.class);
		exampleClient2 = Mockito.spy(new ExampleClient("127.0.0.1", "8080"));
		verify(exampleClient2, times(1)).registerUser(userExpected);
	
		
	}

	@Test
	public void testSayMessage() throws Exception {
		Expense mockedExpense = mock(Expense.class);
		UserData mockedUser = mock(UserData.class);
		verify(exampleClient.sayMessage(mockedUser.getLogin().toString(), mockedUser.getPassword().toString(), mockedExpense));
	}

	@Test
	public void testStoreExpense() throws Exception {
		ExpenseData mockedExpenseData = mock(ExpenseData.class);
		UserData mockedUser = mock(UserData.class);
		verify(exampleClient.storeExpense(mockedUser, mockedExpenseData));
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
		UserData mockedUser = mock(UserData.class);
		verify(exampleClient.showExpenses(mockedUser));
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