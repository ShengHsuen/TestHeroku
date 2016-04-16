package com.mett.writeMe.contracts;

import java.util.List;

import com.mett.writeMe.pojo.UserPOJO;

public class UsersRequest extends BaseRequest {
	private String mail;
	private UserPOJO user;
	private List<UserPOJO> luser;
	
	public List<UserPOJO> getLuser() {
		return luser;
	}

	public void setLuser(List<UserPOJO> luser) {
		this.luser = luser;
	}

	public UsersRequest() {
		super();
	}
	
	public UserPOJO getUser() {
		return user;
	}
	
	public void setUser(UserPOJO user) {
		this.user = user;
	}

	public String getEmail() {
		return mail;
	}

	public void setEmail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "UsersRequest [user=" + user + "]";
	}
}