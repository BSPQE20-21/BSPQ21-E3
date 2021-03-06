package es.deusto.client;

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

public class LoginWindowTest {
	ExampleClient exampleClient;
	LoginWindow lw;
	static Logger logger = Logger.getLogger(LoginWindowTest.class.getName());

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

			lw = new LoginWindow(exampleClient);
			logger.info("Leaving setUp");

		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}

	/**
	 * This method validates if the created window is from the same class as
	 * LoginWindow
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginWindowClass() throws Exception {
		try {
			logger.info("Starting testLoginWindowClass");
			assertEquals(lw.getClass(), LoginWindow.class);
			logger.info("Finishing testLoginWindowClass");

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
	public void testLoginWindowVisible() throws Exception {
		try {
			logger.info("Staring testLoginWindowVisible");
			assertTrue(lw.isVisible());
			logger.info("Finishing testLoginWindowVisible");

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
	public void testLoginWindowCloseOperation() throws Exception {
		try {
			logger.info("Starting testLoginWindowCloseOperation");
			assertEquals(lw.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			logger.info("Finishing testLoginWindowCloseOperation");

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
	}

}
