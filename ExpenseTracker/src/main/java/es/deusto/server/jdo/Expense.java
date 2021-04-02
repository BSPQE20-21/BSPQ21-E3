package es.deusto.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import java.util.Date;


// this sould be our expenses 

@PersistenceCapable
public class Expense {
	User user=null; // who 
	String text=null; // the description of the purchase
	long timestamp; // when 
    float amount; // how much 
    //String category; this should be selected from a list that cannot be hardcoded
	

    public Expense(String text, float amount) {
        this.text = text;
		this.timestamp = System.currentTimeMillis();
        this.amount = amount; 
    }

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public float getAmount() {
        return this.amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String toString() {
        return "Message: message --> " + this.text + ", timestamp -->  " + new Date(this.timestamp)+ ", amount = " + this.amount;
    }
}