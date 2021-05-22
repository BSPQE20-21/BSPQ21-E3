package es.deusto.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.platform.commons.annotation.Testable;


import javax.ws.rs.core.Response;

//import org.databene.contiperf.PerfTest;
//import org.databene.contiperf.Required;

import com.github.javatlacati.contiperf.PerfTest;
import com.github.javatlacati.contiperf.Required;


import es.deusto.serialization.DirectedMessage;
import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.LoginData;
import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;
import org.apache.log4j.Logger;


@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
@Testable
public class ServerPerfTest {
	
	Server server;
	UserData user;
	ExpenseData expenseDataExpected;
	DirectedMessage dMExpected = new DirectedMessage();
	Response responseExpected;
	static Logger logger = Logger.getLogger(ServerPerfTest.class.getName());
	
	/**
	 * This method is the SET UP that will create the instances and objects needed to perform all the tests
	 * @throws Exception
	 */
	@Before
    public void setUp() throws Exception {
		server = new Server();
		user = new UserData("userTest", "12345", "123456789", 20, 2000);
		expenseDataExpected = new ExpenseData();
    	expenseDataExpected.setAmount(1);
    	expenseDataExpected.setCategory(Category.CLOTHES);
    	expenseDataExpected.setText("expenseUno");
    	dMExpected.setUserData(user);
    	dMExpected.setExpenseData(expenseDataExpected);
		server.registerUser(user);


    	responseExpected = Response.ok().build();
	}
	
	/**
	 * This is a performance test that calls the constructor of the server multiple times imitating the situation where 
	 * several client will contact the server at the same time.
	 * @throws Exception
	 */
	@Test
	@PerfTest(invocations = 100, threads = 20)
	@Required(max = 20000, average = 3000)
	public void testServer() throws Exception {
		server = new Server();
	}

	/**This test validates if the response of the method registerUser is the same as the expected
	 * @throws Exception
	 */
	@PerfTest(invocations = 5)
	@Required(max = 1200, average = 250)
	@Testable
	public void testRegisterUser() throws Exception {
		//server.registerUser(user);
		//UserData userBD = new UserData("user2","13345", "143456789", 21, 3000);
    	//Assert.assertNotEquals(userBD.getLogin(), user.getLogin());
    	//Assert.assertNotEquals(userBD.getPassword(), user.getPassword());
    	//Assert.assertNotEquals(userBD.getCardNumber(), user.getCardNumber());
    	//Assert.assertNotEquals(userBD.getAge(), user.getAge());
    	//Assert.assertNotEquals(userBD.getExpenseLimit(), user.getExpenseLimit(), 0);
    	Response response = server.registerUser(user);
    	Assert.assertEquals(responseExpected.getStatus(), response.getStatus());
    	

	}
	
	/**
	 * The idea of the test is to store an expense which is related to a concrete user (this relationship is represented in the DirectMessage object)
	 * and to check if it was done correctly\n
	 * To do so we look if the RESPONSE is OK and if it we can say that the expense has been correctly stored.
	 * @throws Exception
	 */
	@PerfTest(invocations = 5)
	@Required(max = 1200, average = 250)
	@Testable
	public void testStoreExpense() throws Exception {
		
		Response response = server.storeExpense(dMExpected);
		Assert.assertEquals(responseExpected.getStatus(), response.getStatus());
		
	}
	
	
	/**This test validates if the response obtained when validating the user is the expected
	 * @throws Exception
	 */
	@PerfTest(invocations = 5)
	@Required(max = 1200, average = 250)
	@Testable
	public void testValidateUser() throws Exception {

		LoginData loginData = new LoginData(user.getLogin(),user.getPassword());
    	Response response = server.validateUser(loginData);
		// TODO  response read entity returns an error
		//UserData userData = response.readEntity(UserData.class);
		Assert.assertEquals(responseExpected.getStatus(), response.getStatus());
    	//Assert.assertEquals(loginData.getLogin(), userData.getLogin());
		//Assert.assertEquals(loginData.getPassword(), userData.getPassword());
	}
	
	
	/**This test validates the resonse of the methos show expense is the same as the expected
	 * @throws Exception
	 */
	@PerfTest(invocations = 5)
	@Required(max = 1200, average = 250)
	@Testable
	public void testShowExpenses() throws Exception {
		Response response = server.showExpenses(user); 
		/*
		ExpenseData exp = new ExpenseData();
		exp.setText(expenseDataExpected.getText());
		exp.setAmount(expenseDataExpected.getAmount());
		exp.setCategory(expenseDataExpected.getCategory());
		Assert.assertEquals(exp.getText(),expenseDataExpected.getText()); 
		Assert.assertEquals(exp.getAmount(),expenseDataExpected.getAmount(),0);
		Assert.assertEquals(exp.getCategory(),expenseDataExpected.getCategory());
		*/
    	Assert.assertEquals(responseExpected.getStatus(), response.getStatus());


	}
	
	/**This test validates if the response of the method updateUser is the same as the expected
	 * @throws Exception
	 */
	@PerfTest(invocations = 5)
	@Required(max = 1200, average = 250)
	@Testable
	public void testUpdateUser()throws Exception{
		Response response = server.updateuser(user); 
    	Assert.assertEquals(responseExpected.getStatus(), response.getStatus());



	}

	@After
    public void tearDown() throws Exception {
    	logger.info("Starting tearDown");
		server.deleteUser(user);
    	logger.info("Leaving tearDown");
    }
    


}