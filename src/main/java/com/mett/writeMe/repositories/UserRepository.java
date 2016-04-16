package com.mett.writeMe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mett.writeMe.ejb.User;
import com.mett.writeMe.ejb.Writting;

public interface UserRepository extends CrudRepository<User,Integer> {
	
	User findByMailAndPassword(String mail,String password);
	List<User> findAllByOrderByAuthor();
	List<User> findAll();
	List<User> findByNameContaining(String name);
	List<User> findByAuthorContaining(String author);
	User save(User user);
	User findByMail(String mail);
}
