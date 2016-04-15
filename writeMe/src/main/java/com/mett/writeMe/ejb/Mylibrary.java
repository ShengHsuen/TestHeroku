package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mylibrary database table.
 * 
 */
@Entity
@NamedQuery(name="Mylibrary.findAll", query="SELECT m FROM Mylibrary m")
public class Mylibrary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int myLibraryId;

	private String description;

	private String title;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="mylibrary")
	private List<User> users;

	//bi-directional many-to-many association to Writting
	@ManyToMany(mappedBy="mylibraries")
	private List<Writting> writtings;

	public Mylibrary() {
	}

	public int getMyLibraryId() {
		return this.myLibraryId;
	}

	public void setMyLibraryId(int myLibraryId) {
		this.myLibraryId = myLibraryId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setMylibrary(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setMylibrary(null);

		return user;
	}

	public List<Writting> getWrittings() {
		return this.writtings;
	}

	public void setWrittings(List<Writting> writtings) {
		this.writtings = writtings;
	}

}