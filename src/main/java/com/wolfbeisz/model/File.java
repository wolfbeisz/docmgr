package com.wolfbeisz.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


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

	private Timestamp uploadstamp;

	@Column(name="VERSION")
	private BigDecimal version;

	//bi-directional many-to-one association to Document
	@ManyToOne
	@JoinColumn(name="DOCUMENTID")
	private Document document;

	//bi-directional many-to-one association to Change
	@OneToMany(mappedBy="file")
	private List<Change> changes;

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

	public Timestamp getUploadstamp() {
		return this.uploadstamp;
	}

	public void setUploadstamp(Timestamp uploadstamp) {
		this.uploadstamp = uploadstamp;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public List<Change> getChanges() {
		return this.changes;
	}

	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}

	public Change addChange(Change change) {
		getChanges().add(change);
		change.setFile(this);

		return change;
	}

	public Change removeChange(Change change) {
		getChanges().remove(change);
		change.setFile(null);

		return change;
	}

}