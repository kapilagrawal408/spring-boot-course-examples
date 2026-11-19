# Spring Boot JPA Join Example

The Twelfth Spring Boot application. It demonstrates Join operations in the Spring Data JPA and MapStruct.

###### Spring Boot Version: 4.1.1

## What this app demonstrates

- OneToMany, Embedded, ManyToOne, JsonFormat, JsonProperty annotation
- Use of Is Empty, Is Not Empty
- Using MapStruct to perform conversion Entity to DTO conversion and vice versa
- Using environment variable in the yml file along with their default value
.

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
    name: SpringDataJPAJoinPractice
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/${DB_NAME:springdatajpajoinpractice}
    username: ${DB_USER:root}
    password: ${DB_PASSWORD:root}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
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
POST http://localhost:8080/departments/add

Request Body

```
{
	"name": "Finance",
	"location": "Delhi",
	"employeeList": [
		{
			"name": "Abhay",
			"joiningDate": "2026-09-03",
			"address": {
				"city": "Faridabad",
				"state": "Haryana",
				"zipcode": 453422
			}
		},
		{
			"name": "Bhavya",
			"joiningDate": "2026-09-03",
			"address": {
				"city": "Fatehabad",
				"state": "UP",
				"zipcode": 765466
			}
		}
	]
}
```

Output

```
{
    "id": 1,
    "name": "Finance",
    "location": "Delhi",
    "employeeList": [
        {
            "id": 1,
            "name": "Abhay",
            "address": {
                "city": "Faridabad",
                "state": "Haryana",
                "zipcode": 453422
            },
            "joiningDate": "2026-09-03"
        },
        {
            "id": 2,
            "name": "Bhavya",
            "address": {
                "city": "Fatehabad",
                "state": "UP",
                "zipcode": 765466
            },
            "joiningDate": "2026-09-03"
        }
    ]
}
```

Again POST http://localhost:8080/departments/add


Request Body

```
{
	"name": "Legal",
	"location": "Mumbai",
}
```

Output

```
{
    "id": 2,
    "name": "Legal",
    "location": "Mumbai",
    "employeeList": null
}

+----+----------+---------+
| id | location | name    |
+----+----------+---------+
|  1 | Delhi    | Finance |
|  2 | Mumbai   | Legal   |
+----+----------+---------+

+----+-----------+-----------+---------+--------------+----------+---------------+
| id | city      | state     | zipcode | joining_date | name     | department_id |
+----+-----------+-----------+---------+--------------+----------+---------------+
|  1 | Faridabad | Haryana   |  453422 | 2026-09-03   | Abhay    |             1 |
|  2 | Fatehabad | UP        |  765466 | 2026-09-03   | Bhavya   |             1 |
+----+-----------+-----------+---------+--------------+----------+---------------+
```



POST http://localhost:8080/employee/add

Request Body

```
{
	"name": "Chanakya",
	"address": {
		"city": "Jaipur",
		"state": "Rajasthan",
		"zipcode": "302002"
	},
	"joiningDate": "2026-08-06",
	"departmentId": 1
}
```

Output

```
{
    "id": 3,
    "name": "Chanakya",
    "address": {
        "city": "Jaipur",
        "state": "Rajasthan",
        "zipcode": 302002
    },
    "joiningDate": "2026-08-06"
}

+----+-----------+-----------+---------+--------------+----------+---------------+
| id | city      | state     | zipcode | joining_date | name     | department_id |
+----+-----------+-----------+---------+--------------+----------+---------------+
|  1 | Faridabad | Haryana   |  453422 | 2026-09-03   | Abhay    |             1 |
|  2 | Fatehabad | UP        |  765466 | 2026-09-03   | Bhavya   |             1 |
|  3 | Jaipur    | Rajasthan |  302002 | 2026-08-06   | Chanakya |             1 |
+----+-----------+-----------+---------+--------------+----------+---------------+
```



POST http://localhost:8080/employee/add

Request Body

```
{
	"name": "YUR",
	"address": {
		"city": "Udaipur",
		"state": "Rajasthan",
		"zipcode": "859625"
	},
	"joiningDate": "2026-08-09",
	"departmentId": 4
}
```

Output

```
{
    "detail": "No department with department id 4",
    "instance": "/employee/add",
    "status": 400,
    "title": "Invalid Department id"
}
```



GET http://localhost:8080/departments/viewdepartments

Output

