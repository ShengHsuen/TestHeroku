package com.mett.writeMe.pojo;

import java.util.Date;

public class WrittingPOJO {
	private int writtingId;
	private String cantUsers;
	private Date date;
	private String description;
	private String likes;
	private Date limitTime;
	private String name;
	private String numMaxCharacters;
	private String numMinCharacters;
	private boolean participation;
	private boolean published;
	private String content;
	private String typeWritting;
	private String category;
	private String image; 
	private int writtingFather;
	private int mainWritting;
	public int getMainWritting() {
		return mainWritting;
	}

	public void setMainWritting(int mainWritting) {
		this.mainWritting = mainWritting;
	}

	public WrittingPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getWrittingId() {
		return writtingId;
	}

	public void setWrittingId(int writtingId) {
		this.writtingId = writtingId;
	}

	public String getCantUsers() {
		return cantUsers;
	}

	public void setCantUsers(String cantUsers) {
		this.cantUsers = cantUsers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public Date getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumMaxCharacters() {
		return numMaxCharacters;
	}

	public void setNumMaxCharacters(String numMaxCharacters) {
		this.numMaxCharacters = numMaxCharacters;
	}

	public String getNumMinCharacters() {
		return numMinCharacters;
	}

	public void setNumMinCharacters(String numMinCharacters) {
		this.numMinCharacters = numMinCharacters;
	}

	public boolean isParticipation() {
		return participation;
	}

	public void setParticipation(boolean participation) {
		this.participation = participation;
	}

	public boolean getPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTypeWritting() {
		return typeWritting;
	}

	public void setTypeWritting(String typeWritting) {
		this.typeWritting = typeWritting;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getWrittingFather() {
		return writtingFather;
	}

	public void setWrittingFather(int writtingFather) {
		this.writtingFather = writtingFather;
	}
	
	
}
