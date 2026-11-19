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

import com.example.dto.DepartmentDTO;
import com.example.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/departments")
public class DepartmentController{
	private DepartmentService departmentService;
	
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO department){
		log.info("Inside com.example.controller.DepartmentController.addDepartment");
		department = departmentService.addDepartment(department);
		log.info("Leaving com.example.controller.DepartmentController.addDepartment\n");
		return new ResponseEntity<DepartmentDTO>(department, HttpStatus.CREATED);
	}
	
	/*
	 * POST http://localhost:8080/departments/add
	 * 
	 * {
	 * 	"name": "Finance",
	 * 	"location": "Delhi",
	 * 	"employeeList": [
	 * 		{
	 * 			"name": "Abhay",
	 * 			"joiningDate": "2026-09-03",
	 * 			"address": {
	 * 				"city": "Faridabad",
	 * 				"state": "Haryana",
	 * 				"zipcode": 453422
	 * 			}
	 * 		},
	 * 		{
	 * 			"name": "Bhavya",
	 * 			"joiningDate": "2026-09-03",
	 * 			"address": {
	 * 				"city": "Fatehabad",
	 * 				"state": "UP",
	 * 				"zipcode": 765466
	 * 			}
	 * 		}
	 * 	]
	 * }
	 * 
	 * Output
	 * {
    "id": 1,
    "name": "Finance",
    "location": "Delhi",
    "employeeList": [
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
        }
    ]
}
	 * 
	 * POST http://localhost:8080/departments/add
	 * {
	 * 	"name": "Legal",
	 * 	"location": "Mumbai",
	 * }
	 * 
	 * {
    "id": 2,
    "name": "Legal",
    "location": "Mumbai",
    "employeeList": null
}
	 */
	
	@GetMapping("/viewdepartments")
	public ResponseEntity<List<DepartmentDTO>> viewDepartments() {
		log.info("Inside com.example.controller.DepartmentController.viewDepartments");
		List<DepartmentDTO> viewDepartments = departmentService.viewDepartments();
		log.info("Leaving com.example.controller.DepartmentController.viewDepartments\n");
		return new ResponseEntity<List<DepartmentDTO>>(viewDepartments, HttpStatus.OK);
	}
	/*
	 * GET http://localhost:8080/departments/viewdepartments
[
    {
        "id": 1,
        "name": "Finance",
        "location": "Delhi",
        "employeeList": [
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
    },
    {
        "id": 2,
        "name": "Legal",
        "location": "Mumbai",
        "employeeList": []
    }
]
	*/
	
	@GetMapping("/viewdepartmentbyid/{id}")
	public ResponseEntity<DepartmentDTO> viewDepartmentById(@PathVariable("id") int id) {
		log.info("Inside com.example.controller.DepartmentController.viewDepartmentById");
		DepartmentDTO department = departmentService.viewDepartmentById(id);
		log.info("Leaving com.example.controller.DepartmentController.viewDepartmentById\n");
		return new ResponseEntity<DepartmentDTO>(department, HttpStatus.OK);
	}
	/*
	 * http://localhost:8080/departments/viewdepartmentbyid/1
{
    "id": 1,
    "name": "Finance",
    "location": "Delhi",
    "employeeList": [
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
}
	 * */
	
	@GetMapping("/viewdepartmentwithnoemployee")
	public ResponseEntity<List<DepartmentDTO>> viewDepartmentsWithNoEmployees() {
		log.info("Inside com.example.controller.DepartmentController.viewDepartmentsWithNoEmployees");
		List<DepartmentDTO> departments = departmentService.viewDepartmentsWithNoEmployees();
		log.info("Leaving com.example.controller.DepartmentController.viewDepartmentsWithNoEmployees\n");
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	/* GET http://localhost:8080/departments/viewdepartmentwithnoemployee
	 *
[
    {
        "id": 2,
        "name": "Legal",
        "location": "Mumbai",
        "employeeList": []
    }
]
	*/
	@GetMapping("/viewdepartmentwithemployee")
	public ResponseEntity<List<DepartmentDTO>> viewDepartmentsWithEmployees() {
		log.info("Inside com.example.controller.DepartmentController.viewDepartmentsWithEmployees");
		List<DepartmentDTO> departments = departmentService.viewDepartmentsWithAtLeastOneEmployees();
		log.info("Leaving com.example.controller.DepartmentController.viewDepartmentsWithEmployees\n");
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	/*
	 * GET http://localhost:8080/departments/viewdepartmentwithemployee
[
    {
        "id": 1,
        "name": "Finance",
        "location": "Delhi",
        "employeeList": [
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
    }
]
	*/
	
	@GetMapping("/viewdepartmentwithtwoormoreemployees")
	public ResponseEntity<List<DepartmentDTO>> viewdepartmentwithtwoormoreemployees() {
		log.info("Inside com.example.controller.DepartmentController.viewdepartmentwithtwoormoreemployees");
		List<DepartmentDTO> departments = departmentService.viewDepartmentsWithTwoOrMoreEmployees();
		log.info("Leaving com.example.controller.DepartmentController.viewdepartmentwithtwoormoreemployees\n");
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	/*
	 * GET http://localhost:8080/departments/viewdepartmentwithtwoormoreemployees
[
    {
        "id": 1,
        "name": "Finance",
        "location": "Delhi",
        "employeeList": [
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
    }
]
	*/
}