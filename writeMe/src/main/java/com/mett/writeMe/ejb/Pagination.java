package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pagination database table.
 * 
 */
@Entity
@NamedQuery(name="Pagination.findAll", query="SELECT p FROM Pagination p")
public class Pagination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int paginationId;

	private String numMaxCharacters;

	//bi-directional many-to-one association to Writting
	@OneToMany(mappedBy="pagination")
	private List<Writting> writtings;

	public Pagination() {
	}

	public int getPaginationId() {
		return this.paginationId;
	}

	public void setPaginationId(int paginationId) {
		this.paginationId = paginationId;
	}

	public String getNumMaxCharacters() {
		return this.numMaxCharacters;
	}

	public void setNumMaxCharacters(String numMaxCharacters) {
		this.numMaxCharacters = numMaxCharacters;
	}

	public List<Writting> getWrittings() {
		return this.writtings;
	}

	public void setWrittings(List<Writting> writtings) {
		this.writtings = writtings;
	}

	public Writting addWritting(Writting writting) {
		getWrittings().add(writting);
		writting.setPagination(this);

		return writting;
	}

	public Writting removeWritting(Writting writting) {
		getWrittings().remove(writting);
		writting.setPagination(null);

		return writting;
	}

}