package com.mett.writeMe.contracts;

import java.util.Date;

import com.mett.writeMe.pojo.UserPOJO;
import com.mett.writeMe.pojo.WrittingPOJO;

/**
 * @author Sheng hsuen
 *
 */
public class WrittingRequest extends BaseRequest {
	
	private WrittingPOJO writting;
	private UserPOJO user;
	private Date date;
	
	
	
	public UserPOJO getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setUser(UserPOJO user) {
		this.user = user;
	}

	public WrittingRequest() {
		super();
	}
	
	public WrittingPOJO getWritting() {
		return writting;
	}
	
	public void setWritting(WrittingPOJO writting) {
		this.writting = writting;
	}

	@Override
	public String toString() {
		return "WrittingRequest [writting=" + writting + "]";
	}
}
