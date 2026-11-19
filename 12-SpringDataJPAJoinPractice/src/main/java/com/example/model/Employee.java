package com.example.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;
	private String name;
	
	@Embedded
	private Address address;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d/M/y")
	private LocalDate joiningDate;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	@ToString.Exclude
	private Department department;
	
	public Employee(String name, LocalDate joiningDate, Department department, Address address) {
		super();
		this.name = name;
		this.joiningDate = joiningDate;
		this.department = department;
		this.address = address;
	}
	
	@ToString.Include(name = "department")
    private Integer getDepartmentIdForToString() {
		return department != null ? department.getId() : null;
    }
}