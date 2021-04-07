package es.deusto.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import es.deusto.serialization.UserData;

public class AddExpenseWindow extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton submit, cancel;
	JLabel expenseName, expenseAmount, expenseCategory;
	JComboBox<String> comboCategory;
	JTextField name_text, amount_text;

	
	public void switchPanel(JPanel current, JPanel next) {
		current.setVisible(false);
		current.setEnabled(false);
		next.setVisible(true);
		next.setEnabled(true);
		
	}
	
	public AddExpenseWindow(UserData userData) {
		
		JPanel panelAddExpense;
		panelAddExpense = new JPanel(new GridLayout(2, 1));
		
		//Expense details
		expenseName = new JLabel("Name: ");
		name_text = new JTextField("14");
		panelAddExpense.add(expenseName);
		panelAddExpense.add(name_text);
		
		expenseAmount = new JLabel("Amount: ");
		amount_text = new JTextField(6);
		panelAddExpense.add(expenseAmount);
		panelAddExpense.add(amount_text);
		
		expenseCategory = new JLabel("Category: ");
		comboCategory = new JComboBox<String>();
		comboCategory.addItem("Food");
		comboCategory.addItem("Clothes");
		comboCategory.addItem("Entertainment");
		comboCategory.addItem("Gas");
		comboCategory.addItem("Healthcare");
		comboCategory.addItem("Others");
		panelAddExpense.add(expenseCategory);
		panelAddExpense.add(comboCategory);
		


		JPanel buttonJPanel;
		buttonJPanel = new JPanel(new GridLayout(3, 1));
		//Add/cancel buttons
		JButton add = new JButton("Add");
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
		
		buttonJPanel.add(add); 
		buttonJPanel.add(cancel);


		getContentPane().add(panelAddExpense,"North"); 
		getContentPane().add(buttonJPanel,"South"); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Please Login Here !");
		setSize(300, 100);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
