package com.example.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.dto.DepartmentDTO;
import com.example.dto.EmployeeDTO;
import com.example.model.Department;
import com.example.model.Employee;

@Mapper(componentModel = "spring")
public interface MapperUtils {
	MapperUtils INSTANCE = Mappers.getMapper(MapperUtils.class);
	
	DepartmentDTO toDTO(Department department);
	Department toEntity(DepartmentDTO dto);
	
	@Mapping(source = "department.id", target = "departmentId")
	EmployeeDTO toEmployeeDTO(Employee employee);
	
	@Mapping(source = "departmentId", target = "department.id")
	Employee toEmployeeEntity(EmployeeDTO dto);
	
	List<DepartmentDTO> toDTOList(List<Department> list);
	List<EmployeeDTO> toEmployeeDTOList(List<Employee> list);
}
