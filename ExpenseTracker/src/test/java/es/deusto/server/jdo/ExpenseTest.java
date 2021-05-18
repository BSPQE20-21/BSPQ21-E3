package es.deusto.server.jdo;

import org.junit.Test;
import org.junit.Before;

import es.deusto.server.jdo.*;
import static org.junit.Assert.*;

/**
 * This class validates the constructor and getters and setters of the Expense
 * class (JDO)
 */
public class ExpenseTest {

	Expense exT1, exT2;
	User user; 

	@Before
	public void setUp() throws Exception {
		exT1 = new Expense("Apple", 1, Category.FOOD);
		exT2 = new Expense("Apple", 1, Category.FOOD);
		user = new User("user", "123", "1234567", 20, 200); 

	}

	/**
	 * Tests that the constructor works properly by validating that the new object
	 * created is of the correct type
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExpense() throws Exception {
		Expense exNew = new Expense("Apple", 1, Category.FOOD);
		assertEquals(exNew.getClass(), Expense.class);
	}

	/**
	 * Checks if the text of the user is correctly returned
	 * 
	 * @throws Exception
	 */

	@Test
	public void testGetText() throws Exception {
		assertEquals(exT1.getText(), "Apple");

	}

	/**
	 * This test makes a change in the text-description using the SETTER
	 * and validates that the expense is correctly changed because it should be different than the second expense.
	 * @throws Exception
	 */
	@Test
	public void testSetText() throws Exception {
		exT1.setText("Orange");
		assertNotEquals(exT1.getText(), exT2.getText());

	}
	/**
	 * This test validates that the getter of the category returns the correct category. 
	 * @throws Exception
	 */
	@Test
	public void testGetCategory() throws Exception {
		assertEquals(exT1.getCategory(), Category.FOOD);

	}
	/**
	 * This test checks if the setter of category changes correctly the values. 
	 * @throws Exception
	 */
	@Test
	public void testSetCategory() throws Exception {
		exT1.setCategory(Category.OTHERS);
		assertNotEquals(exT1.getCategory(), exT2.getCategory());

		exT1.setCategory(Category.FOOD);
		assertEquals(exT1.getAmount(), exT2.getCategory());

	}
	/**
	 * This test validates that the getter of user returns the correct value 
	 * @throws Exception
	 */
	@Test
	public void testGetUser() throws Exception {
		assertEquals(exT1.getUser(), exT2.getUser());

	}

	/**
	 * This test assigns a user to an expense and validates that the associeation is correctly made.
	 */
	@Test
	public void testSetUser() throws Exception {
		exT1.setUser(user); 
		assertNotEquals(exT1.getUser(), exT2.getUser()); 

		assertEquals(exT1.getUser().getLogin(), "user"); 


	}

	/**
	 * This test validates that the amount getter returns a correct value
	 * @throws Exception
	 */
	@Test
	public void testGetAmount() throws Exception {
		assertEquals(exT1.getAmount(), 1,0); 


	}
	/**
	 * This test changes the value of the amount using the setter and checks if it correctly done. 
	 * @throws Exception
	 */
	@Test
	public void testSetAmount() throws Exception {
		exT1.setAmount(5); 
		assertNotEquals(exT1.getAmount(), exT2.getAmount()); 

		exT1.setAmount(1);
		assertEquals(exT1.getAmount(), exT2.getAmount(), 0);

	}

}
