package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.Student;

@RepositoryRestResource(path = "students", collectionResourceRel = "scholars")
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByGrade(@Param("grade") String grade);
}

/*
POST http://localhost:8080/students
{
  "name": "John Doe",
  "grade": "A"
}

Output
{
   "_links": {
       "self": {
           "href": "http://localhost:8080/students/1"
       },
       "student": {
           "href": "http://localhost:8080/students/1"
       }
   },
   "name": "John Doe",
   "grade": "A"
}

GET http://localhost:8080/students

Output
{
   "_embedded": {
       "students": [
           {
               "_links": {
                   "self": {
                       "href": "http://localhost:8080/students/1"
                   },
                   "student": {
                       "href": "http://localhost:8080/students/1"
                   }
               },
               "name": "John Doe",
               "grade": "A"
           }
       ]
   },
   "_links": {
       "self": {
           "href": "http://localhost:8080/students?page=0&size=20"
       },
       "profile": {
           "href": "http://localhost:8080/profile/students"
       },
       "search": {
           "href": "http://localhost:8080/students/search"
       }
   },
   "page": {
       "number": 0,
       "size": 20,
       "totalElements": 1,
       "totalPages": 1
   }
}

GET http://localhost:8080/students/search/findByGrade?grade=A

Output
{
   "_embedded": {
       "students": [
           {
               "_links": {
                   "self": {
                       "href": "http://localhost:8080/students/1"
                   },
                   "student": {
                       "href": "http://localhost:8080/students/1"
                   }
               },
               "name": "John Doe",
               "grade": "A"
           }
       ]
   },
   "_links": {
       "self": {
           "href": "http://localhost:8080/students/search/findByGrade?grade=A"
       }
   }
}


PUT http://localhost:8080/students/1

{
 "name": "John Doe",
 "grade": "B"
}

Output
405 Method not allowed

DELETE http://localhost:8080/students/1
405 Method not allowed
*/