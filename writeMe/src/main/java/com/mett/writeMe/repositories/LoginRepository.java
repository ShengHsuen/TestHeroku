package com.mett.writeMe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mett.writeMe.ejb.User;

public interface LoginRepository extends CrudRepository<User,Integer> {
	
	public static final int PAGE_SIZE = 5;
	
	User findByMailAndPassword(String mail, String password);
}
