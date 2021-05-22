package es.deusto.serialization;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;

import es.deusto.server.jdo.Category;


public class DirectedMessageTest {
	
	UserData usrDt = new UserData();
	DirectedMessage dMsg = new DirectedMessage();
	ExpenseData expDt, expDt2 = new ExpenseData();
	
	static Logger logger = Logger.getLogger(DirectedMessageTest.class.getName());
	
	/**
	 * The SETUP generates all the user and expenses needed for the tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		logger.info("Entering Set UP");
		logger.info("Creating expense");
		expDt = new ExpenseData("ropa", 1, Category.CLOTHES);
		logger.info("Creating user");
		usrDt.setLogin("Eduardo");
		usrDt.setPassword("11111");
		usrDt.setAge(22);
		usrDt.setCardNumber("111111111");
		usrDt.setExpenseLimit(1000);
		dMsg = new DirectedMessage(usrDt, expDt);
		
		expDt2.setAmount(1);
		expDt2.setCategory(Category.CLOTHES);
		expDt2.setText("ropa");
		logger.info("Leaving setUp");

	}
	
	/**
	 * This test is in charge of validating that the relationship between the user and the expense is correctly done.\n
	 * This test the constructor of the class. 
	 * @throws Exception
	 */
	@Test
	public void testDirectedMessage() throws Exception {
		logger.info("Entering testDirectedMessage");
		logger.info("Creating user and expense");
		UserData usrData = new UserData("Eduardo", "11111", "111111111", 22, 1000);
		ExpenseData expData = new ExpenseData("ropa", 1, Category.CLOTHES);
		DirectedMessage DMSG = new DirectedMessage(usrData, expData);
		assertEquals(DMSG.getClass(), DirectedMessage.class); 
		assertEquals(DMSG.getUserData().getLogin(), dMsg.getUserData().getLogin()); 

		//assertEquals(dMsg, DMSG);
		logger.info("Leaving testDirectedMessage");
	}

	/**
	 * This test validates that the setter of the user works corretly.\n
	 * A complete object is created using the empty construtor and the setters and then all the atributes are compared\n
	 */
	@Test
	public void testSetUserData() throws Exception {
		logger.info("Entering testSetUserData");
		UserData userData2 = new UserData();
		userData2.setAge(22);
		userData2.setCardNumber("111111111");
		userData2.setExpenseLimit(1000);
		userData2.setLogin("Eduardo");
		userData2.setPassword("11111");
		logger.info("Validating using assert equals");
		assertEquals(usrDt.getLogin(), userData2.getLogin());
		assertEquals(usrDt.getPassword(), userData2.getPassword());
		assertEquals(usrDt.getCardNumber(), userData2.getCardNumber());
		assertEquals(usrDt.getAge(), userData2.getAge());
		assertEquals(usrDt.getExpenseLimit(), userData2.getExpenseLimit(),0);
		logger.info("Leaving testSetUserData");
	}
	
	/**
	 * This test validates that the getter of the user returns the correct values
	 */
	@Test
	public void testGetUserData() throws Exception {
		logger.info("Entering testGetUserData");
		UserData eduUsr = new UserData("Eduardo", "11111", "111111111", 22, 1000);
		assertEquals(usrDt.getLogin(), eduUsr.getLogin());
		assertEquals(usrDt.getPassword(), eduUsr.getPassword());
		assertEquals(usrDt.getCardNumber(), eduUsr.getCardNumber());
		assertEquals(usrDt.getAge(), eduUsr.getAge());
		assertEquals(usrDt.getExpenseLimit(), eduUsr.getExpenseLimit());
		logger.info("Leaving testGetUserData");
	}
 
	/**
	 * This test adds a new expense to the user and validates that it has been correctly assign by checking the attributes. 
	 * @throws Exception
	 */
	@Test
	public void testSetExpenseData() throws Exception {
		logger.info("Entering testSetExpenseData");
		logger.info("Generating expense");
		ExpenseData eduExp = new ExpenseData("ropa", 1, Category.CLOTHES);
		dMsg.setExpenseData(eduExp); 
		assertEquals(dMsg.getExpenseData().getText(), eduExp.getText());
		assertEquals(dMsg.getExpenseData().getAmount(), eduExp.getAmount(),0);
		assertEquals(dMsg.getExpenseData().getCategory(), eduExp.getCategory());

		logger.info("Leaving testSetExpenseData");
	}

	/**
	 * This test validates that the expense getter returns correctly the expense
	 * @throws Exception
	 */
	@Test
	public void testGetExpenseData() throws Exception {
		logger.info("Entering testGetExpenseData");
		ExpenseData eduExp = new ExpenseData("ropa", 1, Category.CLOTHES);
		assertEquals(expDt.getText(), eduExp.getText());
		assertEquals(expDt.getAmount(), eduExp.getAmount());
		assertEquals(expDt.getCategory(), eduExp.getCategory());
		logger.info("Leaving testGetExpenseData");
	}
	

}
