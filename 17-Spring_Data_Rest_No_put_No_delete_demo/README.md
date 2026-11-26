# Spring_Data_Rest_No_put_No_delete_demo

The seventeenth Spring Boot application. It demonstrates use of Spring Data REST to prohibit some methods.

###### Spring Boot Version: 4.1.1

## What this app demonstrates

- The @RepositoryRestResource and RepositoryRestConfigurer interface
- The H2 database (An In-memory database)

## Prerequisites
- Java 17 or higher.
- Maven is included via the Maven Wrapper; no separate Maven installation is needed.
- Postman to hit API endpoint

## Running
- Import this project into the Spring-Tool-Suite (STS) and run as spring boot application (Alt + Shift + X, B)
- or Simply run the commands

```
spring.application.name=Spring_Data_Rest_No_put_No_delete_demo
```

## Output

POST http://localhost:8080/students

Request Body

```
{
  "name": "John Doe",
  "grade": "A"
}
```

Output

```
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
```



GET http://localhost:8080/students

```
{
    "_embedded": {
        "scholars": [
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
```



GET http://localhost:8080/students/search/findByGrade?grade=A

```
{
    "_embedded": {
        "scholars": [
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
```



PUT http://localhost:8080/students/1

Request Body

```
{
 "name": "John Doe",
 "grade": "B"
}
```

Output

```
405 Method not allowed
```



DELETE http://localhost:8080/students/1

Output

```
405 Method not allowed
```