package es.deusto.serialization;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import org.apache.log4j.Logger;

/**
 * This class is a collection of test that validates that the login data object is 
 * correctly generated and that all the getters and setters work properly well.
 */
public class LoginDataTest {
	LoginData logData = new LoginData(); 
	LoginData logData1 = new LoginData(); 
	static Logger logger = Logger.getLogger(LoginDataTest.class.getName());


	/**
	 * The SET UP of the test case\n
	 * Thanks to this method the objects used to validate the test are generated\n
	 * A simple Login Data Example - user,12345
	 * @throws Exception
	 */

	@Before
	public void setUp() throws Exception{
		logger.info("Entering Set UP");
		logData.setLogin("user");
		logData.setPassword("12345");
		logger.info("Leaving Set UP");
		
	}

	/**
	 * Test the constructor\n 
	 * Validates if the login info is corretly generated\n 
	 */
	@Test
	public void testLoginData() throws Exception {
		logger.info("Entering testLoginData");
		LoginData logData2 = new LoginData("user", "12345"); 
		//assertEquals(logData2, logData);
		logger.info("Leaving testLoginData");

	}
	/**
	 * Test if the getter of the login-email is correct\n
	 * Given an object LOGDATA, that is generated in the SET UP, validate if the getter returns the information that should. 
	 */

	@Test
	public void testGetLogin() throws Exception {
		logger.info("Entering testGetLogin");
		assertEquals(logData.getLogin(), "user");
		logger.info("Leaving testGetLogin");

	}
	/**
	 * Assigns a value to the login-email using the set method and validate that is has been correctly stored.\n
	 * @throws Exception
	 */
	@Test
	public void testSetLogin() throws Exception {

		logger.info("Entering testSetLogin");

		logData1.setLogin("user");
		assertEquals(logData1.getLogin(), "user");

		logData1.setLogin("user1");
		assertEquals(logData1.getLogin(), "user1");

		logger.info("Leaving testSetLogin");

	}


	/**
	 * Test if the getter of the password is correct\n
	 * Given an object logData, that is generated in the SET UP, validate if the getter returns the information that should. 
	 */
	@Test
	public void testGetPassword() throws Exception {
		logger.info("Entering testGetPassword");
		assertEquals(logData.getPassword(), "12345"); 
		logger.info("Leaving testGetPassword");
	}

	/**
	 * Assigns a value to the PASSWORD using the set method and validate that is has been correctly stored.\n
	 * Change the value and check again if this is done correctly.
	 * @throws Exception
	 */


	@Test
	public void testSetPassword() throws Exception {

		logger.info("Entering testSetPassword");
		logData1.setPassword("newPas");
		assertEquals(logData1.getPassword(), "newPas");

		logData1.setPassword("123");
		assertEquals(logData1.getPassword(), "123");
		logger.info("Leaving testSetPassword");

	}

	@Test
	public void testToString() throws Exception {
		//TODO
	}

}
