package es.deusto.client;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import es.deusto.server.jdo.User;

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

	/**
	 * The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * An exampleClient will be created as well as a default user and expense. 
	 * @throws Exception
	 */

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


    /**
     * This test is related to es.deusto.client.ClientExample.registerUser \n
     * @throws Exception
     */
    /*
    @Test
    public void testRegisterUser() throws Exception {
        UserData newUser = new UserData();
        newUser.setLogin("user1");
        newUser.setPassword("1234");
        //assertThrows(Exception.class, exampleClient.registerUser(userExpected)); 
        
        
        //Assert.assertNotEquals(newUser.getLogin(), userExpected.getLogin());
       //   Assert.assertNotEquals(newUser.getPassword(), userExpected.getPassword());

    }
    
    */

   /**
    * 
    * @throws Exception
    */
    /*
    @Test
    public void testStoreExpense() throws Exception {
        UserData newUser = new UserData();
        newUser.setLogin("user");
        newUser.setPassword("12345");

        ExpenseData newExpData = new ExpenseData();
        newExpData.setText("expenseUno");
        newExpData.setAmount(1);
        newExpData.setCategory(Category.CLOTHES);

        DirectedMessage newDM = new DirectedMessage();
        newDM.setUserData(newUser);
        newDM.setExpenseData(newExpData);
        Assert.assertNotEquals(dMExpected, newDM);

    }
   */
    /**
     * 
     * @throws Exception
     */
    /*
    @Test
    public void testValidateUser() throws Exception {

        //exampleClient.getResourceBundle().getString("update"); 
        UserData userDB = exampleClient.validateUser("user", "12345");
        
        Assert.assertEquals(userDB.getLogin(), userExpected.getLogin());
        Assert.assertEquals(userDB.getPassword(), userExpected.getPassword());
        Assert.assertEquals(userDB.getCardNumber(), userExpected.getCardNumber());
        Assert.assertEquals(userDB.getAge(), userExpected.getAge());
        Assert.assertEquals(userDB.getExpenseLimit(), userExpected.getExpenseLimit(), 0);
        //Assert.assertSame(userExpected, userDB);
       
    }
    */
    
    /*
    @Test
    public void testShowExpenses() throws Exception {
        UserData newUser = new UserData();
        newUser.setLogin("user1");
        newUser.setPassword("1234");
        Assert.assertNotEquals(newUser.getLogin(), userExpected.getLogin());
        Assert.assertNotEquals(newUser.getPassword(), userExpected.getPassword());
    }
    */  
    
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