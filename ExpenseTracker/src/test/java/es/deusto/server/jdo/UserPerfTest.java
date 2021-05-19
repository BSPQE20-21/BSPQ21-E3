package es.deusto.server.jdo;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;


import es.deusto.server.jdo.User;
import es.deusto.server.jdo.Expense;
import static org.junit.Assert.*;
import org.junit.platform.commons.annotation.Testable;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;

import org.apache.log4j.Logger;

/**
 * This class is a copy of UserTest, in this class will be validated the performance 
 */

@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
@Testable
public class UserPerfTest {


	User userT1, userT2; 
	Expense exT1; 
	static Logger logger = Logger.getLogger(UserPerfTest.class.getName());


	@Rule
	// TODO - error in the test that says the following --> java.lang.RuntimeException: java.lang.NoSuchFieldException: fNext
	// at org.databene.contiperf.junit.ContiPerfRule.apply(ContiPerfRule.java:176)
	public ContiPerfRule i = new ContiPerfRule();
	
	
	
	@Before
	public void setUp()throws Exception {
		logger.info("Entering setUp");
		userT1 = new User("user", "123", "1234567", 20, 200); 
		userT2 = new User("user", "123", "1234567", 20, 200); 
		exT1 = new Expense("Apple", 1, Category.FOOD); 
		logger.info("Leaving setUp");
	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testUser() throws Exception {
		logger.info("Starting testUser");
		assertEquals(User.class, userT1.getClass()); 
		logger.debug("Finishing testUser");
	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testAddExpense() throws Exception {
		logger.info("Starting testAddExpense");
		userT1.addExpense(exT1);
		assertNotEquals(userT1.getMessages(), userT2.getMessages()); 
		logger.debug("Finishing testAddExpense");

	}
	
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testRemoveExpense() throws Exception {
		logger.info("Starting testRemoveExpense");
		userT1.removeExpense(exT1);
		assertEquals(userT1.getMessages(), userT2.getMessages()); 
		logger.debug("Finishing testRemoveExpense");

	}
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetLogin() throws Exception {
		logger.debug("Starting testGetLogin");
		assertEquals(userT1.getLogin(), "user"); 
		logger.debug("Finishing testGetLogin");


	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetLogin() throws Exception {
		logger.debug("Starting testSetLogin");
		userT1.setLogin("userNew");
		assertNotEquals(userT1.getLogin(), userT2.getLogin()); 

		userT1.setLogin("user");
		assertEquals(userT1.getLogin(), userT2.getLogin()); 
		logger.debug("Finishing testSetLogin");


	}


	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetPassword() throws Exception {
		logger.debug("Starting testGetPassword");
		assertEquals(userT1.getPassword(), "123"); 
		logger.debug("Finishing testGetPassword");

	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetPassword() throws Exception {
		logger.debug("Starting testSetPassword");
		userT1.setPassword("pasNew");
		assertNotEquals(userT1.getPassword(), userT2.getPassword()); 

		userT1.setPassword("123");
		assertEquals(userT1.getPassword(), userT2.getPassword());
		logger.debug("Finishing testSetPassword");


	}
	
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetAge() throws Exception {
		logger.debug("Starting testGetAge");
		assertEquals(userT1.getAge(), 20); 
		logger.debug("Finishing testGetAge");

	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetAge() throws Exception {
		logger.debug("Starting testSetAge");
		userT1.setAge(21);
		assertNotEquals(userT1.getAge(), userT2.getAge()); 

		userT1.setAge(20);
		assertEquals(userT1.getAge(), userT2.getAge());
		logger.debug("Finishing testSetAge");


	}
	
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetCardNumber() throws Exception {
		logger.debug("Starting testGetCardNumber");
		assertEquals(userT1.getCardNumber(), userT2.getCardNumber()); 
		logger.debug("Finishing testGetCardNumber");

	}
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetCardNumber() throws Exception {
		logger.debug("Starting testSetCardNumber");
		userT1.setCardNumber("21");
		assertNotEquals(userT1.getCardNumber(), userT2.getCardNumber()); 

		userT1.setCardNumber("1234567");
		assertEquals(userT1.getCardNumber(), userT2.getCardNumber());
		logger.debug("Finishing testSetCardNumber");

	}
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetExpenseLimit() throws Exception {
		logger.debug("Finishing testGetExpenseLimit");
		assertEquals(userT1.getExpenseLimit(), userT2.getExpenseLimit(), 0); 
		logger.debug("Finishing testGetExpenseLimit");

	}

 
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetExpenseLimit() throws Exception {
		logger.debug("Starting testSetExpenseLimit");
		userT1.setExpenseLimit(21);
		assertNotEquals(userT1.getExpenseLimit(), userT2.getExpenseLimit()); 

		userT1.setExpenseLimit(200);
		assertEquals(userT1.getExpenseLimit(), userT2.getExpenseLimit(),0);
		logger.debug("Finishing testSetExpenseLimit");

	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetMessages() throws Exception {
		logger.debug("Starting testGetMessages");
		assertEquals(userT1.getMessages(), userT2.getMessages()); 
		logger.debug("Finishing testGetMessages");

	}


}
