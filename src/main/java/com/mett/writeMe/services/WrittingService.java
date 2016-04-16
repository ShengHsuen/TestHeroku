package com.mett.writeMe.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mett.writeMe.contracts.WrittingRequest;
import com.mett.writeMe.ejb.User;
import com.mett.writeMe.ejb.UserHasWritting;
import com.mett.writeMe.ejb.Writting;
import com.mett.writeMe.pojo.UserHasWrittingPOJO;
import com.mett.writeMe.pojo.UserPOJO;
import com.mett.writeMe.pojo.WrittingPOJO;
import com.mett.writeMe.repositories.UserHasWrittingRepository;
import com.mett.writeMe.repositories.UserRepository;
import com.mett.writeMe.repositories.WrittingRepository;

/**
 * @author Sheng hsuen
 *
 */
@Service
public class WrittingService implements WrittingServiceInterface{
	
	@Autowired 
	private WrittingRepository writtingRepository;
	@Autowired 
	private UserHasWrittingRepository userHasWrittingRepository; 
	@Autowired 
	private UserRepository userRepository;

	/* (non-Javadoc)
	 * @see com.mett.writeMe.services.WrittingServiceInterface#getAll(com.mett.writeMe.contracts.WrittingRequest)
	 */
	@Override
	@Transactional
	public List<WrittingPOJO> getAll(WrittingRequest ur) {
		List<Writting> Writtings = writtingRepository.findAll();
		return generateWrittingDtos(Writtings);
	}
	
	
	/**author Dani, Sheng
	 * @return
	 */
	@Override
	@Transactional
	public List<WrittingPOJO> getAllWithoutNameNull(){
		List<Writting> Writtings = writtingRepository.findAllByNameNotNull();
		List<WrittingPOJO> dtos = new ArrayList<WrittingPOJO>();
		Writtings.stream().forEach(tu ->{
			WrittingPOJO dto = new WrittingPOJO();
			BeanUtils.copyProperties(tu, dto);
			if( tu.getWritting()!= null){

				dto.setWrittingFather(tu.getWritting().getWrittingId());
			}
			dtos.add(dto);
		});
		return dtos;
	}

	/* @author Mildred Guerra
	 * Get the list of all writtings
	 * Return a List<WrittingPOJO>  dtos 
	 */
	@Override
	@Transactional
	public List<WrittingPOJO> getAll() {
		List<Writting> wirttings = writtingRepository.findAll();
		List<WrittingPOJO> dtos = new ArrayList<WrittingPOJO>();
		wirttings.stream().forEach(tu ->{
			WrittingPOJO dto = new WrittingPOJO();
			BeanUtils.copyProperties(tu, dto);
			if( tu.getWritting()!= null){

				dto.setWrittingFather(tu.getWritting().getWrittingId());
			}
			dtos.add(dto);
		});
		return dtos;
	}

	/* (non-Javadoc)
	 * @see com.mett.writeMe.services.WrittingServiceInterface#getAllByName(com.mett.writeMe.contracts.WrittingRequest)
	 */
	@Override
	@Transactional
	public List<WrittingPOJO> getAllByName(WrittingRequest ur) {
		  List<Writting> Writtings =  writtingRepository.findByNameContaining(ur.getSearchTerm());
		  return generateWrittingDtos(Writtings);
	}

	@Override
	@Transactional
	public List<WrittingPOJO> getPublished(WrittingRequest ur){
		  System.out.println("Service /getPublished");
		  List<Writting> Writtings =  writtingRepository.findByPublishedTrueAndNameNotNullOrderByWrittingIdDesc();
		  //System.out.println("Service /getPublished : " + Writtings.get(0).getUserHasWrittings().get(0).getUser().getName());
		  return generateWrittingDtos(Writtings);
	}
	
