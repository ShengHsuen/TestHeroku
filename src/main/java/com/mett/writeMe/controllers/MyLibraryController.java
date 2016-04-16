package com.mett.writeMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mett.writeMe.contracts.MyLibraryRequest;
import com.mett.writeMe.contracts.MyLibraryResponse;
import com.mett.writeMe.services.MyLibraryServiceInterface;

/**
 * @author Sheng hsuen
 *
 */
@RestController

@RequestMapping(value ="rest/protected/myLibraries")

public class MyLibraryController {
@Autowired private MyLibraryServiceInterface usersService;
	
	/**
	 * @param ur
	 * @return
	 */
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public MyLibraryResponse create(@RequestBody MyLibraryRequest ur){	
		MyLibraryResponse us = new MyLibraryResponse();
		Boolean state = usersService.saveLibrary(ur);		
			if(state){
				us.setCode(200);
				us.setCodeMessage("library created succesfully");
			}
			return us;
	}
	
}
