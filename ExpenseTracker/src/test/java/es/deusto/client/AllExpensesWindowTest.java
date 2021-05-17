package es.deusto.client;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;

public class AllExpensesWindowTest {
	ExpenseData expenseData;
	ExampleClient exampleClient;
	UserData userExpected;
	AllExpensesWindow aew;
	Set<ExpenseData> expenses;

	/**The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * An expenseData will be created. 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		expenseData = new ExpenseData("apple", 1.0, Category.FOOD);
		exampleClient = new ExampleClient("127.0.0.1", "8080");
        userExpected = new UserData();
        userExpected.setLogin("user");
        userExpected.setPassword("12345");
        userExpected.setAge(20);
        userExpected.setCardNumber("123456789");
        userExpected.setExpenseLimit(2000);
    	expenses = new HashSet<ExpenseData>();
    	expenses.add(expenseData);

		aew = new AllExpensesWindow(exampleClient, expenses, userExpected);

		
	}


	/**This method validates if the created window is from the same class as AllExpensesWindow
	 * @throws Exception
	 */
	@Test
	public void testAllExpensesWindowClass() throws Exception {
		assertEquals(aew.getClass(), AllExpensesWindow.class);
		
	}
	
	/**This method validates if the created window is visible
	 * @throws Exception
	 */
	@Test
	public void testAllExpensesWindowVisible() throws Exception {
		assertTrue(aew.isVisible());
	}
	
	/**This method validates if the created window disposes on close
	 * @throws Exception
	 */
	@Test
	public void testAllExpensesWindowCloseOperation() throws Exception {
		assertEquals(aew.getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
		
	}

	@Test
	public void testActionPerformed() throws Exception {

	}

}
