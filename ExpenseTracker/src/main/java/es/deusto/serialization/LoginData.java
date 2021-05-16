package es.deusto.serialization;

/**
 * This class is made with the prupose of just storing the login and password data instead of the whole user 
 */
public class LoginData {
    
    private String login;
    private String password;
    
    /**
     * Empty constructor
     */
    public LoginData() {

    }
    /**
     * Complete constructor
     * @param login - email
     * @param password 
     */
	public LoginData(String login, String password) {
		super();
		this.login = login;
		this.password = password;
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

    /**
     * TO STRING\n
     * Easy way to visualize the infromation\n
     * [login= , password= ]
     */
    public String toString() {
        return "[login=" + login + ", password=" + password + " ]";
    }
}
