package es.deusto.server.jdo;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;

import es.deusto.server.jdo.*;
import static org.junit.Assert.*;
import org.apache.log4j.Logger;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.platform.commons.annotation.Testable;

/**This class is a copy of ExpenseTest, in this class will be validated the performance
 * 
 */
@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
@Testable
public class ExpensePerfTest {

	Expense exT1, exT2;
	User user; 
	static Logger logger = Logger.getLogger(ExpensePerfTest.class.getName());
	
	//@Rule
	// TODO - error in the test that says the following --> java.lang.RuntimeException: java.lang.NoSuchFieldException: fNext
	// at org.databene.contiperf.junit.ContiPerfRule.apply(ContiPerfRule.java:176)
	//public ContiPerfRule rule = new ContiPerfRule();
	

	@Before
	public void setUp() throws Exception {
		logger.info("Entering setUp");
		exT1 = new Expense("Apple", 1, Category.FOOD);
		exT2 = new Expense("Apple", 1, Category.FOOD);
		user = new User("user", "123", "1234567", 20, 200); 
		logger.info("Leaving setUp");

	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testExpense() throws Exception {
		logger.info("Starting testExpense");
		Expense exNew = new Expense("Apple", 1, Category.FOOD);
		assertEquals(exNew.getClass(), Expense.class);
		logger.info("Finishing testExpense");

	}


	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetText() throws Exception {
		logger.info("Starting testGetText");
		assertEquals(exT1.getText(), "Apple");
		logger.info("Finishing testGetText");

	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetText() throws Exception {
		logger.info("Starting testSetText");
		exT1.setText("Orange");
		assertNotEquals(exT1.getText(), exT2.getText());
		logger.info("Finishing testSetText");

	}
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetCategory() throws Exception {
		logger.info("Starting testGetCategory");
		assertEquals(exT1.getCategory(), Category.FOOD);
		logger.info("Finishing testGetCategory");

	}
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetCategory() throws Exception {
		logger.info("Starting testSetCategory");
		exT1.setCategory(Category.OTHERS);
		assertNotEquals(exT1.getCategory(), exT2.getCategory());

		exT1.setCategory(Category.FOOD);
		assertEquals(exT1.getCategory(), exT2.getCategory());
		logger.info("Finishing testSetCategory");


	}
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetUser() throws Exception {
		logger.info("Starting testGetUser");
		assertEquals(exT1.getUser(), exT2.getUser());
		logger.info("Finishing testGetUser");

	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetUser() throws Exception {
		logger.info("Starting testSetUser");
		exT1.setUser(user); 
		assertNotEquals(exT1.getUser(), exT2.getUser()); 

		assertEquals(exT1.getUser().getLogin(), "user"); 
		logger.info("Finishing testSetUser");


	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetAmount() throws Exception {
		logger.info("Starting testGetAmount");
		assertEquals(exT1.getAmount(), 1,0); 
		logger.info("Finishing testGetAmount");


	}
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetAmount() throws Exception {
		logger.info("Starting testSetAmount");
		exT1.setAmount(5); 
		assertNotEquals(exT1.getAmount(), exT2.getAmount()); 

		exT1.setAmount(1);
		assertEquals(exT1.getAmount(), exT2.getAmount(), 0);
		logger.info("Finishing testSetAmount");

	}

}
