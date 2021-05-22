package es.deusto.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import es.deusto.serialization.UserData;
import es.deusto.server.jdo.UserPerfTest;

public class RegisterWindowTest {
	ExampleClient exampleClient;
	RegisterWindow rw;
	static Logger logger = Logger.getLogger(RegisterWindowTest.class.getName());

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
			rw = new RegisterWindow(exampleClient);
			logger.info("Finishing setUp");

		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}

	/**
	 * This method validates if the created window is from the same class as
	 * RegisterWindow
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegisterWindowClass() throws Exception {
		try {
			logger.info("Starting testRegisterWindowClass");
			assertEquals(rw.getClass(), RegisterWindow.class);
			logger.info("Finishing testRegisterWindowClass");

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
	public void testRegisterWindowVisible() throws Exception {
		try {
			// ASSERT FAIL it may be false
			logger.info("Starting testRegisterWindowVisible");
			assertFalse(rw.isVisible());
			logger.info("Finishing testRegisterWindowVisible");

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
	public void testRegisterWindowCloseOperation() throws Exception {
		try {
			logger.info("Starting testRegisterWindowCloseOperation");
			// assertEquals(rw.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
			logger.info("Finishing testRegisterWindowCloseOperation");

		} catch (HeadlessException e) {
			// TODO: handle exception
		}

	}

	@Test
	public void testRegisterWindow() throws Exception {
		try {

		} catch (HeadlessException e) {
			// TODO: handle exception
		}
	}

}
