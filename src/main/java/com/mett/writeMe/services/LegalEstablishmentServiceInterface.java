package com.mett.writeMe.services;

import java.util.List;

import com.mett.writeMe.ejb.LegalEstablishment;
import com.mett.writeMe.pojo.LegalEstablishmentPOJO;;

/**
 * @author  Mildred Guerra
 *
 */
public interface LegalEstablishmentServiceInterface {

	void deleteLegalEstablishment(int idLegalEstablishment);
	List<LegalEstablishmentPOJO> getAll();
	LegalEstablishment getLegalEstablishmentById(int idLegalEstablishment);
	Boolean saveLegalEstablishment(LegalEstablishment legalEstablishmentRequest);
}
