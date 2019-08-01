package com.accolite.ppm.sow.v1.model;

import java.util.Set;

public class SOWMapModel {

	/*
	 * private Integer id; private String sowId; private String projectId; private
	 * String name; private Date proposedStartDate; private Date proposedEndDate;
	 * private SowStatus status; private double sowBudget; private Currency
	 * currency; private String type; private String taxId;
	 */
	
	private SOWv1 sowDetails;
	private Set<FixedBid> fixedBid;
	private Set<FixedMonthlyBid> fixedMonthlyBid;
	private Set<TimeAndMaterial> timeAndMaterial;
	private Set<Attachments> attachments;

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

	public void setAttachments(Set<Attachments> attachments) {
		this.attachments = attachments;
	}

	public SOWv1 getSowDetails() {
		return sowDetails;
	}

	@Override
	public String toString() {
		return "SOWMapModel [sowDetails=" + sowDetails + ", fixedBid=" + fixedBid + ", fixedMonthlyBid="
				+ fixedMonthlyBid + ", timeAndMaterial=" + timeAndMaterial + "]";
	}

	public void setSowDetails(SOWv1 sowDetails) {
		this.sowDetails = sowDetails;
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

}
