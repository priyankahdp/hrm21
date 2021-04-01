package com.soas.hrm21.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soas.hrm21.repository.DepartmentRepository;
import com.soas.hrm21.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("employee")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@PostMapping("employee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		// Optional<Department> optionalDepartment= employeeService.//TODO
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@PutMapping("employee")
	public ResponseEntity<Employee> upadteEmployee(@RequestBody Employee employee) {
		employeeService.editEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@DeleteMapping("employee")
	public ResponseEntity<String> deleteEmployee(@RequestParam("empId") Integer empId) {
		employeeService.deleteEmployee(empId);
		return new ResponseEntity<String>("ID : " + empId + " Employee deleted successfully", HttpStatus.NO_CONTENT);
	}

}
