# Spring_Data_Rest_demo

The sixteenth Spring Boot application. It demonstrates use of Spring Data REST.

###### Spring Boot Version: 4.1.1

## What this app demonstrates

- The @RepositoryRestResource
- Instantaneously exposing the API endpoint using Spring Data REST
- The H2 database (An In-memory database)

## Prerequisites
- Java 17 or higher.
- Maven is included via the Maven Wrapper; no separate Maven installation is needed.
- Postman to hit API endpoint

## Running
- Import this project into the Spring-Tool-Suite (STS) and run as spring boot application (Alt + Shift + X, B)
- or Simply run the commands

```
spring.application.name=Spring_Data_Rest_demo
spring.h2.console.enabled=true
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
    "grade": "B"
}
```



DELETE http://localhost:8080/students/1

```
status: 200 (No Body Output)
```

To access database; check the console log for database name

```
Database JDBC URL [jdbc:h2:mem:58cfa7e1-dfd5-4ad0-9d82-a40020a53f0c]
Database driver: H2 JDBC Driver
Database dialect: H2Dialect
Database version: 2.4.240
Default catalog/schema: 58CFA7E1-DFD5-4AD0-9D82-A40020A53F0C/PUBLIC
Autocommit mode: undefined/unknown
Isolation level: READ_COMMITTED [default READ_COMMITTED]
JDBC fetch size: 100
Pool: DataSourceConnectionProvider
Minimum pool size: undefined/unknown
Maximum pool size: undefined/unknown
```

Here it is

```58cfa7e1-dfd5-4ad0-9d82-a40020a53f0c
```

In browser hit: http://localhost:8080/h2-console

Enter jdbc:h2:mem:58cfa7e1-dfd5-4ad0-9d82-a40020a53f0c in JDBC URL and click on test connection.

![Login Image](docs\images\login.png)


This output before hitting http://localhost:8080/students/1 with DELETE method



![Student Table Image](docs\images\SELECT.png)