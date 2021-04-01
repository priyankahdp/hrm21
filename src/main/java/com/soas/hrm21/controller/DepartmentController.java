package com.soas.hrm21.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
import com.soas.hrm21.repository.DepartmentRepository;

@RestController
@RequestMapping("/api/v1/department")
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
	public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Department department) {
		Optional<Department> optionalDepartment = departmentRepository.findById(id);
		if (!optionalDepartment.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		department.setDepartmentId(optionalDepartment.get().getDepartmentId());
		departmentRepository.save(department);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Department> delete(@PathVariable Long id) {
		Optional<Department> optionalDepartment = departmentRepository.findById(id);
		if (!optionalDepartment.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		departmentRepository.delete(optionalDepartment.get());
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getById(@PathVariable Long id) {
		Optional<Department> optionalDepartment = departmentRepository.findById(id);
		if (!optionalDepartment.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(optionalDepartment.get());
	}

	@GetMapping
	public ResponseEntity<Page<Department>> getAll(Pageable pageable) {
		return ResponseEntity.ok(departmentRepository.findAll(pageable));
	}
}