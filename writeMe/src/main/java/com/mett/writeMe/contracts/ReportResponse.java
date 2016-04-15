package com.mett.writeMe.contracts;

import java.util.List;

import com.mett.writeMe.pojo.ReportPOJO;

public class ReportResponse extends BaseResponse  {
	private List<ReportPOJO> legalEstList;

	public ReportResponse() {
		super();
	}

	public void setReportList(List<ReportPOJO> legalEstList) {
		this.legalEstList = legalEstList;
	}

	public List<ReportPOJO> getlegalList() {
		return legalEstList;
	}
}
