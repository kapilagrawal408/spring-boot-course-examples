package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "job_postings")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JobPosting {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Setter(value = AccessLevel.NONE)
  private Integer id;

  @Column(name = "job_title", nullable = false, length = 100)
  private String jobTitle;

  @Column(name = "company_name", nullable = false, length = 100)
  private String companyName;

  @Enumerated(EnumType.STRING)
  @Column(name = "work_mode", nullable = false)
  private WorkMode workMode;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;	//will be mapped to bit data type

  @Column(name = "tech_stack", length = 200)
  private String techStack;

  public JobPosting(String jobTitle, String companyName, WorkMode workMode, Boolean isActive, String techStack) {
    this.jobTitle = jobTitle;
    this.companyName = companyName;
    this.workMode = workMode;
    this.isActive = isActive;
    this.techStack = techStack;
  }

  public enum WorkMode {
    REMOTE, HYBRID, ONSITE
  }
}