	@Override
	@Transactional
	public List<UserPOJO> getOwnersPublished(WrittingRequest ur){
		  System.out.println("Service /getPublished");
		  List<User> us = new ArrayList<User>();
		  List<Writting> Writtings =  writtingRepository.findByPublishedTrueAndNameNotNullOrderByWrittingIdDesc();
		  /*List<UserHasWritting> uhw = new ArrayList<UserHasWritting>();
		  userHasWrittingRepository.findAll();*/
		  //System.out.println("Service /getPublished : " + Writtings.get(0).getUserHasWrittings().get(0).getUser().getName());
		  for(int i=0;i<Writtings.size();i++){
			  //for(int j=0; )
			  us.add(Writtings.get(i).getUserHasWrittings().get(0).getUser());
		  }
		  return generateUserDtos(us);
	}
	
	/* author Sheng Hsuen
	 * @see com.mett.writeMe.services.WrittingServiceInterface#getWrittingsByMainWritting(com.mett.writeMe.ejb.Writting)
	 */
	@Override
	@Transactional
	public List<WrittingPOJO> getWrittingsByMainWritting(Writting wr){
		List<WrittingPOJO> WrittingPOJO = new ArrayList<WrittingPOJO>();
		List<Writting> Writting = writtingRepository.findByNameContaining(wr.getName());
		List<Writting> Writtings = writtingRepository.findAll();
		
		WrittingPOJO dto = new WrittingPOJO();
		BeanUtils.copyProperties(Writting.get(0), dto);
		System.out.println("ESTE ES LA OBRA PROPIETARIO"+Writting.get(0).getName());
		WrittingPOJO.add(dto);
		
		for(int i=0; i <= Writtings.size()-1; i++){
			System.out.println("ID DE LA OBRA SELECCIONADA"+ Writting.get(0).getWrittingId());
			System.out.println("LISTAD E LOS HIJOS"+ Writtings.get(i).getMainWritting());
			if(Writtings.get(i).getMainWritting() == Writting.get(0).getWrittingId()){
				BeanUtils.copyProperties(Writtings.get(i), dto);
				WrittingPOJO.add(dto);
				System.out.println("ESTOS SON LOS HIJOS DE UNA OBRA"+ Writtings.get(i).getWrittingId());
			}
		}
		return WrittingPOJO;
	}
	
	// Comentado
	public List<UserPOJO> getUsersPublished(){
		List<UserPOJO> Users = new ArrayList<UserPOJO>();
		/*List<Writting> Writtings =  writtingRepository.findByPublishedTrue();
		List<UserHasWritting> UserHasWrittings = userHasWrittingRepository.findAll();
		System.out.println("Size: "+ Writtings.size());
		int j = 0;
		for(int i=0;i<=UserHasWrittings.size()-1;i++){
			if(Writtings.get(j).getWrittingId() == UserHasWrittings.get(i).getWritting().getWrittingId()){
				j++;
				System.out.println("j "+j);
				UserPOJO dto = new UserPOJO();
				BeanUtils.copyProperties(UserHasWrittings.get(i).getUser(),dto);
				Users.add(dto);
			}else{
				
			}
		}*/
		  return Users;
	}
	
	/* (non-Javadoc)
	 * @see com.mett.writeMe.services.WrittingServiceInterface#getWrittingByName(com.mett.writeMe.contracts.WrittingRequest)
	 */
	@Override
	@Transactional
	public WrittingPOJO getWrittingByName(WrittingRequest ur) {
		  List<Writting> Writtings =  writtingRepository.findByNameContaining(ur.getSearchTerm());
		  System.out.println("La obra: "+ Writtings.get(0));
		  return generateWrittingDtos(Writtings).get(0);
	}


	
	  @Override
	  @Transactional 
	  public String getWrittingContent(WrittingRequest ur) {
      String content ="";
	  Writting writting = writtingRepository.findOne(Integer.parseInt(ur.getSearchTerm()));
	  List<Writting> wri = writtingRepository.findAll();
      int j=0;
      for (int i=0; i<= wri.size() -1; i++){
    	  if(writting.getMainWritting() == wri.get(i).getMainWritting()){
    		  j++;
    		//  content = content + wri.get(i).getContent() ;
    		  content =  wri.get(i).getContent() ;
    		 // System.out.println("Aqui la obra" + content + "\n");
    	  }else{
    		  
    	  }
      }
      return content;
     }


