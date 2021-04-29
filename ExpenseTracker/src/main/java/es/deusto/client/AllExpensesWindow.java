package es.deusto.client;

import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.ExpenseList;
import es.deusto.serialization.UserData;
import java.util.*; 
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import es.deusto.server.jdo.Expense;

public class AllExpensesWindow extends JFrame implements ActionListener {
    private ExampleClient client;

    private Set<ExpenseData> expenses; 
    private UserData userData; 
    private JButton addExpense; 
    private JPanel panel, buttonPanel; 
    

    public AllExpensesWindow(ExampleClient client, Set<ExpenseData> expenses, UserData userData) {
		this.client  = client; 
        this.expenses = expenses; 
        panel = new JPanel(); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("YOUR EXPENSES");
        panel.add(titleLabel); 
    
        DefaultListModel<String> dlm = new DefaultListModel<String> ();
        for(ExpenseData e : expenses){
                        
            dlm.addElement(e.toString());
        }    
       
        JList<String> jList = new JList<String>(dlm);

        panel.add(jList); 

        buttonPanel = new JPanel(); 
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        addExpense = new JButton("Add Expense"); 

        buttonPanel.add(addExpense); 
        addExpense.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			
				AddExpenseWindow aew = new AddExpenseWindow(userData, client);
				setVisible(false); 
			}		
			
		});

        getContentPane().add(buttonPanel, "South"); 
        getContentPane().add(panel,"Center"); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("All your expenses");
		setSize(600, 500);
		setVisible(true);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
    
		
    
}
