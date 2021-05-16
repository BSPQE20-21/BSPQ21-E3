package es.deusto.serialization;


/**
 * The object that contains all the information about an user\n
 */
public class UserData {

    private String login;
    private String password;
    private String cardNumber;
    private int age;
    private double expenseLimit; 
   
    /**
     * Empty constructor
     */
    public UserData() {

    }
    /**
     * Complete constructor
     * @param login 
     * @param password
     * @param cardNumber 
     * @param age
     * @param expenseLimit
     */
	public UserData(String login, String password, String cardNumber, int age, double expenseLimit) {
		super();
		this.login = login;
		this.password = password;
		this.cardNumber = cardNumber;
		this.age = age;
		this.expenseLimit = expenseLimit;
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

    /**
     * TO STRING\n
     * Easy way of visualizating the user\n
     * [login= , password= , expenseLimit= , age= , cardNumber= ]
     */
    public String toString() {
        return "[login=" + login + ", password=" + password + ", expense limit=" + expenseLimit + ", age=" + age+ ", card Number=" + cardNumber+" ]";
    }
}