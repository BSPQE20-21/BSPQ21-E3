package es.deusto.client;

import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.ExpenseList;
import es.deusto.serialization.UserData;
import java.util.*; 
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import es.deusto.server.jdo.Expense;
import org.apache.log4j.Logger;
/** 
*@class ALL EXPENSE WINDOW
*This class constructs the window that allows users to show expenses 
*/

public class AllExpensesWindow extends JFrame {
    private ExampleClient client; 

    private Set<ExpenseData> expenses;  /**< Set that contains all the expenses of the user */
    private UserData userData; 
    private JButton addExpense, deleteExpense; 
    private JPanel panel, buttonPanel; 
    static Logger logger = Logger.getLogger(AllExpensesWindow.class.getName());

    /**
     * Contractor of the window that recieves the following parameters
     * @param ExampleClient client - so we can make the connection with the client 
     * @param Set<ExpenseData> expneses - list of expenses assigned to the loged in user
     * @UserData userData - the information of the loged in user
     * This constructor is called inside the  @see es.deusto.client.AddExpenseWindow
     */

    public AllExpensesWindow(ExampleClient client, Set<ExpenseData> expenses, UserData userData) {
		logger.info("Entering constructor");
        this.client  = client; 
        this.expenses = expenses; 
        panel = new JPanel(); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(client.getResourceBundle().getString("expenseTitle"));
        panel.add(titleLabel); 
    
        DefaultListModel<ExpenseData> dlm = new DefaultListModel<ExpenseData> ();
        for(ExpenseData e : expenses){
            dlm.addElement(e);
        }    
       
        JList<ExpenseData> jList = new JList<ExpenseData>(dlm);
        panel.add(jList); 

        buttonPanel = new JPanel(); 
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        addExpense = new JButton(client.getResourceBundle().getString("addExpenseButton")); 

        buttonPanel.add(addExpense); 

        addExpense.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
                logger.info("Entering Action Listener of the addExpense button");
				logger.info("Opening the AddExpenseWindow");

				AddExpenseWindow aew = new AddExpenseWindow(userData, client);
				setVisible(false); 
			}		
			
		});

        deleteExpense = new JButton(client.getResourceBundle().getString("deleteExpense")); 
        buttonPanel.add(deleteExpense); 
        deleteExpense.addActionListener(new ActionListener(){
            @Override
			public void actionPerformed(ActionEvent e) {
                logger.info("Entering Action Listener of the deleteExpense");
				ExpenseData value = (ExpenseData) jList.getSelectedValue();
                System.out.println(value);
				//setVisible(false); 
                client.deleteExpense(value);
			}		
        });

        getContentPane().add(buttonPanel, "South"); 
        getContentPane().add(panel,"Center"); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(client.getResourceBundle().getString("expenseTitle"));
		setSize(600, 500);
		setVisible(true);
    }


	
	
    
		
    
}
