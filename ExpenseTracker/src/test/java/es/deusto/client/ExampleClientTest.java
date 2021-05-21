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
    * 
    * @throws Exception
    */
    
    @Test
    public void testStoreExpense() throws Exception {
    	logger.info("Starting testStoreExpense");
        assertDoesNotThrow(()->exampleClient.storeExpense(userExpected, expenseDataExpected));
        logger.info("Finishing testStoreExpense");

    }
   
    /**
     * 
     * @throws Exception
     */
    
    @Test
    public void testValidateUser() throws Exception {

        //exampleClient.getResourceBundle().getString("update"); 
        UserData userDB = exampleClient.validateUser("userTest", "12345");
        
        Assert.assertEquals(userDB.getLogin(), userExpected.getLogin());
        Assert.assertEquals(userDB.getPassword(), userExpected.getPassword());
        Assert.assertEquals(userDB.getCardNumber(), userExpected.getCardNumber());
        Assert.assertEquals(userDB.getAge(), userExpected.getAge());
        Assert.assertEquals(userDB.getExpenseLimit(), userExpected.getExpenseLimit(), 0);
        //Assert.assertSame(userExpected, userDB);
       
    }
    
    
    
    @Test
    public void testShowExpenses() throws Exception {
    	
    	Set<ExpenseData> expenseList = exampleClient.showExpenses(userExpected);
    	
    	//assertEquals(1, expenseList.size());
    	
    	for (ExpenseData expense : expenseList) {
			assertEquals(expenseDataExpected.getAmount(), expense.getAmount(), 0);
			assertEquals(expenseDataExpected.getCategory(), expense.getCategory());
			assertEquals(expenseDataExpected.getText(), expense.getText());
		}
    	
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
    

    
    @After
    public void tearDown() throws Exception {
    	logger.info("Starting tearDown");
    	exampleClient.deleteUser(userExpected);
    	logger.info("Leaving tearDown");
    }
    
}