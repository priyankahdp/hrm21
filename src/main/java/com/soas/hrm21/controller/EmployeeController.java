package com.soas.hrm21.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.soas.hrm21.entity.Department;
import com.soas.hrm21.entity.Employee;
import com.soas.hrm21.exception.ResourceNotFoundException;
import com.soas.hrm21.repository.DepartmentRepository;
import com.soas.hrm21.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@PostMapping
	public ResponseEntity<Employee> create(@RequestBody Employee employee) {
		Department department = departmentRepository.findById(employee.getDepartment().getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Department not found for Id : " + employee.getDepartment().getDepartmentId()));
		employee.setDepartment(department);
		Employee savedEmployee = employeeRepository.save(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEmployee.getEmpId()).toUri();
		return ResponseEntity.created(location).body(savedEmployee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> update(@RequestBody Employee employeeTmp, @PathVariable Long id) {
		Department department = departmentRepository.findById(employeeTmp.getDepartment().getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Department not found for Id : " + employeeTmp.getDepartment().getDepartmentId()));
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found for Id : " + employeeTmp.getEmpId()));
		employee.setDepartment(department);
		employee.setEmpId(employeeTmp.getEmpId());
		employeeRepository.save(employee);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> delete(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for Id : " + id));
		employeeRepository.delete(employee);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for Id : " + id));
		return ResponseEntity.ok(employee);
	}

	@GetMapping
	public ResponseEntity<Page<Employee>> getAll(Pageable pageable) {
		return ResponseEntity.ok(employeeRepository.findAll(pageable));
	}
}