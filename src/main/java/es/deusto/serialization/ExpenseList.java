package es.deusto.serialization;
import java.util.*; 

/**
 * This class is used to hold lists of the expenses.
 */

public class ExpenseList {

    private Set<ExpenseData> expenses;
    
    /**
	 * Empty constructor
	 */

    public ExpenseList() {
        expenses = new HashSet<>();
    }
    
    /**
	 * Getters and Setters for the ExpenseList class
	 */
   
    public void setExpenseList(Set<ExpenseData> expenses){
        this.expenses = expenses; 
    
    }
    
    public Set<ExpenseData> getExpenseList(){
        return this.expenses; 
    
    }
}
    

