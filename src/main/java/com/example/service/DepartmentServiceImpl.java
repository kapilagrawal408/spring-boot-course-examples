package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.DepartmentDTO;
import com.example.exception.NoDepartmentFoundException;
import com.example.mapper.MapperUtils;
import com.example.model.Department;
import com.example.model.Employee;
import com.example.repository.DepartmentRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentRepository departmentRepository;
	private MapperUtils mapperUtils;

	public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
		log.info("Inside com.example.service.DepartmentServiceImpl.addDepartment");
		Department entity = mapperUtils.toEntity(departmentDTO);
		List<Employee> employeeList = entity.getEmployeeList();
		
		if(employeeList != null)
			for(Employee emp: employeeList)
				emp.setDepartment(entity);
		
		Department savedDepartment = departmentRepository.saveAndFlush(entity);
		departmentDTO = mapperUtils.toDTO(savedDepartment);
		log.info("Leaving com.example.service.DepartmentServiceImpl.addDepartment\n");
		return departmentDTO;
	}
	
	public List<DepartmentDTO> viewDepartments() {
		log.info("Inside com.example.service.DepartmentServiceImpl.viewDepartments");
		List<Department> departments = departmentRepository.findAll();
		List<DepartmentDTO> dtoList = mapperUtils.toDTOList(departments);
		log.info("Leaving com.example.service.DepartmentServiceImpl.viewDepartments\n");
		return dtoList;
	}
	
	@Override
	public DepartmentDTO viewDepartmentById(Integer id) {
		log.info("Inside com.example.service.DepartmentServiceImpl.viewDepartmentById");
		Department departmentEntity = departmentRepository.findById(id).orElseThrow(() -> new NoDepartmentFoundException("No department with department id " + id));
		log.info("Leaving com.example.service.DepartmentServiceImpl.viewDepartmentById\n");
		return mapperUtils.toDTO(departmentEntity);
	}
	
	@Override
	public List<DepartmentDTO> viewDepartmentsWithNoEmployees() {
		log.info("Inside com.example.service.DepartmentServiceImpl.viewDepartmentsWithNoEmployees");
		List<Department> departments = departmentRepository.findByEmployeeListIsEmpty();
		List<DepartmentDTO> dtoList = mapperUtils.toDTOList(departments);
		log.info("Leaving com.example.service.DepartmentServiceImpl.viewDepartmentsWithNoEmployees\n");
		return dtoList;
	}
	
	@Override
	public List<DepartmentDTO> viewDepartmentsWithAtLeastOneEmployees() {
		log.info("Inside com.example.service.DepartmentServiceImpl.viewDepartmentsWithAtLeastOneEmployees");
		List<Department> departments = departmentRepository.findByEmployeeListIsNotEmpty();
		List<DepartmentDTO> dtoList = mapperUtils.toDTOList(departments);
		log.info("Leaving com.example.service.DepartmentServiceImpl.viewDepartmentsWithAtLeastOneEmployees\n");
		return dtoList;
	}
	
	@Override
	public List<DepartmentDTO> viewDepartmentsWithTwoOrMoreEmployees() {
		log.info("Inside com.example.service.DepartmentServiceImpl.viewDepartmentsWithTwoOrMoreEmployees");
		List<Department> departments = departmentRepository.anyNameIsOkay();
		List<DepartmentDTO> dtoList = mapperUtils.toDTOList(departments);
		log.info("Leaving com.example.service.DepartmentServiceImpl.viewDepartmentsWithTwoOrMoreEmployees\n");
		return dtoList;
	}
}