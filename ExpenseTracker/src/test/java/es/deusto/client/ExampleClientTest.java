package es.deusto.client;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;

import es.deusto.serialization.DirectedMessage;
import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;
import es.deusto.server.jdo.Expense;
import es.deusto.server.jdo.User;
import es.deusto.server.jdo.UserPerfTest;

/**
 * This class is a collection of test that validates that the CLIENT side methods work properly well\n
 * All the methods that are included inside es.deusto.client.ExampleClient are going to be tested
 * 
 */

public class ExampleClientTest {

    ExampleClient exampleClient;
    UserData userExpected; 
    DirectedMessage dMExpected = new DirectedMessage();
    ExpenseData expenseDataExpected;
    Expense expenseExpected;
    
    ResourceBundle resourceBundle;
	static Logger logger = Logger.getLogger(ExampleClientTest.class.getName());


	/**
	 * The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * An exampleClient will be created as well as a default user and expense. 
	 * @throws Exception
	 */

    @Before
    public void setUp() throws Exception {
		logger.info("Entering setUp");
        exampleClient = new ExampleClient("127.0.0.1", "8080");
        userExpected = new UserData();
        userExpected.setLogin("userTest");
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
        exampleClient.registerUser(userExpected);
		logger.info("Leaving setUp");

        
    }

    

    /**
     * This test is related to es.deusto.client.ClientExample.registerUser \n
     * @throws Exception
     */
    
    @Test
    public void testRegisterUser() throws Exception {
    	logger.info("Starting testRegisterUser");
        assertDoesNotThrow(()->exampleClient.registerUser(userExpected));
        logger.info("Finishing testRegisterUser");
    }
    
    /**
     * This test validates the user in the DB
     * @throws Exception
     */
    
    @Test
    public void testValidateUser() throws Exception {
        logger.info("Entering testValidateUser");

        //exampleClient.getResourceBundle().getString("update"); 
        logger.info("Validating the user ...");
        UserData userDB = exampleClient.validateUser(userExpected.getLogin(), userExpected.getPassword());
        
        Assert.assertEquals(userDB.getLogin(), userExpected.getLogin());
        Assert.assertEquals(userDB.getPassword(), userExpected.getPassword());
        Assert.assertEquals(userDB.getCardNumber(), userExpected.getCardNumber());
        Assert.assertEquals(userDB.getAge(), userExpected.getAge(),0);
        Assert.assertEquals(userDB.getExpenseLimit(), userExpected.getExpenseLimit(), 0);
        //Assert.assertSame(userExpected, userDB);
        logger.info("Leaving testValidateUser");
       
    }
    

   /** 
    * This test validates the Expense Storing in the DB
    * @throws Exception
    */
    @Test
    public void testStoreExpense() throws Exception {
    	logger.info("Starting testStoreExpense");
        assertDoesNotThrow(()->exampleClient.storeExpense(userExpected, expenseDataExpected));
        logger.info("Finishing testStoreExpense");

    }
   

    
    
    
    /**This test handles the validation of showing the expenses 
     * @throws Exception
     */
    @Test
    public void testShowExpenses() throws Exception {
        logger.info("Starting testShowExpenses");
    	
    	Set<ExpenseData> expenseList = exampleClient.showExpenses(userExpected);
    	
    	//assertEquals(1, expenseList.size());
    	
    	for (ExpenseData expense : expenseList) {
			assertEquals(expenseDataExpected.getAmount(), expense.getAmount(), 0);
			assertEquals(expenseDataExpected.getCategory(), expense.getCategory());
			assertEquals(expenseDataExpected.getText(), expense.getText());
		}
        logger.info("Finishing testShowExpenses");

    }
     
    /**This test validates if the user is updated correctly
     * @throws Exception
     */
    @Test
    public void testUpdateUser() throws Exception {
        logger.info("Starting testUpdateUser");
        UserData newUser = new UserData(); 
        newUser.setLogin("userTest");
        newUser.setPassword("newPass");
        newUser.setAge(20);
        newUser.setCardNumber("123456789");  
        newUser.setExpenseLimit(2000);
        logger.info("Updating user ...");

        UserData userDB = exampleClient.updateUser(newUser);
        
        Assert.assertEquals(userDB.getLogin(), userExpected.getLogin());
        Assert.assertNotEquals(userDB.getPassword(), userExpected.getPassword());
        logger.info("Leaving testUpdateUser");
    }
    
    /**This test validates if the created client is from the class ExampleClient 
     * @throws Exception
     */
    @Test
    public void testMain() throws Exception {
        logger.info("Starting testMain");
        ExampleClient client = new ExampleClient("127.0.0.1", "8080"); 
        assertEquals(client.getClass(), ExampleClient.class); 
        logger.info("Leaving testMain");    
    }

    
    /**The after of the test
     * Deletes the user after it is tested
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    	logger.info("Starting tearDown");
    	exampleClient.deleteUser(userExpected);
    	logger.info("Leaving tearDown");
    }
    
}