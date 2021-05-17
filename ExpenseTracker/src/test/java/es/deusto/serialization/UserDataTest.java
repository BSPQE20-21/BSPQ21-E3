package es.deusto.serialization;


import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * This class is a collection of test that validates that the user data object is 
 * correctly generated and that all the getters and setters work properly well.
 */
public class UserDataTest {
	
	UserData userData;
	UserData userData2;
	

	/**
	 * The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * A simple USER DATA Example - Mireya , 11111, 20, 111111111, 1000
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		userData = new UserData();
		userData.setLogin("Mireya");
		userData.setPassword("11111");
		userData.setAge(20);
		userData.setCardNumber("111111111");
		userData.setExpenseLimit(1000);
		
		userData2 = new UserData("Haizea", "22222", "222222222", 20, 1500);
	}

	/**
	 * Test the constructor.\n
	 * Checks if the user is corretly created and validate that the attributes are the same
	 * @throws Exception
	 */
	@Test
	public void testUserData() throws Exception {
		UserData userMireya = new UserData("Mireya", "11111", "111111111", 20, 1000);

		Assert.assertEquals(userMireya.getLogin(), userData.getLogin());
	}


	/**
	 * This test validates if the object can return the correct card number
	 * @throws Exception
	 */
	@Test
	public void testGetCardNumber() throws Exception {
		assertEquals("111111111", userData.getCardNumber());
	}

	/**
	 * This test that the setter of the card number works well\n
	 * Inside the test a card number is assigned to a user and validates that it has changed correctly\n
	 * @throws Exception
	 */
	@Test
	public void testSetCardNumber() throws Exception {
		userData2.setCardNumber("987654321");
		assertEquals("987654321", userData2.getCardNumber());
	}

	/**
	 * This test validates if the object can return the correct age of the user
	 * @throws Exception
	 */
	@Test
	public void testGetAge() throws Exception {
		assertEquals(20, userData.getAge());
	}
	/**
	 * This test that the setter of the age works well\n
	 * Inside the test a age is assigned to a user and validates that it has changed correctly\n
	 * @throws Exception
	 */
	@Test
	public void testSetAge() throws Exception {
		userData2.setAge(21);
		assertEquals(21, userData2.getAge());
	}
	/**
	 * This test validates if the object can return the correct email of the user
	 * @throws Exception
	 */

	@Test
	public void testGetLogin() throws Exception {
		assertEquals("Mireya", userData.getLogin());
	}
	/**
	 * This test that the setter of the login works well\n
	 * Inside the test a email is assigned to a user and validates that it has changed correctly\n
	 * @throws Exception
	 */
	@Test
	public void testSetLogin() throws Exception {
		userData2.setLogin("Haizea Rodriguez");
		assertEquals("Haizea Rodriguez", userData2.getLogin());
	}
	/**
	 * This test validates if the object can return the correct password of the user
	 * @throws Exception
	 */
	@Test
	public void testGetPassword() throws Exception {
		assertEquals("11111", userData.getPassword());
	}
	
	/**
	 * This test that the setter of the password works well\n
	 * Inside the test a password is assigned to a user and validates that it has changed correctly\n
	 * @throws Exception
	 */
	@Test
	public void testSetPassword() throws Exception {
		userData2.setPassword("54321");
		assertEquals("54321", userData2.getPassword());
	}
	/**
	 * This test validates if the object can return the correct expenseLimit of the user
	 * @throws Exception
	 */
	@Test
	public void testGetExpenseLimit() throws Exception {
		assertEquals(1000, userData.getExpenseLimit());
	}
	/**
	 * This test that the setter of the expense limit works well\n
	 * Inside the test a expense limit is assigned to a user and validates that it has changed correctly\n
	 * @throws Exception
	 */
	@Test
	public void testSetExpenseLimit() throws Exception {
		userData2.setExpenseLimit(3000);
		assertEquals(3000, userData2.getExpenseLimit());
	}
	

}
