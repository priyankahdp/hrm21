package com.soas.hrm21.entity;

import java.io.Serializable;
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
public class Department extends AuditEntity implements Serializable {

	private static final long serialVersionUID = 368719600023084454L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private long departmentId;

	@Column(unique = true)
	private String departmentName;
	
	private String departmentType;

	// @OneToMany(mappedBy = "department", cascade{CascadeType.PERSIST,CascadeType.MERGE})
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Employee> employees;

	public Department(String departmentName, String departmentType) {
		this.departmentName = departmentName;
		this.departmentType = departmentType;
	}

	public Department() {
	}
}