# Spring Boot JPA QBE and Pagination-Sorting Example

The thirteenth Spring Boot application. It demonstrates Query by Example using the JobApplication entity and Pagination-Sorting using the ResidentialPlot entity.

###### Spring Boot Version: 4.1.1

## What this app demonstrates

- Query By Example
- Pagination and Sorting
- Using MapStruct to perform conversion Entity to DTO conversion and vice versa
- Using environment variable in the yml file along with their default value
- Using POST method to perform searching as special case
- Validation of pageSize and pageNumber as they should be positive values. The error messages are taken from ValidationMessages.properties file.

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
SET DB_PASSWORD=database-user-password
SET DB_NAME=database-name
mvnw.cmd clean spring-boot:run
```
- Use postman to hit API end-points

## Configuration

The application name in `src/main/resources/application.yml`:

```
spring:
  application:
    name: SpringDataJpaQBEPagingSortingPractice
  datasource:
    url: jdbc:mysql://localhost:3306/${DB_NAME:SpringDataJpaQBEPagingSortingPractice}
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ${DB_USER:root}
    password: ${DB_PASSWORD:root}
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

##### For Query By Example
POST http://localhost:8080/jobs/search

Request Body

```
{
  "jobTitle": "java"
}
```
 
Output


```
[
  {
    "companyName": "TechCorp",
	"id": 1,
	"isActive": true,
	"jobTitle": "Junior Java Developer",
	"techStack": "Java, Spring Boot, MySQL",
	"workMode": "REMOTE"
  },
  {
	"companyName": "TechCorp",
	"id": 2,
	"isActive": true,
	"jobTitle": "Senior Java Engineer",
	"techStack": "Java, Spring Boot, Microservices",
	"workMode": "HYBRID"
  }
]
```


 
POST http://localhost:8080/jobs/search

Request Body

```
{
  "workMode": "REMOTE"
}
```

Output

```
[
  {
    "companyName": "TechCorp",
	"id": 1,
	"isActive": true,
	"jobTitle": "Junior Java Developer",
	"techStack": "Java, Spring Boot, MySQL",
	"workMode": "REMOTE"
  },
  {
    "companyName": "WebMakers",
	"id": 3,
	"isActive": true,
	"jobTitle": "React Frontend Developer",
	"techStack": "React, Redux, TypeScript",
	"workMode": "REMOTE"
  },
  {
    "companyName": "CloudNet",
	"id": 6,
	"isActive": true,
	"jobTitle": "DevOps Engineer",
	"techStack": "AWS, Docker, Kubernetes",
	"workMode": "REMOTE"
  },
  {
    "companyName": "StartupInc",
	"id": 8,
	"isActive": false,
	"jobTitle": "Junior Python Developer",
	"techStack": "Python, Django",
	"workMode": "REMOTE"
  },
  {
    "companyName": "CreativeDesign",
	"id": 10,
	"isActive": true,
	"jobTitle": "UI/UX Designer",
	"techStack": "Figma, Adobe XD",
	"workMode": "REMOTE"
  }
]
```

 

POST http://localhost:8080/api/jobs/search
Request Body

```
{
  "isActive": false
}
```
 
Output

```
[
  {
    "companyName": "Innovate LLC",
	"id": 4,
	"isActive": false,
	"jobTitle": "Full Stack Developer",
	"techStack": "Java, Angular, PostgreSQL",
	"workMode": "ONSITE"
  },
  {
    "companyName": "StartupInc",
	"id": 8,
	"isActive": false,
	"jobTitle": "Junior Python Developer",
	"techStack": "Python, Django",
	"workMode": "REMOTE"
  }
]
```



POST http://localhost:8080/api/jobs/search

Request Body

```
{
  "companyName": "TechCorp",
  "isActive": true,
  "workMode": "ONSITE"
}
```
 
Output

```
[
  {
    "companyName": "TechCorp",
	"id": 7,
	"isActive": true,
	"jobTitle": "QA Automation Engineer",
	"techStack": "Java, Selenium, TestNG",
	"workMode": "ONSITE"
  }
]
```

##### For Pagination and Sorting

GET http://localhost:8080/residentialplot/getplots/1/2

Output

```
{
  "content": [
    {
	  "areaInSqFt": 2200,
	  "facingDirection": "SOUTH",
	  "id": 5,
	  "isVastuCompliant": false,
	  "listedDate": "2026-09-12T20:05:11.995541",
	  "locality": "Sector 49",
	  "priceInLakhs": 145.0
	},
	{
	  "areaInSqFt": 2000,
	  "facingDirection": "NORTH",
	  "id": 9,
	  "isVastuCompliant": true,
	  "listedDate": "2026-09-12T20:05:11.995541",
	  "locality": "Sector 53",
	  "priceInLakhs": 132.25
	}
  ],
  "firstPage": true,
  "hasNextPage": true,
  "hasPreviousPage": false,
  "isFirstPage": true,
  "isLastPage": false,
  "lastPage": false,
  "totalPages": 5,
  "totalRecords": 10,
  "totalRecordsThisPage": 2
}
```

GET http://localhost:8080/residentialplot/getplots/3/3

Output

```
{
  "content": [
    {
	  "areaInSqFt": 1200,
	  "facingDirection": "NORTH",
	  "id": 1,
	  "isVastuCompliant": true,
	  "listedDate": "2026-09-12T20:05:11.994543",
	  "locality": "Sector 45",
	  "priceInLakhs": 85.5
	},
	{
	  "areaInSqFt": 1100,
	  "facingDirection": "EAST",
	  "id": 6,
	  "isVastuCompliant": true,
	  "listedDate": "2026-09-12T20:05:11.995541",
	  "locality": "Sector 50",
	  "priceInLakhs": 78.0
	},
    {
      "areaInSqFt": 900,
	  "facingDirection": "WEST",
	  "id": 3,
	  "isVastuCompliant": false,
	  "listedDate": "2026-09-12T20:05:11.995541",
	  "locality": "Sector 47",
	  "priceInLakhs": 60.25
	}
  ],
  "firstPage": false,
  "hasNextPage": true,
  "hasPreviousPage": true,
  "isFirstPage": false,
  "isLastPage": false,
  "lastPage": false,
  "totalPages": 4,
  "totalRecords": 10,
  "totalRecordsThisPage": 3
}
```

	
GET http://localhost:8080/residentialplot/getplots/6/2

Output

```
{
  "detail": "No residential plot(s) Found for page 6",
  "instance": "/residentialplot/getplots/6/2",
  "status": 400,
  "title": "No residential plot found"
}
```
	
GET http://localhost:8080/residentialplot/getplots/0/0

Output

```
{
  "detail": "URL parameter validation failed.",
  "instance": "/residentialplot/getplots/0/0",
  "status": 400,
  "title": "Invalid URL Parameter",
  "errors": {
      "pageNumber": "Page number must be a positive number",
      "pageSize": "Page size must be a positive number"
  }
}
```