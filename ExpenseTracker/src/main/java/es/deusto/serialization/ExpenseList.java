package es.deusto.serialization;
import java.util.*; 
import es.deusto.server.jdo.Expense;

public class ExpenseList {

    private Set<Expense> expenses;

    public ExpenseList() {
        expenses = new HashSet<>();
    }

    public void setExpenseList(Set<Expense> expenses){
        this.expenses = expenses; 
    
    }
    
    public Set<Expense> getExpenseList(){
        return this.expenses; 
    
    }
}
    

