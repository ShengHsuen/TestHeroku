package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the notificationtype database table.
 * 
 */
@Entity
@NamedQuery(name="Notificationtype.findAll", query="SELECT n FROM Notificationtype n")
public class Notificationtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int notificationTypeId;

	private String name;

	//bi-directional many-to-one association to Notification
	@ManyToOne(fetch=FetchType.LAZY)
	private Notification notification;

	public Notificationtype() {
	}

	public int getNotificationTypeId() {
		return this.notificationTypeId;
	}

	public void setNotificationTypeId(int notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Notification getNotification() {
		return this.notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

}