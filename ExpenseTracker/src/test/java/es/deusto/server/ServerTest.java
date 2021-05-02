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
import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;


@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
@Testable
public class ServerTest {
	
	Server server;
	UserData user;
	ExpenseData expenseDataExpected;
	DirectedMessage dMExpected;
	Response responseExpected;

	@Before
    public void setUp() throws Exception {
	
		user = new UserData("user", "12345", "123456789", 20, 2000);
		expenseDataExpected = new ExpenseData();
    	expenseDataExpected.setAmount(1);
    	expenseDataExpected.setCategory(Category.CLOTHES);
    	expenseDataExpected.setText("expenseUno");
    	dMExpected.setUserData(user);
    	dMExpected.setExpenseData(expenseDataExpected);
    	responseExpected = Response.ok(dMExpected).build();
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
		Assert.assertEquals(mockedMsg, dMExpected);
		Response response = server.storeExpense(mockedMsg);
		Assert.assertEquals(responseExpected, response);
		
	}
	
	
	/*
	@Test
	public void testValidateUser() throws Exception {
		
	}
	/*
	@Test
	public void testRegisterUser() throws Exception {
		server.registerUser(user);
		ArgumentCaptor<UserData> userCaptor = ArgumentCaptor.forClass(UserData.class);
		verify(server).registerUser(userCaptor.capture());
		UserData newUser = userCaptor.getValue();
		Assert.assertEquals(user.getLogin(), newUser.getLogin());

	}
	*/
	@Test
	public void testShowExpenses() throws Exception {
		
	}



}
