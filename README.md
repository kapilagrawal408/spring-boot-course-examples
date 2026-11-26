# Spring_Profiling_Demo

The eighteenth Spring Boot application. It demonstrates use of Spring Profiling.

###### Spring Boot Version: 4.1.1

## What this app demonstrates
- When 'prod' profile is active then mysql database is used.
- When 'dev' profile is active then H2 database is used.

## Prerequisites
- Java 17 or higher.
- Maven is included via the Maven Wrapper; no separate Maven installation is needed.
- Mysql
- Postman to hit API endpoint

## Running
- Import this project into the Spring-Tool-Suite (STS) and run as spring boot application (Alt + Shift + X, B)
- or Simply run the commands

The application-prod.yml file

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/Spring_Profiling_Demo
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update # In real prod, this is 'validate', but 'update' is fine for demo
    show-sql: false # Turn off for performance in production
```

The application-dev.yml file

```
spring:
  datasource:
    url: jdbc:h2:mem:devdb
    driverClassName: org.h2.Driver
    username: sa
    password: password
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
  h2:
    console:
      enabled: true # Access at http://localhost:8080/h2-console
```

The application.yml file

```
spring:
  application:
    name: Spring_Profiling_Demo
  profiles:
    active: prod
  web:
    resources:
      add-mappings: false

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    url: /api-docs
    operationsSorter: method
```

## Output

POST http://localhost:8080/api/v1/products

Request Body

```
{
  "name": "chair",
  "category": "furniture",
  "price": 2563
}
```

Output

```
{
  "id": "1"
  "name": "chair",
  "category": "furniture",
  "price": 2563
}
```

GET http://localhost:8080/api/v1/products

Output

```
[
	{
		"category": "furniture",
		"id": 1,
		"name": "chair",
		"price": 2563.0
	}
]
```