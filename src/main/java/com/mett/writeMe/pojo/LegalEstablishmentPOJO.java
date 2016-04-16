package com.mett.writeMe.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.mett.writeMe.ejb.User;

public class LegalEstablishmentPOJO {
	private int legal_establishmentId;
	private String description;
	private int part;
	private String name;

	public LegalEstablishmentPOJO() {
		super();
	}

	public int getLegal_establishmentId() {
		return this.legal_establishmentId;
	}

	public void setLegal_establishmentId(int legal_establishmentId) {
		this.legal_establishmentId = legal_establishmentId;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPart() {
		return this.part;
	}

	public void setPart(int part) {
		this.part = part;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}



}