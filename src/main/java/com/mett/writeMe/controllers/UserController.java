package com.mett.writeMe.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mett.writeMe.contracts.UsersRequest;
import com.mett.writeMe.contracts.UsersResponse;
import com.mett.writeMe.ejb.Writting;
import com.mett.writeMe.services.UsersServiceInterface;

/**
 * @author Dani
 */
@RestController

@RequestMapping(value ="users")

public class UserController {
	@Autowired private UsersServiceInterface usersService;
	
	/**
	 * @param ur
	 * @return
	 */
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public UsersResponse create(@RequestBody UsersRequest ur){	
		UsersResponse us = new UsersResponse();
		Boolean state = usersService.saveUser(ur);
		//System.out.println("user has w: "+us.getUserHasWritting().get(0).getUser().getName());
			if(state){
				us.setCode(200);
				us.setCodeMessage("user created succesfully");
			}
			return us;
	}
	
	/*
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public UsersResponse getAll() {
		UsersResponse response = new UsersResponse();
		response.setCode(200);
		response.setCodeMessage("Recibe usuarios satisfactoriamente");
		response.setUsers(usersService.getAll());
		return response;
	}*/
	
	@RequestMapping(value ="/edit", method = RequestMethod.POST)
	public UsersResponse edit(@RequestBody UsersRequest ur){	
		UsersResponse us = new UsersResponse();
		Boolean state = usersService.saveUser(ur);		
			if(state){
				us.setCode(200);
				us.setCodeMessage("user created succesfully");
			}
			return us;
	}
	
	@RequestMapping(value ="/delete", method = RequestMethod.DELETE)
	public void delete(
			@RequestParam("idUser") int idUser){	
		    usersService.deleteUser(idUser);
			
	}
	
	@RequestMapping(value ="/getWrittings", method = RequestMethod.POST)
	public UsersResponse getWrittings(HttpServletRequest servletRequest){	

		UsersResponse response = new UsersResponse();
		HttpSession currentSession = servletRequest.getSession();
		response.setCode(200);
		response.setCodeMessage("obtiene bien los writtings");
		response.setWrittings(usersService.getWrittingsByUser(currentSession));
		return response;		
	}
	
	@RequestMapping(value ="/getWrittingsInvitation", method = RequestMethod.POST)
	public UsersResponse getWrittingsInvitation(HttpServletRequest servletRequest){	

		UsersResponse response = new UsersResponse();
		HttpSession currentSession = servletRequest.getSession();
		response.setCode(200);
		response.setCodeMessage("obtiene bien los writtings");
		response.setWrittings(usersService.getWrittingsByUserInvitation(currentSession));
		return response;		
	}
	
	@RequestMapping(value ="/getWrittingsPublic", method = RequestMethod.POST)
	public UsersResponse getWrittingsPublic(HttpServletRequest servletRequest){	

		UsersResponse response = new UsersResponse();
		HttpSession currentSession = servletRequest.getSession();
		response.setCode(200);
		response.setCodeMessage("obtiene bien los writtings");
		response.setWrittings(usersService.getWrittingsByUserPublic(currentSession));
		return response;		
	}
	
}

