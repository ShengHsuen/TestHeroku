package com.mett.writeMe.services;

import javax.servlet.http.HttpSession;

import com.mett.writeMe.contracts.LoginRequest;
import com.mett.writeMe.contracts.LoginResponse;
import com.mett.writeMe.ejb.User;

public interface LoginServiceInterface {

	public void checkUser(LoginRequest lr, LoginResponse response, HttpSession currentSession);
	public User getUser();
	HttpSession getCurrentSession();

}
