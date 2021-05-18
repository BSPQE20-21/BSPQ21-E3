package es.deusto.server.jdo;

import org.junit.Test;
import org.junit.Before;
import es.deusto.server.jdo.User;
import es.deusto.server.jdo.Expense;
import static org.junit.Assert.*;
import org.apache.log4j.Logger;


/**
 * This class is a collection of test focused on validating the USER 
 */
public class UserTest {


	User userT1, userT2; 
	Expense exT1; 
	static Logger logger = Logger.getLogger(UserPerfTest.class.getName());


	/**
	 * The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * Both users will be the same in the initialization and userT1 will suffer changes in each test so we can validate all the functionalities.
	 * @throws Exception
	 */
	@Before
	public void setUp()throws Exception {
		logger.info("Entering setUp");
		userT1 = new User("user", "123", "1234567", 20, 200); 
		userT2 = new User("user", "124", "1234567", 20, 200); 
		exT1 = new Expense("Apple", 1, Category.FOOD); 
		logger.info("Leaving setUp");

	}

	/**
	 * Test the constructor of the User class\n
	 * The class type is validated\n
	 * 
	 */
	@Test
	public void testUser() throws Exception {
		logger.info("Starting testUser");
		assertEquals(User.class, userT1.getClass()); 
		logger.info("Finishing testUser");

	}

	/**
	 * This test validates that expenses are added corretly to a user\n
	 * We are going to use ASSERT NOT EQUALS checking if the userT1 which is supposed to have 1 expense
	 * is different than userT2 that has no expenses.
	 */
	@Test
	public void testAddExpense() throws Exception {
		logger.info("Starting testAddExpense");
		userT1.addExpense(exT1);
		assertNotEquals(userT1.getMessages(), userT2.getMessages()); 
		logger.info("Finishing testAddExpense");


	}
	/**
	 * This test validates that expenses can be removed from a concrete user\n
	 * We will use userT1, remove the only expense it has and check is it has none like userT2
	 * @throws Exception
	 */
	@Test
	public void testRemoveExpense() throws Exception {
		logger.info("Starting testRemoveExpense");
		userT1.removeExpense(exT1);
		assertEquals(userT1.getMessages(), userT2.getMessages()); 
		logger.info("Finishing testRemoveExpense");


	}
	/**
	 * This test checks is the getter returns the correct login-email value
	 * @throws Exception
	 */
	@Test
	public void testGetLogin() throws Exception {
		logger.info("Starting testGetLogin");
		assertEquals(userT1.getLogin(), "user"); 
		logger.info("Finishing testGetLogin");


	}

	/**
	 * This test makes changes to the user login using the setter and validates if the changes are correctly done.
	 * @throws Exception
	 */
	@Test
	public void testSetLogin() throws Exception {
		logger.info("Starting testSetLogin");
		userT1.setLogin("userNew");
		assertNotEquals(userT1.getLogin(), userT2.getLogin()); 

		userT1.setLogin("user");
		assertEquals(userT1.getLogin(), userT2.getLogin()); 
		logger.info("Finishing testSetLogin");


	}


	/**
	 * This test validates if the password getter returns the correct value
	 * @throws Exception
	 */
	@Test
	public void testGetPassword() throws Exception {
		logger.info("Starting testGetPassword");
		assertEquals(userT1.getPassword(), "123"); 
		logger.info("Finishing testGetPassword");

	}

	/**
	 * Validates if the setter of the password works correctly\n
	 * To do that the password is changed twice validating that in both cases the changes is done
	 * @throws Exception
	 */
	@Test
	public void testSetPassword() throws Exception {
		logger.info("Starting testSetPassword");
		userT1.setPassword("pasNew");
		assertNotEquals(userT1.getPassword(), userT2.getPassword()); 

		userT1.setPassword("123");
		assertEquals(userT1.getPassword(), userT2.getPassword()); 
		logger.info("Finishing testSetPassword");

	}
	/**
	 * Validates that the age getter return the correct value
	 * @throws Exception
	 */
	@Test
	public void testGetAge() throws Exception {
		logger.info("Starting testGetAge");
		assertEquals(userT1.getAge(), 20); 
		logger.info("Finishing testGetAge");

	}

	/**
	 * Validates if the setter of the age works correctly\n
	 * To do that the age is changed twice validating that in both cases the changes is done
	 */
	@Test
	public void testSetAge() throws Exception {
		logger.info("Starting testSetAge");
		userT1.setAge(21);
		assertNotEquals(userT1.getAge(), userT2.getAge()); 

		userT1.setAge(20);
		assertEquals(userT1.getAge(), userT2.getAge());
		logger.info("Finishing testSetAge");

	}
	/**
	 * Validates that the card Number getter return the correct value
	 * @throws Exception
	 */
	@Test
	public void testGetCardNumber() throws Exception {
		logger.info("Starting testGetCardNumber");
		assertEquals(userT1.getCardNumber(), userT2.getCardNumber()); 
		logger.info("Finishing testGetCardNumber");

	}
	/**
	 * Validates if the setter of the cardNumber works correctly\n
	 * To do that the card Number is changed twice validating that in both cases the changes is done
	 */
	@Test
	public void testSetCardNumber() throws Exception {
		logger.info("Starting testSetCardNumber");
		userT1.setCardNumber("21");
		assertNotEquals(userT1.getCardNumber(), userT2.getCardNumber()); 

		userT1.setCardNumber("1234567");
		assertEquals(userT1.getCardNumber(), userT2.getCardNumber());
		logger.info("Finishing testSetCardNumber");

	}
	/**
	 * This test validates if the getter of the expense limit
	 * @throws Exception
	 */
	@Test
	public void testGetExpenseLimit() throws Exception {
		logger.info("Starting testGetExpenseLimit");
		assertEquals(userT1.getExpenseLimit(), userT2.getExpenseLimit(), 0); 
		logger.info("Finishing testGetExpenseLimit");

	}


	/**
	 * Validates if the setter of the expenselimit works correctly\n
	 * To do that the expense Limit is changed twice validating that in both cases the changes is done\n
	 * @throws Exception
	 */
	@Test
	public void testSetExpenseLimit() throws Exception {
		logger.info("Starting testSetExpenseLimit");
		userT1.setExpenseLimit(21);
		assertNotEquals(userT1.getExpenseLimit(), userT2.getExpenseLimit()); 

		userT1.setExpenseLimit(2000);
		assertEquals(userT1.getExpenseLimit(), userT2.getExpenseLimit(),0);
		logger.info("Finishing testSetExpenseLimit");

	}

	/**
	 * This validates that the getter of the messages(expenses) works correctly by checking if two users have the same messages\n
	 * @throws Exception
	 */
	@Test
	public void testGetMessages() throws Exception {
		logger.info("Starting testGetMessages");
		assertEquals(userT1.getMessages(), userT2.getMessages()); 
		logger.info("Finishing testGetMessages");

	}


}
