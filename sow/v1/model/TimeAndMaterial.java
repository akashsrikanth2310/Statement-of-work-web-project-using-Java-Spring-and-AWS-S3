package com.accolite.ppm.sow.v1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "time_and_material", schema = "ezhire")
public class TimeAndMaterial {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	@Column(name = "role")
	private String role;
	@Column(name = "ratePerHour")
	private Double ratePerHour;
	@Column(name = "employeeId")
	private String employeeId;
	@Column(name = "employee_name")
	private String employeeName;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

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

	public Double getRatePerHour() {
		return ratePerHour;
	}

	public void setRatePerHour(Double ratePerHour) {
		this.ratePerHour = ratePerHour;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "TimeAndMaterial [id=" + id + ", role=" + role + ", ratePerHour=" + ratePerHour + ", employeeId="
				+ employeeId + "]";
	}

}
