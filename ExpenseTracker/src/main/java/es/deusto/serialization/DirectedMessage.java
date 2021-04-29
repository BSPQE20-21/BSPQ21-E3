package es.deusto.serialization;

// the relationship between the user and the message or in our case user and expenses

public class DirectedMessage {

    
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