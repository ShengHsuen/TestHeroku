package com.mett.writeMe.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mett.writeMe.contracts.LoginRequest;
import com.mett.writeMe.contracts.LoginResponse;
import com.mett.writeMe.ejb.User;
import com.mett.writeMe.repositories.LoginRepository;

@Service
public class LoginService implements LoginServiceInterface{

	@Autowired private LoginRepository loginRepository;
	private User user;
	private HttpSession currentS;
	
	@Override
	@Transactional
	public void checkUser(LoginRequest lr, LoginResponse response, HttpSession currentSession) {
		User loggedUser = loginRepository.findByMailAndPassword(lr.getEmail(), lr.getPassword());
		if(loggedUser == null){
			response.setCode(401);
			response.setErrorMessage("Unauthorized User");
		}else{
			response.setCode(200);
			response.setCodeMessage("User authorized");
			
			//CREATE AND SET THE VALUES FOR THE CONTRACT OBJECT
			response.setIdUser(loggedUser.getUserId());
			response.setName(loggedUser.getName());
			response.setLastName(loggedUser.getLastName());
			response.setAuthor(loggedUser.getAuthor());
			response.setAdmin(loggedUser.getAdmin());
			//
			currentSession.setAttribute("idUser", loggedUser.getUserId());
			
			//Setea el id del usuario loggeado
			user = loggedUser;
			currentS = currentSession;
		}
	}
	
	@Override
	@Transactional
	public User getUser(){
		return user;
	}
	
	@Override
	@Transactional
	public HttpSession getCurrentSession(){
		return currentS;
	}
	
}