package es.deusto.serialization;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.deusto.server.jdo.Category;
import org.apache.log4j.Logger;

/**
 * This class is a collection of test that validates that the expense data object is 
 * correctly generated and that all the getters and setters work properly well.
 */
public class ExpenseDataTest {
	ExpenseData exp = new ExpenseData(); 
	ExpenseData emptyExp = new ExpenseData(); 
	static Logger logger = Logger.getLogger(ExpenseDataTest.class.getName());

	/**
	 * The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * A simple Expense Example - Apples,5,FOOD
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception{
		logger.info("Entering Set UP");
		exp.setText("Apples");
		exp.setAmount(5);
		exp.setCategory(Category.FOOD);		
		logger.info("Leaving Set UP");
	}

	/**
	 * Test the constructor\n 
	 * Validates if the expense is corretly generated\n 
	 */
	@Test
	public void testExpenseData() throws Exception {
		logger.info("Entering testExpenseData");
		ExpenseData testExp = new ExpenseData("Apples", 5, Category.FOOD);
		assertEquals(testExp.getClass(), ExpenseData.class);
		logger.info("Leaving testExpenseData");


	}


	/**
	 * Test if the getter of the text-description is correct\n
	 * Given an object EXP, that is generated in the SET UP, validate if the getter returns the information that should. 
	 */
	@Test
	public void testGetText() throws Exception {
		logger.info("Entering testGetText");
		assertEquals(exp.getText(), "Apples"); 
		logger.info("Leaving testGetText");

	}
	/**
	 * Assigns a value to the Text-description using the set method and validate that is has been correctly stored.\n
	 * 
	 * @throws Exception
	 */

	@Test
	public void testSetText() throws Exception {
		logger.info("Entering testSetText");
		emptyExp.setText("Apple");
		assertEquals(emptyExp.getText(), "Apple");
		// Change it and check if it works
		emptyExp.setText("Apple2");
		assertEquals(emptyExp.getText(), "Apple2");
		logger.info("Leaving testSetText");

	}

	/**
	 * Test if the getter of the amount is correct\n
	 * Given an object EXP, that is generated in the SET UP, validate if the getter returns the information that should.\n
	 * The assert Equals uses a third parameter 0 inidcating that they should be exactly the same
	 */
	@Test
	public void testGetAmount() throws Exception {
		logger.info("Entering testGetAmount");
		assertEquals(exp.getAmount(), 5, 0);
		logger.info("Leaving testGetAmount");
		

	}
	/**
	 * Assigns a value to the Amount using the set method and validate that is has been correctly stored.\n
	 * Change the value and check again if this is done correctly.
	 * @throws Exception
	 */

	@Test
	public void testSetAmount() throws Exception {
		logger.info("Entering testSetAmount");
		emptyExp.setAmount(2);
		assertEquals(emptyExp.getAmount(), 2, 0);
		// Change it and check if it still works
		emptyExp.setAmount(3);
		assertEquals(emptyExp.getAmount(), 3, 0);
		logger.info("Leaving testSetAmount");

		

	}

	/**
	 * Test if the getter of the amount is correct\n
	 * Given an object EXP, that is generated in the SET UP, validate if the getter returns the information that should.\n
	 */
	@Test
	public void testGetCategory() throws Exception {
		logger.info("Entering testGetCategory");
		assertEquals(exp.getCategory(),Category.FOOD); 
		logger.info("Leaving testGetCategory");
	}

	/**
	 * Assigns a value to the CATEGORY using the set method and validate that is has been correctly stored.\n
	 * Change the value and check again if this is done correctly.
	 * @throws Exception
	 */

	@Test
	public void testSetCategory() throws Exception {
		logger.info("Entering testSetCategory");
		emptyExp.setCategory(Category.FOOD);
		assertEquals(emptyExp.getCategory(), Category.FOOD);
		// Change it and check if it still works
		emptyExp.setCategory(Category.OTHERS);
		assertEquals(emptyExp.getCategory(), Category.OTHERS);
		logger.info("Leaving testSetCategory");

	}

	@Test
	public void testToString() throws Exception {
		//TODO

	}

}
