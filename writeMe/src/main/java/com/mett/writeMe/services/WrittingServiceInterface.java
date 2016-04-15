package com.mett.writeMe.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.mett.writeMe.contracts.WrittingRequest;
import com.mett.writeMe.ejb.Report;
import com.mett.writeMe.ejb.User;
import com.mett.writeMe.ejb.Writting;
import com.mett.writeMe.pojo.UserHasWrittingPOJO;
import com.mett.writeMe.pojo.UserPOJO;
import com.mett.writeMe.pojo.WrittingPOJO;

/**
 * @author Sheng hsuen
 *
 */
public interface WrittingServiceInterface {
	List<WrittingPOJO> getAll(WrittingRequest ur);
	List<WrittingPOJO> getAllByName(WrittingRequest ur);

	List<WrittingPOJO> getAll();
	List<WrittingPOJO> getPublished(WrittingRequest ur);
	List<UserPOJO> getOwnersPublished(WrittingRequest ur);
	List<UserPOJO> getUsersPublished();
	List<WrittingPOJO> getWrittingsByMainWritting(Writting wr);
	WrittingPOJO getWrittingByName(WrittingRequest ur);
	String getWrittingContent(WrittingRequest ur);
	void deletewritting(int writtingId);
	Boolean saveWritting(WrittingRequest ur);
	Boolean editWritting(Writting wr);
	Boolean publish(WrittingRequest ur);
	Boolean createWrittingInvitation(Writting wr);
	Writting getWrittingById(int idWritting);
	Boolean editWrittingInvitation(Writting wr, HttpSession currentSession);
	List<WrittingPOJO> getWrittingsInvitationByUser(WrittingRequest ur);
	List<WrittingPOJO> getWrittingsConfirmationByUser(String searchTerm);
	List<WrittingPOJO> getWrittingsAcceptedByUser(WrittingRequest ur);
	List<UserPOJO> getUsersConfirmationByUser(String searchTerm);
	
	List<String> getUsersInvited(WrittingRequest ur, String s);
	WrittingPOJO getContentLastWrittingByMainWritting(WrittingPOJO wr);
	Boolean finishWritting(Writting wr);
	Boolean getOwner(String userTerm, Writting w);
	WrittingPOJO getWrittingInviContent(WrittingPOJO wr);
	List<WrittingPOJO> getAllWithoutNameNull();
	Boolean outWritting(Writting wr);
	Boolean getOwnerInvitation(String userTerm, Writting w);
	
}
