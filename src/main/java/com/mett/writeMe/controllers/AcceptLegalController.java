package com.mett.writeMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mett.writeMe.contracts.LegalEstablishmentResponse;
import com.mett.writeMe.services.LegalEstablishmentServiceInterface;

/**
 * @author Mildred Guerra
 *
 */
@RestController
@RequestMapping(value ="acceptLegalEstablishment")
public class AcceptLegalController {

	@Autowired private LegalEstablishmentServiceInterface legalEstablishmentService;


	/**
	 * @return
	 */
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public LegalEstablishmentResponse getAll(){	
		LegalEstablishmentResponse response = new LegalEstablishmentResponse();
		response.setLegalEstablishmentList(legalEstablishmentService.getAll());
		response.setCode(200);
		response.setCodeMessage("Muestra reglas satisfactoriamente");
		return response;		
	}
}
