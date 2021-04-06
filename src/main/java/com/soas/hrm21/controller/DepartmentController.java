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
import com.soas.hrm21.exception.ResourceNotFoundException;
import com.soas.hrm21.repository.DepartmentRepository;

@RestController
@RequestMapping("/api/v1/department")
@CrossOrigin
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@PostMapping
	public ResponseEntity<Department> create(@RequestBody Department department) {
		Department savedDepartment = departmentRepository.save(department);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedDepartment.getDepartmentId()).toUri();
		return ResponseEntity.created(location).body(savedDepartment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Department departmentTmp) {
		Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found for Id : " + id));
		departmentTmp.setDepartmentId(department.getDepartmentId());
		departmentRepository.save(departmentTmp);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Department> delete(@PathVariable Long id) {
		Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found for Id : " + id));
		departmentRepository.delete(department);
		// deleteDepartmentInTransaction(optionalDepartment.get());
		return ResponseEntity.noContent().build();
	}

	/**
	 * void deleteDepartmentInTransaction(Department department) {
	 * employeeRepository.deleteByDepartmentId(department.getDepartmentId());
	 * departmentRepository.delete(department); }
	 */

	@GetMapping("/{id}")
	public ResponseEntity<Department> getById(@PathVariable Long id) {
		Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found for Id : " + id));
		return ResponseEntity.ok(department);
	}

	@GetMapping
	public ResponseEntity<Page<Department>> getAll(Pageable pageable) {
		return ResponseEntity.ok(departmentRepository.findAll(pageable));
	}
}