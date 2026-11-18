package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.dto.StudentDTO;
import com.example.model.Student;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	//THE CONTROLLER AND SERVICE METHODS ARE NOT WRITTEN FOR THESE QUERIES FOR THE SAKE OF TIME SAVING
	//BUT IMPLEMENTATION OF CRUD OPERATION IS IN SERVICE AND CONTROLLER
	
	List<Student> rollNumberMoreThan100(@Param("rollNo") Integer rollNo);
	
	//For Query: SELECT s FROM Student s WHERE name = :name
	Optional<Student> findByName(String name);
	
	//For Query: SELECT s FROM Student WHERE rollNo > :rollNo and percentage < :percentage
	List<Student> findByRollNoGreaterThanAndPercentageLessThan(Integer rollNo, Double percentage);
	//Note : In findBy method syntax we will use entity variable names.
	
	//For named parameter
	@Query("SELECT s FROM Student s WHERE s.percentage BETWEEN :start AND :end")
	List<Student> anyName(@Param("start") Double start, @Param("end") Double end);
	
	//For positional parameters
	@Query("SELECT s FROM Student s WHERE rollNo = ?1")
	List<Student> anyNameAgain(Integer rollNo);
	
	@Query(nativeQuery = true, value = "SELECT * FROM st")
	List<Student> anyNameAgain2();
	
	@Query("SELECT new com.example.dto.StudentDTO(s.name, s.percentage) FROM Student s")
	List<StudentDTO> anyNameAgain();
	
	@Transactional
	@Modifying
	@Query("Update Student s SET s.name = :name WHERE s.rollNo = :rollNo")
	Integer updateName(@Param("name") String name, @Param("rollNo") Integer rollNo);
	
	//SELECT s FROM Student s WHERE percentage > :percentage LIMIT 0,2
	List<Student> findTop2ByPercentageGreaterThan(Double percentage);
	
	//SELECT s FROM Student s ORDER BY name DESC limit 0,3
	List<Student> findTop3ByOrderByNameDesc();
}