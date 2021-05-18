package es.deusto.serialization;


import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;

/**
 * This class is a collection of test that validates that the user data object is 
 * correctly generated and that all the getters and setters work properly well.
 */
public class UserDataTest {
	
	UserData userData;
	UserData userData2;
	
	static Logger logger = Logger.getLogger(UserDataTest.class.getName());
	/**
	 * The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * A simple USER DATA Example - Mireya , 11111, 20, 111111111, 1000
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		logger.info("Entering Set UP");
		userData = new UserData();
		userData.setLogin("Mireya");
		userData.setPassword("11111");
		userData.setAge(20);
		userData.setCardNumber("111111111");
		userData.setExpenseLimit(1000);
		
		userData2 = new UserData("Haizea", "22222", "222222222", 20, 1500);
		logger.info("Leaving Set UP");


	}

	/**
	 * Test the constructor.\n
	 * Checks if the user is corretly created and validate that the attributes are the same
	 * @throws Exception
	 */
	@Test
	public void testUserData() throws Exception {
		logger.info("Entering testUserData");

		UserData userMireya = new UserData("Mireya", "11111", "111111111", 20, 1000);
		Assert.assertEquals(userMireya.getLogin(), userData.getLogin());

		logger.info("Leaving testUserData");
	}


	/**
	 * This test validates if the object can return the correct card number
	 * @throws Exception
	 */
	@Test
	public void testGetCardNumber() throws Exception {
		logger.info("Entering testGetCardNumber");
		assertEquals("111111111", userData.getCardNumber());
		logger.info("Leaving testGetCardNumber");
	}

	/**
	 * This test that the setter of the card number works well\n
	 * Inside the test a card number is assigned to a user and validates that it has changed correctly\n
	 * @throws Exception
	 */
	@Test
	public void testSetCardNumber() throws Exception {
		logger.info("Entering testSetCardNumber");
		userData2.setCardNumber("987654321");
		assertEquals("987654321", userData2.getCardNumber());
		logger.info("Leaving testSetCardNumber");
	}

	/**
	 * This test validates if the object can return the correct age of the user
	 * @throws Exception
	 */
	@Test
	public void testGetAge() throws Exception {
		logger.info("Entering testGetAge");
		assertEquals(20, userData.getAge());
		logger.info("Leaving testGetAge");
	}
	/**
	 * This test that the setter of the age works well\n
	 * Inside the test a age is assigned to a user and validates that it has changed correctly\n
	 * @throws Exception
	 */
	@Test
	public void testSetAge() throws Exception {
		logger.info("Entering testGetAge");
		userData2.setAge(21);
		assertEquals(21, userData2.getAge());
		logger.info("Leaving testGetAge");

	}
	/**
	 * This test validates if the object can return the correct email of the user
	 * @throws Exception
	 */

	@Test
	public void testGetLogin() throws Exception {
		logger.info("Entering testGetLogin");
		assertEquals("Mireya", userData.getLogin());
		logger.info("Leaving testGetLogin");
	}
	/**
	 * This test that the setter of the login works well\n
	 * Inside the test a email is assigned to a user and validates that it has changed correctly\n
	 * @throws Exception
	 */
	@Test
	public void testSetLogin() throws Exception {
		logger.info("Entering testSetLogin");
		userData2.setLogin("Haizea Rodriguez");
		assertEquals("Haizea Rodriguez", userData2.getLogin());
		logger.info("Leaving testSetLogin");

	}
	/**
	 * This test validates if the object can return the correct password of the user
	 * @throws Exception
	 */
	@Test
	public void testGetPassword() throws Exception {
		logger.info("Entering testGetPassword");
		assertEquals("11111", userData.getPassword());
		logger.info("Leaving testGetPassword");
	}
	
	/**
	 * This test that the setter of the password works well\n
	 * Inside the test a password is assigned to a user and validates that it has changed correctly\n
	 * @throws Exception
	 */
	@Test
	public void testSetPassword() throws Exception {
		logger.info("Entering testSetPassword");
		userData2.setPassword("54321");
		assertEquals("54321", userData2.getPassword());
		logger.info("Leaving testSetPassword");

	}
	/**
	 * This test validates if the object can return the correct expenseLimit of the user
	 * @throws Exception
	 */
	@Test
	public void testGetExpenseLimit() throws Exception {
		logger.info("Entering testGetExpenseLimit");
		assertEquals(1000, userData.getExpenseLimit());
		logger.info("Leaving testGetExpenseLimit");
	}
	/**
	 * This test that the setter of the expense limit works well\n
	 * Inside the test a expense limit is assigned to a user and validates that it has changed correctly\n
	 * @throws Exception
	 */
	@Test
	public void testSetExpenseLimit() throws Exception {
		logger.info("Entering testSetExpenseLimit");
		userData2.setExpenseLimit(3000);
		assertEquals(3000, userData2.getExpenseLimit());
		logger.info("Leaving testSetExpenseLimit");

	}
	

}
