# Spring Boot JPA First Example

The Eleventh Spring Boot application. It demonstrates Spring Data JPA and MapStruct.

###### Spring Boot Version: 4.1.1

## What this app demonstrates

- Using @PrePersist and @PreUpdate for auditing log
- The @Query and the @NamedQuery annotation
- The named and the positional parameters
- The @Transactional annotation and the @Modifying annotation
- Using MapStruct for entity to DTO conversion
- Using environment variable in the yml file along with their default value

## Tip:
For the sake of brevity only CRUD operations are demonstrated rather than every method defined in the repository.

## Prerequisites
- Java 17 or higher.
- Maven is included via the Maven Wrapper; no separate Maven installation is needed.
- MySql database
- Postman to hit API endpoint

## Running
- Import this project into the Spring-Tool-Suite (STS) and run as spring boot application (Alt + Shift + X, B)
- or Simply run the commands

```
SET DB_USER=database-username
SET DB_USER=database-user-password
SET DB_NAME=database-name
mvnw.cmd clean spring-boot:run
```
- Use postman to hit API end-points

## Configuration

The application name in `src/main/resources/application.yml`:

```
spring:
  application:
    name: SpringDataJPAPractice
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ${DB_USER:root}
    password: ${DB_PASSWORD:root}
    url: jdbc:mysql://localhost:3306/${DB_NAME:SpringDataJPAPractice}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  web:
    resources:
      add-mappings: false
  #mvc: (Not needed in spring 6.1 and onwards)
    #throw-exception-if-no-handler-found: true (Not needed in spring 6.1 and onwards)

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    url: /api-docs
    operationsSorter: method
```

## Output
POST http://localhost:8080/student/add

Request Body

```
{
	"name": "ABC",
	"percentage": 78.25
}
```

Output

```
{
	"rollNo": 1,
	"name": "ABC",
	"percentage": 78.25,
	"registrationDate": "2026-09-02T12:55:25.5240412",
	"lastModified": null
}

+---------+---------------+------+------------+----------------------------+
| roll_no | last_modified | name | percentage | registration_date          |
+---------+---------------+------+------------+----------------------------+
|       1 | NULL          | ABC  |      78.25 | 2026-09-02 12:55:25.524041 |
+---------+---------------+------+------------+----------------------------+
```



GET http://localhost:8080/student/viewstudents

Output

```
[
	{
		"rollNo": 1,
	 	"name": "ABC",
	 	"percentage": 78.25,
	 	"registrationDate": "2026-09-02T12:55:25.524041",
	 	"lastModified": null
	}
]
MIND THE [...] HERE
```



GET http://localhost:8080/student/getStudentByRollNo/1

Output

```
{
	"rollNo": 1,
	"name": "ABC",
	"percentage": 78.25,
	"registrationDate": "2026-09-02T12:55:25.524041",,
	"lastModified": null
}
NO [...] HERE
```



PUT http://localhost:8080/student/updatenameandmarks/1

Request Body

```
{
	"name": "PQR",
	"percentage": 85.25
}
```
 
OUTPUT

```
{
	"rollNo": 1,
	"name": "PQR",
	"percentage": 85.25,
	"registrationDate": "2026-09-01T20:42:58.826389",
	"lastModified": "2026-09-02T12:59:32.238921"
}

+---------+-------------------------------------+------+------------+----------------------------+
| roll_no | last_modified                       | name | percentage | registration_date          |
+---------+-------------------------------------+------+------------+----------------------------+
|       1 | 2026-09-02T12:59:32.238921          | ABC  |      78.25 | 2026-09-02 12:55:25.524041 |
+---------+-------------------------------------+------+------------+----------------------------+
```

DELETE http://localhost:8080/student/deletestudent/1

```
204 NO CONTENT
```