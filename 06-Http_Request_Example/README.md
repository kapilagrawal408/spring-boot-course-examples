# HTTP Request Methods

The Sixth Spring Boot application. It demonstrates all popular HTTP methods

###### Spring Boot Version: 4.1.1

## What this app demonstrates

- Demonstrate GET, POST, PUT, PATCH and Delete 
- Demonstrate @RequestParam, @PathVariable, @RequestBoby and @MatrixVariable

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
spring.application.name=Http_Request_Example
```

## Output
GET http://localhost:8080/students

```
[
	{
		"rollNo":1,
		"name":"ABC",
		"marks":75.0
	},
	{
		"rollNo":2,
		"name":"BCD",
		"marks":85.0
	},
	{
		"rollNo":3,
		"name":"CDE",
		"marks":95.0
	}
]
```



GET http://localhost:8080/students/1

```
{
	"rollNo":1,
	"name":"ABC",
	"marks":75.0
}
```



GET http://localhost:8080/studentsbymarks?start=80&end=95

```
[
	{
		"rollNo":2,
		"name":"BCD",
		"marks":85.0
	},
	{
		"rollNo":3,
		"name":"CDE",
		"marks":95.0
	}
]
```



POST http://localhost:8080/students

Request Body

```
{
	"rollNo":4,
	"name":"pqr",
	"marks":65.0
}
```

Output

```
{
	"rollNo":4,
	"name": "pqr",
	"marks":65.0
}
````

Now hit the same URL with GET Request

GET http://localhost:8080/students

```
[
	{
		"rollNo":1,
		"name":"ABC",
		"marks":75.0
	},
	{
		"rollNo":2,
		"name":"BCD",
		"marks":85.0
	},
	{
		"rollNo":3,
		"name":"CDE",
		"marks":95.0
	},
	{
		"rollNo":4,
		"name":"pqr",
		"marks":65.0
	}
]
```



GET http://localhost:8080/students/1

```
{
	"rollNo":1,
	"name":"ABC",
	"marks":75.0
}
```

PUT http://localhost:8080/students

Request Body

```
{
	"rollNo":1,
	"name":"XYZ",
	"marks":55.0
}
```
Response

```
{
	"rollNo":1,
	"name":"XYZ",
	"marks":55.0
}
```


GET http://localhost:8080/students/1

```
{
	"rollNo":1,
	"name":"XYZ",
	"marks":55.0
}
```

GET http://localhost:8080/students/1

```
{
	"rollNo":1,
	"name":"XYZ",
	"marks":55.0
}
```

PATCH http://localhost:8080/students/1/pqr

Output:

```
{
	"rollNo":1,
	"name":"pqr",
	"marks":55.0
}
```
	
GET http://localhost:8080/students/1

```
{
	"rollNo":1,
	"name":"pqr",
	"marks":55.0
}
```


GET http://localhost:8080/students/1

```
{
	"rollNo":1,
	"name":"pqr",
	"marks":55.0
}
```
	
DELETE http://localhost:8080/students/1

```
{
	"rollNo":1,
	"name":"pqr",
	"marks":55.0
}
```
	
GET http://localhost:8080/students

```
[
    {
        "rollNo": 2,
        "name": "BCD",
        "marks": 85.0
    },
    {
        "rollNo": 3,
        "name": "CDE",
        "marks": 95.0
    },
    {
        "rollNo": 4,
        "name": "pqr",
        "marks": 65.0
    }
]
```

GET http://localhost:8080/cars/101;color=red;year=2020;options=sunroof,navigation

```
Found car: Car(id=101, model=Sedan, color=red, year=2020, options=[sunroof, navigation])
```