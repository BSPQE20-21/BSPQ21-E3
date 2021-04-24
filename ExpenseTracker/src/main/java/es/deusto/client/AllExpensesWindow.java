package es.deusto.client;

import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.ExpenseList;
import java.util.*; 
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class AllExpensesWindow extends JFrame {
    private ExampleClient client;
    private ExpenseList expenses; 
    

    public AllExpensesWindow(ExampleClient client, ExpenseList expenses) {
		this.client  = client; 
        this.expenses = expenses; 
        JPanel panel = new JPanel(); 
        
        ArrayList<ExpenseData> exp = expenses.getExpenseList();
        
        DefaultListModel<ExpenseData> dlm = new DefaultListModel<ExpenseData> ();
        for(ExpenseData e : exp ){
            dlm.addElement(e);
        }    
        JList<ExpenseData> jList = new JList<ExpenseData>(dlm);
        
        panel.add(jList); 

        getContentPane().add(panel,"Center"); 
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("All your expenses");
		setSize(600, 500);
		setVisible(true);
    }

    
		
    
}
