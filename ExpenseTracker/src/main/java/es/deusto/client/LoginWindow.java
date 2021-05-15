package es.deusto.client;

// We need to change it, I just copy paste it from the internet so we can use it as a base

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import es.deusto.serialization.UserData;
/**
 * @class LOGIN WINDOW
 * The initial window that allows each user to enter the aplication if they are already registred.
 * If the user is not registered there is a way to change the window to @see es.deusto.client.RegisterWindow
 * 
 */
public class LoginWindow extends JFrame implements ActionListener {

  
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton login, register;
    private ExampleClient client;
    /**
     * Constructor of the window it just receiver the client created on the @see es.deusto.client.ExampleClient
     * All the Jlabels, JtextFields and JButtons are created as a form so the users can easily add their data to the window
     * @param client
     */
    LoginWindow(ExampleClient client) {
        this.client = client; 
        // User Label
        user_label = new JLabel();
        user_label.setText("Email:");
        userName_text = new JTextField(15);
        
        // Password

        password_label = new JLabel();
        password_label.setText("Password:");
        password_text = new JPasswordField(10);

        panel = new JPanel(new GridLayout(3,1));
        
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);

        JPanel buttonPanel = new JPanel();
        
        login = new JButton("LOGIN");
        
        buttonPanel.add(login);
        
        login.addActionListener(this);
        
        register = new JButton("REGISTER");
        
        register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				RegisterWindow rw = new RegisterWindow(client);
				//rw.setVisible(true);
				
			}
		});
        
        buttonPanel.add(register);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components..
        
        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here !");
        setSize(600, 500);
        getContentPane().add(panel,"North");
        getContentPane().add(buttonPanel,"South");
        setVisible(true);

    }

    /**
     * The ACTION LISTENER is related to the JButton LoginIn
     * The user introduces the email and the password and the method validate user @see es.deusto.client.Client.validaUser is called
     * This method return a TRUE or FALSE and only if the user is stored(TRUE) he/she will be able to acces the program 
     * 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
    	String userName = userName_text.getText();
        String password = String.valueOf(password_text.getPassword()); 
       // TODO create method validate user
         
       UserData userData = client.validateUser(userName, password);

       if(userData.getLogin() != null){
        this.setVisible(false); 
        AddExpenseWindow aew = new AddExpenseWindow(userData, client);
        
    }
    
    

}
}