	/**
	 * @param Writtings
	 * @return
	 */
	private List<WrittingPOJO> generateWrittingDtos(List<Writting> Writtings){
		List<WrittingPOJO> uiWrittings = new ArrayList<WrittingPOJO>();
		Writtings.stream().forEach(u -> {
			WrittingPOJO dto = new WrittingPOJO();
			BeanUtils.copyProperties(u,dto);
			uiWrittings.add(dto);
		});	
		return uiWrittings;
	}
	
	/* (non-Javadoc)
	 * @see com.mett.writeMe.services.WrittingServiceInterface#saveWritting(com.mett.writeMe.contracts.WrittingRequest)
	 */
	@Override
	@Transactional
	public Boolean saveWritting(WrittingRequest ur) {
		Writting writting = new Writting();
		UserHasWritting UHW = new UserHasWritting();
		
		BeanUtils.copyProperties(ur.getWritting(), writting);
		
		if(writting.getTypeWritting().equals("Pública")){
			writting.setPublished(true);
		}

		Writting nWritting = writtingRepository.save(writting);
		
		return (nWritting == null) ? false : true;
	}
	
	/* @author Sheng Hsuen
	 * @see com.mett.writeMe.services.WrittingServiceInterface#editWritting(com.mett.writeMe.ejb.Writting)
	 */
	@Override
	@Transactional
	public Boolean editWritting(Writting wr) {
		List<WrittingPOJO> WrittingPOJO = new ArrayList<WrittingPOJO>();
		Writting writting = new Writting();
		int index = 0;
		int edit = 0;
		WrittingPOJO = getWrittingsByMainWritting(wr);
		index = WrittingPOJO.size()-1;
		edit = WrittingPOJO.get(index).getWrittingId();
		
		System.out.println("ESTE ES EL ID DEL WRITTING QUE SE EDITA" + edit);
		writting = getWrittingById(edit);
		writting.setContent(wr.getContent());
		
		Writting nwritting = writtingRepository.save(writting);

		return (nwritting == null) ? false : true;
	}
	
	/* @Sheng Hsuen
	 * @see com.mett.writeMe.services.WrittingServiceInterface#finishWritting(com.mett.writeMe.ejb.Writting)
	 */
	@Override
	@Transactional
	public Boolean finishWritting(Writting wr) {
		List<WrittingPOJO> WrittingPOJO = new ArrayList<WrittingPOJO>();
		Writting writting = new Writting();
		int index = 0;
		int edit = 0;
		WrittingPOJO = getWrittingsByMainWritting(wr);
		index = WrittingPOJO.size()-1;
		edit = WrittingPOJO.get(index).getWrittingId();
		
		writting = getWrittingById(edit);
		writting.setContent(wr.getContent());
		writting.setParticipation(false);
		System.out.println("GUARDA Y CAMBIA PARTICIPATION A FALSE" + writting.getParticipation());
		
		Writting nwritting = writtingRepository.save(writting);

		return (nwritting == null) ? false : true;
		
	}
	
	
	/* author Sheng Hsuen
	 * @see com.mett.writeMe.services.WrittingServiceInterface#outWritting(com.mett.writeMe.ejb.Writting)
	 */
	@Override
	@Transactional
	public Boolean outWritting(Writting wr) {
		List<WrittingPOJO> WrittingPOJO = new ArrayList<WrittingPOJO>();
		Writting writting = new Writting();
		int index = 0;
		int edit = 0;
		WrittingPOJO = getWrittingsByMainWritting(wr);
		index = WrittingPOJO.size()-1;
		edit = WrittingPOJO.get(index).getWrittingId();
		
		writting = getWrittingById(edit);
		writting.setContent("");
		writting.setParticipation(false);
		System.out.println("GUARDA Y CAMBIA PARTICIPATION A FALSE" + writting.getParticipation());
		
		Writting nwritting = writtingRepository.save(writting);

		return (nwritting == null) ? false : true;
		
	}
	
