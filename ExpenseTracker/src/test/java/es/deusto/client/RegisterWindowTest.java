package es.deusto.client;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import es.deusto.serialization.UserData;
import es.deusto.server.jdo.UserPerfTest;

public class RegisterWindowTest {
	ExampleClient exampleClient;
	RegisterWindow rw;
	static Logger logger = Logger.getLogger(UserPerfTest.class.getName());


	/**The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * An expenseData will be created. 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		logger.info("Entering setUp");
		exampleClient = new ExampleClient("127.0.0.1", "8080");
		rw = new RegisterWindow(exampleClient);
		logger.info("Finishing setUp");

	}

	/**This method validates if the created window is from the same class as RegisterWindow
	 * @throws Exception
	 */
	@Test
	public void testRegisterWindowClass() throws Exception {
		logger.info("Starting testRegisterWindowClass");
		assertEquals(rw.getClass(), RegisterWindow.class);
		logger.info("Finishing testRegisterWindowClass");

	}
	
	/**This method validates if the created window is visible
	 * @throws Exception
	 */
	@Test
	public void testRegisterWindowVisible() throws Exception {
		logger.info("Starting testRegisterWindowVisible");
		assertTrue(rw.isVisible());
		logger.info("Finishing testRegisterWindowVisible");

	}
	
	/**This method validates if the created window disposes on close
	 * @throws Exception
	 */
	@Test
	public void testRegisterWindowCloseOperation() throws Exception {
		logger.info("Starting testRegisterWindowCloseOperation");
		assertEquals(rw.getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
		logger.info("Finishing testRegisterWindowCloseOperation");
		
	}
	@Test
	public void testRegisterWindow() throws Exception {
		//TODO
	}



}
