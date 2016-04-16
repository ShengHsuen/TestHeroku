package com.mett.writeMe.contracts;

import com.mett.writeMe.contracts.BaseResponse;

/**
 * @author Sheng hsuen
 *
 */
public class LoginResponse extends BaseResponse {
	
	private int idUser;
	private String name;
	private String lastName;
	private String author;
	private boolean admin;

	public LoginResponse() {
		super();
	}
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	

}

