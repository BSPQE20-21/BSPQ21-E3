package es.deusto.serialization;

public class LoginData {
    
    private String login;
    private String password;
    
    public LoginData() {

    }
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

    
    public String toString() {
        return "[login=" + login + ", password=" + password + " ]";
    }
}
