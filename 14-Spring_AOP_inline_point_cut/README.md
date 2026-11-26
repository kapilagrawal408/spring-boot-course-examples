# Spring_AOP_inline_point_cut

The fourteenth Spring Boot application. It demonstrates Inline point cut in Aspect Oriented Programming.

###### Spring Boot Version: 4.1.1

## What this app demonstrates

- @Aspect, @Before, @AfterReturning, @AfterThrowing, @After, @Around
- The JoinPoint and ProceedingJoinPoint interface and their methods

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

POST http://localhost:8080/api/v1/air-conditioners

Request Body

```
{
  "deviceId": "LG-AC-1001",
  "companyName": "LG",
  "modelName": "Dual Inverter 1.5 Ton",
  "price": 45000.00,
  "mfdDate": "2023-05-15"
}
```
 
Output

```
{
  "id": 1,
  "companyName": "LG",
  "deviceId": "LG-AC-1001",
  "mfdDate": "2023-05-15",
  "modelName": "Dual Inverter 1.5 Ton",
  "price": 45000.0
}

Output on console

2026-09-15 13:02:13.915 [http-nio-8080-exec-9] INFO  com.example.aspect.AspectLogger - [@Before] Received request to execute endpoint: add
2026-09-15 13:02:14.759 [http-nio-8080-exec-9] INFO  com.example.aspect.AspectLogger - [@AfterReturning] Successfully added AC. Database assigned ID: 1
2026-09-15 13:02:14.759 [http-nio-8080-exec-9] INFO  com.example.aspect.AspectLogger - [@After] Service execution finished for: add
```

Again POST http://localhost:8080/api/v1/air-conditioners

Request Body

```
{
  "deviceId": "LG-AC-1001",
  "companyName": "LG",
  "modelName": "Dual Inverter 1.5 Ton",
  "price": 45000.00,
  "mfdDate": "2023-05-15"
}
```

Output

```
HTTP STATUS: 409
{
  "detail": "Air Conditioner with Device ID LG-AC-1001 already exists.",
  "instance": "/api/v1/air-conditioners",
  "status": 409,
  "title": "Duplicate Device ID"
}

Output on console

2026-09-15 13:09:39.660 [http-nio-8080-exec-10] INFO  com.example.aspect.AspectLogger - [@Before] Received request to execute endpoint: add
2026-09-15 13:09:39.721 [http-nio-8080-exec-10] INFO  com.example.aspect.AspectLogger - [@After] Service execution finished for: add
2026-09-15 13:09:39.752 [http-nio-8080-exec-10] WARN  o.s.w.s.m.m.a.ExceptionHandlerExceptionResolver - Resolved [com.example.exception.ACAlreadyExistsWithDeviceIdException: Air Conditioner with Device ID LG-AC-1001 already exists.]
```



POST http://localhost:8080/api/v1/air-conditioners/search

Request Body

```
{
    "companyName": "LG",
    "price": 45000.00
}
```

Output

```
[
    {
        "companyName": "LG",
        "deviceId": "LG-AC-1001",
        "id": 1,
        "mfdDate": "2023-05-15",
        "modelName": "Dual Inverter 1.5 Ton",
        "price": 45000.0
    }
]

Output on Console
2026-09-15 13:21:40.534 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@Before] Received request to execute endpoint: searchByAnyDetails
2026-09-15 13:21:40.536 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@Around - START] Starting performance profiling for searchByAnyDetails
2026-09-15 13:21:40.725 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@After] Service execution finished for: searchByAnyDetails
2026-09-15 13:21:40.725 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@Around - END] searchByAnyDetails executed in 189 ms
```

Again POST http://localhost:8080/api/v1/air-conditioners/search

Request Body

```
{
  "companyName": "Sony"
}
```

Output

```
Http Status: 404

{
  "detail": "No Air Conditioners found matching the given criteria.",
  "instance": "/api/v1/air-conditioners/search",
  "status": 404,
  "title": "Air Conditioner Not Found"
}

Output on Console
2026-09-15 13:28:17.355 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@Before] Received request to execute endpoint: searchByAnyDetails
2026-09-15 13:28:17.355 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@Around - START] Starting performance profiling for searchByAnyDetails
2026-09-15 13:28:17.366 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@After] Service execution finished for: searchByAnyDetails
```



