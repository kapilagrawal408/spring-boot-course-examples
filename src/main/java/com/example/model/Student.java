package com.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "st")
@NamedQuery(name = "Student.rollNumberMoreThan100", query = "SELECT s FROM Student s WHERE s.rollNo > :rollNo")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)	//no setter will be generated for this
	private Integer rollNo;
	
	@EqualsAndHashCode.Exclude	//name will not be used in the hashcode and equals
	@Column(name = "name", length = 20, nullable = false)
	private String name;
	
	@EqualsAndHashCode.Exclude	//percentage will not be used in the hashcode and equals
	@Column(precision = 5, scale = 2, nullable = false)
	private BigDecimal percentage;
	
	@EqualsAndHashCode.Exclude	//registrationDate will not be used in the hashcode and equals
	@Column(updatable = false)
	@Setter(AccessLevel.NONE)	//no setter will be generated for this
	private LocalDateTime registrationDate;
	
	@EqualsAndHashCode.Exclude	//lastModified will not be used in the hashcode and equals
	@Column(insertable = false)	
	private LocalDateTime lastModified;

	//this constructor has to be written manually not possible to write this by lombok
	public Student(String name, BigDecimal percentage, LocalDateTime registrationDate) {
		super();
		this.name = name;
		this.percentage = percentage;
		this.registrationDate = registrationDate;
	}
	
	@PrePersist	//this will be called before the persist() will be called
	public void onPrePersist() {
		registrationDate = LocalDateTime.now();
	}
	
	@PreUpdate	//this will be called before the merge() will be called
	public void onPreUpdate() {
		lastModified = LocalDateTime.now();
	}
}