package com.soas.hrm21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soas.hrm21.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	/**
	@Modifying
	@Transactional
	@Query("DELETE FROM EMPLOYEE E WHERE E.DEPARTMENT.ID =? 1")
	void deleteByDepartmentId(Long departmentId);
	*/

}
