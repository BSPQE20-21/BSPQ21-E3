package es.deusto.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import es.deusto.serialization.ExpenseData;
import es.deusto.serialization.ExpenseList;

import es.deusto.serialization.UserData;
import es.deusto.server.jdo.Category;
import es.deusto.server.jdo.Expense;
import java.util.*; 


/** 
*@class ADD EXPENSE WINDOW
*This class constructs the window that allows users to add expenses 
*The class is based on a formulary where users can add the following attributes
*> expenseName = description of the purchase
*> expenseAmount = the money spended
*> expenseCategory (comboBox that gets the data from an Enum) 
*/

public class AddExpenseWindow extends JFrame  {
	

	private static final long serialVersionUID = 1L;
	
	JButton submit, cancel; /**< Jbuttons that allow us to add a new expense */
	JLabel expenseName, expenseAmount, expenseCategory; 
	JComboBox<Category> comboCategory; /**< Shows all the categories that the user can add*/
	JTextField name_text, amount_text;

	private ExampleClient client; 
	

	/**
	 * (Not used method) 
	 * The main putpose is to change from one window to another making visible TRUE visible FALSE the desired windows
	 */

	public void switchPanel(JPanel current, JPanel next) {
		
		current.setVisible(false);
		current.setEnabled(false);
		next.setVisible(true);
		next.setEnabled(true);
		  
	}
	/**
 	* Constructor of the window
 	* UserData is passed as a parameter so the expense can be assigned to the loged in user
 	*Expense details
	*>ExpenseName
	*>ExpenseAmount
	*>ExpenseCategory	
 	*/
	
	public AddExpenseWindow(UserData userData, ExampleClient client) {
		this.client = client; 
		JPanel panelAddExpense;
		panelAddExpense = new JPanel();
		panelAddExpense.setLayout(new BoxLayout(panelAddExpense, BoxLayout.Y_AXIS));
		
	
		expenseName = new JLabel("Description: ");
		name_text = new JTextField(14);
		panelAddExpense.add(expenseName);
		panelAddExpense.add(name_text);
		
		expenseAmount = new JLabel("Amount: ");
		amount_text = new JTextField(6);
		panelAddExpense.add(expenseAmount);
		panelAddExpense.add(amount_text);
		
		expenseCategory = new JLabel("Category: ");
		comboCategory = new JComboBox<Category>();
		
		for (Category c : Category.values()) {
			comboCategory.addItem(c);
		}
		
		panelAddExpense.add(expenseCategory);
		panelAddExpense.add(comboCategory);
		
		JPanel buttonJPanel;
		buttonJPanel = new JPanel();
		buttonJPanel.setLayout(new BoxLayout(buttonJPanel, BoxLayout.Y_AXIS));
		//Add/cancel buttons
		JButton add = new JButton("Add");
		
		
		/** The action listener is assigned to the ADD button
		 * Each time we click on ADD a new wxpense is creted and stored in the DB 
		 * The client is called so it can manage the connection with the server and send the request of storage
		 */
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = name_text.getText();
				double amount = Double.parseDouble(amount_text.getText());
				Category category = (Category) comboCategory.getSelectedItem();
				
				ExpenseData expenseData = new ExpenseData();
				expenseData.setText(name);
				expenseData.setAmount(amount);
				expenseData.setCategory(category);
				
				client.storeExpense(userData, expenseData);
				//switchPanel(panelAddExpense, NEXTPANEL);
				
			}
		});
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//switchPanel(panelAddExpense, PREVIOUSPANEL);
				
			}
		});
		
		JButton allExepenses = new JButton("All Exepenses");
		allExepenses.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//switchPanel(panelAddExpense, PREVIOUSPANEL);
				Set<ExpenseData> expenses = client.showExpenses(userData);
				System.out.println(expenses);	
				AllExpensesWindow allexp = new AllExpensesWindow(client, expenses, userData); 
				setVisible(false); 
			}		
			
		});
		buttonJPanel.add(add); 
		buttonJPanel.add(allExepenses); 
		// cancel has no functionality
		buttonJPanel.add(cancel);

		getContentPane().add(panelAddExpense,"North"); 
		getContentPane().add(buttonJPanel,"South"); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Add your expenses here");
		setSize(600, 500);
		setVisible(true);
	}


}
