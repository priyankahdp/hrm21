package com.soas.hrm21.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "Department")
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id", updatable = false)
	private long departmentId;

	@Column(name = "department_name", nullable = false)
	private String departmentName;

	@Column(name = "department_Type", nullable = false)
	private String departmentType;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Employee> employees;

	private LocalDateTime created;
	private LocalDateTime updated;

	public Department(String departmentName, String departmentType, LocalDateTime created, LocalDateTime updated) {
		this.departmentName = departmentName;
		this.departmentType = departmentType;
		this.created = created;
		this.updated = updated;
	}

	public Department() {
	}
}