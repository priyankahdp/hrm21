package com.soas.hrm21.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Entity(name = "Employee")
@Table(name = "employee", uniqueConstraints = {
		@UniqueConstraint(name = "student_mobile_unique", columnNames = "mobile") })
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empId;

	@Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
	private String firstName;
	
	@Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
	private String lastName;
	
	@Column(name = "mobile", nullable = false, columnDefinition = "TEXT")
	private String mobile;
	
	@Column(name = "address", nullable = false, columnDefinition = "TEXT")
	private String address;

	private LocalDateTime created;
	private LocalDateTime updated;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "departmentid")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Department department;

	public Employee() {
	}

	public Employee(String firstName, String lastName, String mobile, LocalDateTime created, LocalDateTime updated,
			String address, Department department) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.created = created;
		this.updated = updated;
		this.address = address;
		this.department = department;
	}

}