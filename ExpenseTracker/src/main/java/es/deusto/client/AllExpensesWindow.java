package es.deusto.client;

import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.ExpenseList;
import java.util.*; 
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class AllExpensesWindow extends JFrame {
    private ExampleClient client;
    private Set<ExpenseData> expenses; 
    

    public AllExpensesWindow(ExampleClient client, Set<ExpenseData> expenses) {
		this.client  = client; 
        this.expenses = expenses; 
        JPanel panel = new JPanel(); 
        
     
        DefaultListModel<String> dlm = new DefaultListModel<String> ();
        for(ExpenseData e : expenses){
                        
            dlm.addElement(e.toString());
        }    
       
        JList<String> jList = new JList<String>(dlm);
        
        panel.add(jList); 

        getContentPane().add(panel,"Center"); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("All your expenses");
		setSize(600, 500);
		setVisible(true);
    }

    
		
    
}
