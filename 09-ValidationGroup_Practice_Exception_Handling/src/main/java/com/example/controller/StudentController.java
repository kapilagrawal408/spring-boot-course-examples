package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.validation.OnCreate;
import com.example.validation.OnUpdate;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping(value = "/students")
public class StudentController {
	List<Student> studentList;

	public StudentController() {
		studentList = new ArrayList<>();
	}
	
	@PostConstruct
	public void addStudent() {
		log.info("Initializing the Student List");
		studentList.add(new Student(Integer.valueOf(10), "ABC", Double.valueOf(55.65)));
		studentList.add(new Student(Integer.valueOf(11), "BCD", Double.valueOf(60.65)));
		studentList.add(new Student(Integer.valueOf(12), "CDE", Double.valueOf(65.65)));
	}
	
	@PostMapping("/registerstudent")
	public ResponseEntity<Student> registerStudent(@Validated(OnCreate.class) @RequestBody Student student){
		studentList.add(student);
		log.info("Student added with roll number {}", student.getRollNo());
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}
	//POST http://localhost:8080/students/registerstudent
	/*
	 {
	 	"rollNo":"13",
		"name": "DEF",
		"marks": 70.65
	 }
	 
	 Output
	 {
	 	"rollNo":"13",
		"name": "DEF",
		"marks": 70.65
	 }
	 */
	
	//POST http://localhost:8080/students/registerstudent
	/*
	 {
		"rollNo":"0",
		"name": "K",
		"marks": -70
	 }
	 
	 Output
	 {
       "detail": "Object validation failed. Please check the payload.",
       "instance": "/students/registerstudent",
       "status": 400,
       "title": "Invalid Request Body",
       "invalid_fields": {
         "rollNo": "Roll number must have a positive value",
         "marks": "Mark cannot have a negative value"
    	   }
	 }
	 */
	
	@PatchMapping("/updatestudent/{rollNo}")
	public ResponseEntity<Student> updateStudent(@PathVariable("rollNo") @Range(min = 1L, max = 100L, message = "{rollNo.out_of_range}") Integer rollno, @Validated(OnUpdate.class) @RequestBody Student student){
		Student findSt = studentList.stream().filter(st->st.getRollNo() == rollno).findFirst().get();
		findSt.setName(student.getName());
		findSt.setMarks(student.getMarks());
		log.info("updated name to {} and marks to {} of student whose roll number is {}", student.getName(), student.getMarks(), rollno);
		return new ResponseEntity<Student>(findSt, HttpStatus.OK);
	}
	
	//PATCH http://localhost:8080/students/updatestudent/10
	/*
	 {
		"rollNo":"10",	//this is optional
		"name": "CBA",
		"marks": 56.75
	 }
	 
	 Output
	 {
       "rollNo": 10,
       "name": "BCA",
       "marks": 54.75
	 }
	 
	 //PATCH http://localhost:8080/students/updatestudent/101
	 {
       "rollNo": 10,
       "name": "BCA",
       "marks": 54.75
	 }
	 
	 Output
	 {
       "detail": "URL parameter validation failed.",
       "instance": "/students/updatestudent/101",
       "status": 400,
       "title": "Invalid URL Parameter",
       "invalid_params": {
         "rollno": "Roll number must be between 1 and 100"
    	   }
	 }
	 
	 //PATCH http://localhost:8080/students/updatestudent/10
	 {
		"name": "",
		"marks": -54.75
	 }
	 
	 Output
	 {
      "detail": "Object validation failed. Please check the payload.",
      "instance": "/students/updatestudent/10",
      "status": 400,
      "title": "Invalid Request Body",
      "invalid_fields": {
        "name": "Name is mandatory",
        "marks": "Mark cannot have a negative value"
       }
	 }
	 */
	
	
	@GetMapping("/searchstudent/{rollNo}")
	public ResponseEntity<Student> searchStudent(@PathVariable("rollNo") @Range(min = 1L, max = 100L, message = "{rollNo.out_of_range}") Integer rollno){
		Student student = studentList.stream().filter(st->st.getRollNo() == rollno).findFirst().get();
		log.info("Found student with roll number {}", rollno);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	//GET http://localhost:8080/students/searchstudent/10
	/*
	 {
       "rollNo": 10,
       "name": "ABC",
       "marks": 55.65
	 }
	 
	 //GET http://localhost:8080/students/searchstudent/101
	 
	 {
       "detail": "URL parameter validation failed.",
       "instance": "/students/searchstudent/101",
       "status": 400,
       "title": "Invalid URL Parameter",
       "invalid_params": {
         "rollno": "Roll number must be between 1 and 100"
       }
	 }
	 */
}