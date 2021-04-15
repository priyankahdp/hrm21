package com.soas.hrm21;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.soas.hrm21.entity.Department;
import com.soas.hrm21.entity.Employee;
import com.soas.hrm21.repository.DepartmentRepository;
import com.soas.hrm21.repository.EmployeeRepository;

@SpringBootApplication
@EnableJpaAuditing
public class Hrm21Application {

	public static void main(String[] args) {
		SpringApplication.run(Hrm21Application.class, args);
	}

	@Bean
	public CommandLineRunner addEmployeeDummy(EmployeeRepository employeeRepository,
			DepartmentRepository departmentRepository) {
		return args -> {
			Department department = new Department("Sales", "Admin");
			//departmentRepository.save(department);
			Employee employee = new Employee("Priyanka", "Kulathilake", "0716172931", "Kelaniya", department);
			//employeeRepository.save(employee);
		};
	}
}