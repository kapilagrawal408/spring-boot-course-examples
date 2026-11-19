package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	public List<Department> findByEmployeeListIsEmpty();
	public List<Department> findByEmployeeListIsNotEmpty();
	
	@Query(value = "SELECT d FROM Department d WHERE SIZE(d.employeeList) >= 2")
	public List<Department> anyNameIsOkay();
}
