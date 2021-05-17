package es.deusto.serialization;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;


/**
 * This class is a collection of test that validates that the login data object is 
 * correctly generated and that all the getters and setters work properly well.
 */
public class LoginDataTest {
	LoginData logData = new LoginData(); 
	LoginData logData1 = new LoginData(); 
	/**
	 * The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * A simple Login Data Example - user,12345
	 * @throws Exception
	 */

	@Before
	public void setUp() throws Exception{
		logData.setLogin("user");
		logData.setPassword("12345");
		
	}

	/**
	 * Test the constructor\n 
	 * Validates if the login info is corretly generated\n 
	 */
	@Test
	public void testLoginData() throws Exception {
		LoginData logData2 = new LoginData("user", "12345"); 
		assertEquals(logData2, logData);

	}
	/**
	 * Test if the getter of the login-email is correct\n
	 * Given an object LOGDATA, that is generated in the SET UP, validate if the getter returns the information that should. 
	 */

	@Test
	public void testGetLogin() throws Exception {
		assertEquals(logData.getLogin(), "user");

	}
	/**
	 * Assigns a value to the login-email using the set method and validate that is has been correctly stored.\n
	 * @throws Exception
	 */
	@Test
	public void testSetLogin() throws Exception {

		logData1.setLogin("user");
		assertEquals(logData1.getLogin(), "user");

		logData1.setLogin("user1");
		assertEquals(logData1.getLogin(), "user1");

	}


	/**
	 * Test if the getter of the password is correct\n
	 * Given an object logData, that is generated in the SET UP, validate if the getter returns the information that should. 
	 */
	@Test
	public void testGetPassword() throws Exception {
		
		assertEquals(logData.getPassword(), "12345"); 
	}

	/**
	 * Assigns a value to the PASSWORD using the set method and validate that is has been correctly stored.\n
	 * Change the value and check again if this is done correctly.
	 * @throws Exception
	 */


	@Test
	public void testSetPassword() throws Exception {
		logData1.setPassword("newPas");
		assertEquals(logData1.getPassword(), "newPas");

		logData1.setPassword("123");
		assertEquals(logData1.getPassword(), "123");

	}

	@Test
	public void testToString() throws Exception {
		//TODO
	}

}
