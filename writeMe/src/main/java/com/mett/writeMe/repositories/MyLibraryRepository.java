package com.mett.writeMe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mett.writeMe.ejb.Mylibrary;

public interface MyLibraryRepository extends CrudRepository<Mylibrary,Integer>  {

	List<Mylibrary> findAll();
	List<Mylibrary> findByTitleContaining(String title);
	Mylibrary save(Mylibrary library);
}
