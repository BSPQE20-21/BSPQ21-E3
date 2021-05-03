package es.deusto.serialization;

import es.deusto.server.jdo.Category;

// the relationship between the user and the message or in our case user and expenses

public class DirectedMessage {

	

	public DirectedMessage(UserData userData, ExpenseData expenseData) {
		super();
		this.userData = userData;
		this.expenseData = expenseData;
	}

	private UserData userData;
    private ExpenseData expenseData;
    
    public DirectedMessage() {
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return this.userData;
    }

    public void setExpenseData(ExpenseData expenseData) {
        this.expenseData = expenseData;
    }

    public ExpenseData getExpenseData() {
        return this.expenseData;
    }

	
}