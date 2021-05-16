package es.deusto.serialization;

import es.deusto.server.jdo.Category;

/**
 * This is the relationship between the user and their expenses.\n
 * 
 */

public class DirectedMessage {

    private UserData userData;
    private ExpenseData expenseData;
	
    /**
     * Constructor of the object 
     * @param userData
     * @param expenseData
     */
	public DirectedMessage(UserData userData, ExpenseData expenseData) {
		super();
		this.userData = userData;
		this.expenseData = expenseData;
	}

    /**
     * Empty constructor
     */
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