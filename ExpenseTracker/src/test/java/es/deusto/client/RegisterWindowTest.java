package es.deusto.client;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import es.deusto.serialization.UserData;

public class RegisterWindowTest {
	ExampleClient exampleClient;
	RegisterWindow rw;

	/**The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * An expenseData will be created. 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		exampleClient = new ExampleClient("127.0.0.1", "8080");
		rw = new RegisterWindow(exampleClient);

	}

	/**This method validates if the created window is from the same class as RegisterWindow
	 * @throws Exception
	 */
	@Test
	public void testRegisterWindowClass() throws Exception {
		assertEquals(rw.getClass(), RegisterWindow.class);
		
	}
	
	/**This method validates if the created window is visible
	 * @throws Exception
	 */
	@Test
	public void testRegisterWindowVisible() throws Exception {
		assertTrue(rw.isVisible());
	}
	
	/**This method validates if the created window disposes on close
	 * @throws Exception
	 */
	@Test
	public void testRegisterWindowCloseOperation() throws Exception {
		assertEquals(rw.getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
		
	}
	@Test
	public void testRegisterWindow() throws Exception {

	}



}
