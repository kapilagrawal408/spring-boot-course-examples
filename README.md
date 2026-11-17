# First Web Application

The fifth Spring Boot application. It demonstrates on very simple "Hello World" response from API endpoint

###### Spring Boot Version: 4.1.1

## What this app demonstrates

- Creating an method in controller with @GetMapping annotation 
- Using spring-boot-starter-webmvc dependency

## Prerequisites
- Java 17 or higher.
- Maven is included via the Maven Wrapper; no separate Maven installation is needed.
- Postman to hit API endpoint

## Running
- Import this project into the Spring-Tool-Suite (STS) and run as spring boot application (Alt + Shift + X, B)
- or Simply run the command `mvnw.cmd clean spring-boot:run"` from the root folder of the project in the terminal
- Use postman to hit API end-point with POST request

````
GET http://localhost:8080/hello
````
## Configuration

The application name in `src/main/resources/application.properties`:

```
spring.application.name=First_Web_Application
```

## Output
````
Hello World
````