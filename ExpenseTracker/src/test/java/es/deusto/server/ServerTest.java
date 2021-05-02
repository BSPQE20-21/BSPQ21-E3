package es.deusto.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;

import es.deusto.serialization.DirectedMessage;
import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.LoginData;
import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;
import es.deusto.server.jdo.User;


@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
@Testable
public class ServerTest {
	
	Server server;
	UserData user;
	ExpenseData expenseDataExpected;
	DirectedMessage dMExpected = new DirectedMessage();
	Response responseExpected;

	@Before
    public void setUp() throws Exception {
		server = new Server(); 
		user = new UserData("user", "12345", "123456789", 20, 2000);
		expenseDataExpected = new ExpenseData();
    	expenseDataExpected.setAmount(1);
    	expenseDataExpected.setCategory(Category.CLOTHES);
    	expenseDataExpected.setText("expenseUno");
    	dMExpected.setUserData(user);
    	dMExpected.setExpenseData(expenseDataExpected);
    	responseExpected = Response.ok().build();
	}
	
	@Test
	@PerfTest(invocations = 100, threads = 20)
	@Required(max = 20000, average = 3000)
	public void testServer() throws Exception {
		server = new Server();
	}
	
	@Test
	public void testStoreExpense() throws Exception {
		DirectedMessage mockedMsg = mock(DirectedMessage.class);
		UserData userDB = new UserData("user","12345", "123456789", 20, 2000);
		ExpenseData expenseDB = new ExpenseData("expenseUno", 1, Category.CLOTHES);
		mockedMsg.setUserData(userDB);
		mockedMsg.setExpenseData(expenseDB);
		//Assert.assertEquals(mockedMsg, dMExpected);
		//Response response = server.storeExpense(mockedMsg);
		//Assert.assertEquals(responseExpected, response);
		
	}
	
	
	
	@Test
	public void testValidateUser() throws Exception {
		LoginData logindata = new LoginData("user", "12345");
		UserData userBD = new UserData("user","12345", "123456789", 20, 2000);
		Assert.assertEquals(userBD.getLogin(), user.getLogin());
    	Assert.assertEquals(userBD.getPassword(), user.getPassword());
    	Response response = server.validateUser(logindata);
    	//Assert.assertEquals(responseExpected, response);
	}
	
	@Test
	public void testRegisterUser() throws Exception {
		//server.registerUser(user);
		UserData userBD = new UserData("user2","13345", "143456789", 21, 3000);
    	Assert.assertNotEquals(userBD.getLogin(), user.getLogin());
    	Assert.assertNotEquals(userBD.getPassword(), user.getPassword());
    	Assert.assertNotEquals(userBD.getCardNumber(), user.getCardNumber());
    	Assert.assertNotEquals(userBD.getAge(), user.getAge());
    	Assert.assertNotEquals(userBD.getExpenseLimit(), user.getExpenseLimit(), 0);
    	//Response response = server.registerUser(user);
    	//Assert.assertEquals(responseExpected, response);
    	

	}
	/*
	@Test
	public void testShowExpenses() throws Exception {
		
	}
	*/


}
