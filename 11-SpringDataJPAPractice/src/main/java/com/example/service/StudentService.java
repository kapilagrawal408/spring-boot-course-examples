package com.example.service;

import java.util.List;

import com.example.dto.StudentDTO;

public interface StudentService {
	public StudentDTO addStudent(StudentDTO student);
	public List<StudentDTO> viewStudents();
	public StudentDTO getStudentByRollNo(Integer rollNo);
	public StudentDTO updateNameAndMarks(Integer rollNo, StudentDTO student);
	public void deleteStudent(Integer rollNo);
}