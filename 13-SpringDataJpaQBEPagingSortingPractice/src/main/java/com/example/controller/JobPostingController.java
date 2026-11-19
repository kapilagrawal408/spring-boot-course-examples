package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.JobPostingDTO;
import com.example.service.JobPostingService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
public class JobPostingController {

    private JobPostingService jobPostingService;

    @PostMapping("/search")
    public ResponseEntity<List<JobPostingDTO>> searchJobs(@RequestBody JobPostingDTO searchCriteria) {
    		log.info("Inside com.example.controller.JobPostingController.searchJobs");
    		
        List<JobPostingDTO> results = jobPostingService.searchJobs(searchCriteria);
        
        log.info("Leaving com.example.controller.JobPostingController.searchJobs");
        return new ResponseEntity<List<JobPostingDTO>>(results, HttpStatus.OK);
    }
    /*
     * POST http://localhost:8080/jobs/search
     * Request Body
     * {
     *  "jobTitle": "java"
     *  }
     *  
     *  Output
     *  [
		    {
		        "companyName": "TechCorp",
		        "id": 1,
		        "isActive": true,
		        "jobTitle": "Junior Java Developer",
		        "techStack": "Java, Spring Boot, MySQL",
		        "workMode": "REMOTE"
		    },
		    {
		        "companyName": "TechCorp",
		        "id": 2,
		        "isActive": true,
		        "jobTitle": "Senior Java Engineer",
		        "techStack": "Java, Spring Boot, Microservices",
		        "workMode": "HYBRID"
		    }
		]
     *  
     *  POST http://localhost:8080/jobs/search
     *  Request Body
     *  {
     *    "workMode": "REMOTE"
     *  }
     *  
     *  Output
     *  [
		    {
		        "companyName": "TechCorp",
		        "id": 1,
		        "isActive": true,
		        "jobTitle": "Junior Java Developer",
		        "techStack": "Java, Spring Boot, MySQL",
		        "workMode": "REMOTE"
		    },
		    {
		        "companyName": "WebMakers",
		        "id": 3,
		        "isActive": true,
		        "jobTitle": "React Frontend Developer",
		        "techStack": "React, Redux, TypeScript",
		        "workMode": "REMOTE"
		    },
		    {
		        "companyName": "CloudNet",
		        "id": 6,
		        "isActive": true,
		        "jobTitle": "DevOps Engineer",
		        "techStack": "AWS, Docker, Kubernetes",
		        "workMode": "REMOTE"
		    },
		    {
		        "companyName": "StartupInc",
		        "id": 8,
		        "isActive": false,
		        "jobTitle": "Junior Python Developer",
		        "techStack": "Python, Django",
		        "workMode": "REMOTE"
		    },
		    {
		        "companyName": "CreativeDesign",
		        "id": 10,
		        "isActive": true,
		        "jobTitle": "UI/UX Designer",
		        "techStack": "Figma, Adobe XD",
		        "workMode": "REMOTE"
		    }
		]
     *  
     *  POST http://localhost:8080/api/jobs/search
     *  Request Body
     *  {
     *    "isActive": false
     *  }
     *  
     *  Output
		[
		    {
		        "companyName": "Innovate LLC",
		        "id": 4,
		        "isActive": false,
		        "jobTitle": "Full Stack Developer",
		        "techStack": "Java, Angular, PostgreSQL",
		        "workMode": "ONSITE"
		    },
		    {
		        "companyName": "StartupInc",
		        "id": 8,
		        "isActive": false,
		        "jobTitle": "Junior Python Developer",
		        "techStack": "Python, Django",
		        "workMode": "REMOTE"
		    }
		]
     *  
     *  POST http://localhost:8080/api/jobs/search
     *  Request Body
     *  {
     *  	  "companyName": "TechCorp",
     *  	  "isActive": true,
     *    "workMode": "ONSITE"
     *  }
     *  
     *  Output
     *  [
		    {
		        "companyName": "TechCorp",
		        "id": 7,
		        "isActive": true,
		        "jobTitle": "QA Automation Engineer",
		        "techStack": "Java, Selenium, TestNG",
		        "workMode": "ONSITE"
		    }
		]
     */
}