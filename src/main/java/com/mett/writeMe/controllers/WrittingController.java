package com.mett.writeMe.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mett.writeMe.contracts.UserHasWrittingRequest;
import com.mett.writeMe.contracts.UserHasWrittingResponse;
import com.mett.writeMe.contracts.UsersResponse;
import com.mett.writeMe.contracts.WrittingRequest;
import com.mett.writeMe.contracts.WrittingResponse;
import com.mett.writeMe.ejb.User;
import com.mett.writeMe.ejb.UserHasWritting;
import com.mett.writeMe.ejb.Writting;
import com.mett.writeMe.pojo.ReportPOJO;
import com.mett.writeMe.pojo.UserHasWrittingPOJO;
import com.mett.writeMe.pojo.WrittingPOJO;
import com.mett.writeMe.services.GeneratePDFService;
import com.mett.writeMe.services.LoginServiceInterface;
import com.mett.writeMe.services.ReportServiceInterface;
import com.mett.writeMe.services.UserHasWrittingServiceInterface;
import com.mett.writeMe.services.UsersService;
import com.mett.writeMe.services.UsersServiceInterface;
import com.mett.writeMe.services.WrittingServiceInterface;
import com.mett.writeMe.utils.Utils;

/**
 * @author Dani
 * @author Sheng
 */
@RestController

@RequestMapping(value = "rest/protected/writting")

public class WrittingController {
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private WrittingServiceInterface WrittingService;
	@Autowired
	private UserHasWrittingServiceInterface UserHasWrittingService;
	@Autowired
	private LoginServiceInterface LoginService;
	@Autowired
	private UsersServiceInterface userService;
	@Autowired
	private ReportServiceInterface reportService;
	
	private User u = new User();
	private Writting wr = new Writting();
	private String resultFileName;

	/**
	 * @param ur
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public WrittingResponse create(@RequestBody WrittingRequest ur) {
		WrittingResponse us = new WrittingResponse();

		if (!resultFileName.equals("")) {
			ur.getWritting().setImage(resultFileName);
			Boolean state = false;
			state = WrittingService.saveWritting(ur);

			WrittingPOJO w = WrittingService.getWrittingByName(ur);
			u = LoginService.getUser();
			BeanUtils.copyProperties(w, wr);

			if (state) {
				us.setCode(200);
				us.setCodeMessage("write created succesfully");
			}
		} else {
			// create a common webservice error codes enum or statics
			us.setCode(409);
			us.setErrorMessage("create/edit conflict");
		}
		return us;
	}

	@RequestMapping(value = "/getPublished", method = RequestMethod.POST)
	public WrittingResponse getPublished(@RequestBody WrittingRequest ur) {
		System.out.println("Controller /getPublished");
		WrittingResponse us = new WrittingResponse();
		us.setCode(200);
		us.setCodeMessage("users fetch success");
		us.setWritting(WrittingService.getPublished(ur));
		us.setUser(WrittingService.getOwnersPublished(ur));
		return us;
	}

	/*
	 * @author Sheng Hsuen
	 * 
	 * @param ur
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createWrittingInvitation", method = RequestMethod.POST)
	public WrittingResponse createInvitation(@RequestBody WrittingRequest ur) {
		WrittingResponse us = new WrittingResponse();

		WrittingPOJO w = WrittingService.getWrittingByName(ur);
		u = LoginService.getUser();
		BeanUtils.copyProperties(w, wr);

		Boolean state = WrittingService.createWrittingInvitation(wr);

		if (state) {
			us.setCode(200);
			us.setCodeMessage("write created succesfully");
		}
		return us;
	}

	/**
	 * author Sheng Hsuen Cheng
	 * 
	 * @param ur
	 * @return
	 */
	@RequestMapping(value = "/editContentFinish", method = RequestMethod.POST)
	public WrittingResponse editContentFinish(@RequestBody WrittingRequest ur) {
		WrittingResponse us = new WrittingResponse();
		WrittingPOJO w = WrittingService.getWrittingByName(ur);
		BeanUtils.copyProperties(w, wr);

		wr.setContent(ur.getWritting().getContent());
		Boolean state = WrittingService.finishWritting(wr);
		if (state) {
			us.setCode(200);
			us.setCodeMessage("write created succesfully");
		}
		return us;
	};
	
