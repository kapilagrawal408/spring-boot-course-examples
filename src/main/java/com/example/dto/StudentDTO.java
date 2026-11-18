package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class StudentDTO {
	private Integer rollNo;
	private String name;
	private BigDecimal percentage;
	private LocalDateTime registrationDate;
	private LocalDateTime lastModified;
	
	public StudentDTO(String name, BigDecimal percentage) {
		super();
		this.name = name;
		this.percentage = percentage;
	}
	//will be used for-
	//@Query("SELECT new com.example.dto.StudentDTO(s.name, s.percentage) FROM Student s")
}