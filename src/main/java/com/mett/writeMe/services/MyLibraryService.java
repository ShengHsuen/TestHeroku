package com.mett.writeMe.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mett.writeMe.contracts.MyLibraryRequest;
import com.mett.writeMe.ejb.Mylibrary;
import com.mett.writeMe.pojo.MyLibraryPOJO;
import com.mett.writeMe.repositories.MyLibraryRepository;

/**
 * @author Sheng hsuen
 *
 */
@Service
public class MyLibraryService implements MyLibraryServiceInterface{
	@Autowired 
	private MyLibraryRepository myLibraryRepository;	

	/* (non-Javadoc)
	 * @see com.mett.writeMe.services.MyLibraryServiceInterface#getAll(com.mett.writeMe.contracts.MyLibraryRequest)
	 */
	@Override
	@Transactional
	public List<MyLibraryPOJO> getAll(MyLibraryRequest ur) {
		List<Mylibrary> MyLibraries =  myLibraryRepository.findAll();
		return generateLibraryDtos(MyLibraries);
	}
	
	/* (non-Javadoc)
	 * @see com.mett.writeMe.services.MyLibraryServiceInterface#getAllByTitle(com.mett.writeMe.contracts.MyLibraryRequest)
	 */
	@Override
	@Transactional
	public List<MyLibraryPOJO> getAllByTitle(MyLibraryRequest ur) {
		List<Mylibrary> MyLibraries =  myLibraryRepository.findByTitleContaining(ur.getSearchTerm());
		return generateLibraryDtos(MyLibraries);
	}
	
	/**
	 * @param MyLibraries
	 * @return
	 */
	private List<MyLibraryPOJO> generateLibraryDtos(List<Mylibrary> MyLibraries){
		List<MyLibraryPOJO> uiMyLibraries = new ArrayList<MyLibraryPOJO>();
		MyLibraries.stream().forEach(u -> {
			MyLibraryPOJO dto = new MyLibraryPOJO();
			BeanUtils.copyProperties(u,dto);
			uiMyLibraries.add(dto);
		});	
		return uiMyLibraries;
	}
	
	/* (non-Javadoc)
	 * @see com.mett.writeMe.services.MyLibraryServiceInterface#saveLibrary(com.mett.writeMe.contracts.MyLibraryRequest)
	 */
	@Override
	@Transactional
	public Boolean saveLibrary(MyLibraryRequest ur) {
		Mylibrary library = new Mylibrary();
		BeanUtils.copyProperties(ur.getLibrary(), library);

		Mylibrary nLibrary = myLibraryRepository.save(library);
		
		return (nLibrary == null) ? false : true;
	}
}
