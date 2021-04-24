package es.deusto.serialization;
import java.util.*; 

public class ExpenseList {

    private ArrayList<ExpenseData> expenses;

    public ExpenseList() {
        expenses = new ArrayList<>();
    }

    public void setExpenseList(ArrayList<ExpenseData> expenses){
        this.expenses = expenses; 
    
    }
    
    public ArrayList<ExpenseData> getExpenseList(){
        return expenses; 
    
    }
  
}
    

