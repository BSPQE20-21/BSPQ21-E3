package es.deusto.serialization;

import es.deusto.server.jdo.Category;

// expenses
// more detail
public class ExpenseData {

    private String text;
    private double amount;
    private Category category;


	
    public ExpenseData() {

    }

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
    
    
  
}