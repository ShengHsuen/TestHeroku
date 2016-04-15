package com.mett.writeMe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mett.writeMe.ejb.User;
import com.mett.writeMe.ejb.UserHasWritting;
import com.mett.writeMe.ejb.Writting;

public interface UserHasWrittingRepository extends CrudRepository<UserHasWritting,Integer>{
	UserHasWritting save(UserHasWritting userHasWritting);
	List<UserHasWritting> findAll();
	List<UserHasWritting> findAllByInvitationStatusTrue();
	List<UserHasWritting> findAllByInvitationStatusTrueAndWrittingTypeWritting(String type);
	UserHasWritting findUserHasWrittingByWrittingWrittingIdAndOwnerTrue(int p);
	List<UserHasWritting> findUserHasWrittingByWrittingWrittingIdAndOwnerFalse(int p);
	UserHasWritting findUserHasWrittingByWrittingWrittingIdAndUserUserIdAndOwnerTrue(int w, int u);
	List<UserHasWritting> findAllBylinkInvitation(int p);
	UserHasWritting findByUserAndWritting(User u, Writting w);
	List<UserHasWritting> findAllByUserUserIdAndWrittingNameNotNullAndInvitationStatusTrueAndWrittingTypeWritting(int id, String invitation);
	List<UserHasWritting> findAllByConfirmationTrue();
	List<UserHasWritting> findAllByWrittingWrittingIdAndConfirmationTrue(int a);
	List<UserHasWritting> findAllByIdOwnerAndConfirmationTrue(int a);
	List<UserHasWritting> findAllByUserUserIdAndOwnerFalseAndPubliccTrue(int a);
	List<UserHasWritting> findAllByUserUserIdAndOwnerTrueAndPubliccTrue(int a);
	List<UserHasWritting> findAllByUserUserIdAndPubliccTrue(int a);
	UserHasWritting findUserHasWrittingByWrittingWrittingIdAndUserUserIdAndPubliccTrue(int w, int u);
	List<UserHasWritting> findAllByIdOwnerAndPubliccTrue(int a);
	List<UserHasWritting> findAllByIdOwnerAndPubliccFalse(int a);
	List<UserHasWritting> findAllByWrittingWrittingId(int a);
	List<UserHasWritting> findUserHasWrittingByWrittingWrittingIdAndPubliccTrue(int w);
	UserHasWritting findUserHasWrittingByWrittingWrittingIdAndCanWriteTrue(int w);
	UserHasWritting findUserHasWrittingByWrittingWrittingIdAndUserUserId(int a, int b);
}
