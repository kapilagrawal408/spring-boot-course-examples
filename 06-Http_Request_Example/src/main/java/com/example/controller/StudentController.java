package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StudentController {
	private List<Student> listStudent;

	public StudentController() {
		listStudent = new ArrayList<>();
	}
	
	@PostConstruct	
	public void startUp() {
		log.info("Student list initialized");
		listStudent.add(new Student(1, "ABC", 75.00));
		listStudent.add(new Student(2, "BCD", 85.00));
		listStudent.add(new Student(3, "CDE", 95.00));
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(){
		log.info("Getting all student list");
		return new ResponseEntity<List<Student>>(listStudent, HttpStatus.OK);
	}
	//GET http://localhost:8080/students
	//[{"rollNo":1,"name":"ABC","marks":75.0},{"rollNo":2,"name":"BCD","marks":85.0},{"rollNo":3,"name":"CDE","marks":95.0}]
	
	
	@GetMapping("/students/{rollno}")	//GET http://localhost:8080/students/1
	public ResponseEntity<Student> getStudentByRollNo(@PathVariable Integer rollno){
		log.info("Getting details of student having roll-number {}", rollno);
		return new ResponseEntity(listStudent.stream().filter(st -> st.getRollNo() == rollno).toList().get(0) , HttpStatus.OK);
	}
	//GET http://localhost:8080/students/1
	//{"rollNo":1,"name":"ABC","marks":75.0}
	
	@GetMapping("/studentsbymarks")	
	//public ResponseEntity<List<Student>> getStudentByMarksRange(@RequestParam("start") Integer start, @RequestParam("end") Integer end){	//is same as
	public ResponseEntity<List<Student>> getStudentByMarksRange(Integer start, Integer end){
		log.info("Getting student in the marks range {} - {}", start, end);
		return new ResponseEntity<List<Student>>(listStudent.stream().filter(st -> st.getMarks() >= start && st.getMarks() <= end).toList(), HttpStatus.OK);
	}
	//GET http://localhost:8080/studentsbymarks?start=80&end=95
	//[{"rollNo":2,"name":"BCD","marks":85.0},{"rollNo":3,"name":"CDE","marks":95.0}]
	
	@PostMapping("/students")
	public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
		log.info("Adding student with rollno {}", student.getRollNo());
		listStudent.add(student);
		return new ResponseEntity(student, HttpStatus.CREATED);
	}
	//POST http://localhost:8080/students
	//Request: {"rollNo":4,"name":"pqr","marks":65.0}
	//Response: {"rollNo":4,"name":"pqr","marks":65.0}
	
	//now hit the same URL with GET Request
	//GET http://localhost:8080/students
	//[{"rollNo":1,"name":"ABC","marks":75.0},{"rollNo":2,"name":"BCD","marks":85.0},
	//{"rollNo":3,"name":"CDE","marks":95.0},{"rollNo":4,"name":"pqr","marks":65.0}]

	@PutMapping("/students")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		log.info("Upsert operation for student with roll no {}", student.getRollNo());
		//find the student for given roll number
		Student stu = listStudent.stream().filter(st -> st.getRollNo() == student.getRollNo()).toList().get(0);
		stu.setName(student.getName());
		stu.setMarks(student.getMarks());
		return new ResponseEntity(stu, HttpStatus.OK);
	}
	//GET http://localhost:8080/students/1
	//{"rollNo":1,"name":"ABC","marks":75.0}
	
	//PUT http://localhost:8080/students
	//Request: {"rollNo":1,"name":"XYZ","marks":55.0}
	//Response: {"rollNo":1,"name":"XYZ","marks":55.0}
	
	//GET http://localhost:8080/students/1
	//{"rollNo":1,"name":"XYZ","marks":55.0}
	
	@PatchMapping("/students/{rollNo}/{name}")
	public ResponseEntity<Student> updateStudentName(@PathVariable Map<String, String> map){
		log.info("Update the name to {} of student with rollno {}",map.get("name"), map.get("rollNo"));
		//find the student for given roll number
		Student stu = listStudent.stream().filter(st -> st.getRollNo() == Integer.parseInt(map.get("rollNo"))).toList().get(0);
		stu.setName(map.get("name"));
		return new ResponseEntity(stu, HttpStatus.OK);
	}
	//GET http://localhost:8080/students/1
	//{"rollNo":1,"name":"ABC","marks":75.0}
	
	//PATCH http://localhost:8080/students/1/pqr
	//Response: {"rollNo":1,"name":"pqr","marks":75.0}
	
	//GET http://localhost:8080/students/1
	//{"rollNo":1,"name":"pqr","marks":75.0}
	
	@DeleteMapping("/students/{rollNo}")
	public ResponseEntity<Student> deleteStudent(@PathVariable Integer rollNo) {
		Student stu = listStudent.stream().filter(st -> st.getRollNo() == rollNo).toList().get(0);
		log.info("Deleting student with roll no {}", rollNo);
		listStudent.remove(stu);
		return new ResponseEntity(stu, HttpStatus.OK);
	}
	//GET http://localhost:8080/students/1
	//{"rollNo":1,"name":"ABC","marks":75.0}
	
	//DELETE http://localhost:8080/students/1
	//{"rollNo":1,"name":"ABC","marks":75.0}
	
	//GET http://localhost:8080/students
	//[{"rollNo":2,"name":"BCD","marks":85.0},{"rollNo":3,"name":"CDE","marks":95.0}]
}