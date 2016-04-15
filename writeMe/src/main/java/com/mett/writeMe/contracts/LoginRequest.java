package com.mett.writeMe.contracts;

/**
 * @author Sheng hsuen
 *
 */
public class LoginRequest {

	private String mail;
	private String password;
	
	public LoginRequest() {
		super();
	}

	public LoginRequest(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}

	public String getEmail() {
		return mail;
	}

	public void setEmail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

