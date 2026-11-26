# Spring_AOP_named_point_cut

The fifteenth Spring Boot application. It demonstrates named point cut in Aspect Oriented Programming.

###### Spring Boot Version: 4.1.1

## What this app demonstrates

- @Pointcut, within, @AfterThrowing, @Around

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
    name: Spring_AOP_inline_point_cut
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ${DB_USER:root}
    password: ${DB_PASSWORD:root}
    url: jdbc:mysql://localhost:3306/${DB_NAME:Spring_AOP_inline_point_cut}
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

Add a record in the table `air_conditioners` using following query

```
INSERT INTO air_conditioners VALUES (2, 'Samsung', 'SS-AC-2002', '2023-06-20', 'WindFree 1.5 Ton', 48000.00);
```



PATCH http://localhost:8080/api/v1/air-conditioners/2/price?newPrice=45000.50

Output

```
{
  "companyName": "Samsung",
  "deviceId": "SS-AC-2002",
  "id": 2,
  "mfdDate": "2023-06-20",
  "modelName": "WindFree 1.5 Ton",
  "price": 45000.5
}

Output on console
2026-09-15 19:39:31.759 [http-nio-8080-exec-2] INFO  com.example.aspect.AspectLogger - Enter: com.example.controller.AirConditionerController.editPrice() with argument[s] = [2, 45000.5]
2026-09-15 19:39:31.872 [http-nio-8080-exec-2] INFO  com.example.aspect.AspectLogger - Enter: com.example.service.AirConditionerServiceImpl.editPrice() with argument[s] = [2, 45000.5]
2026-09-15 19:39:31.938 [http-nio-8080-exec-2] INFO  com.example.aspect.AspectLogger - Exit: com.example.service.AirConditionerServiceImpl.editPrice() with result = AirConditionerDTO(id=2, deviceId=SS-AC-2002, companyName=Samsung, modelName=WindFree 1.5 Ton, price=45000.5, mfdDate=2023-06-20)
2026-09-15 19:39:32.002 [http-nio-8080-exec-2] INFO  com.example.aspect.AspectLogger - Exit: com.example.controller.AirConditionerController.editPrice() with result = <200 OK OK,AirConditionerDTO(id=2, deviceId=SS-AC-2002, companyName=Samsung, modelName=WindFree 1.5 Ton, price=45000.5, mfdDate=2023-06-20),[]>
```



PATCH http://localhost:8080/api/v1/air-conditioners/999/price?newPrice=45000.50

Output

```
{
	"detail": "Cannot update price. Air Conditioner with ID 999 not found.",
	"instance": "/api/v1/air-conditioners/999/price",
	"status": 404,
	"title": "Air Conditioner Not Found"
}

2026-09-15 19:41:11.390 [http-nio-8080-exec-4] INFO  com.example.aspect.AspectLogger - Enter: com.example.controller.AirConditionerController.editPrice() with argument[s] = [999, 45000.5]
2026-09-15 19:41:11.405 [http-nio-8080-exec-4] INFO  com.example.aspect.AspectLogger - Enter: com.example.service.AirConditionerServiceImpl.editPrice() with argument[s] = [999, 45000.5]

2026-09-15 19:41:11.415 [http-nio-8080-exec-4] ERROR com.example.aspect.AspectLogger - Exception in com.example.controller.AirConditionerController.editPrice() with cause = 'NULL' and exception = 'Cannot update price. Air Conditioner with ID 999 not found.'
com.example.exception.NoACFoundException: Cannot update price. Air Conditioner with ID 999 not found.
...
at java.base/java.lang.Thread.run(Thread.java:1474)
```



DELETE http://localhost:8080/api/v1/air-conditioners/999

Output

```
{
	"detail": "Cannot delete. Air Conditioner with ID 999 not found.",
	"instance": "/api/v1/air-conditioners/999",
	"status": 404,
	"title": "Air Conditioner Not Found"
}

2026-09-15 19:47:32.464 [http-nio-8080-exec-6] INFO  com.example.aspect.AspectLogger - Enter: com.example.controller.AirConditionerController.deleteAirConditionById() with argument[s] = [999]
2026-09-15 19:47:32.467 [http-nio-8080-exec-6] INFO  com.example.aspect.AspectLogger - Enter: com.example.service.AirConditionerServiceImpl.deleteAirConditionById() with argument[s] = [999]

2026-09-15 19:47:33.100 [http-nio-8080-exec-6] ERROR com.example.aspect.AspectLogger - Exception in com.example.service.AirConditionerServiceImpl.deleteAirConditionById() with cause = 'NULL' and exception = 'Cannot delete. Air Conditioner with ID 999 not found.'
com.example.exception.NoACFoundException: Cannot delete. Air Conditioner with ID 999 not found.
at com.example.service.AirConditionerServiceImpl.deleteAirConditionById(AirConditionerServiceImpl.java:86)
...
at java.base/java.lang.Thread.run(Thread.java:1474)

2026-09-15 19:47:33.105 [http-nio-8080-exec-6] ERROR com.example.aspect.AspectLogger - Exception in com.example.controller.AirConditionerController.deleteAirConditionById() with cause = 'NULL' and exception = 'Cannot delete. Air Conditioner with ID 999 not found.'
com.example.exception.NoACFoundException: Cannot delete. Air Conditioner with ID 999 not found.
at com.example.service.AirConditionerServiceImpl.deleteAirConditionById(AirConditionerServiceImpl.java:86)
...
at java.base/java.lang.Thread.run(Thread.java:1474)
```