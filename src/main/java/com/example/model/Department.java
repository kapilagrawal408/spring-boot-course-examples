package com.example.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//Setter method for id is needed otherwise Mapper will suffer from an issue
	//"Property "id" has no write accessor in Department for target name "department.id".
	//while converting EmployeeDTO to entity
	
	private String name;
	private String location;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department", fetch = FetchType.EAGER)
	private List<Employee> employeeList;

	public Department(String name, String location, List<Employee> employeeList) {
		super();
		this.name = name;
		this.location = location;
		this.employeeList = employeeList;
	}
}
