package com.mett.writeMe.pojo;

import java.util.Date;

import com.mett.writeMe.ejb.User;
import com.mett.writeMe.ejb.Writting;

public class UserHasWrittingPOJO {
	
	private int user_has_writtingId;

	private boolean banned;

	private Date dateCreate;

	private Date dateModifie;

	private boolean invitationStatus;

	private int linkInvitation;

	private boolean statusColor;
	
	private boolean owner;
	
	private boolean confirmation;
	
	private boolean canWrite;
	
	public boolean getCanWrite() {
		return canWrite;
	}

	public void setCanWrite(boolean canWrite) {
		this.canWrite = canWrite;
	}

	private boolean public_;
	
	public boolean getPublic_() {
		return public_;
	}

	public void setPublic_(boolean public_) {
		this.public_ = public_;
	}

	public int getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}

	private int idOwner;
	
	public boolean getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public boolean getOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	private User user;
	
	private Writting writting;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Writting getWritting() {
		return writting;
	}

	public void setWritting(Writting writting) {
		this.writting = writting;
	}

	public int getUser_has_writtingId() {
		return user_has_writtingId;
	}

	public void setUser_has_writtingId(int user_has_writtingId) {
		this.user_has_writtingId = user_has_writtingId;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
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
}
