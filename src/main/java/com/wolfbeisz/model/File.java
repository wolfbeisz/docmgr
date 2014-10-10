package com.wolfbeisz.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FILES database table.
 * 
 */
@Entity
@Table(name="FILES")
@NamedQuery(name="File.findAll", query="SELECT f FROM File f")
public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long fileid;

	@Column(name="PATH")
	private String path;

	//bi-directional many-to-one association to Document
	@ManyToOne
	@JoinColumn(name="DOCUMENTID")
	private Document document;

	public File() {
	}

	public long getFileid() {
		return this.fileid;
	}

	public void setFileid(long fileid) {
		this.fileid = fileid;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}