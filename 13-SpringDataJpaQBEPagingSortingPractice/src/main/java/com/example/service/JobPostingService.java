package com.example.service;

import java.util.List;

import com.example.dto.JobPostingDTO;

public interface JobPostingService {
	List<JobPostingDTO> searchJobs(JobPostingDTO searchCriteria);
}
