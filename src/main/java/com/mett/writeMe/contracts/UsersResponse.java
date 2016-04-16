package com.mett.writeMe.contracts;

import java.util.List;

import com.mett.writeMe.ejb.User;
import com.mett.writeMe.pojo.UserHasWrittingPOJO;
import com.mett.writeMe.pojo.UserPOJO;
import com.mett.writeMe.pojo.WrittingPOJO;

public class UsersResponse extends BaseResponse{
	
	private List<UserHasWrittingPOJO> userHasWritting;
	private List<WrittingPOJO> writtings;
	private List<UserPOJO> users;
	private Boolean isOwner;
	private UserPOJO user;
	
	

	public UserPOJO getUser() {
		return user;
	}
	public void setUser(UserPOJO user) {
		this.user = user;
	}
	public void setUserHasWritting(List<UserHasWrittingPOJO> userHasWritting) {
		this.userHasWritting = userHasWritting;
	}
	public Boolean getIsOwner() {
		return isOwner;
	}
	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}
	public UsersResponse() {
		super();
	}
	public List<UserPOJO> getUsers() {
		return users;
	}

	public void setUsers(List<UserPOJO> users) {
		this.users = users;
	}

	public List<UserHasWrittingPOJO> getUserHasWritting() {
		return userHasWritting;
	}

	public void setUsuarios(List<UserHasWrittingPOJO> userHasWritting) {
		this.userHasWritting = userHasWritting;
	}
	
	public List<WrittingPOJO> getWrittings() {
		return writtings;

	}
	public void setWrittings(List<WrittingPOJO> writtings) {
		this.writtings = writtings;
	}

}