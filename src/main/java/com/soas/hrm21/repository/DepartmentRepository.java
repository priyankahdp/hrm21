package com.soas.hrm21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soas.hrm21.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
