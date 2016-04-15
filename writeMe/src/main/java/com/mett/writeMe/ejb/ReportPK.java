package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the report database table.
 * 
 */
@Embeddable
public class ReportPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int reportId;

	@Column(insertable=false, updatable=false)
	private int writting_writtingId;

	public ReportPK() {
	}
	public int getReportId() {
		return this.reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public int getWritting_writtingId() {
		return this.writting_writtingId;
	}
	public void setWritting_writtingId(int writting_writtingId) {
		this.writting_writtingId = writting_writtingId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReportPK)) {
			return false;
		}
		ReportPK castOther = (ReportPK)other;
		return 
			(this.reportId == castOther.reportId)
			&& (this.writting_writtingId == castOther.writting_writtingId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.reportId;
		hash = hash * prime + this.writting_writtingId;
		
		return hash;
	}
}