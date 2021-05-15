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
* expenseName = description of the purchase \n
* expenseAmount = the money spended \n
* expenseCategory (comboBox that gets the data from an Enum) \n
*/

public class AddExpenseWindow extends JFrame  {
	

	private static final long serialVersionUID = 1L;
	
	JButton submit; /**< Jbuttons that allow us to add a new expense */
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
 	* @param UserData the user that is loged in 
	* @param ExampleClient the client created in @see es.deusto.client.ExampleClient
	* 	
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
		//Add buttons
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
		
		
		
		JButton allExepenses = new JButton("All Exepenses");
		/**
		 * This ACTION LISTENER is related to the allExpenses JButton \n
		 * After clicking on it a new window is opened @see es.deusto.client.AllExpensesWindow \n
		 * Before changing the window a request is made to the DB where given a USER all the expenses related to he/she are extracted \n
		 * In order to do so the method showExpenses is called (@see es.Deusto.client.ExampleClient.showExpenses)
		 */
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
		

		getContentPane().add(panelAddExpense,"North"); 
		getContentPane().add(buttonJPanel,"South"); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Add your expenses here");
		setSize(600, 500);
		setVisible(true);
	}


}
