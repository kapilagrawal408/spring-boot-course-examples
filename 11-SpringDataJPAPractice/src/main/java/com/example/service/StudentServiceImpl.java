package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.StudentDTO;
import com.example.exception.StudentNotFoundException;
import com.example.mapper.StudentMapper;
import com.example.model.Student;
import com.example.repository.StudentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
	private StudentRepository studentRepository;
	private StudentMapper studentMapper;

	@Override
	@Transactional
	public StudentDTO addStudent(StudentDTO studentDTO) {
		log.info("Inside com.example.service.StudentServiceImpl.addStudent");
		//code to convert the studentDTO to studentEntity
		Student studentEntity = studentMapper.getEntityFromDTO(studentDTO);
		log.info("From studentDTO, entity savedStudent created successfully");
		
		//The above line is equivalent to this code
//		Student st = new Student();
//		st.setName(studentDTO.getName());
//		st.setPercentage(studentDTO.getPercentage());
		
		//Save the Entity, the result is also an Entity
		Student savedStudent = studentRepository.save(studentEntity);
		log.info("Student created successfully");
		studentDTO = studentMapper.getDTOFromEntity(savedStudent);
		log.info("From savedStudent entity, studentDTO created successfully");
		
		//The above line is equivalent to this code
//		studentDTO.setRollNo(savedStudent.getRollNo());
//		studentDTO.setRegistrationDate(savedStudent.getRegistrationDate());
//		studentDTO.setLastModified(savedStudent.getLastModified());
		log.info("Leaving com.example.service.StudentServiceImpl.addStudent");
		return studentDTO;
	}

	@Override
	public List<StudentDTO> viewStudents() {
		log.info("Inside com.example.service.StudentServiceImpl.viewStudents");
		List<Student> allStudents = studentRepository.findAll();
		log.info("all students fetched from database");
		List<StudentDTO> list = allStudents.stream().map(studentEntity -> studentMapper.getDTOFromEntity(studentEntity)).toList();
		log.info("Leaving com.example.service.StudentServiceImpl.viewStudents");
		return list;
	}

	@Override
	public StudentDTO getStudentByRollNo(Integer rollNo) {
		log.info("Inside com.example.service.StudentServiceImpl.getStudentByRollNo");
		Student studentEntity = studentRepository.findById(rollNo).orElseThrow(() -> new StudentNotFoundException("No Student with roll number " + rollNo));
		log.info("Leaving com.example.service.StudentServiceImpl.getStudentByRollNo");
		return studentMapper.getDTOFromEntity(studentEntity);
	}

	@Override
	@Transactional
	public StudentDTO updateNameAndMarks(Integer rollNo, StudentDTO student) {
		log.info("Inside com.example.service.StudentServiceImpl.deleteStudent");
		Student studentEntity = studentRepository.findById(rollNo).orElseThrow(() -> new StudentNotFoundException("No Student with roll number " + rollNo));
		studentEntity.setName(student.getName());
		studentEntity.setPercentage(student.getPercentage());
		studentRepository.flush();
		log.info("Student whose roll number {} has new name {} and percentage {}", rollNo, student.getName(), student.getPercentage());
		log.info("Leaving com.example.service.StudentServiceImpl.deleteStudent");
		return studentMapper.getDTOFromEntity(studentEntity);
	}

	@Override
	@Transactional
	public void deleteStudent(Integer rollNo) {
		log.info("Inside com.example.service.StudentServiceImpl.deleteStudent");
		Student studentEntity = studentRepository.findById(rollNo).orElseThrow(() -> new StudentNotFoundException("No Student with roll number " + rollNo));
		log.info("Student with roll number {} deleted", rollNo);
		log.info("Leaving com.example.service.StudentServiceImpl.deleteStudent");
		studentRepository.delete(studentEntity);
	}
}