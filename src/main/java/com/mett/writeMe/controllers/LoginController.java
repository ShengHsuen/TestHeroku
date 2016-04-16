package com.mett.writeMe.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mett.writeMe.contracts.BaseResponse;
import com.mett.writeMe.contracts.LoginRequest;
import com.mett.writeMe.contracts.LoginResponse;
import com.mett.writeMe.services.LoginServiceInterface;

/**
 * @author Sheng hsuen
 *
 */
@RestController
@RequestMapping(value = "rest/signin")
public class LoginController {
	
	@Autowired private LoginServiceInterface loginService;
	
	/**
	 * @param lr
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 */
	@RequestMapping(value = "/checkuser", method = RequestMethod.POST)
	@Transactional	
	public BaseResponse checkuser(@RequestBody LoginRequest lr,HttpServletRequest servletRequest,HttpServletResponse servletResponse){		
		
		LoginResponse response = new LoginResponse();
		HttpSession currentSession = servletRequest.getSession();
		System.out.println("This is: "+currentSession);
		loginService.checkUser(lr,response,currentSession);
		return response;	
	}
}

