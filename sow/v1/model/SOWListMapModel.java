package com.accolite.ppm.sow.v1.model;

import java.util.Date;

import com.accolite.ppm.enums.SowStatus;
import com.accolite.rpm.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SOWListMapModel {
	private Integer buid;
	private String buname;
	private Date proposedenddate;
	private String projectname;
	private Integer billingid;
	private Integer clientid;
	private String type;
	private String sowname;
	private String taxid;
	private String clientname;
	private Integer currency;
	private Integer projectid;
	private String sowid;
	private Date proposedstartdate;
	private Double budget;
	private Integer status;
	
	@JsonProperty("buId")
	public Integer getBuid() {
		return buid;
	}
	public void setBuid(Integer buid) {
		this.buid = buid;
	}
	@JsonProperty("buName")
	public String getBuname() {
		return buname;
	}
	public void setBuname(String buname) {
		this.buname = buname;
	}
	@JsonProperty("proposedEndDate")
	public Date getProposedenddate() {
		return proposedenddate;
	}
	public void setProposedenddate(Date proposedenddate) {
		this.proposedenddate = proposedenddate;
	}
	@JsonProperty("projectName")
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	@JsonProperty("billingId")
	public Integer getBillingid() {
		return billingid;
	}
	public void setBillingid(Integer billingid) {
		this.billingid = billingid;
	}
	@JsonProperty("clientId")
	public Integer getClientid() {
		return clientid;
	}
	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@JsonProperty("sowName")
	public String getSowname() {
		return sowname;
	}
	public void setSowname(String sowname) {
		this.sowname = sowname;
	}
	@JsonProperty("taxId")
	public String getTaxid() {
		return taxid;
	}
	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}
	@JsonProperty("clientName")
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public Currency getCurrency() {
		return Currency.fromCode(currency);
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	@JsonProperty("projectId")
	public Integer getProjectid() {
		return projectid;
	}
	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}
	@JsonProperty("sowId")
	public String getSowid() {
		return sowid;
	}
	public void setSowid(String sowid) {
		this.sowid = sowid;
	}
	@JsonProperty("proposedStartDate")
	public Date getProposedstartdate() {
		return proposedstartdate;
	}
	public void setProposedstartdate(Date proposedstartdate) {
		this.proposedstartdate = proposedstartdate;
	}
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	public SowStatus getStatus() {
		return SowStatus.fromCode(status);
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
