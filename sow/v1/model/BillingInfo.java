package com.accolite.ppm.sow.v1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "billing_info", schema = "ezhire")
public class BillingInfo {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	@Column(name = "billingTo")
	private String billingTo;
	@Column(name = "billingFrom")
	private String billingFrom;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBillingTo() {
		return billingTo;
	}
	public void setBillingTo(String billingTo) {
		this.billingTo = billingTo;
	}
	public String getBillingFrom() {
		return billingFrom;
	}
	public void setBillingFrom(String billingFrom) {
		this.billingFrom = billingFrom;
	}
	public String getWiringInfo() {
		return wiringInfo;
	}
	public void setWiringInfo(String wiringInfo) {
		this.wiringInfo = wiringInfo;
	}
	@Column(name = "wiringInfo")
	private String wiringInfo;
	
	@Override
	public String toString() {
		return "Billing info  [id=" + id + ", billing to=" + billingTo + ", billing form=" +billingFrom +  ", wiring info=" + wiringInfo + "]";
	
}
}