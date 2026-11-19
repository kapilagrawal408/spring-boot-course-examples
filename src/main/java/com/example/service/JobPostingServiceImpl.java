package com.example.service;

import com.example.dto.JobPostingDTO;
import com.example.mapper.Entity_DTO_Mapper;
import com.example.model.JobPosting;
import com.example.model.JobPosting.WorkMode;
import com.example.repository.JobPostingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor // Automatically generates constructor for the injected dependencies
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository repository;
    private final Entity_DTO_Mapper mapper;

    @PostConstruct
    public void initData() {
        // Prevent duplicate inserts if the server restarts
        if (repository.count() == 0) {
            log.info("Auto-populating 10 Job Postings for QBE Testing...");
            
            List<JobPosting> jobs = List.of(
                new JobPosting("Junior Java Developer", "TechCorp", WorkMode.REMOTE, true, "Java, Spring Boot, MySQL"),
                new JobPosting("Senior Java Engineer", "TechCorp", WorkMode.HYBRID, true, "Java, Spring Boot, Microservices"),
                new JobPosting("React Frontend Developer", "WebMakers", WorkMode.REMOTE, true, "React, Redux, TypeScript"),
                new JobPosting("Full Stack Developer", "Innovate LLC", WorkMode.ONSITE, false, "Java, Angular, PostgreSQL"),
                new JobPosting("Data Scientist", "DataWorks", WorkMode.HYBRID, true, "Python, TensorFlow, SQL"),
                new JobPosting("DevOps Engineer", "CloudNet", WorkMode.REMOTE, true, "AWS, Docker, Kubernetes"),
                new JobPosting("QA Automation Engineer", "TechCorp", WorkMode.ONSITE, true, "Java, Selenium, TestNG"),
                new JobPosting("Junior Python Developer", "StartupInc", WorkMode.REMOTE, false, "Python, Django"),
                new JobPosting("Backend Developer", "FinTech Solutions", WorkMode.HYBRID, true, "Java, Spring Boot, Oracle"),
                new JobPosting("UI/UX Designer", "CreativeDesign", WorkMode.REMOTE, true, "Figma, Adobe XD")
            );
            
            repository.saveAll(jobs);
            log.info("Successfully inserted 10 Job Postings.");
        }
    }

    @Override
    public List<JobPostingDTO> searchJobs(JobPostingDTO searchCriteria) {
    		log.info("Inside com.example.service.JobPostingServiceImpl.searchJobs");
    	
        // 1. Convert incoming DTO to Entity (This acts as the QBE "Probe")
    		JobPosting entity = mapper.toEntity(searchCriteria);
    		
        // 2. Define the Matcher rules
    		// Make all string comparisons case-insensitive (e.g., "java" matches "Java")
    		// Use LIKE '%value%' instead of exact match for all Strings
    		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
        
        // 3. Combine the Probe (Dynamic Data) and the Matcher (Static Rules)
    		Example<JobPosting> example = Example.of(entity, matcher);
        
        // 4. Let Spring Data JPA dynamically generate and execute the SQL
    		List<JobPosting> all = repository.findAll(example);
    		
    		log.info("Leaving com.example.service.JobPostingServiceImpl.searchJobs");
    		
        // 5. Convert the Entity list back to a DTO list
        return mapper.toDtoList(all);
    }
}