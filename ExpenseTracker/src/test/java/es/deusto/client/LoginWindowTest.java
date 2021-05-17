package es.deusto.client;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;


public class LoginWindowTest {
	ExampleClient exampleClient;
	LoginWindow lw;

	/**The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * An expenseData will be created. 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		exampleClient = new ExampleClient("127.0.0.1", "8080");
       
		lw = new LoginWindow(exampleClient);

		
	}

	/**This method validates if the created window is from the same class as LoginWindow
	 * @throws Exception
	 */
	@Test
	public void testLoginWindowClass() throws Exception {
		assertEquals(lw.getClass(), LoginWindow.class);
		
	}
	
	/**This method validates if the created window is visible
	 * @throws Exception
	 */
	@Test
	public void testLoginWindowVisible() throws Exception {
		assertTrue(lw.isVisible());
	}
	
	/**This method validates if the created window disposes on close
	 * @throws Exception
	 */
	@Test
	public void testLoginWindowCloseOperation() throws Exception {
		assertEquals(lw.getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
		
	}
	@Test
	public void testActionPerformed() throws Exception {

	}

	

}
