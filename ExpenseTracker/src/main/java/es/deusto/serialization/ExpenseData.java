package es.deusto.serialization;

import es.deusto.server.jdo.Category;

/**
 * This objects collects all the information of an expense
 */
public class ExpenseData {

    private String text;
    private double amount;
    private Category category;

	/**
	 * Complete constructor.\n
	 * @param text - the description of the expense made
	 * @param amount - the money it cost
	 * @param category  - the type of expense made
	 */
    public ExpenseData(String text, double amount, Category category) {
		super();
		this.text = text;
		this.amount = amount;
		this.category = category;
	}
	/**
	 * Empty constructor
	 */
	public ExpenseData() {

    }
	
	/**
	 * Getters and Setters for the ExpenseData class
	 */

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * TO STRING\n
	 * Method that provides a string conversion display to print the data.
	 * The format is the following:\n
	 * Description: (amount), category. 
	 */
	@Override
	public String toString() {
		return "Description:" + text + " (" + amount + "), Category:" + category + "";
	}


    
    
  
}