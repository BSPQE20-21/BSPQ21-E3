package es.deusto.client;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.junit.Before;

import static org.mockito.Mockito.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Assert;

import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;
import es.deusto.server.jdo.Expense;

public class ExampleClientTest {
	ExampleClient exampleClient;
	UserData userExpected; 

	ExpenseData expenseDataExpected;
	
	//ResourceBundle resourceBundle;

	@Before
	public void setUp() throws Exception {
    	
    	exampleClient = new ExampleClient("127.0.0.1", "8080");
    	
    	userExpected = new UserData();
    	userExpected.setLogin("user");
    	userExpected.setPassword("12345");
    	userExpected.setAge(20);
    	userExpected.setCardNumber("123456789");
    	userExpected.setExpenseLimit(2000);
    	
    	expenseDataExpected = new ExpenseData();
    	expenseDataExpected.setAmount(1);
    	expenseDataExpected.setCategory(Category.CLOTHES);
    	expenseDataExpected.setText("expensiveExpense");
    	
    	
    }

	/*@Test
	public void testRegisterUser() throws Exception {
		UserData mockedUser = mock(UserData.class);
		verify(exampleClient, times(1)).registerUser(mockedUser);
		
	}

	@Test
	public void testSayMessage() throws Exception {
		Expense mockedExpense = mock(Expense.class);
		UserData mockedUser = mock(UserData.class);
		verify(exampleClient).sayMessage(mockedUser.getLogin().toString(), mockedUser.getPassword().toString(), mockedExpense);
	}


	@Test
	public void testStoreExpense() throws Exception {
		exampleClient.storeExpense(userExpected, expenseDataExpected);
		ArgumentCaptor<UserData> userCaptor = ArgumentCaptor.forClass(UserData.class);
		ArgumentCaptor<ExpenseData> expenseCaptor = ArgumentCaptor.forClass(ExpenseData.class);
		verify(exampleClient).storeExpense(userCaptor.capture(), expenseCaptor.capture());
		UserData newUser = userCaptor.getValue();
		ExpenseData newExpense = expenseCaptor.getValue();
		Assert.assertEquals(userExpected.getLogin(), newUser.getLogin());
		Assert.assertEquals(expenseDataExpected.getText(), newExpense.getText());
		Assert.assertEquals(expenseDataExpected.getCategory(), newExpense.getCategory());
	}*/

	@Test
	public void testValidateUser() throws Exception {
		ResourceBundle.getBundle("SystemMessages", Locale.forLanguageTag("es"));
		
		UserData userDB = exampleClient.validateUser("user", "12345");
    	Assert.assertEquals(userDB.getLogin(), userExpected.getLogin());
    	Assert.assertEquals(userDB.getPassword(), userExpected.getPassword());
    	Assert.assertEquals(userDB.getCardNumber(), userExpected.getCardNumber());
    	Assert.assertEquals(userDB.getAge(), userExpected.getAge());
    	Assert.assertEquals(userDB.getExpenseLimit(), userExpected.getExpenseLimit(), 0);
    	//Assert.assertSame(userExpected, userDB);
	}

	/*@Test
	public void testShowExpenses() throws Exception {
		UserData mockedUser = mock(UserData.class);
		verify(exampleClient.showExpenses(mockedUser));
	}*/



	
	
	
	
	
	
	
	
	
	
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