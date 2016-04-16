package com.mett.writeMe.contracts;

import com.mett.writeMe.pojo.UserHasWrittingPOJO;
import com.mett.writeMe.pojo.UserPOJO;
import com.mett.writeMe.pojo.WrittingPOJO;

import java.util.Date;
import java.util.List;

import com.mett.writeMe.contracts.BaseRequest;
import com.mett.writeMe.ejb.User;
import com.mett.writeMe.ejb.Writting;

public class UserHasWrittingRequest extends BaseRequest{
	private List<UserHasWrittingPOJO> userHasWrittings;
	private List<WrittingPOJO> writtings;
	private List<UserPOJO> users;
	private UserHasWrittingPOJO userHasWritting;
	private WrittingPOJO writting;
	private UserPOJO user;
	private String resultFileName;
	private Date dateCreate;

	private Date dateModifie;

	private boolean invitationStatus;

	private int linkInvitation;

	private boolean statusColor;
	
	private boolean owner;

	private User userUser;
	
	private Writting writtingWritting;
	
	
	public List<UserHasWrittingPOJO> getUserHasWrittings() {
		return userHasWrittings;
	}

	public void setUserHasWrittings(List<UserHasWrittingPOJO> userHasWrittings) {
		this.userHasWrittings = userHasWrittings;
	}

	public List<WrittingPOJO> getWrittings() {
		return writtings;
	}

	public void setWrittings(List<WrittingPOJO> writtings) {
		this.writtings = writtings;
	}

	public List<UserPOJO> getUsers() {
		return users;
	}

	public void setUsers(List<UserPOJO> users) {
		this.users = users;
	}

	public String getResultFileName() {
		return resultFileName;
	}

	public void setResultFileName(String resultFileName) {
		this.resultFileName = resultFileName;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateModifie() {
		return dateModifie;
	}

	public void setDateModifie(Date dateModifie) {
		this.dateModifie = dateModifie;
	}

	public boolean isInvitationStatus() {
		return invitationStatus;
	}

	public void setInvitationStatus(boolean invitationStatus) {
		this.invitationStatus = invitationStatus;
	}

	public int getLinkInvitation() {
		return linkInvitation;
	}

	public void setLinkInvitation(int linkInvitation) {
		this.linkInvitation = linkInvitation;
	}

	public boolean isStatusColor() {
		return statusColor;
	}

	public void setStatusColor(boolean statusColor) {
		this.statusColor = statusColor;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public User getUserUser() {
		return userUser;
	}

	public void setUserUser(User userUser) {
		this.userUser = userUser;
	}

	public Writting getWrittingWritting() {
		return writtingWritting;
	}

	public void setWrittingWritting(Writting writtingWritting) {
		this.writtingWritting = writtingWritting;
	}

	public WrittingPOJO getWritting() {
		return writting;
	}

	public void setWritting(WrittingPOJO writting) {
		this.writting = writting;
	}

	
	public UserPOJO getUser() {
		return user;
	}

	public void setUser(UserPOJO user) {
		this.user = user;
	}
	
	public UserHasWrittingRequest() {
		super();
	}
	
	public UserHasWrittingPOJO getUserHasWritting() {
		return userHasWritting;
	}
	
	public void setUserHasWritting(UserHasWrittingPOJO userHasWritting) {
		this.userHasWritting = userHasWritting;
	}

	@Override
	public String toString() {
		return "UserHasWrittingRequest [userHasWritting=" + userHasWritting + "]";
	}

}