```
[
    {
        "id": 1,
        "name": "Finance",
        "location": "Delhi",
        "employeeList": [
            {
                "id": 1,
                "name": "Abhay",
                "address": {
                    "city": "Faridabad",
                    "state": "Haryana",
                    "zipcode": 453422
                },
                "joiningDate": "2026-09-03"
            },
            {
                "id": 2,
                "name": "Bhavya",
                "address": {
                    "city": "Fatehabad",
                    "state": "UP",
                    "zipcode": 765466
                },
                "joiningDate": "2026-09-03"
            },
            {
                "id": 3,
                "name": "Chanakya",
                "address": {
                    "city": "Jaipur",
                    "state": "Rajasthan",
                    "zipcode": 302002
                },
                "joiningDate": "2026-08-06"
            }
        ]
    },
    {
        "id": 2,
        "name": "Legal",
        "location": "Mumbai",
        "employeeList": []
    }
]
```



GET http://localhost:8080/departments/viewdepartmentbyid/1

```
{
    "id": 1,
    "name": "Finance",
    "location": "Delhi",
    "employeeList": [
        {
            "id": 1,
            "name": "Abhay",
            "address": {
                "city": "Faridabad",
                "state": "Haryana",
                "zipcode": 453422
            },
            "joiningDate": "2026-09-03"
        },
        {
            "id": 2,
            "name": "Bhavya",
            "address": {
                "city": "Fatehabad",
                "state": "UP",
                "zipcode": 765466
            },
            "joiningDate": "2026-09-03"
        },
        {
            "id": 3,
            "name": "Chanakya",
            "address": {
                "city": "Jaipur",
                "state": "Rajasthan",
                "zipcode": 302002
            },
            "joiningDate": "2026-08-06"
        }
    ]
}
```



GET http://localhost:8080/departments/viewdepartmentwithnoemployee

Output

```
[
    {
        "id": 2,
        "name": "Legal",
        "location": "Mumbai",
        "employeeList": []
    }
]
```



GET http://localhost:8080/departments/viewdepartmentwithemployee

Output

```
[
    {
        "id": 1,
        "name": "Finance",
        "location": "Delhi",
        "employeeList": [
            {
                "id": 1,
                "name": "Abhay",
                "address": {
                    "city": "Faridabad",
                    "state": "Haryana",
                    "zipcode": 453422
                },
                "joiningDate": "2026-09-03"
            },
            {
                "id": 2,
                "name": "Bhavya",
                "address": {
                    "city": "Fatehabad",
                    "state": "UP",
                    "zipcode": 765466
                },
                "joiningDate": "2026-09-03"
            },
            {
                "id": 3,
                "name": "Chanakya",
                "address": {
                    "city": "Jaipur",
                    "state": "Rajasthan",
                    "zipcode": 302002
                },
                "joiningDate": "2026-08-06"
            }
        ]
    }
]
```



GET http://localhost:8080/departments/viewdepartmentwithtwoormoreemployees

Output

```
[
    {
        "id": 1,
        "name": "Finance",
        "location": "Delhi",
        "employeeList": [
            {
                "id": 1,
                "name": "Abhay",
                "address": {
                    "city": "Faridabad",
                    "state": "Haryana",
                    "zipcode": 453422
                },
                "joiningDate": "2026-09-03"
            },
            {
                "id": 2,
                "name": "Bhavya",
                "address": {
                    "city": "Fatehabad",
                    "state": "UP",
                    "zipcode": 765466
                },
                "joiningDate": "2026-09-03"
            },
            {
                "id": 3,
                "name": "Chanakya",
                "address": {
                    "city": "Jaipur",
                    "state": "Rajasthan",
                    "zipcode": 302002
                },
                "joiningDate": "2026-08-06"
            }
        ]
    }
]
```



GET http://localhost:8080/employee/getemployeesbydepartmentid/1

Output

```
[
    {
        "id": 1,
        "name": "Abhay",
        "address": {
            "city": "Faridabad",
            "state": "Haryana",
            "zipcode": 453422
        },
        "joiningDate": "2026-09-03"
    },
    {
        "id": 2,
        "name": "Bhavya",
        "address": {
            "city": "Fatehabad",
            "state": "UP",
            "zipcode": 765466
        },
        "joiningDate": "2026-09-03"
    },
    {
        "id": 3,
        "name": "Chanakya",
        "address": {
            "city": "Jaipur",
            "state": "Rajasthan",
            "zipcode": 302002
        },
        "joiningDate": "2026-08-06"
    }
]
```