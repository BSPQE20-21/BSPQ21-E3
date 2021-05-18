package es.deusto.server.jdo;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;

import es.deusto.server.jdo.User;
import es.deusto.server.jdo.Expense;
import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;


/**
 * This class is a copy of UserTest, in this class will be validated the performance 
 */
public class UserPerfTest {


	User userT1, userT2; 
	Expense exT1; 

	@Rule
	public ContiPerfRule i = new ContiPerfRule();
	
	
	
	@Before
	public void setUp()throws Exception {
		userT1 = new User("user", "123", "1234567", 20, 200); 
		userT2 = new User("user", "124", "1234567", 20, 200); 
		exT1 = new Expense("Apple", 1, Category.FOOD); 
	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testUser() throws Exception {
		assertEquals(User.class, userT1.getClass()); 
	
	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testAddExpense() throws Exception {
		userT1.addExpense(exT1);
		assertNotEquals(userT1.getMessages(), userT2.getMessages()); 

	}
	
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testRemoveExpense() throws Exception {
		userT1.removeExpense(exT1);
		assertEquals(userT1.getMessages(), userT2.getMessages()); 

	}
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetLogin() throws Exception {
		assertEquals(userT1.getLogin(), "user"); 


	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetLogin() throws Exception {
		userT1.setLogin("userNew");
		assertNotEquals(userT1.getLogin(), userT2.getLogin()); 

		userT1.setLogin("user");
		assertEquals(userT1.getLogin(), userT2.getLogin()); 


	}


	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetPassword() throws Exception {
		assertEquals(userT1.getPassword(), "123"); 

	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetPassword() throws Exception {
		userT1.setPassword("pasNew");
		assertNotEquals(userT1.getPassword(), userT2.getPassword()); 

		userT1.setPassword("123");
		assertEquals(userT1.getPassword(), userT2.getPassword()); 

	}
	
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetAge() throws Exception {
		assertEquals(userT1.getAge(), 20); 

	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetAge() throws Exception {
		userT1.setAge(21);
		assertNotEquals(userT1.getAge(), userT2.getAge()); 

		userT1.setAge(20);
		assertEquals(userT1.getAge(), userT2.getAge());

	}
	
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetCardNumber() throws Exception {
		assertEquals(userT1.getCardNumber(), userT2.getCardNumber()); 

	}
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetCardNumber() throws Exception {
		userT1.setCardNumber("21");
		assertNotEquals(userT1.getCardNumber(), userT2.getCardNumber()); 

		userT1.setCardNumber("1234567");
		assertEquals(userT1.getCardNumber(), userT2.getCardNumber());

	}
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetExpenseLimit() throws Exception {
		assertEquals(userT1.getExpenseLimit(), userT2.getExpenseLimit(), 0); 

	}


	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testSetExpenseLimit() throws Exception {
		userT1.setExpenseLimit(21);
		assertNotEquals(userT1.getExpenseLimit(), userT2.getExpenseLimit()); 

		userT1.setExpenseLimit(2000);
		assertEquals(userT1.getExpenseLimit(), userT2.getExpenseLimit(),0);
	}

	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testGetMessages() throws Exception {
		assertEquals(userT1.getMessages(), userT2.getMessages()); 

	}


}
