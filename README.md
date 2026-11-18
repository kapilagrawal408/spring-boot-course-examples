# Spring Boot HATEOAS Example

The tenth Spring Boot application. It demonstrates on how to generate dynamic URL for resources in the spring boot.

###### Spring Boot Version: 4.1.1

## What this app demonstrates

- Using EntityModel to generate link for a resources
- Using CollectionModel to generate links for every resource of the collection
- Dynamic URL generation based on the state of the resource
- No database is used here, the foreign key is maintained by programming only

## Prerequisites
- Java 17 or higher.
- Maven is included via the Maven Wrapper; no separate Maven installation is needed.
- Postman to hit API endpoint

## Running
- Import this project into the Spring-Tool-Suite (STS) and run as spring boot application (Alt + Shift + X, B)
- or Simply run the command `mvnw.cmd clean spring-boot:run` from the root folder of the project in the terminal
- Use postman to hit API end-points

## Configuration

The application name in `src/main/resources/application.properties`:

```
spring.application.name=Spring_Boot_HATEOS_Demo
spring.web.resources.add-mappings=false
```

## Output
GET http://localhost:8080/companies/1

Output

```
{
	"_links": {
		"self": {
			"href": "http://localhost:8080/companies/1"
		},
		"all-companies": {
			"href": "http://localhost:8080/companies"
		},
		"manufactured-cars": {
			"href": "http://localhost:8080/companies/1/cars"
		}
	},
   	"id": 1,
	"name": "Toyota",
	"headquarters": "Japan"
}
```



GET http://localhost:8080/companies

Output

```
{
    "_embedded": {
        "companyList": [
            {
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/companies/1"
                    },
                    "manufactured-cars": {
                        "href": "http://localhost:8080/companies/1/cars"
                    }
                },
                "id": 1,
                "name": "Toyota",
                "headquarters": "Japan"
            },
            {
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/companies/2"
                    },
                    "manufactured-cars": {
                        "href": "http://localhost:8080/companies/2/cars"
                    }
                },
                "id": 2,
                "name": "Ford",
                "headquarters": "USA"
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/companies"
        }
    }
}
```



POST http://localhost:8080/companies


Request Body

```
{
  "name": "Honda",
  "headquarters": "Minato, Tokyo, Japan"
}
```

Output

```
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/companies/3"
        },
        "all-companies": {
            "href": "http://localhost:8080/companies"
        }
    },
    "id": 3,
    "name": "Honda",
    "headquarters": "Minato, Tokyo, Japan"
}
```



PUT http://localhost:8080/companies/1

Request Body

```
{
  "name": "Toyota Motor Corp",
  "headquarters": "Toyota City, Japan"
}
```

Output

```
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/companies/1"
        },
        "all-companies": {
            "href": "http://localhost:8080/companies"
        }
    },
    "id": 1,
    "name": "Toyota Motor Corp",
    "headquarters": "Toyota City, Japan"
}
```



GET http://localhost:8080/cars/101

Output

```
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/cars/101"
        },
        "parent-company": {
            "href": "http://localhost:8080/companies/1"
        }
    },
    "id": 101,
    "modelName": "Camry",
    "type": "Sedan",
    "companyId": 1
}
```



GET http://localhost:8080/companies/1/cars

Output

```
{
    "_embedded": {
        "carList": [
            {
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/cars/101"
                    }
                },
                "id": 101,
                "modelName": "Camry",
                "type": "Sedan",
                "companyId": 1
            },
            {
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/cars/102"
                    }
                },
                "id": 102,
                "modelName": "RAV4",
                "type": "SUV",
                "companyId": 1
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/companies/1/cars"
        },
        "parent-company": {
            "href": "http://localhost:8080/companies/1"
        }
    }
}
```


POST http://localhost:8080/cars

Request Body

```
{
   "modelName": "Civic",
   "type": "Sedan",
   "companyId": 3 
}
```

Output

```
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/cars/104"
        },
        "parent-company": {
            "href": "http://localhost:8080/companies/3"
        }
    },
    "id": 104,
    "modelName": "Civic",
    "type": "Sedan",
    "companyId": 3
}
```