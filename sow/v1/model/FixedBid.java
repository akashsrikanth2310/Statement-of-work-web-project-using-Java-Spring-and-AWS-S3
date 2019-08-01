package com.accolite.ppm.sow.v1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fix_bid", schema = "ezhire")
public class FixedBid {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	@Column(name = "milestone_no")
	private Integer milestoneNumber;
	@Column(name = "milestone_amount")
	private Double milestoneAmount;

	@Column(name = "milestone_name")
	private String milestoneName;

	@Column(name = "milestone_end_date")
	private Date milestoneEndDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMilestoneNumber() {
		return milestoneNumber;
	}

	public void setMilestoneNumber(Integer milestoneNumber) {
		this.milestoneNumber = milestoneNumber;
	}

	public Double getMilestoneAmount() {
		return milestoneAmount;
	}

	public void setMilestoneAmount(Double milestoneAmount) {
		this.milestoneAmount = milestoneAmount;
	}

	public String getMilestoneName() {
		return milestoneName;
	}

	public void setMilestoneName(String milestoneName) {
		this.milestoneName = milestoneName;
	}

	public Date getMilestoneEndDate() {
		return milestoneEndDate;
	}

	public void setMilestoneEndDate(Date milestoneEndDate) {
		this.milestoneEndDate = milestoneEndDate;
	}

	@Override
	public String toString() {
		return "FixedBid [id=" + id + ", milestoneNumber=" + milestoneNumber + ", milestoneAmount=" + milestoneAmount
				+ ", milestoneName=" + milestoneName + ", milestoneEndDate=" + milestoneEndDate + "]";
	}

}
