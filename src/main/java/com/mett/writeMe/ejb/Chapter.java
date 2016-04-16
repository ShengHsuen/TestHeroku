package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the chapter database table.
 * 
 */
@Entity
@NamedQuery(name="Chapter.findAll", query="SELECT c FROM Chapter c")
public class Chapter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int chaperId;

	private String name;

	//bi-directional many-to-one association to Writting
	@ManyToOne(fetch=FetchType.LAZY)
	private Writting writting;

	public Chapter() {
	}

	public int getChaperId() {
		return this.chaperId;
	}

	public void setChaperId(int chaperId) {
		this.chaperId = chaperId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Writting getWritting() {
		return this.writting;
	}

	public void setWritting(Writting writting) {
		this.writting = writting;
	}

}