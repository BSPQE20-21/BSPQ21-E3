package es.deusto.server.jdo;

import org.junit.Test;
import org.junit.Before;

import es.deusto.server.jdo.*;
import static org.junit.Assert.*;
import org.apache.log4j.Logger;

/**
 * This class validates the constructor and getters and setters of the Expense
 * class (JDO)
 */
public class ExpenseTest {

	Expense exT1, exT2;
	User user; 
	static Logger logger = Logger.getLogger(UserPerfTest.class.getName());

	@Before
	public void setUp() throws Exception {
		logger.info("Entering setUp");
		exT1 = new Expense("Apple", 1, Category.FOOD);
		exT2 = new Expense("Apple", 1, Category.FOOD);
		user = new User("user", "123", "1234567", 20, 200); 
		logger.info("Leaving setUp");

	}

	/**
	 * Tests that the constructor works properly by validating that the new object
	 * created is of the correct type
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExpense() throws Exception {
		logger.info("Starting testExpense");
		Expense exNew = new Expense("Apple", 1, Category.FOOD);
		assertEquals(exNew.getClass(), Expense.class);
		logger.info("Finishing testExpense");

	}

	/**
	 * Checks if the text of the user is correctly returned
	 * 
	 * @throws Exception
	 */

	@Test
	public void testGetText() throws Exception {
		logger.info("Starting testGetText");
		assertEquals(exT1.getText(), "Apple");
		logger.info("Finishing testGetText");

	}

	/**
	 * This test makes a change in the text-description using the SETTER
	 * and validates that the expense is correctly changed because it should be different than the second expense.
	 * @throws Exception
	 */
	@Test
	public void testSetText() throws Exception {
		logger.info("Starting testSetText");
		exT1.setText("Orange");
		assertNotEquals(exT1.getText(), exT2.getText());
		logger.info("Finishing testSetText");

	}
	/**
	 * This test validates that the getter of the category returns the correct category. 
	 * @throws Exception
	 */
	@Test
	public void testGetCategory() throws Exception {
		logger.info("Starting testGetCategory");
		assertEquals(exT1.getCategory(), Category.FOOD);
		logger.info("Finishing testGetCategory");

	}
	/**
	 * This test checks if the setter of category changes correctly the values. 
	 * @throws Exception
	 */
	@Test
	public void testSetCategory() throws Exception {
		logger.info("Starting testSetCategory");
		exT1.setCategory(Category.OTHERS);
		assertNotEquals(exT1.getCategory(), exT2.getCategory());

		exT1.setCategory(Category.FOOD);
		assertEquals(exT1.getCategory(), exT2.getCategory());
		logger.info("Finishing testSetCategory");


	}
	/**
	 * This test validates that the getter of user returns the correct value 
	 * @throws Exception
	 */
	@Test
	public void testGetUser() throws Exception {
		logger.info("Starting testGetUser");
		assertEquals(exT1.getUser(), exT2.getUser());
		logger.info("Finishing testGetUser");

	}

	/**
	 * This test assigns a user to an expense and validates that the associeation is correctly made.
	 */
	@Test
	public void testSetUser() throws Exception {
		logger.info("Starting testSetUser");
		exT1.setUser(user); 
		assertNotEquals(exT1.getUser(), exT2.getUser()); 

		assertEquals(exT1.getUser().getLogin(), "user"); 
		logger.info("Finishing testSetUser");


	}

	/**
	 * This test validates that the amount getter returns a correct value
	 * @throws Exception
	 */
	@Test
	public void testGetAmount() throws Exception {
		logger.info("Starting testGetAmount");
		assertEquals(exT1.getAmount(), 1,0); 
		logger.info("Finishing testGetAmount");


	}
	/**
	 * This test changes the value of the amount using the setter and checks if it correctly done. 
	 * @throws Exception
	 */
	@Test
	public void testSetAmount() throws Exception {
		logger.info("Starting testSetAmount");
		exT1.setAmount(5); 
		assertNotEquals(exT1.getAmount(), exT2.getAmount()); 

		exT1.setAmount(1);
		assertEquals(exT1.getAmount(), exT2.getAmount(), 0);
		logger.info("Finishing testSetAmount");

	}

}
