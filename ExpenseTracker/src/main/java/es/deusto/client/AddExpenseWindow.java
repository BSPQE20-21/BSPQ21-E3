package es.deusto.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import es.deusto.serialization.UserData;

public class AddExpenseWindow extends JFrame implements ActionListener {
	
	public AddExpenseWindow(UserData userData) {
		 setTitle("Please Login Here !");
		 setSize(300, 100);
		 //setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
