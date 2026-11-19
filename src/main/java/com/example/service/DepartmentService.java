package com.example.service;

import java.util.List;

import com.example.dto.DepartmentDTO;

public interface DepartmentService{
	public DepartmentDTO addDepartment(DepartmentDTO departmentDTO);
	public List<DepartmentDTO> viewDepartments();
	public DepartmentDTO viewDepartmentById(Integer id);
	public List<DepartmentDTO> viewDepartmentsWithNoEmployees();
	public List<DepartmentDTO> viewDepartmentsWithAtLeastOneEmployees();
	public List<DepartmentDTO> viewDepartmentsWithTwoOrMoreEmployees();
}
