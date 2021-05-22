package es.deusto.client;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import es.deusto.serialization.*;
import org.apache.log4j.Logger;
import java.awt.BorderLayout;
/**
 * MODIFY USER
 * Once the user is logged in give them the possibility to change some of the parameters\n
 * The window is similar to the register window but the user info is displaey and changes can be made in all the parameters except the login/email\n
 * This class is called from the es.deusto.client.AddExpenseWindow
 */
public class ModifyUserWindow extends JFrame {


	private static final long serialVersionUID = 1L;
	private JFrame frame; 
	private JButton buttonUpdate, buttonReturn, deleteExpense; 
	
	

	private ExampleClient client;
    private final UserData userData; 

	static Logger logger = Logger.getLogger(ModifyUserWindow.class.getName());

    
	
	/**
	 * The constructor of the modify user window. \n
	 * @param client
	 */
	public ModifyUserWindow(ExampleClient client, UserData userData) {
		logger.info("Entering constructor");

		this.client = client;
        this.userData = userData; 
		
		JPanel registerPanel = new JPanel();
		registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
		// EMAIL 
		registerPanel.add(new JLabel(client.getResourceBundle().getString("email")));
		JLabel emailField = new JLabel(userData.getLogin());
		registerPanel.add(emailField);
		// PASSWORD 
		registerPanel.add(new JLabel(client.getResourceBundle().getString("password")));
		JPasswordField passwordField = new JPasswordField(userData.getPassword());
		registerPanel.add(passwordField);
		// CARD NUMBER
		registerPanel.add(new JLabel(client.getResourceBundle().getString("cardNumber")));
		JTextField carNumberField = new JTextField(userData.getCardNumber());
		registerPanel.add(carNumberField);
		// AGE
		registerPanel.add(new JLabel(client.getResourceBundle().getString("age")));
		JTextField ageField = new JTextField(Integer.toString(userData.getAge()));
		registerPanel.add(ageField);
		// EXPENSE LIMIT
		registerPanel.add(new JLabel(client.getResourceBundle().getString("expenseLimit"))); 
		JTextField expenseLimitField = new JTextField(Double.toString(userData.getExpenseLimit()));
		registerPanel.add(expenseLimitField);


		buttonUpdate = new JButton(client.getResourceBundle().getString("update"));
		registerPanel.add(buttonUpdate); 
		
		buttonUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Entering Action Listener of the buttonUpdate button");

                String login = emailField.getText();
				String password = String.valueOf(passwordField.getPassword()); 
				String cardNumber = carNumberField.getText(); 
				int age = Integer.parseInt(ageField.getText());
				double expenseLimit = Double.parseDouble(expenseLimitField.getText()); 
				
				UserData uData = new UserData();
				uData.setLogin(login);
				uData.setPassword(password);
				uData.setCardNumber(cardNumber);
				uData.setAge(age); 
				uData.setExpenseLimit(expenseLimit);
				logger.info("Updating user ..."); 
				UserData usData = client.updateUser(uData);
                userData.setLogin(usData.getLogin());
                userData.setPassword(usData.getPassword());
                userData.setAge(usData.getAge());
				userData.setCardNumber(usData.getCardNumber());
				userData.setExpenseLimit(usData.getExpenseLimit());
			}
		});

		deleteExpense = new JButton(client.getResourceBundle().getString("deleteExpense")); 
        registerPanel.add(deleteExpense); 
        this.deleteExpense.addActionListener(new ActionListener(){
            @Override
			public void actionPerformed(ActionEvent e) {
                logger.info("Entering Action Listener of the deleteExpense");
			
                
				setVisible(false);
                client.deleteUser(userData);
				
			}		
        });

        buttonReturn= new JButton(client.getResourceBundle().getString("return"));
		registerPanel.add(buttonReturn); 
        buttonReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Entering the action listener of the return button"); 
                
				logger.info("Opening the AddExpenseWindow"); 

                AddExpenseWindow aew = new AddExpenseWindow(userData,client); 
				setVisible(false);
				
			}
		});

	
        
        // Adding the listeners to components..
        
        add(registerPanel, BorderLayout.CENTER);
        setTitle(client.getResourceBundle().getString("modifyUser"));
        setSize(600, 500);
        
        
        setVisible(true);
	
	
	}
	
    
}
