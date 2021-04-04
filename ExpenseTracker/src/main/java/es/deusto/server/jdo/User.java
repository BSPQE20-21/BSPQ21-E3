package es.deusto.server.jdo;

import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import java.util.HashSet;


// This class will be similar we just need to add more parameters because our user has more info 
// the user has a collection of expenses so we will have a Hashset like this: 
// Set<Expense> expenses = new HashSet<Expense>(); 

@PersistenceCapable
public class User {
	@PrimaryKey
	String login=null;
	String password=null;
	String cardNumber=null;
	int age;   
	
	@Persistent(mappedBy="user", dependentElement="true")
	@Join
	Set<Expense> expenses = new HashSet<Expense>();
	
	
	
	public User(String login, String password, String cardNumber, int age) {
		this.login = login;
		this.password = password;
		this.cardNumber = cardNumber; 
		this.age = age;
	}
	// TODO review this 
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
	public int getAge(){
		return this.age; 
	}
	public void setAge(int age){
		this.age = age; 
	}
	public String getCardNumber(){
		return this.cardNumber; 
	}
	public void setCardNumber(String cardNumber){
		this.cardNumber = cardNumber; 
	}
	
	 public Set<Expense> getMessages() {return this.expenses;}
	 
	 public String toString() {
		StringBuffer expensesStr = new StringBuffer();
		for (Expense ex: this.expenses) {
			expensesStr.append(ex.toString() + " - ");
		}
        return "User: login --> " + this.login + ", password -->  " + this.password + ", expenses --> [" + expensesStr + "]";
    }
}

