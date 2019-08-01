package com.accolite.ppm.sow.v1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attachment_sow", schema = "ezhire")
public class Attachments {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	@Column(name = "url")
	private String url;
	@Column(name = "attachment_date")
	private Date attachmentDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getAttachmentDate() {
		return attachmentDate;
	}

	public void setAttachmentDate(Date attachmentDate) {
		this.attachmentDate = attachmentDate;
	}

	@Override
	public String toString() {
		return "Attachments [id=" + id + ", url=" + url + ", attachmentDate=" + attachmentDate + "]";
	}

}
