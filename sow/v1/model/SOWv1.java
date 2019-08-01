package com.accolite.ppm.sow.v1.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.accolite.ppm.enums.SowStatus;
import com.accolite.rpm.enums.Currency;

@Entity
@Table(name = "sow_v1", schema = "ezhire")
public class SOWv1 {
	@Id
	@Column(name = "id")
	private String sowId;

	@Column(name = "project_id")
	private Integer projectId;

	@Column(name = "name")
	private String name;

	@Column(name = "proposed_start_date")
	private Date proposedStartDate;

	@Column(name = "proposed_end_date")
	private Date proposedEndDate;

	@Column(name = "status")
	private SowStatus status;

	@Column(name = "budget")
	private double sowBudget;

	@Column(name = "currency")
	private Currency currency;

	@Column(name = "type")
	private String type;

	@Column(name = "tax_id")
	private String taxId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sow_id")
	private Set<TimeAndMaterial> timeAndMaterial;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sow_id")
	private Set<FixedBid> fixedBid;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sow_id")
	private Set<FixedMonthlyBid> fixedMonthlyBid;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sow_id")
	private Set<Attachments> attachments;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_id")
	private BillingInfo billingInfo ;

	
	public BillingInfo getBillingInfo() {
		return billingInfo;
	}

	public void setBillingInfo(BillingInfo billingInfo) {
		this.billingInfo = billingInfo;
	}

	
	public Set<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachments> Attachments) {
		attachments = Attachments;
	}


	public Set<FixedBid> getFixedBid() {
		return fixedBid;
	}

	public void setFixedBid(Set<FixedBid> fixedBid) {
		this.fixedBid = fixedBid;
	}

	public Set<FixedMonthlyBid> getFixedMonthlyBid() {
		return fixedMonthlyBid;
	}

	public void setFixedMonthlyBid(Set<FixedMonthlyBid> fixedMonthlyBid) {
		this.fixedMonthlyBid = fixedMonthlyBid;
	}

	public Set<TimeAndMaterial> getTimeAndMaterial() {
		return timeAndMaterial;
	}

	public void setTimeAndMaterial(Set<TimeAndMaterial> timeAndMaterial) {
		this.timeAndMaterial = timeAndMaterial;
	}

	@Override
	public String toString() {
		return "SOWv1 [sowId=" + sowId + ", projectId=" + projectId + ", name=" + name + ", proposedStartDate="
				+ proposedStartDate + ", proposedEndDate=" + proposedEndDate + ", status=" + status + ", sowBudget="
				+ sowBudget + ", currency=" + currency + ", type=" + type + ", taxId=" + taxId + "]";
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getSowId() {
		return sowId;
	}

	public void setSowId(String sowId) {
		this.sowId = sowId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getProposedStartDate() {
		return proposedStartDate;
	}

	public void setProposedStartDate(Date proposedStartDate) {
		this.proposedStartDate = proposedStartDate;
	}

	public Date getProposedEndDate() {
		return proposedEndDate;
	}

	public void setProposedEndDate(Date proposedEndDate) {
		this.proposedEndDate = proposedEndDate;
	}

	public SowStatus getStatus() {
		return status;
	}

	public void setStatus(SowStatus status) {
		this.status = status;
	}

	public double getSowBudget() {
		return sowBudget;
	}

	public void setSowBudget(double sowBudget) {
		this.sowBudget = sowBudget;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

}