	@Override
	@Transactional
	public Boolean publish(WrittingRequest ur){
		List<Writting> Writtings =  writtingRepository.findByNameContaining(ur.getSearchTerm());
		Writtings.get(0).setPublished(true);
		Writtings.get(0).setContent(ur.getWritting().getContent());
		Writtings.get(0).setDate(ur.getWritting().getDate());
		Writting nwritting = writtingRepository.save(Writtings.get(0));
		return (nwritting == null) ? false : true;
	}

	
	/* @author Mildred Guerra
	 * Delete a writting
	 * @param int writtingId
	 * @see com.mett.writeMe.services.WrittingServiceInterface#editWritting(com.mett.writeMe.ejb.Writting)
	 */
	@Override
	public void deletewritting(int writtingId) {
		writtingRepository.delete(writtingId);
	}

	/* @author Sheng Hsuen Cheng
	 * @see com.mett.writeMe.services.WrittingServiceInterface#editWrittingInvitation(com.mett.writeMe.contracts.WrittingRequest)
	 */
	@Override
	@Transactional
	public Boolean createWrittingInvitation(Writting wr) {
		List<WrittingPOJO> wrPojos = getWrittingsByMainWritting(wr);
		List<Writting> writtings = new ArrayList<Writting>();
		Writting writtingFather = new Writting();
		BeanUtils.copyProperties(wrPojos, writtings);
		int idFather = 0;
		int getIdFather = 0;
	
		List<UserHasWritting> UserHasWrittings = userHasWrittingRepository.findAll();
		for(int i=0;i<=UserHasWrittings.size()-1;i++){
			if(wr.getWrittingId() == UserHasWrittings.get(i).getWritting().getWrittingId()){
				
				wr.setWrittingId(0);
				wr.setName(null);
				wr.setMainWritting(UserHasWrittings.get(i).getWritting().getWrittingId());
				wr.setParticipation(true);
				System.out.println("ID PARA EL MAIN WRITTING "+UserHasWrittings.get(i).getWritting().getWrittingId());
				
				
				idFather = wrPojos.size()-1;
				getIdFather = wrPojos.get(idFather).getWrittingId();
				System.out.println("ESTE ES EL ID DEL PADRE"+idFather);
				writtingFather = writtingRepository.findOne(getIdFather);
				
				wr.setWritting(writtingFather);
				System.out.println("ID DEL PADRE ES:  "+writtingFather.getWrittingId());
				
			}
		}
		Writting nWritting = writtingRepository.save(wr);

		
		return (nWritting == null) ? false : true;
	}
	
	@Override
	@Transactional
	public Writting getWrittingById(int idWritting) {
		return writtingRepository.findOne(idWritting);
	}
	
	@Override
	@Transactional
	public List<WrittingPOJO> getWrittingsInvitationByUser(WrittingRequest ur) {
		List<User> user = userRepository.findByAuthorContaining(ur.getSearchTerm()); //Siempre sera un usuario el que recibe
		List<Writting> wr = new ArrayList<Writting>();
		List<UserHasWritting> uhw = userHasWrittingRepository.findAll();
		for(int i=0;i<=uhw.size()-1;i++){
			if(uhw.get(i).getUser().getAuthor().equals(user.get(0).getAuthor()) && uhw.get(i).getInvitationStatus() == false && uhw.get(i).getOwner() == false){
				wr.add(writtingRepository.findByNameContaining(uhw.get(i).getWritting().getName()).get(0));
				//System.out.println("WRITTINGS INVITATION BY USER: "+ wr.get(i).getName());
			}
		}
		return generateWrittingDtos(wr);
	}
	
