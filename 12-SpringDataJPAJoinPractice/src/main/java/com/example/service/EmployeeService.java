package com.example.service;

import java.util.List;

import com.example.dto.EmployeeDTO;

public interface EmployeeService {
	public EmployeeDTO addEmployee(EmployeeDTO employee);
	public List<EmployeeDTO> findByDepartmentId(Integer id);
}