	/**
	 * author Sheng Hsuen Cheng
	 * 
	 * @param ur
	 * @return
	 */
	@RequestMapping(value = "/outWritting", method = RequestMethod.POST)
	public WrittingResponse outWritting(@RequestBody WrittingRequest ur) {
		WrittingResponse us = new WrittingResponse();
		WrittingPOJO w = WrittingService.getWrittingByName(ur);
		BeanUtils.copyProperties(w, wr);

		wr.setContent(ur.getWritting().getContent());
		Boolean state = WrittingService.outWritting(wr);
		if (state) {
			us.setCode(200);
			us.setCodeMessage("write created succesfully");
		}
		return us;
	}
	
	

	/**
	 * author Sheng Hsuen Cheng
	 * 
	 * @param ur
	 * @return
	 */
	@RequestMapping(value = "/editContent", method = RequestMethod.POST)
	public WrittingResponse editContent(@RequestBody WrittingRequest ur) {
		WrittingResponse us = new WrittingResponse();
		WrittingPOJO w = WrittingService.getWrittingByName(ur);
		BeanUtils.copyProperties(w, wr);

		System.out.print("EDIT CONTENT IMPRIMIR EL POJO" + w.getName());

		wr.setContent(ur.getWritting().getContent());
		Boolean state = WrittingService.editWritting(wr);
		if (state) {
			us.setCode(200);
			us.setCodeMessage("write created succesfully");
		}
		return us;
	}

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public WrittingResponse publish(@RequestBody WrittingRequest ur) {
		WrittingResponse us = new WrittingResponse();
		// WrittingPOJO w = WrittingService.getWrittingByName(ur);
		// System.out.println("WrittingPOJO: " + w.getName());
		/*
		 * BeanUtils.copyProperties(w, wr);
		 * //wr.setContent(ur.getWritting().getContent());
		 * wr.setDate(ur.getWritting().getDate());
		 * wr.setPublished(ur.getWritting().getPublished());
		 */
		Boolean state = WrittingService.publish(ur);
		us.setContent(ur.getWritting().getContent());
		if (state) {
			us.setCode(200);
			us.setCodeMessage("write created succesfully");
		}
		return us;
	}

	/**
	 * @param ur
	 * @return
	 */
	@RequestMapping(value = "/createUserHasWritting", method = RequestMethod.POST)
	public UserHasWrittingResponse create(@RequestBody UserHasWrittingRequest ur) {
		UserHasWrittingResponse us = new UserHasWrittingResponse();
		ur.getUserHasWritting().setUser(u);
		ur.getUserHasWritting().setWritting(wr);
		ur.getUserHasWritting().setInvitationStatus(true);
		System.out.println("Obra a la que estoy seteando: " + ur.getUserHasWritting().getWritting().getWrittingId());
		Boolean state = UserHasWrittingService.save(ur);

		if (state) {
			us.setCode(200);
			us.setCodeMessage("write created succesfully");
		}
		return us;
	}

	/**
	 * @author Mildred Guerra Add files to util
	 * 
	 * @param MultipartFile
	 *            file
	 */
	@RequestMapping(value = "/addFiles", method = RequestMethod.POST)
	public void create(@RequestParam("file") MultipartFile file, WrittingRequest ur) {
		resultFileName = Utils.writeToFile(file, servletContext);
		System.out.println("Entra a agregar files");
	}

