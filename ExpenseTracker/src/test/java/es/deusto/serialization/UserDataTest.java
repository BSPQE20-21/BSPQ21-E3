package es.deusto.serialization;


import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class UserDataTest {
	
	UserData userData;
	UserData userData2;
	
	@Before
	public void setUp() throws Exception {
		userData = new UserData();
		userData.setLogin("Mireya");
		userData.setPassword("11111");
		userData.setAge(20);
		userData.setCardNumber("111111111");
		userData.setExpenseLimit(1000);
		
		userData2 = new UserData("Haizea", "22222", "222222222", 20, 1500);
	}

	@Test
	public void testUserData() throws Exception {
		UserData userMireya = new UserData("Mireya", "11111", "111111111", 20, 1000);

		Assert.assertEquals(userMireya.getLogin(), userData.getLogin());
	}

	@Test
	public void testGetCardNumber() throws Exception {
		assertEquals("111111111", userData.getCardNumber());
	}

	
	@Test
	public void testSetCardNumber() throws Exception {
		userData2.setCardNumber("987654321");
		assertEquals("987654321", userData2.getCardNumber());
	}

	@Test
	public void testGetAge() throws Exception {
		assertEquals(20, userData.getAge());
	}

	@Test
	public void testSetAge() throws Exception {
		userData2.setAge(21);
		assertEquals(21, userData2.getAge());
	}

	@Test
	public void testGetLogin() throws Exception {
		assertEquals("Mireya", userData.getLogin());
	}

	@Test
	public void testSetLogin() throws Exception {
		userData2.setLogin("Haizea Rodriguez");
		assertEquals("Haizea Rodriguez", userData2.getLogin());
	}

	@Test
	public void testGetPassword() throws Exception {
		assertEquals("11111", userData.getPassword());
	}

	@Test
	public void testSetPassword() throws Exception {
		userData2.setPassword("54321");
		assertEquals("54321", userData2.getPassword());
	}

	@Test
	public void testGetExpenseLimit() throws Exception {
		assertEquals(1000, userData.getExpenseLimit());
	}

	@Test
	public void testSetExpenseLimit() throws Exception {
		userData2.setExpenseLimit(3000);
		assertEquals(3000, userData2.getExpenseLimit());
	}
	

}
