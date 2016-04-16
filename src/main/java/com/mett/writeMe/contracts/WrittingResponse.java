package com.mett.writeMe.contracts;

import java.util.Date;
import java.util.List;

import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mett.writeMe.pojo.UserHasWrittingPOJO;
import com.mett.writeMe.pojo.UserPOJO;
import com.mett.writeMe.pojo.WrittingPOJO;

/**
 * @author Sheng hsuen
 *
 */
public class WrittingResponse extends BaseResponse{
	private List<WrittingPOJO> writting;
	private List<UserPOJO> user;
	private List<String> owner;
	private List<Boolean> isOwnerList;
	private List<UserPOJO> userAccepted;
	private List<String> usersInvited;
	
	public List<UserPOJO> getUserAccepted() {
		return userAccepted;
	}

	public void setUserAccepted(List<UserPOJO> userAccepted) {
		this.userAccepted = userAccepted;
	}

	public List<Boolean> getIsOwnerList() {
		return isOwnerList;
	}

	public void setIsOwnerList(List<Boolean> isOwnerList) {
		this.isOwnerList = isOwnerList;
	}

	
	public List<String> getUsersInvited() {
		return usersInvited;
	}

	public void setUsersInvited(List<String> usersInvited) {
		this.usersInvited = usersInvited;
	}

	public List<String> getOwner() {
		return owner;
	}

	public void setOwner(List<String> owner) {
		this.owner = owner;
	}

	public List<UserPOJO> getUser() {
		return user;
	}

	public void setUser(List<UserPOJO> user) {
		this.user = user;
	}

	private List<UserHasWrittingPOJO> userHasWritting;
	private int idWritting;
	private String name;
	private boolean participation;
	private String content;
	private Date date;
	private String category;
	private String description;

	private String image;

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getIdWritting() {
		return idWritting;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdWritting(int idWritting) {
		this.idWritting = idWritting;
	}

	public WrittingResponse() {
		super();
	}
	
	public List<WrittingPOJO> getWritting() {
		return writting;
	}

	public void setWritting(List<WrittingPOJO> writting) {
		this.writting = writting;
	}
	
	public List<UserHasWrittingPOJO> getUserHasWritting() {
		return userHasWritting;
	}

	public void setUsuarios(List<UserHasWrittingPOJO> userHasWritting) {
		this.userHasWritting = userHasWritting;
	}

	public boolean isParticipation() {
		return participation;
	}

	public void setParticipation(boolean participation) {
		this.participation = participation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