	/**
	 * @author Mildred Guerra Delete writting
	 * @param int
	 *            idwritting
	 * @return WrittingResponse wr
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public WrittingResponse delete(@RequestParam("writtingId") int writtingMainId) {
		WrittingResponse wr = new WrittingResponse();
		List<WrittingPOJO> listaUserHasWritting = new ArrayList<WrittingPOJO>();
		// buscar todos los userHasWritting
		// botener todas para comparar con el padre
		List<WrittingPOJO> allWrittings = getAll().getWritting();
		List<UserHasWrittingPOJO> allUserHasWritting = UserHasWrittingService.getAll();
		List<ReportPOJO> writtingreports = reportService.getAllbyWritting(writtingMainId);
		wr = getAll();
		// comparar los que tienen de padre writtingId

		for (int j = allWrittings.size() - 1; j >= 0; j--) {
			if (allWrittings.get(j).getMainWritting() == writtingMainId) {
				// obtiene los userHasWritting de los hijos
				for (int i = allUserHasWritting.size() - 1; i >= 0; i--) {
					// comparar los que tienen de writtingId del hijo
					if (allUserHasWritting.get(i).getWritting().getWrittingId() == allWrittings.get(j)
							.getWrittingId()) {
						// eliminar userhasWritting de los hijos
						UserHasWrittingService
						.deleteUserHaswritting(allUserHasWritting.get(i).getUser_has_writtingId());
					}
				};
				// eliminar hijos
				WrittingService.deletewritting(allWrittings.get(j).getWrittingId());
			}
		}
		// eliminar reportes de writting
		for (int k = writtingreports.size() - 1; k >= 0; k--) {
			System.out.println("entra al report main"+ writtingreports.size() );
			reportService.deleteReport(writtingreports.get(k).getReportId());
		};
		allUserHasWritting.stream().forEach(uhw -> {
			if (uhw.getWritting().getWrittingId() == writtingMainId) {
				System.out.println("entra al userHasWritting main");
				// eliminar userhasWritting del main
				UserHasWrittingService.deleteUserHaswritting(uhw.getUser_has_writtingId());
			}
		});
		allWrittings = getAll().getWritting();
		allWrittings.stream().forEach(wrt -> {
			if (wrt.getWrittingId() == writtingMainId) {

				// eliminar el main
				WrittingService.deletewritting(writtingMainId);
				/*
				 * elimina la imagen del fichero File fichero = new
				 * File(wrt.getImage());
				 * System.out.println(fichero.getAbsolutePath()); String
				 * absolutePath = "resources/writtingImages/" +
				 * fichero.getName(); fichero = new File(absolutePath); if
				 * (fichero.delete()) System.out.println(
				 * "El fichero ha sido borrado satisfactoriamente"); else
				 * System.out.println("El fichero no puede ser borrado");
				 */
			}

		});

		return wr;
	}

	/**
	 * @author Mildred Guerra This method get all the writtings
	 * 
	 * @return WrittingResponse response
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public WrittingResponse getAll() {
		WrittingResponse response = new WrittingResponse();
		response.setCode(200);
		response.setCodeMessage("Muestra reglas satisfactoriamente");
		response.setWritting(WrittingService.getAll());
		return response;
	}

	/**author Sheng Hsuen
	 * @param ur
	 * @return
	 */
	@RequestMapping(value = "/getOwnerListInvitation", method = RequestMethod.POST)
	public WrittingResponse getOwnerListInvitation(@RequestBody WrittingRequest ur) {
		HttpSession currentSession = LoginService.getCurrentSession();
		WrittingResponse response = new WrittingResponse();
		List<Boolean> isOwnerList = new ArrayList<Boolean>();
		List<WrittingPOJO> wPojo = new ArrayList<WrittingPOJO>();

		response.setCode(200);
		response.setCodeMessage("Muestra reglas satisfactoriamente");
		wPojo = userService.getWrittingsByUserWrittingNameNotNullAndTypeWrittingInvitation(currentSession);
		System.out.println("ESTE ES EL TAMANNO DE WRITTINGS QUE TRAE"+wPojo.size());

//		response.setWritting(WrittingService.getAllWithoutNameNull());
		for (int i = 0; i < wPojo.size(); i++) {
				Writting wr = new Writting();
				BeanUtils.copyProperties(wPojo.get(i), wr);
				isOwnerList.add(WrittingService.getOwner(ur.getSearchTerm(), wr));
		}
		response.setIsOwnerList(isOwnerList);
		return response;
	}
	
	
	/**author Sheng Hsuen
	 * @param ur
	 * @return
	 */
	@RequestMapping(value = "/getOwnerListPublic", method = RequestMethod.POST)
	public WrittingResponse getOwnerListPublic(@RequestBody WrittingRequest ur) {
		HttpSession currentSession = LoginService.getCurrentSession();
		WrittingResponse response = new WrittingResponse();
		List<Boolean> isOwnerList = new ArrayList<Boolean>();
		List<WrittingPOJO> wPojo = new ArrayList<WrittingPOJO>();

		response.setCode(200);
		response.setCodeMessage("Muestra reglas satisfactoriamente");
		wPojo = userService.getWrittingsByUserWrittingNameNotNullAndTypeWrittingPublic(currentSession);
		System.out.println("ESTE ES EL TAMANNO DE WRITTINGS QUE TRAE"+wPojo.size());

//		response.setWritting(WrittingService.getAllWithoutNameNull());
		for (int i = 0; i < wPojo.size(); i++) {
				Writting wr = new Writting();
				BeanUtils.copyProperties(wPojo.get(i), wr);
				isOwnerList.add(WrittingService.getOwner(ur.getSearchTerm(), wr));
		}
		response.setIsOwnerList(isOwnerList);
		return response;
	}
	

	/**
	 * @author Mildred Guerra Get writtings with main
	 * @param int
	 *            idwritting
	 * @return WrittingResponse wr
	 */
	@RequestMapping(value = "/getByMain", method = RequestMethod.POST)
	public WrittingResponse getByMain(@RequestParam("writtingId") int idWritting) {
		WrittingResponse wrresponse = new WrittingResponse();
		Writting w = WrittingService.getWrittingById(idWritting);
		wrresponse.setWritting(WrittingService.getWrittingsByMainWritting(w));
		return wrresponse;
	}

	/**
	 * @author Mildred Guerra Generate pdf
	 * @throws Exception
	 */
	@RequestMapping(value = "/generatePDF", method = RequestMethod.POST)
	public WrittingResponse generatePDF(@RequestParam("writtingId") int idWritting) throws Exception {
		Writting w = WrittingService.getWrittingById(idWritting);
		GeneratePDFService pdfService = new GeneratePDFService();
		WrittingResponse wrresponse = new WrittingResponse();
		wrresponse.setName(pdfService.pdf(w, servletContext));
		return wrresponse;
	}

	/**
	 * @author Mario Villalobos
	 * @param idWritting
	 * @return WrittingResponse
	 */
	@RequestMapping(value = "/getContentLastWrittingByMain", method = RequestMethod.POST)
	public WrittingResponse getContentLastWrittingByMain(@RequestBody WrittingRequest ur) {
		WrittingResponse wrresponse = new WrittingResponse();
		WrittingPOJO w = WrittingService.getWrittingByName(ur);
		WrittingPOJO wrpojo = WrittingService.getContentLastWrittingByMainWritting(w);
		wrresponse.setContent(wrpojo.getContent());
		wrresponse.setParticipation(wrpojo.isParticipation());
		return wrresponse;
	}

	@RequestMapping(value = "/getOwner", method = RequestMethod.POST)
	public UsersResponse getOwner(@RequestBody WrittingRequest ur) {
		UsersResponse response = new UsersResponse();
		Writting wr = new Writting();
		BeanUtils.copyProperties(ur.getWritting(), wr);
		response.setCode(200);
		response.setCodeMessage("obtiene bien los writtings");
		Boolean isOwner = WrittingService.getOwner(ur.getSearchTerm(), wr);
		response.setIsOwner(isOwner);
		return response;
	}

	@RequestMapping(value = "/getWrittingInviContent", method = RequestMethod.POST)
	public WrittingResponse getWrittingInviContent(@RequestBody WrittingRequest ur) {
		WrittingResponse wrresponse = new WrittingResponse();
		WrittingPOJO wr = WrittingService.getWrittingByName(ur);
		WrittingPOJO wrpojo = WrittingService.getWrittingInviContent(wr);
		wrresponse.setContent(wrpojo.getContent());
		return wrresponse;
	}

	@RequestMapping(value = "/getWrittingOne", method = RequestMethod.POST)
	public WrittingResponse getWrittingOne(@RequestParam("writtingId") int idWritting) {
		WrittingResponse wrresponse = new WrittingResponse();
		Writting wr = WrittingService.getWrittingById(idWritting);
		wrresponse.setContent(wr.getContent());
		wrresponse.setName(wr.getName());
		wrresponse.setImage(wr.getImage());
		wrresponse.setDescription(wr.getDescription());
		wrresponse.setCategory(wr.getCategory());
		return wrresponse;
	}

}