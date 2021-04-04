package es.deusto.serialization;


// same but with more detail
public class UserData {

    private String login;
    private String password;
    private String cardNumber;
    private int age;
    private double expenseLimit; 
    
    public UserData() {

    }

    public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getExpenseLimit() {
        return this.expenseLimit;
    }

    public void setExpenseLimit(double expenseLimit){
        this.expenseLimit = expenseLimit;
    }


    public String toString() {
        return "[login=" + login + ", password=" + password + ", expense limit=" + expenseLimit + ", age=" + age+ ", card Number=" + cardNumber+" ]";
    }
}