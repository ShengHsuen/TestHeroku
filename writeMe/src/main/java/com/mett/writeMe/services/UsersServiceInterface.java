package com.mett.writeMe.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.mett.writeMe.contracts.UsersRequest;
import com.mett.writeMe.contracts.WrittingRequest;
import com.mett.writeMe.ejb.User;
import com.mett.writeMe.ejb.UserHasWritting;
import com.mett.writeMe.ejb.Writting;
import com.mett.writeMe.pojo.UserPOJO;
import com.mett.writeMe.pojo.WrittingPOJO;


public interface UsersServiceInterface {
    
	List<UserPOJO> getAll();
	List<UserPOJO> getAllByName(UsersRequest ur);
	Boolean saveUser(UsersRequest ur);
	void deleteUser (int idUser);
	User getUserByMail(UsersRequest ur);
	List<WrittingPOJO> getWrittingsByUser(HttpSession currentSession);
	List<String> getUsersOwner(List<WrittingPOJO> wpojo, String userTerm);
	List<String> getUsersInvited(Writting wr, String userTerm);
	List<WrittingPOJO> getWrittingsByUserWrittingNameNotNullAndTypeWrittingInvitation(HttpSession currentSession);
	List<WrittingPOJO> getWrittingsByUserWrittingNameNotNullAndTypeWrittingPublic(HttpSession currentSession);
	List<UserPOJO> getUserByAuthor(String author);
	List<WrittingPOJO> getWrittingsByUserInvitation(HttpSession currentSession);
	List<WrittingPOJO> getWrittingsByUserPublic(HttpSession currentSession);
	
}
