package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EmployeeDTO;
import com.example.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employee){
		employee = employeeService.addEmployee(employee);
		return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.CREATED);
	}
	
	/* POST http://localhost:8080/employee/add
	* 
	* {
	* 	"name": "Chanakya",
	* 	"address": {
	* 		"city": "Jaipur",
	* 		"state": "Rajasthan",
	* 		"zipcode": "302002"
	* 	},
	* 	"joiningDate": "2026-08-06",
	* 	"departmentId": 1
	* }
	* 
	* 
	* Output
{
    "id": 3,
    "name": "UYT",
    "address": {
        "city": "Jaipur",
        "state": "Rajasthan",
        "zipcode": 302002
    },
    "joiningDate": "2026-08-06"
}
	* 
	* Add an employee to department that doesn't exists
	* POST http://localhost:8080/employee/add
	* {
	* 	"name": "YUR",
	* 	"address": {
	* 		"city": "Udaipur",
	* 		"state": "Rajasthan",
	* 		"zipcode": "859625"
	* 	},
	* 	"joiningDate": "2026-08-09",
	* 	"departmentId": 4
	* }
	* 
	* Output
	* {
    		"detail": "No department with department id 4",
    		"instance": "/employee/add",
    		"status": 400,
    		"title": "Invalid Department id"
	}
	*/
	
	@GetMapping("/getemployeesbydepartmentid/{departmentId}")
	public ResponseEntity<List<EmployeeDTO>> fun(@PathVariable("departmentId") Integer departmentId) {
		List<EmployeeDTO> employees = employeeService.findByDepartmentId(departmentId);
		return new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);
	}
	
	/* GET http://localhost:8080/employee/getemployeesbydepartmentid/1
	* 
	* Output
[
    {
        "id": 1,
        "name": "Abhay",
        "address": {
            "city": "Faridabad",
            "state": "Haryana",
            "zipcode": 453422
        },
        "joiningDate": "2026-09-03"
    },
    {
        "id": 2,
        "name": "Bhavya",
        "address": {
            "city": "Fatehabad",
            "state": "UP",
            "zipcode": 765466
        },
        "joiningDate": "2026-09-03"
    },
    {
        "id": 3,
        "name": "Chanakya",
        "address": {
            "city": "Jaipur",
            "state": "Rajasthan",
            "zipcode": 302002
        },
        "joiningDate": "2026-08-06"
    }
]
	*/
}