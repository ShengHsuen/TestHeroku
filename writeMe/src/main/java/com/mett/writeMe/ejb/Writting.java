package com.mett.writeMe.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the writting database table.
 * 
 */
@Entity
@NamedQuery(name="Writting.findAll", query="SELECT w FROM Writting w")
public class Writting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int writtingId;

	@Lob
	private String cantUsers;

	private String category;

	@Lob
	private String content;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String description;

	private String image;

	@Lob
	private String likes;

	@Temporal(TemporalType.DATE)
	private Date limitTime;

	private int mainWritting;

	private String name;

	@Lob
	private String numMaxCharacters;

	@Lob
	private String numMinCharacters;

	private boolean participation;

	private boolean published;

	private String typeWritting;

	//bi-directional many-to-one association to Chapter
	@OneToMany(mappedBy="writting")
	private List<Chapter> chapters;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="writting")
	private List<Report> reports;

	//bi-directional many-to-one association to UserHasWritting
	@OneToMany(mappedBy="writting")
	private List<UserHasWritting> userHasWrittings;

	//bi-directional many-to-many association to Mylibrary
	@ManyToMany
	@JoinTable(
		name="mylibrary_has_writting"
		, joinColumns={
			@JoinColumn(name="writting_writtingId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="myLibrary_myLibraryId")
			}
		)
	private List<Mylibrary> mylibraries;

	//bi-directional many-to-one association to Pagination
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="paginationId")
	private Pagination pagination;

	//bi-directional many-to-one association to Record
	@ManyToOne(fetch=FetchType.LAZY)
	private Record record;

	//bi-directional many-to-one association to Restriction
	@ManyToOne(fetch=FetchType.LAZY)
	private Restriction restriction;

	//bi-directional many-to-one association to Writting
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="writting_father")
	private Writting writting;

	//bi-directional many-to-one association to Writting
	@OneToMany(mappedBy="writting")
	private List<Writting> writtings;

	public Writting() {
	}

	public int getWrittingId() {
		return this.writtingId;
	}

	public void setWrittingId(int writtingId) {
		this.writtingId = writtingId;
	}

	public String getCantUsers() {
		return this.cantUsers;
	}

	public void setCantUsers(String cantUsers) {
		this.cantUsers = cantUsers;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLikes() {
		return this.likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public Date getLimitTime() {
		return this.limitTime;
	}

	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}

	public int getMainWritting() {
		return this.mainWritting;
	}

	public void setMainWritting(int mainWritting) {
		this.mainWritting = mainWritting;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumMaxCharacters() {
		return this.numMaxCharacters;
	}

	public void setNumMaxCharacters(String numMaxCharacters) {
		this.numMaxCharacters = numMaxCharacters;
	}

	public String getNumMinCharacters() {
		return this.numMinCharacters;
	}

	public void setNumMinCharacters(String numMinCharacters) {
		this.numMinCharacters = numMinCharacters;
	}

	public boolean getParticipation() {
		return this.participation;
	}

	public void setParticipation(boolean participation) {
		this.participation = participation;
	}

	public boolean getPublished() {
		return this.published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getTypeWritting() {
		return this.typeWritting;
	}

	public void setTypeWritting(String typeWritting) {
		this.typeWritting = typeWritting;
	}

	public List<Chapter> getChapters() {
		return this.chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public Chapter addChapter(Chapter chapter) {
		getChapters().add(chapter);
		chapter.setWritting(this);

		return chapter;
	}

	public Chapter removeChapter(Chapter chapter) {
		getChapters().remove(chapter);
		chapter.setWritting(null);

		return chapter;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setWritting(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setWritting(null);

		return report;
	}

	public List<UserHasWritting> getUserHasWrittings() {
		return this.userHasWrittings;
	}

	public void setUserHasWrittings(List<UserHasWritting> userHasWrittings) {
		this.userHasWrittings = userHasWrittings;
	}

	public UserHasWritting addUserHasWritting(UserHasWritting userHasWritting) {
		getUserHasWrittings().add(userHasWritting);
		userHasWritting.setWritting(this);

		return userHasWritting;
	}

	public UserHasWritting removeUserHasWritting(UserHasWritting userHasWritting) {
		getUserHasWrittings().remove(userHasWritting);
		userHasWritting.setWritting(null);

		return userHasWritting;
	}

	public List<Mylibrary> getMylibraries() {
		return this.mylibraries;
	}

	public void setMylibraries(List<Mylibrary> mylibraries) {
		this.mylibraries = mylibraries;
	}

	public Pagination getPagination() {
		return this.pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Record getRecord() {
		return this.record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public Restriction getRestriction() {
		return this.restriction;
	}

	public void setRestriction(Restriction restriction) {
		this.restriction = restriction;
	}

	public Writting getWritting() {
		return this.writting;
	}

	public void setWritting(Writting writting) {
		this.writting = writting;
	}

	public List<Writting> getWrittings() {
		return this.writtings;
	}

	public void setWrittings(List<Writting> writtings) {
		this.writtings = writtings;
	}

	public Writting addWritting(Writting writting) {
		getWrittings().add(writting);
		writting.setWritting(this);

		return writting;
	}

	public Writting removeWritting(Writting writting) {
		getWrittings().remove(writting);
		writting.setWritting(null);

		return writting;
	}

}