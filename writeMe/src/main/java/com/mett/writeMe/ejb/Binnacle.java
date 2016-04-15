package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the binnacle database table.
 * 
 */
@Entity
@NamedQuery(name="Binnacle.findAll", query="SELECT b FROM Binnacle b")
public class Binnacle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int binnacleId;

	private String action;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String name;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	public Binnacle() {
	}

	public int getBinnacleId() {
		return this.binnacleId;
	}

	public void setBinnacleId(int binnacleId) {
		this.binnacleId = binnacleId;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}