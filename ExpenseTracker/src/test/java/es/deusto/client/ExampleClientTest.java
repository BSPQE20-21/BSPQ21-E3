package es.deusto.client;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.junit.Before;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;

import es.deusto.serialization.DirectedMessage;
import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;
import es.deusto.server.jdo.Expense;

public class ExampleClientTest {
	ExampleClient exampleClient;
	UserData userExpected; 
	DirectedMessage dMExpected;
	ExpenseData expenseDataExpected;
	Expense expenseExpected;
	
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
    	
    	expenseExpected = new Expense();
    	expenseExpected.setText("expenseUno");
    	expenseExpected.setAmount(1);
    	expenseExpected.setCategory(Category.CLOTHES);
    	
    	expenseDataExpected = new ExpenseData();
    	expenseDataExpected.setAmount(expenseExpected.getAmount());
    	expenseDataExpected.setCategory(expenseExpected.getCategory());
    	expenseDataExpected.setText(expenseExpected.getText());
    	
    	dMExpected.setUserData(userExpected);
    	dMExpected.setExpenseData(expenseDataExpected);
    	
    	
    }
	
	
	@Test
	public void testRegisterUser() throws Exception {
		UserData mockedUser = mock(UserData.class);
		mockedUser.setLogin("user");
		mockedUser.setPassword("12345");
		Assert.assertEquals(mockedUser.getLogin(), userExpected.getLogin());
		Assert.assertEquals(mockedUser.getPassword(), userExpected.getPassword());
		
	}
	
	@Test
	public void testSayMessage() throws Exception {
	
		UserData mockedUser = mock(UserData.class);
		mockedUser.setLogin("user");
		mockedUser.setPassword("12345");
		
		Expense mockedExpense = mock(Expense.class);
		mockedExpense.setText("expenseUno");
		mockedExpense.setAmount(1);
		mockedExpense.setCategory(Category.CLOTHES);
		
		ExpenseData mockedExpenseData = mock(ExpenseData.class);
		mockedExpenseData.setText(mockedExpense.getText());
		mockedExpenseData.setAmount(mockedExpense.getAmount());
		mockedExpenseData.setCategory(mockedExpense.getCategory());
		
		DirectedMessage mockedDM = mock(DirectedMessage.class);
		mockedDM.setUserData(mockedUser);
		mockedDM.setExpenseData(mockedExpenseData);
		Assert.assertEquals(dMExpected, mockedDM);
		
	}

	@Test
	public void testStoreExpense() throws Exception {
		UserData mockedUser = mock(UserData.class);
		mockedUser.setLogin("user");
		mockedUser.setPassword("12345");

		ExpenseData mockedExpenseData = mock(ExpenseData.class);
		mockedExpenseData.setText("expenseUno");
		mockedExpenseData.setAmount(1);
		mockedExpenseData.setCategory(Category.CLOTHES);

		DirectedMessage mockedDM = mock(DirectedMessage.class);
		mockedDM.setUserData(mockedUser);
		mockedDM.setExpenseData(mockedExpenseData);
		Assert.assertEquals(dMExpected, mockedDM);
	}
	

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

	
	@Test
	public void testShowExpenses() throws Exception {
		UserData mockedUser = mock(UserData.class);
		mockedUser.setLogin("user");
		mockedUser.setPassword("12345");
		Assert.assertEquals(mockedUser.getLogin(), userExpected.getLogin());
		Assert.assertEquals(mockedUser.getPassword(), userExpected.getPassword());
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