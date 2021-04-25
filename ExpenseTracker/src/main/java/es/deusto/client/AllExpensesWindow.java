package es.deusto.client;

import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.ExpenseList;
import java.util.*; 
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import es.deusto.server.jdo.Expense;

public class AllExpensesWindow extends JFrame {
    private ExampleClient client;
    private Set<Expense> expenses; 
    

    public AllExpensesWindow(ExampleClient client, Set<Expense> expenses) {
		this.client  = client; 
        this.expenses = expenses; 
        JPanel panel = new JPanel(); 
        
     
        DefaultListModel<Expense> dlm = new DefaultListModel<Expense> ();
        for(Expense e : expenses ){
            
            System.out.println(e);
            dlm.addElement(e);
        }    
        System.out.println("here2");
        JList<Expense> jList = new JList<Expense>(dlm);
        
        panel.add(jList); 

        getContentPane().add(panel,"Center"); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("All your expenses");
		setSize(600, 500);
		setVisible(true);
    }

    
		
    
}
