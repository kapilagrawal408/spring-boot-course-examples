package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.StudentDTO;
import com.example.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
	private StudentService studentService;
	
	//@Autowired //Not needed as this is one argument constructor
	StudentController(StudentService studentService){
		this.studentService = studentService;
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<StudentDTO> registerStudent(@RequestBody StudentDTO student){
		log.info("inside com.example.controller.StudentController.registerStudent");
		StudentDTO registeredStudent = studentService.addStudent(student);
		log.info("Leaving com.example.controller.StudentController.registerStudent");
		return new ResponseEntity<StudentDTO>(registeredStudent, HttpStatus.CREATED);
	}
	
	//URL: POST http://localhost:8080/student/add
	/*
	 * {
	 * 	"name": "ABC",
	 * 	"percentage": 78.25
	 * }
	 * 
	 * {
	 *  "rollNo": 1,
	 *  "name": "ABC",
	 *  "percentage": 78.25,
	 *  "registrationDate": "2026-09-02T12:55:25.5240412",
	 *  "lastModified": null
	 * }
	 * 
	 * Output
	 * +---------+---------------+------+------------+----------------------------+
	 * | roll_no | last_modified | name | percentage | registration_date          |
	 * +---------+---------------+------+------------+----------------------------+
	 * |       1 | NULL          | ABC  |      78.25 | 2026-09-02 12:55:25.524041 |
	 * +---------+---------------+------+------------+----------------------------+
	 * */
	
	@GetMapping(value = "/viewstudents")
	public ResponseEntity<List<StudentDTO>> getStudents(){
		log.info("Inside com.example.controller.StudentController.getStudents");
		List<StudentDTO> viewStudents = studentService.viewStudents();
		log.info("Leaving com.example.controller.StudentController.getStudents");
		return new ResponseEntity<List<StudentDTO>>(viewStudents, HttpStatus.OK);
	}
	
	//URL: GET http://localhost:8080/student/viewstudents
	
	/*
	 * [
	 *  {
	 *  "rollNo": 1,
	 *  "name": "ABC",
	 *  "percentage": 78.25,
	 *  "registrationDate": "2026-09-02T12:55:25.524041",
	 *  "lastModified": null
	 *  }
	 * ]
	 * MIND THE [...] HERE
	 */
	
	@GetMapping(value = "/getStudentByRollNo/{rollNo}")
	public ResponseEntity<StudentDTO> getStudents(@PathVariable("rollNo") Integer rollNo){
		log.info("Inside com.example.controller.StudentController.getStudents");
		StudentDTO student = studentService.getStudentByRollNo(rollNo);
		log.info("Leaving com.example.controller.StudentController.getStudents");
		return new ResponseEntity<StudentDTO>(student, HttpStatus.OK);
	}
	
	//GET http://localhost:8080/student/getStudentByRollNo/1
	/*
	 * {
	 * "rollNo": 1,
	 * "name": "ABC",
	 * "percentage": 78.25,
	 * "registrationDate": "2026-09-02T12:55:25.524041",,
	 * "lastModified": null
	 * }
	 * NO [...] HERE
	 */
	@PutMapping(value = "/updatenameandmarks/{rollNo}")
	public ResponseEntity<StudentDTO> updateNameAndMarks(@PathVariable("rollNo") Integer rollNo, @RequestBody StudentDTO studentDTO){
		log.info("Inside com.example.controller.StudentController.updateNameAndMarks");
		StudentDTO student = studentService.updateNameAndMarks(rollNo, studentDTO);
		log.info("Leaving com.example.controller.StudentController.updateNameAndMarks");
		return new ResponseEntity<StudentDTO>(student, HttpStatus.OK);
	}
	
	//PUT http://localhost:8080/student/updatenameandmarks/1
	/*
	 * {
	 * "name": "PQR",
	 * "percentage": 85.25
	 * }
	 * 
	 * OUTPUT
	 * {
	 * "rollNo": 1,
	 * "name": "PQR",
	 * "percentage": 85.25,
	 * "registrationDate": "2026-09-01T20:42:58.826389",
	 * "lastModified": "2026-09-02T12:59:32.238921"
	 * }
	 * 
	 * +---------+-------------------------------------+------+------------+----------------------------+
	 * | roll_no | last_modified                       | name | percentage | registration_date          |
	 * +---------+-------------------------------------+------+------------+----------------------------+
	 * |       1 | 2026-09-02T12:59:32.238921          | ABC  |      78.25 | 2026-09-02 12:55:25.524041 |
	 * +---------+-------------------------------------+------+------------+----------------------------+
	 */
	
	@DeleteMapping(value = "/deletestudent/{rollNo}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("rollNo") Integer rollNo){
		log.info("Inside com.example.controller.StudentController.deleteStudent");
		studentService.deleteStudent(rollNo);
		log.info("Leaving com.example.controller.StudentController.deleteStudent");
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//DELETE http://localhost:8080/student/deletestudent/1
	//204 NO CONTENT
}