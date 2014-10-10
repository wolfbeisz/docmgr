package com.wolfbeisz.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TAGS database table.
 * 
 */
@Entity
@Table(name="TAGS")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long tagid;

	private String text;

	//bi-directional many-to-one association to Document
	@ManyToOne
	@JoinColumn(name="DOCUMENTID")
	private Document document;

	public Tag() {
	}

	public long getTagid() {
		return this.tagid;
	}

	public void setTagid(long tagid) {
		this.tagid = tagid;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}