PUT http://localhost:8080/api/v1/air-conditioners/1

Request Body

```
{
  "deviceId": "LG-AC-1001",
  "companyName": "LG",
  "modelName": "Dual Inverter 1.5 Ton - UPDATED VERSION",
  "price": 43500.00,
  "mfdDate": "2023-05-15"
}
```

Output

```
{
  "companyName": "LG",
  "deviceId": "LG-AC-1001",
  "id": 1,
  "mfdDate": "2023-05-15",
  "modelName": "Dual Inverter 1.5 Ton - UPDATED VERSION",
  "price": 43500.0
}

Output on console
2026-09-15 13:43:13.859 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@Before] Received request to execute endpoint: addOrUpdate
2026-09-15 13:43:13.919 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@After] Service execution finished for: addOrUpdate
```


Again PUT http://localhost:8080/api/v1/air-conditioners/999

Request Body

```
{
  "deviceId": "LG-AC-1001",
  "companyName": "Sony",
  "modelName": "Bravia Cool",
  "price": 60000.00,
  "mfdDate": "2024-01-01"
}
```

Output

```
{
  "detail": "Cannot create new AC. Device ID LG-AC-1001 is already taken.",
  "instance": "/api/v1/air-conditioners/999",
  "status": 409,
  "title": "Duplicate Device ID"
}

Output on console
2026-09-15 13:46:40.341 [http-nio-8080-exec-3] INFO  com.example.aspect.AspectLogger - [@Before] Received request to execute endpoint: addOrUpdate
2026-09-15 13:46:40.415 [http-nio-8080-exec-3] INFO  com.example.aspect.AspectLogger - [@After] Service execution finished for: addOrUpdate
```



PATCH http://localhost:8080/api/v1/air-conditioners/1/price?newPrice=42000.50

Output

```
{
  "companyName": "LG",
  "deviceId": "LG-AC-1001",
  "id": 1,
  "mfdDate": "2023-05-15",
  "modelName": "Dual Inverter 1.5 Ton - UPDATED VERSION",
  "price": 42000.5
}

Output on console
2026-09-15 13:50:40.536 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@Before] Received request to execute endpoint: editPrice
2026-09-15 13:50:40.553 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@After] Service execution finished for: editPrice
```

Again PATCH http://localhost:8080/api/v1/air-conditioners/999/price?newPrice=42000.50

Output

```
{
  "detail": "Cannot update price. Air Conditioner with ID 999 not found.",
  "instance": "/api/v1/air-conditioners/999/price",
  "status": 404,
  "title": "Air Conditioner Not Found"
}

Output on console
2026-09-15 13:51:55.761 [http-nio-8080-exec-3] INFO  com.example.aspect.AspectLogger - [@Before] Received request to execute endpoint: editPrice
2026-09-15 13:51:55.767 [http-nio-8080-exec-3] INFO  com.example.aspect.AspectLogger - [@After] Service execution finished for: editPrice
```



DELETE http://localhost:8080/api/v1/air-conditioners/1

Output

```
Http Status: 204

2026-09-15 14:07:44.177 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@Before] Received request to execute endpoint: deleteAirConditionById
2026-09-15 14:07:44.255 [http-nio-8080-exec-1] INFO  com.example.aspect.AspectLogger - [@After] Service execution finished for: deleteAirConditionById
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
2026-09-15 14:08:52.622 [http-nio-8080-exec-5] INFO  com.example.aspect.AspectLogger - [@Before] Received request to execute endpoint: deleteAirConditionById
2026-09-15 14:08:52.634 [http-nio-8080-exec-5] ERROR com.example.aspect.AspectLogger - [@AfterThrowing] Failed to delete AC in method deleteAirConditionById. Reason: Cannot delete. Air Conditioner with ID 999 not found.
2026-09-15 14:08:52.634 [http-nio-8080-exec-5] INFO  com.example.aspect.AspectLogger - [@After] Service execution finished for: deleteAirConditionById
```