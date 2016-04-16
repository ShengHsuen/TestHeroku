package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the typereport database table.
 * 
 */
@Entity
@NamedQuery(name="Typereport.findAll", query="SELECT t FROM Typereport t")
public class Typereport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int typeReportId;

	private String name;

	public Typereport() {
	}

	public int getTypeReportId() {
		return this.typeReportId;
	}

	public void setTypeReportId(int typeReportId) {
		this.typeReportId = typeReportId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}