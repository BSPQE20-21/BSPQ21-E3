package es.deusto.serialization;
import java.util.*; 


public class ExpenseList {

    private Set<ExpenseData> expenses;

    public ExpenseList() {
        expenses = new HashSet<>();
    }

    public void setExpenseList(Set<ExpenseData> expenses){
        this.expenses = expenses; 
    
    }
    
    public Set<ExpenseData> getExpenseList(){
        return this.expenses; 
    
    }
}
    

