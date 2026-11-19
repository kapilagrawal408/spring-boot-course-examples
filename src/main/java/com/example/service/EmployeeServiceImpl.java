package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.EmployeeDTO;
import com.example.exception.NoDepartmentFoundException;
import com.example.mapper.MapperUtils;
import com.example.model.Department;
import com.example.model.Employee;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeRepository employeeRepository;
	private DepartmentRepository departmentRepository;
	private MapperUtils mapperUtils;
	
	@Override
	public EmployeeDTO addEmployee(EmployeeDTO employee) {
		log.info("Inside com.example.service.EmployeeServiceImpl.addEmployee");
		Employee employeeEntity = mapperUtils.toEmployeeEntity(employee);
		//The employeeEntity has a dummy object of Department object not actual object
		//Get the department object based on departmentId and set to employeeEntity
		
		Integer departmentId = employee.getDepartmentId();
		Department department = departmentRepository.findById(employee.getDepartmentId()).orElseThrow(() -> new NoDepartmentFoundException("No department with department id " + departmentId));
		employeeEntity.setDepartment(department);
		
		Employee addedEmployee = employeeRepository.save(employeeEntity);
		EmployeeDTO addedemployeeDTO = mapperUtils.toEmployeeDTO(addedEmployee);
		log.info("Leaving com.example.service.EmployeeServiceImpl.addEmployee\n");
		return addedemployeeDTO;
	}
	
	@Override
	public List<EmployeeDTO> findByDepartmentId(Integer id) {
		log.info("Inside com.example.service.EmployeeServiceImpl.findByDepartmentId");
		List<Employee> employees = employeeRepository.findByDepartmentId(id);
		log.info("Leaving com.example.service.EmployeeServiceImpl.findByDepartmentId\n");
		return mapperUtils.toEmployeeDTOList(employees);	
	}
}