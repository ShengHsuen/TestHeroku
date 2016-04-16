package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the legal_establishment database table.
 * 
 */
@Entity
@Table(name="legal_establishment")
@NamedQuery(name="LegalEstablishment.findAll", query="SELECT l FROM LegalEstablishment l")
public class LegalEstablishment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int legal_establishmentId;

	@Lob
	private String description;

	private String name;

	private int part;

	public LegalEstablishment() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPart() {
		return this.part;
	}

	public void setPart(int part) {
		this.part = part;
	}

}