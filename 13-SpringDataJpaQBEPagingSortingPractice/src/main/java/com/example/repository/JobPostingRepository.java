package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.JobPosting;

public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {

}