package es.deusto.server.jdo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Key;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.util.Date;


// this sould be our expenses 

/**This class defines the creation of an expense
 *
 *
 */
@PersistenceCapable(detachable = "true")
public class Expense {
	
	User user=null; // who 
	String text=null; // the description of the purchase
	long timestamp; // when 
	double amount; // how much 
    Category category; // this should be selected from a list that cannot be hardcoded
	 

    /**The constructor of the expense class
     * @param text
     * @param amount
     * @param category
     */
    public Expense(String text, double amount, Category category) {
        this.text = text;
		this.timestamp = System.currentTimeMillis();
        this.amount = amount; 
        this.category = category;
    }
    
    /**Empty expense constructor to create empty expenses
     * 
     */
    public Expense() {
        this.text = "";
		this.timestamp = 0;
        this.amount = 0; 
        this.category = null;
    }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**The structure of the printed string is the following:
     *Expense: expense -->  , timestamp --> , amount = 
     */
    public String toString() {
        return "Expense: expense --> " + this.text + ", timestamp -->  " + new Date(this.timestamp)+ ", amount = " + this.amount + ", category --> " + this.category;
    }
}