	@Override
	@Transactional
	public List<WrittingPOJO> getWrittingsAcceptedByUser(WrittingRequest ur) {
		List<User> user = userRepository.findByAuthorContaining(ur.getSearchTerm()); //Siempre sera un usuario el que recibe
		List<Writting> wr = new ArrayList<Writting>();
		List<UserHasWritting> uhw = userHasWrittingRepository.findAll();
		for(int i=0;i<=uhw.size()-1;i++){
			if(uhw.get(i).getUser().getAuthor().equals(user.get(0).getAuthor()) && uhw.get(i).getInvitationStatus() == true && uhw.get(i).getOwner() == false){
				wr.add(writtingRepository.findByNameContaining(uhw.get(i).getWritting().getName()).get(0));
				//System.out.println("WRITTINGS INVITATION BY USER: "+ wr.get(i).getName());
			}
		}
		return generateWrittingDtos(wr);
	}
	
	@Override
	@Transactional
	public List<WrittingPOJO> getWrittingsConfirmationByUser(String searchTerm) {
		List<User> user = userRepository.findByAuthorContaining(searchTerm); //Siempre sera un usuario el que recibe
		List<Writting> wr = new ArrayList<Writting>();
		List<UserHasWritting> uhw = new ArrayList<UserHasWritting>();
		uhw.addAll(userHasWrittingRepository.findAllByIdOwnerAndConfirmationTrue(user.get(0).getUserId()));
		for(int i=0;i<=uhw.size()-1;i++){
			wr.add(uhw.get(i).getWritting());
			System.out.println("******WRITTING*****: "+uhw.get(i).getWritting().getName());
			System.out.println("******USER*********: "+uhw.get(i).getUser().getAuthor());
			System.out.println("******CONFIRMATION*: "+uhw.get(i).getConfirmation());
		}
		return generateWrittingDtos(wr);
	}
	
	@Override
	@Transactional
	public List<UserPOJO> getUsersConfirmationByUser(String searchTerm) {
		List<User> user = userRepository.findByAuthorContaining(searchTerm); //Siempre sera un usuario el que recibe
		List<User> us = new ArrayList<User>();
		List<UserHasWritting> uhw = new ArrayList<UserHasWritting>();
		uhw.addAll(userHasWrittingRepository.findAllByIdOwnerAndConfirmationTrue(user.get(0).getUserId()));
		for(int i=0;i<=uhw.size()-1;i++){
			us.add(uhw.get(i).getUser());
			System.out.println("******WRITTING*****: "+uhw.get(i).getWritting().getName());
			System.out.println("******USER*********: "+uhw.get(i).getUser().getAuthor());
			System.out.println("******CONFIRMATION*: "+uhw.get(i).getConfirmation());
		}
		return generateUserDtos(us);
	}
	
	private List<UserPOJO> generateUserDtos(List<User> users){
		List<UserPOJO> uiUsers = new ArrayList<UserPOJO>();
		users.stream().forEach(u -> {
			UserPOJO dto = new UserPOJO();
			BeanUtils.copyProperties(u,dto);
			dto.setPassword("");
			uiUsers.add(dto);
		});	
		return uiUsers;
	}
	
	
	/**
	 * @author Mario Villalobos
	 * Get the content of the last writting 
	 * @return a String content 
	 */
	@Override
	public WrittingPOJO getContentLastWrittingByMainWritting(WrittingPOJO ws){
		List<WrittingPOJO> WrittingPOJO = new ArrayList<WrittingPOJO>();
		List<Writting> Writting = writtingRepository.findByNameContaining(ws.getName());
		List<Writting> Writtings = writtingRepository.findAll();
		WrittingPOJO dto = new WrittingPOJO();
	    BeanUtils.copyProperties(Writting.get(0), dto);
		WrittingPOJO.add(dto);
		
		for(int i=0; i <= Writtings.size()-1; i++){
			System.out.println("BUSCANDO ERROR 1 " + Writtings.get(i).getMainWritting());
			System.out.println("BUSCANDO ERROR 2 " + Writting.get(0).getWrittingId());
			System.out.println("BUSCANDO ERROR 3 " + Writtings.get(i).getContent());
				if(Writtings.get(i).getMainWritting() == Writting.get(0).getWrittingId() && !Writtings.get(i).getContent().equals("")){
					BeanUtils.copyProperties(Writtings.get(i), dto);
						WrittingPOJO.add(dto);
						System.out.println("AQUI VA " + dto.getContent());
				}	
		}
		String content;
		WrittingPOJO wrpojo = new WrittingPOJO();
		wrpojo = WrittingPOJO.get(WrittingPOJO.size()-1);
		return wrpojo;
	}
	
