package com.example.dto;

import java.time.LocalDate;

import com.example.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class EmployeeDTO {
	private Integer id;
	private String name;
	private Address address;
	
	private LocalDate joiningDate;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer departmentId;
}