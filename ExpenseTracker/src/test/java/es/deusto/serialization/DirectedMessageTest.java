package es.deusto.serialization;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.deusto.server.jdo.Category;
import junit.framework.Assert;

public class DirectedMessageTest {
	
	UserData usrDt = new UserData();
	DirectedMessage dMsg = new DirectedMessage();
	ExpenseData expDt, expDt2 = new ExpenseData();

	
	@Before
	public void setUp() throws Exception {

		expDt = new ExpenseData("ropa", 1, Category.CLOTHES);
		usrDt.setLogin("Eduardo");
		usrDt.setPassword("11111");
		usrDt.setAge(22);
		usrDt.setCardNumber("111111111");
		usrDt.setExpenseLimit(1000);
		dMsg = new DirectedMessage(usrDt, expDt);
		
		expDt2.setAmount(1);
		expDt2.setCategory(Category.CLOTHES);
		expDt2.setText("ropa");

	}
	
	@Test
	public void testDirectedMessage() throws Exception {
		UserData usrData = new UserData("Eduardo", "11111", "111111111", 22, 1000);
		ExpenseData expData = new ExpenseData("ropa", 1, Category.CLOTHES);
		DirectedMessage DMSG = new DirectedMessage(usrData, expData);
		//assertEquals(dMsg, DMSG);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSetUserData() throws Exception {
		UserData userData2 = new UserData();
		userData2.setAge(22);
		userData2.setCardNumber("111111111");
		userData2.setExpenseLimit(1000);
		userData2.setLogin("Eduardo");
		userData2.setPassword("11111");

		assertEquals(usrDt.getLogin(), userData2.getLogin());
		assertEquals(usrDt.getPassword(), userData2.getPassword());
		assertEquals(usrDt.getCardNumber(), userData2.getCardNumber());
		assertEquals(usrDt.getAge(), userData2.getAge());
		assertEquals(usrDt.getExpenseLimit(), userData2.getExpenseLimit());
	}
	

	@Test
	public void testGetUserData() throws Exception {
		UserData eduUsr = new UserData("Eduardo", "11111", "111111111", 22, 1000);
		assertEquals(usrDt.getLogin(), eduUsr.getLogin());
		assertEquals(usrDt.getPassword(), eduUsr.getPassword());
		assertEquals(usrDt.getCardNumber(), eduUsr.getCardNumber());
		assertEquals(usrDt.getAge(), eduUsr.getAge());
		assertEquals(usrDt.getExpenseLimit(), eduUsr.getExpenseLimit());
	}
 
	@Test
	public void testSetExpenseData() throws Exception {
		ExpenseData eduExp = new ExpenseData("ropa", 1, Category.CLOTHES);
		assertEquals(expDt.getText(), eduExp.getText());
		assertEquals(expDt.getAmount(), eduExp.getAmount());
		assertEquals(expDt.getCategory(), eduExp.getCategory());
	}

	@Test
	public void testGetExpenseData() throws Exception {
		ExpenseData eduExp = new ExpenseData("ropa", 1, Category.CLOTHES);
		assertEquals(expDt.getText(), eduExp.getText());
		assertEquals(expDt.getAmount(), eduExp.getAmount());
		assertEquals(expDt.getCategory(), eduExp.getCategory());
	}
	

}
