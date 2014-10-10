package com.wolfbeisz.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CHANGES database table.
 * 
 */
@Entity
@Table(name="CHANGES")
@NamedQuery(name="Change.findAll", query="SELECT c FROM Change c")
public class Change implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long changeid;

	private Timestamp checkinstamp;

	private Timestamp checkoutstamp;

	//bi-directional many-to-one association to File
	@ManyToOne
	@JoinColumn(name="FILEID")
	private File file;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USERID")
	private User user;

	public Change() {
	}

	public long getChangeid() {
		return this.changeid;
	}

	public void setChangeid(long changeid) {
		this.changeid = changeid;
	}

	public Timestamp getCheckinstamp() {
		return this.checkinstamp;
	}

	public void setCheckinstamp(Timestamp checkinstamp) {
		this.checkinstamp = checkinstamp;
	}

	public Timestamp getCheckoutstamp() {
		return this.checkoutstamp;
	}

	public void setCheckoutstamp(Timestamp checkoutstamp) {
		this.checkoutstamp = checkoutstamp;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}