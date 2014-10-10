package com.wolfbeisz.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the DOCUMENTS database table.
 * 
 */
@Entity
@Table(name="DOCUMENTS")
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long documentid;

	private Timestamp createdon;

	private String title;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="document")
	private List<Comment> comments;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="CREATOR")
	private User user;

	//bi-directional many-to-one association to File
	@OneToMany(mappedBy="document")
	private List<File> files;

	//bi-directional many-to-one association to Tag
	@OneToMany(mappedBy="document")
	private List<Tag> tags;

	public Document() {
	}

	public long getDocumentid() {
		return this.documentid;
	}

	public void setDocumentid(long documentid) {
		this.documentid = documentid;
	}

	public Timestamp getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setDocument(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setDocument(null);

		return comment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<File> getFiles() {
		return this.files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public File addFile(File file) {
		getFiles().add(file);
		file.setDocument(this);

		return file;
	}

	public File removeFile(File file) {
		getFiles().remove(file);
		file.setDocument(null);

		return file;
	}

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Tag addTag(Tag tag) {
		getTags().add(tag);
		tag.setDocument(this);

		return tag;
	}

	public Tag removeTag(Tag tag) {
		getTags().remove(tag);
		tag.setDocument(null);

		return tag;
	}

}