package com.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.dto.StudentDTO;
import com.example.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
	
	StudentDTO getDTOFromEntity(Student student);
	Student getEntityFromDTO(StudentDTO student);
}