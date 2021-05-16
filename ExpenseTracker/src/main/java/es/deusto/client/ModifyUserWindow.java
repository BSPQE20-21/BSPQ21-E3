package es.deusto.client;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import es.deusto.serialization.UserData;
/**
 * MODIFY USER
 * Once the user is logged in give them the possibility to change some of the parameters\n
 * The window is similar to the register window but the user info is displaey and changes can be made in all the parameters except the login/email\n
 * This class is called from the es.deusto.client.AddExpenseWindow
 */
public class ModifyUserWindow extends JFrame {


	private static final long serialVersionUID = 1L;
	private JFrame frame; 
	private JButton buttonUpdate, buttonReturn; 
	private JTextField  carNumberField, ageField, expenseLimitField;  
	private JPasswordField passwordField; 

	private ExampleClient client;
    private final UserData userData; 
    
	
	/**
	 * The constructor of the modify user window. \n
	 * @param client
	 */
	public ModifyUserWindow(ExampleClient client, UserData userData) {
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
		this.passwordField = new JPasswordField(userData.getPassword());
		registerPanel.add(this.passwordField);
		// CARD NUMBER
		registerPanel.add(new JLabel(client.getResourceBundle().getString("cardNumber")));
		this.carNumberField = new JTextField(userData.getCardNumber());
		registerPanel.add(this.carNumberField);
		// AGE
		registerPanel.add(new JLabel(client.getResourceBundle().getString("age")));
		this.ageField = new JTextField(Integer.toString(userData.getAge()));
		registerPanel.add(this.ageField);
		// EXPENSE LIMIT
		registerPanel.add(new JLabel(client.getResourceBundle().getString("expenseLimit"))); 
		this.expenseLimitField = new JTextField(Double.toString(userData.getExpenseLimit()));
		registerPanel.add(this.expenseLimitField);


		this.buttonUpdate = new JButton(client.getResourceBundle().getString("update"));
		registerPanel.add(this.buttonUpdate); 
		
		buttonUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

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
				
				UserData usData = client.updateUser(uData);
                userData.setLogin(usData.getLogin());
                userData.setPassword(usData.getPassword());
                userData.setAge(usData.getAge());
				userData.setCardNumber(usData.getCardNumber());
				userData.setExpenseLimit(usData.getExpenseLimit());
			}
		});
        this.buttonReturn= new JButton(client.getResourceBundle().getString("return"));
		registerPanel.add(this.buttonReturn); 
        buttonReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

                setVisible(false);
                AddExpenseWindow aew = new AddExpenseWindow(userData,client); 
				
				
			}
		});

		this.frame = new JFrame("Modify user"); 
		this.frame.setTitle(client.getResourceBundle().getString("modifyUser"));

		this.frame.setSize(600,500);
		this.frame.getContentPane().add(registerPanel,"North"); 

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//WindowManager.middleLeft(this.frame); Is a class of the project
		this.frame.setVisible(true);
	
	
	}
	
    
}
