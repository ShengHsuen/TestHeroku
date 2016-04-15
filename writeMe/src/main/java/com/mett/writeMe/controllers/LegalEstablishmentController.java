package com.mett.writeMe.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mett.writeMe.contracts.LegalEstablishmentResponse;
import com.mett.writeMe.ejb.LegalEstablishment;
import com.mett.writeMe.services.LegalEstablishmentServiceInterface;
import com.mett.writeMe.utils.Utils;

/**
 * @author Mildred Guerra
 *
 */
@RestController
@RequestMapping(value = "/rest/protected/legal")
public class LegalEstablishmentController {
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private LegalEstablishmentServiceInterface legalEstablishmentService;
	@Autowired
	private HttpServletRequest request;

	/**
	 * This method get all the legalEstablishment rules
	 * 
	 * @return LegalEstablishmentResponse
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public LegalEstablishmentResponse getAll() {
		LegalEstablishmentResponse response = new LegalEstablishmentResponse();
		response.setCode(200);
		response.setCodeMessage("Muestra reglas satisfactoriamente");
		response.setLegalEstablishmentList(legalEstablishmentService.getAll());
		return response;
	}

	/**
	 * Create the legalEstablishment
	 * 
	 * @param ler
	 * @return LegalEstablishmentResponse
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public LegalEstablishmentResponse create(@RequestBody LegalEstablishment ler) {
		System.out.println("Entra a controller" + ler);
		LegalEstablishmentResponse legalEstRes = new LegalEstablishmentResponse();

		Boolean state = legalEstablishmentService.saveLegalEstablishment(ler);

		if (state) {
			legalEstRes.setCode(200);
			legalEstRes.setCodeMessage("La regla se creó satisfactoriamente");
		}

		return legalEstRes;
	}

	/**
	 * Delete legal establishent
	 * 
	 * @param idLegalEstablishment
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void delete(@RequestParam("legal_establishmentId") int legal_establishmentId) {

		legalEstablishmentService.deleteLegalEstablishment(legal_establishmentId);

	}
}
