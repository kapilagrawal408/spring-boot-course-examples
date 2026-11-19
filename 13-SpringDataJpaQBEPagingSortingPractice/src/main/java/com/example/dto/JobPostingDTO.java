package com.example.dto;

import com.example.model.JobPosting.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JobPostingDTO {
  
  private Integer id;
  private String jobTitle;
  private String companyName;
  private WorkMode workMode;
  private Boolean isActive;
  private String techStack;

  public JobPostingDTO(String jobTitle, String companyName, WorkMode workMode, Boolean isActive, String techStack) {
    this.jobTitle = jobTitle;
    this.companyName = companyName;
    this.workMode = workMode;
    this.isActive = isActive;
    this.techStack = techStack;
  }
}