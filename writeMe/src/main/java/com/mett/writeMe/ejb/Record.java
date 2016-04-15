package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the record database table.
 * 
 */
@Entity
@NamedQuery(name="Record.findAll", query="SELECT r FROM Record r")
public class Record implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int recordId;

	@Temporal(TemporalType.DATE)
	private Date date;

	private boolean state;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	//bi-directional many-to-one association to Writting
	@OneToMany(mappedBy="record")
	private List<Writting> writtings;

	public Record() {
	}

	public int getRecordId() {
		return this.recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getState() {
		return this.state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Writting> getWrittings() {
		return this.writtings;
	}

	public void setWrittings(List<Writting> writtings) {
		this.writtings = writtings;
	}

	public Writting addWritting(Writting writting) {
		getWrittings().add(writting);
		writting.setRecord(this);

		return writting;
	}

	public Writting removeWritting(Writting writting) {
		getWrittings().remove(writting);
		writting.setRecord(null);

		return writting;
	}

}