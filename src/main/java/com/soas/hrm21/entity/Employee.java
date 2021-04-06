package com.soas.hrm21.entity;

import java.io.Serializable;

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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity(name = "Employee")
@Table(name = "employee", uniqueConstraints = {
		@UniqueConstraint(name = "student_mobile_unique", columnNames = "mobile") })
public class Employee extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -2040126764735713060L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empId;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String mobile;

	private String address;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "departmentid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Department department;

	public Employee() {
	}

	public Employee(String firstName, String lastName, String mobile, String address, Department department) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.address = address;
		this.department = department;
	}

}