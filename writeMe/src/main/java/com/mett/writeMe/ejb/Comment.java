package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int commentId;

	private String comment;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Lob
	private String likes;

	//bi-directional many-to-one association to UserHasWritting
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_has_writting_user_has_writtingId")
	private UserHasWritting userHasWritting;

	public Comment() {
	}

	public int getCommentId() {
		return this.commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLikes() {
		return this.likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public UserHasWritting getUserHasWritting() {
		return this.userHasWritting;
	}

	public void setUserHasWritting(UserHasWritting userHasWritting) {
		this.userHasWritting = userHasWritting;
	}

}