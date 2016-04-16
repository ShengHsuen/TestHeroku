package com.mett.writeMe.services;

import java.util.List;

import com.mett.writeMe.contracts.UserHasWrittingRequest;
import com.mett.writeMe.ejb.User;
import com.mett.writeMe.ejb.UserHasWritting;
import com.mett.writeMe.ejb.Writting;
import com.mett.writeMe.pojo.UserHasWrittingPOJO;
import com.mett.writeMe.pojo.WrittingPOJO;

public interface UserHasWrittingServiceInterface {
	Boolean save(UserHasWrittingRequest ur);
	List<UserHasWrittingPOJO> getAll(UserHasWrittingRequest ur);
	void deleteUserHaswritting(int uHwrittingId);
	List<UserHasWrittingPOJO> getAll();
	Boolean editUserHasWritting(UserHasWritting uhw);
	Boolean deleteUserHasWritting(Writting wr, User us);
	Boolean edit(UserHasWrittingRequest ur);
	Boolean addPublic(UserHasWrittingRequest ur);
	List<UserHasWrittingPOJO> getUHWByWritting(int writtingId);
}
