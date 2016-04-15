package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user_has_writting database table.
 * 
 */
@Entity
@Table(name="user_has_writting")
@NamedQuery(name="UserHasWritting.findAll", query="SELECT u FROM UserHasWritting u")
public class UserHasWritting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int user_has_writtingId;

	private boolean banned;

	private boolean canWrite;

	private boolean confirmation;

	@Temporal(TemporalType.DATE)
	private Date dateCreate;

	@Temporal(TemporalType.DATE)
	private Date dateModifie;

	@Column(name="id_owner")
	private int idOwner;

	private boolean invitationStatus;

	private int linkInvitation;

	private boolean owner;

	private boolean publicc;

	private boolean statusColor;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="userHasWritting")
	private List<Comment> comments;

	//bi-directional many-to-one association to Role
	@OneToMany(mappedBy="userHasWritting")
	private List<Role> roles;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	//bi-directional many-to-one association to Writting
	@ManyToOne(fetch=FetchType.LAZY)
	private Writting writting;

	public UserHasWritting() {
	}

	public int getUser_has_writtingId() {
		return this.user_has_writtingId;
	}

	public void setUser_has_writtingId(int user_has_writtingId) {
		this.user_has_writtingId = user_has_writtingId;
	}

	public boolean getBanned() {
		return this.banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public boolean getCanWrite() {
		return this.canWrite;
	}

	public void setCanWrite(boolean canWrite) {
		this.canWrite = canWrite;
	}

	public boolean getConfirmation() {
		return this.confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public Date getDateCreate() {
		return this.dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateModifie() {
		return this.dateModifie;
	}

	public void setDateModifie(Date dateModifie) {
		this.dateModifie = dateModifie;
	}

	public int getIdOwner() {
		return this.idOwner;
	}

	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}

	public boolean getInvitationStatus() {
		return this.invitationStatus;
	}

	public void setInvitationStatus(boolean invitationStatus) {
		this.invitationStatus = invitationStatus;
	}

	public int getLinkInvitation() {
		return this.linkInvitation;
	}

	public void setLinkInvitation(int linkInvitation) {
		this.linkInvitation = linkInvitation;
	}

	public boolean getOwner() {
		return this.owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public boolean getPublicc() {
		return this.publicc;
	}

	public void setPublicc(boolean publicc) {
		this.publicc = publicc;
	}

	public boolean getStatusColor() {
		return this.statusColor;
	}

	public void setStatusColor(boolean statusColor) {
		this.statusColor = statusColor;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUserHasWritting(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setUserHasWritting(null);

		return comment;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.setUserHasWritting(this);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.setUserHasWritting(null);

		return role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Writting getWritting() {
		return this.writting;
	}

	public void setWritting(Writting writting) {
		this.writting = writting;
	}

}