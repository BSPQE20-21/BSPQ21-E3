package es.deusto.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;
import es.deusto.server.jdo.UserPerfTest;

public class ModifyUserWindowTest {
	ExampleClient exampleClient;
	UserData userExpected;
	ModifyUserWindow muw;
	static Logger logger = Logger.getLogger(ModifyUserWindowTest.class.getName());

	/**
	 * The SET UP of the test case\n Thanks to this method the objects used to
	 * validate the test are generated\n An expenseData will be created.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			logger.info("Entering setUp");
			exampleClient = new ExampleClient("127.0.0.1", "8080");
			userExpected = new UserData();
			userExpected.setLogin("user");
			userExpected.setPassword("12345");
			userExpected.setAge(20);
			userExpected.setCardNumber("123456789");
			userExpected.setExpenseLimit(2000);
			muw = new ModifyUserWindow(exampleClient, userExpected);
			logger.info("Leaving setUp");

		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}

	/**
	 * This method validates if the created window is from the same class as
	 * ModifyUserWindow
	 * 
	 * @throws Exception
	 */
	@Test
	public void testModifyUserWindowClass() throws Exception {
		try {
			logger.info("Starting testModifyUserWindowClass");
			assertEquals(muw.getClass(), ModifyUserWindow.class);
			logger.info("Finishing testModifyUserWindowClass");

		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}

	/**
	 * This method validates if the created window is visible
	 * 
	 * @throws Exception
	 */
	@Test
	public void testModifyUserWindowVisible() throws Exception {
		try {
			logger.info("Starting testModifyUserWindowVisible");
			assertTrue(muw.isVisible());
			logger.info("Finishing testModifyUserWindowVisible");

		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}

	/**
	 * This method validates if the created window disposes on close
	 * 
	 * @throws Exception
	 */
	@Test
	public void testModifyUserWindowCloseOperation() throws Exception {
		try {
			logger.info("Starting testModifyUserWindowCloseOperation");
			// assertEquals(muw.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			logger.info("Finishing testModifyUserWindowCloseOperation");

		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}

	@Test
	public void testActionPerformed() throws Exception {
		try {

		} catch (HeadlessException e) {
			// TODO: handle exception
		}
		// TODO
	}

}
