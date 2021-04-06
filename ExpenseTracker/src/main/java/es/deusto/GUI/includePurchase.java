package es.deusto.GUI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class includePurchase extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Variables used in this window
	JLabel label1, label2, label3;
	JButton button1;
	JComboBox<String> combo1;
	JSpinner spinner1;
	
	
	public void changePanel(JPanel actual, JPanel next) {
		actual.setVisible(false);
		actual.setEnabled(false);
		next.setVisible(true);
		next.setEnabled(false);
		}
	
	public includePurchase() {
		
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(1,1));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		cp.add(mainPanel);
		
		label1 = new JLabel("HOLA, 123probando");
		label1.setBounds(40,40, 120, 30);
		mainPanel.add(label1);
		
		button1 = new JButton("Include Purchase");
		button1.setBounds(120, 320, 140, 20);
		mainPanel.add(button1);
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		
		this.setVisible(true);
		this.setSize(400,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("includePurchase");
		
		
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new includePurchase();
				
			}
		});

	}

}