	@Override
	@Transactional
	public Boolean getOwner(String userTerm, Writting w) {
		List<User> us = new ArrayList<User>();
		us = userRepository.findByAuthorContaining(userTerm);
		User u = new User();
		u = us.get(0);
		System.out.println("wwwwwww "+us.get(0).getUserId() + "qqqqqqq " +w.getName());
		UserHasWritting uhw = new UserHasWritting();
		Boolean resul;
		uhw = userHasWrittingRepository.findUserHasWrittingByWrittingWrittingIdAndUserUserIdAndOwnerTrue(w.getWrittingId(),u.getUserId()); //Siempre sera un usuario el que recibe
		
		try{
			if(uhw != null){
				System.out.println("ESTE ME TRAE EL OWNER " + uhw.getUser_has_writtingId());
				resul = true;
				
			}else{
				resul = false;
			}
		}catch(Exception e){
			resul = false;
		}
		return resul;
	}
	
	
	@Override
	@Transactional
	public Boolean getOwnerInvitation(String userTerm, Writting w) {
		List<User> us = new ArrayList<User>();
		us = userRepository.findByAuthorContaining(userTerm);
		User u = new User();
		u = us.get(0);
		System.out.println("wwwwwww "+us.get(0).getUserId() + "qqqqqqq " +w.getName());
		UserHasWritting uhw = new UserHasWritting();
		Boolean resul;
		uhw = userHasWrittingRepository.findUserHasWrittingByWrittingWrittingIdAndUserUserIdAndOwnerTrue(w.getWrittingId(),u.getUserId()); //Siempre sera un usuario el que recibe
		
		try{
			if(uhw != null){
				System.out.println("ESTE ME TRAE EL OWNER " + uhw.getUser_has_writtingId());
				resul = true;
				
			}else{
				resul = false;
			}
		}catch(Exception e){
			resul = false;
		}
		return resul;
	}


	@Override
	public Boolean editWrittingInvitation(Writting wr, HttpSession currentSession) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getUsersInvited(WrittingRequest ur, String s) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public WrittingPOJO getWrittingInviContent(WrittingPOJO wr){
		List<WrittingPOJO> WrittingPOJO = new ArrayList<WrittingPOJO>();
		List<Writting> Writting = writtingRepository.findByNameContaining(wr.getName());
		List<Writting> Writtings = writtingRepository.findAll();
		WrittingPOJO wrpojo = new WrittingPOJO();
		WrittingPOJO dto = new WrittingPOJO();
		BeanUtils.copyProperties(Writting.get(0), dto);

		WrittingPOJO.add(dto);
		String content= "";
		for(int i=0; i <= Writtings.size()-1; i++){
			if(Writtings.get(i).getMainWritting() == Writting.get(0).getWrittingId()){
				BeanUtils.copyProperties(Writtings.get(i), dto);
				WrittingPOJO.add(dto);
				content = content + Writtings.get(i).getContent() + " <br> ";
			}
		}
		wrpojo.setContent(content);
		return wrpojo;
	}

	
	
}
