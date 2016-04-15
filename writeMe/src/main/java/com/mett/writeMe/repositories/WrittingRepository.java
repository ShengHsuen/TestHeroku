package com.mett.writeMe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mett.writeMe.ejb.Writting;

public interface WrittingRepository extends CrudRepository<Writting,Integer>{
	List<Writting> findAll();
	List<Writting> findByNameContaining(String name);
	Writting save(Writting Writting);
	List<Writting> findByPublishedTrueAndNameNotNullOrderByWrittingIdDesc();
	List<Writting> findAllByNameNotNull();
	List<Writting> findAllByPublishedTrueAndNameNotNull();
}
