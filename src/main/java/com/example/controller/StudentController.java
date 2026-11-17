package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.StudentNotFoundException;
import com.example.model.Student;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/students")
@Slf4j
public class StudentController {
	private List<Student> studentList;

	public StudentController() {
		studentList = new ArrayList<>();
	}
	
	@PostConstruct
	public void addStudents() {
		log.info("Initializing the Student List");
		studentList.add(new Student(Integer.valueOf(10), "ABC", Double.valueOf(65.25)));
		studentList.add(new Student(Integer.valueOf(11), "BCD", Double.valueOf(75.25)));
		studentList.add(new Student(Integer.valueOf(12), "CDE", Double.valueOf(75.25)));
	}
	
	@GetMapping(value = "getstudent/{rollNo}")
	public ResponseEntity<Student> getStudentByRollNo(@PathVariable("rollNo") Integer rollNo) {
		Student student = studentList.stream().filter(st -> st.getRollNo() == rollNo).findFirst().orElse(null);
		if(student == null)
				throw new StudentNotFoundException("No Student found with roll number " + rollNo);
		log.info("Student with roll number {} found", student.getRollNo());
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	/*
	 * GET http://localhost:8080/students/getstudent/10
	 * {
	 * 	"rollNo": 10,
	 * 	"name": "ABC",
	 * 	"marks": 65.25
	 * }
	 * 
	 * GET http://localhost:8080/students/getstudent/100
	 * {
	 * 	"detail": "No Student found with roll number 100",
	 * 	"instance": "/students/getstudent/100",
	 * 	"status": 404,
	 * 	"title": "Missing Student"
	 * }
	 */
}
