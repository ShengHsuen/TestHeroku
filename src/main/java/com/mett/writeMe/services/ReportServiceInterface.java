package com.mett.writeMe.services;

import java.util.List;

import com.mett.writeMe.ejb.LegalEstablishment;
import com.mett.writeMe.ejb.Report;
import com.mett.writeMe.pojo.ReportPOJO;;

/**
 * @author  Mildred Guerra
 *	
 */
public interface  ReportServiceInterface {
	Boolean save(Report ur);
	void deleteReport(int uHwrittingId);
	List<ReportPOJO> getAll();
	Boolean editReport(Report rep);
	List<ReportPOJO> getAllbyWritting(int idWritting);
}
