package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idrole;

	private String description;

	private String roleName;

	//bi-directional many-to-one association to UserHasWritting
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_has_writting_user_has_writtingId")
	private UserHasWritting userHasWritting;

	public Role() {
	}

	public int getIdrole() {
		return this.idrole;
	}

	public void setIdrole(int idrole) {
		this.idrole = idrole;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserHasWritting getUserHasWritting() {
		return this.userHasWritting;
	}

	public void setUserHasWritting(UserHasWritting userHasWritting) {
		this.userHasWritting = userHasWritting;
	}

}