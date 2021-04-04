package es.deusto.server.jdo;

import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import java.util.HashSet;

@PersistenceCapable
public class User {

	@PrimaryKey
	String login = null; // EMAIL of the user
	String password = null;
	String cardNumber = null;
	int age;
	double expenseLimit; // The max amount that the user is willing to spend monthly

	@Persistent(mappedBy = "user", dependentElement = "true")
	@Join
	Set<Expense> expenses = new HashSet<Expense>();

	public User(String login, String password, String cardNumber, int age, double expenseLimit) {
		this.login = login;
		this.password = password;
		this.cardNumber = cardNumber;
		this.age = age;
		this.expenseLimit = expenseLimit;
	}

	public void addExpense(Expense expense) {
		expenses.add(expense);
	}

	public void removeExpense(Expense expense) {
		expenses.remove(expense);
	}

	public String getLogin() {
		return this.login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getExpenseLimit() {
		return this.expenseLimit;
	}

	public void setExpenseLimit(double expenseLimit) {
		this.expenseLimit = expenseLimit;
	}

	public Set<Expense> getMessages() {
		return this.expenses;
	}

	public String toString() {
		StringBuffer expensesStr = new StringBuffer();
		for (Expense ex : this.expenses) {
			expensesStr.append(ex.toString() + " - ");
		}
		return "User: login --> " + this.login + ", password -->  " + this.password + ", expenses --> [" + expensesStr
				+ "]";
	}
}
