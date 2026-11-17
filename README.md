# Spring Exception Handling

The Seventh Spring Boot application. It demonstrates exception handling in Spring

###### Spring Boot Version: 4.1.1

## What this app demonstrates

- Demonstrates @ExceptionHandler, @RestControllerAdvice annotation and ProblemDetail object 
- Demonstrate Handling of User Defined Exception and NoHandlerFoundException

## Prerequisites
- Java 17 or higher.
- Maven is included via the Maven Wrapper; no separate Maven installation is needed.
- Postman to hit API endpoint

## Running
- Import this project into the Spring-Tool-Suite (STS) and run as spring boot application (Alt + Shift + X, B)
- or Simply run the command `mvnw.cmd clean spring-boot:run` from the root folder of the project in the terminal
- Use postman to hit API end-point

## Configuration

The application name in `src/main/resources/application.properties`:

```
spring.application.name=Spring_Exception_Handling
```

## Output
GET http://localhost:8080/students/getstudent/10

```
{
	"rollNo": 10,
	"name": "ABC",
	"marks": 65.25
}
```


GET http://localhost:8080/students/getstudent/100

```
{
    "detail": "No Student found with roll number 100",
    "instance": "/students/getstudent/100",
    "status": 404,
    "title": "Missing Student"
}
```

