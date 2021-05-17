package es.deusto.server.jdo;

import org.junit.Test;
import org.junit.Before;

import es.deusto.server.jdo.Expense;
import static org.junit.Assert.*;

/**
 * This class validates the constructor and getters and setters of the Expense class (JDO)
 */
public class ExpenseTest {

	Expense exT1, exT2; 


	@Before
	public void setUp() throws Exception{
		exT1 = new Expense("Apple", 1, Category.FOOD); 
		exT2 = new Expense("Apple", 1, Category.FOOD); 

	}
	/**
	 * Tests that the constructor works properly by validating that the new object created is of the correct type
	 * @throws Exception
	 */
	@Test
	public void testExpense() throws Exception {
		Expense exNew = new Expense("Apple", 1, Category.FOOD); 
		assertEquals(exNew.getClass(), Expense.class);
	}

	/**
	 * Checks if the text of the user is correctly returned
	 * @throws Exception
	 */

	@Test
	public void testGetText() throws Exception {
		assertEquals(exT1.getText(), "Apple"); 

	}
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetText() throws Exception {
		exT1.setText("Orange"); 
		assertNotEquals(exT1.getText(), exT2.getText()); 

	}

	@Test
	public void testGetCategory() throws Exception {

	}

	@Test
	public void testSetCategory() throws Exception {

	}

	@Test
	public void testGetUser() throws Exception {

	}

	@Test
	public void testSetUser() throws Exception {

	}

	@Test
	public void testGetAmount() throws Exception {

	}

	@Test
	public void testSetAmount() throws Exception {

	}


}
