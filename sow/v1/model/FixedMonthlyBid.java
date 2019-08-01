package com.accolite.ppm.sow.v1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fixed_monthly_bid", schema = "ezhire")
public class FixedMonthlyBid {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	@Column(name = "role")
	private String role;
	@Column(name = "ratePerMonth")
	private Double ratePerMonth;
	@Column(name = "employeeId")
	private String employeeId;
	@Column(name = "employee_name")
	private String employeeName;

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public Double getRatePerMonth() {
		return ratePerMonth;
	}



	public void setRatePerMonth(Double ratePerMonth) {
		this.ratePerMonth = ratePerMonth;
	}



	public String getEmployeeId() {
		return employeeId;
	}



	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}



	public String getEmployeeName() {
		return employeeName;
	}



	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



	@Override
	public String toString() {
		return "FixedMonthlyBid [id=" + id + ", role=" + role + ", ratePerMonth=" + ratePerMonth + ", employeeId="
				+ employeeId + "]";
	}

}
