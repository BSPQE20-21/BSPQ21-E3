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

public class AddExpenseWindow extends JFrame implements ActionListener {
	
	/**This window les the user store data about expenses/purchases
	 * expenseName = description of the purchase
	 * expenseAmount = the money spended
	 * expenseCategory (comboBox that gets the data from an Enum)
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton submit, cancel;
	JLabel expenseName, expenseAmount, expenseCategory;
	JComboBox<Category> comboCategory;
	JTextField name_text, amount_text;

	private ExampleClient client;
	
	public void switchPanel(JPanel current, JPanel next) {
		current.setVisible(false);
		current.setEnabled(false);
		next.setVisible(true);
		next.setEnabled(true);
		  
	}
	
	public AddExpenseWindow(UserData userData, ExampleClient client) {
		this.client = client; 
		JPanel panelAddExpense;
		panelAddExpense = new JPanel();
		panelAddExpense.setLayout(new BoxLayout(panelAddExpense, BoxLayout.Y_AXIS));
		
		//Expense details
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
				AllExpensesWindow allexp = new AllExpensesWindow(client, expenses); 
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
