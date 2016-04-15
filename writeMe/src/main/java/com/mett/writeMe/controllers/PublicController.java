package com.mett.writeMe.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mett.writeMe.contracts.UserHasWrittingRequest;
import com.mett.writeMe.contracts.UserHasWrittingResponse;
import com.mett.writeMe.contracts.UsersResponse;
import com.mett.writeMe.contracts.WrittingResponse;
import com.mett.writeMe.ejb.User;
import com.mett.writeMe.ejb.UserHasWritting;
import com.mett.writeMe.ejb.Writting;
import com.mett.writeMe.pojo.UserPOJO;
import com.mett.writeMe.repositories.UserHasWrittingRepository;
import com.mett.writeMe.repositories.UserRepository;
import com.mett.writeMe.repositories.WrittingRepository;
import com.mett.writeMe.services.LoginServiceInterface;
import com.mett.writeMe.services.UserHasWrittingServiceInterface;
import com.mett.writeMe.services.UsersServiceInterface;
import com.mett.writeMe.services.WrittingServiceInterface;

@RestController

@RequestMapping(value ="rest/protected/public")

public class PublicController {
	@Autowired private UsersServiceInterface usersService;
	@Autowired private UserHasWrittingServiceInterface userHasWrittingService;
	@Autowired private WrittingServiceInterface writtingService;
	@Autowired private UserRepository userRepository;
	@Autowired private UserHasWrittingRepository userHasWrittingRepository;
	@Autowired private WrittingRepository writtingRepository;
	@Autowired private LoginServiceInterface LoginService;
	
	@RequestMapping(value = "/createPublic", method = RequestMethod.POST)
	public UserHasWrittingResponse createPublic(@RequestBody UserHasWrittingRequest uhw) {
		UserHasWrittingResponse us = new UserHasWrittingResponse();
		Boolean state = false;
		state = userHasWrittingService.addPublic(uhw);
		return us;
	}
	
	@RequestMapping(value = "/getOwnerList", method = RequestMethod.POST)
	public WrittingResponse getOwnerList(@RequestBody UserHasWrittingRequest uhwr) {
		WrittingResponse response = new WrittingResponse();
		List<Boolean> isOwnerList = new ArrayList<Boolean>();
		List<Writting> lw = new ArrayList<Writting>();
		List<UserHasWritting> allUhw = new ArrayList<UserHasWritting>();
		lw.addAll(writtingRepository.findAllByPublishedTrueAndNameNotNull());
		int idUser = userRepository.findByAuthorContaining(uhwr.getSearchTerm()).get(0).getUserId();
		allUhw = userHasWrittingRepository.findAllByUserUserIdAndPubliccTrue(idUser);
		UserHasWritting uhw = new UserHasWritting();
		boolean resul;
		for (int i = 0; i < lw.size(); i++) {
			uhw = userHasWrittingRepository.findUserHasWrittingByWrittingWrittingIdAndUserUserIdAndPubliccTrue(lw.get(i).getWrittingId(),idUser);
			try{
				if(uhw != null){
					System.out.println("ESTE ME TRAE EL OWNER " + uhw.getUser_has_writtingId());
					resul = false;
				}else{
					resul = true;
				}
			}catch(Exception e){
				resul = true;
			}
			isOwnerList.add(resul);
			uhw = new UserHasWritting();
		}
		response.setIsOwnerList(isOwnerList);
		return response;
	}
	
	@RequestMapping(value = "/getContributors", method = RequestMethod.POST)
	public UserHasWrittingResponse getContributors(@RequestBody UserHasWrittingRequest uhw) {
		UserHasWrittingResponse us = new UserHasWrittingResponse();
		List<User> luser = new ArrayList<User>();
		List<UserPOJO> luserpojo = new ArrayList<UserPOJO>();
		List<UserHasWritting> luhw = new ArrayList<UserHasWritting>();
		luhw.addAll(userHasWrittingRepository.findUserHasWrittingByWrittingWrittingIdAndPubliccTrue(uhw.getWritting().getWrittingId()));
		for(int i=0;i<luhw.size();i++){
			System.out.println("luhw.get(i).getUser() >>>>>>>" + luhw.get(i).getUser().getAuthor());
			luser.add(luhw.get(i).getUser());
		}
		luserpojo = generateUserDtos(luser);
		us.setLuser(luserpojo);
		return us;
	}
	
	
	@RequestMapping(value = "/getUserCanWrite", method = RequestMethod.POST)
	public UsersResponse getCan(@RequestBody UserHasWrittingRequest uhw) {
		UsersResponse ur = new UsersResponse();
		UserHasWritting luhw = userHasWrittingRepository.findUserHasWrittingByWrittingWrittingIdAndCanWriteTrue(uhw.getWritting().getWrittingId());
		UserPOJO userPOJO = new UserPOJO();
		User user = luhw.getUser();
		BeanUtils.copyProperties( user ,userPOJO);
		ur.setUser(userPOJO);
		return ur;
	}
	
	// Guarda al siguiente usuario en la lista con canEdit = 1
	@RequestMapping(value = "/setNext", method = RequestMethod.POST)
	public UsersResponse setNext(@RequestBody UserHasWrittingRequest uhw) {
		UsersResponse response = new UsersResponse();
		User user = new User();
		user = userRepository.findByAuthorContaining(uhw.getSearchTerm()).get(0);
		List<UserHasWritting> luhw = new ArrayList<UserHasWritting>();
		luhw = userHasWrittingRepository.findUserHasWrittingByWrittingWrittingIdAndPubliccTrue(uhw.getWritting().getWrittingId());
		System.out.println("SIZE() >>> "+luhw.size());
		for(int i=0; i<=luhw.size()-1;i++){
			System.out.println("luhw.get(i).getUser() " + luhw.get(i).getUser().getAuthor() + " i " + i);
			if(luhw.get(i).getUser() == user){		
				// Entra al if de abajo si es el ultimo de la lista
				if(i == luhw.size()-1){
					System.out.println("Entre al ultimo");
					luhw.get(i).setCanWrite(false);
					luhw.get(0).setCanWrite(true);
					userHasWrittingRepository.save(luhw.get(0));
					userHasWrittingRepository.save(luhw.get(i));
				}else{
					System.out.println("Entre a uno que no es el ultimo");
					luhw.get(i).setCanWrite(false);
					luhw.get(i+1).setCanWrite(true);
					userHasWrittingRepository.save(luhw.get(i+1));
					userHasWrittingRepository.save(luhw.get(i));
				}
			}
		}
		return response;
	}
	
	@RequestMapping(value = "/acceptConfirmation", method = RequestMethod.POST)
	public UserHasWrittingResponse acceptConfirmation(@RequestBody UserHasWrittingRequest uhwr) {
		UserHasWrittingResponse response = new UserHasWrittingResponse();
		int userId = userRepository.findByAuthorContaining(uhwr.getSearchTerm()).get(0).getUserId();
		UserHasWritting uhw = userHasWrittingRepository.findUserHasWrittingByWrittingWrittingIdAndUserUserId(uhwr.getWritting().getWrittingId(),userId);
		uhw.setConfirmation(false);
		userHasWrittingRepository.save(uhw);
		return response;
	}
	
	private List<UserPOJO> generateUserDtos(List<User> users){
		List<UserPOJO> uiUsers = new ArrayList<UserPOJO>();
		users.stream().forEach(u -> {
			UserPOJO dto = new UserPOJO();
			BeanUtils.copyProperties(u,dto);
			dto.setPassword("");
			uiUsers.add(dto);
		});	
		return uiUsers;
	}
}
