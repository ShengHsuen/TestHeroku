package com.mett.writeMe.pojo;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.mett.writeMe.ejb.Writting;

/**
 * @author Mildred Guerra
 * Report POJO
 *
 */
public class ReportPOJO {
	private int reportId;

	private String comment;

	private String penalty;

	private String typeReport;

	private Writting writting;

	public ReportPOJO() {
		super();
	}
	public int getReportId() {
		return this.reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPenalty() {
		return this.penalty;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}

	public String getTypeReport() {
		return this.typeReport;
	}

	public void setTypeReport(String typeReport) {
		this.typeReport = typeReport;
	}

	public Writting getWritting() {
		return this.writting;
	}

	public void setWritting(Writting writting) {
		this.writting = writting;
	}
}
