# Spring_boot_caching_actuator_example

The ninteenth Spring Boot application. It demonstrates use of Spring caching and actuator.

###### Spring Boot Version: 4.1.1

## What this app demonstrates
- @EnableCaching @Cacheable, @Caching, @CachePut, @CacheEvict, 
- Demonstrates MethodArgumentTypeMismatchException
- Use of named point cut in AOP
- Accessing properties of validation annotations in the properties file.
- JPA lifecyle callback, @PrePersist and @PostPersist.

## Prerequisites
- Java 17 or higher.
- Maven is included via the Maven Wrapper; no separate Maven installation is needed.
- Mysql
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

- Postman to hit API endpoint

The application.yml file

```
spring:
  application:
    name: Spring_boot_caching_example
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ${DB_USER:root}
    password: ${DB_PASSWORD:root}
    url: jdbc:mysql://localhost:3306/${DB_NAME:Spring_boot_caching_example}
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

management:
  endpoints:
    web:
      exposure:
        include: metrics,info,caches,mappings
  info:
    env:
      enabled: true

info:
  app:
    name: Spring Chaching Actuator Example
    version: 1.0.0
    developer: Team Kapil
```

## Output

POST http://localhost:8080/countries

Request Body

```
{
  "isoCode": "IN",
  "name": "India",
  "capital": "New Delhi",
  "currencyCode": "INR",
  "continent": "ASIA",
  "population": 1400000000
}
```

Output

```
{
  "capital": "New Delhi",
  "continent": "ASIA",
  "createdAt": "2026-09-18 13:34:48",
  "currencyCode": "INR",
  "id": 1,
  "isoCode": "IN",
  "lastUpdated": "2026-09-18 13:34:48",
  "name": "India",
  "population": 1400000000
}

Hibernate: insert into country (capital,continent,created_at,currency_code,iso_code,last_updated,name,population) values (?,?,?,?,?,?,?,?)
```

9 more countries on the same track  

```
{
  "isoCode": "JP",
  "name": "Japan",
  "capital": "Tokyo",
  "currencyCode": "JPY",
  "continent": "ASIA",
  "population": 125000000
}

{
  "isoCode": "TH",
  "name": "Thailand",
  "capital": "Bangkok",
  "currencyCode": "THB",
  "continent": "ASIA",
  "population": 70000000
}

{
  "isoCode": "FR",
  "name": "France",
  "capital": "Paris",
  "currencyCode": "EUR",
  "continent": "EUROPE",
  "population": 68000000
}

{
  "isoCode": "DE",
  "name": "Germany",
  "capital": "Berlin",
  "currencyCode": "EUR",
  "continent": "EUROPE",
  "population": 84000000
}

{
  "isoCode": "US",
  "name": "United States",
  "capital": "Washington, D.C.",
  "currencyCode": "USD",
  "continent": "NORTH_AMERICA",
  "population": 335000000
}

{
  "isoCode": "CA",
  "name": "Canada",
  "capital": "Ottawa",
  "currencyCode": "CAD",
  "continent": "NORTH_AMERICA",
  "population": 40000000
}

{
  "isoCode": "BR",
  "name": "Brazil",
  "capital": "Brasília",
  "currencyCode": "BRL",
  "continent": "SOUTH_AMERICA",
  "population": 215000000
}

{
  "isoCode": "EG",
  "name": "Egypt",
  "capital": "Cairo",
  "currencyCode": "EGP",
  "continent": "AFRICA",
  "population": 105000000
}

{
  "isoCode": "AU",
  "name": "Australia",
  "capital": "Canberra",
  "currencyCode": "AUD",
  "continent": "OCEANIA",
  "population": 26000000
}

+----+------------------+---------------+----------------------------+---------------+----------+----------------------------+---------------+------------+
| id | capital          | continent     | created_at                 | currency_code | iso_code | last_updated               | name          | population |
+----+------------------+---------------+----------------------------+---------------+----------+----------------------------+---------------+------------+
|  1 | New Delhi        | ASIA          | 2026-11-28 17:04:26.331597 | INR           | IN       | 2026-11-28 17:04:26.331597 | India         | 1400000000 |
|  2 | Tokyo            | ASIA          | 2026-11-28 17:04:44.213387 | JPY           | JP       | 2026-11-28 17:04:44.213387 | Japan         |  125000000 |
|  3 | Bangkok          | ASIA          | 2026-11-28 17:04:52.970994 | THB           | TH       | 2026-11-28 17:04:52.970994 | Thailand      |   70000000 |
|  4 | Paris            | EUROPE        | 2026-11-28 17:04:59.040409 | EUR           | FR       | 2026-11-28 17:04:59.040409 | France        |   68000000 |
|  5 | Berlin           | EUROPE        | 2026-11-28 17:05:05.177739 | EUR           | DE       | 2026-11-28 17:05:05.177739 | Germany       |   84000000 |
|  6 | Washington, D.C. | NORTH_AMERICA | 2026-11-28 17:05:12.602304 | USD           | US       | 2026-11-28 17:05:12.602304 | United States |  335000000 |
|  7 | Ottawa           | NORTH_AMERICA | 2026-11-28 17:05:19.177569 | CAD           | CA       | 2026-11-28 17:05:19.177569 | Canada        |   40000000 |
|  8 | Brasília         | SOUTH_AMERICA | 2026-11-28 17:05:25.295576 | BRL           | BR       | 2026-11-28 17:05:25.295576 | Brazil        |  215000000 |
|  9 | Cairo            | AFRICA        | 2026-11-28 17:05:30.937214 | EGP           | EG       | 2026-11-28 17:05:30.937214 | Egypt         |  105000000 |
| 10 | Canberra         | OCEANIA       | 2026-11-28 17:05:37.633259 | AUD           | AU       | 2026-11-28 17:05:37.633259 | Australia     |   26000000 |
+----+------------------+---------------+----------------------------+---------------+----------+----------------------------+---------------+------------+
```



GET http://localhost:8080/countries/JP

Output

```
{
   "capital": "Tokyo",
   "continent": "ASIA",
   "createdAt": "2026-09-18 14:14:23",
   "currencyCode": "JPY",
   "id": 2,
   "isoCode": "JP",
   "lastUpdated": "2026-09-18 14:14:23",
   "name": "Japan",
   "population": 125000000
}

CHECK CONSOLE NO SQL QUERY IS THERE BECAUSE RESULT IS TAKEN FROM CACHE
```



GET http://localhost:8080/countries/continent/ASIA

```
[
  {
    "capital": "New Delhi",
    "continent": "ASIA",
    "createdAt": "2026-09-18 14:14:12",
    "currencyCode": "INR",
    "id": 1,
    "isoCode": "IN",
    "lastUpdated": "2026-09-18 14:14:12",
    "name": "India",
    "population": 1400000000
  },
  {
    "capital": "Tokyo",
    "continent": "ASIA",
    "createdAt": "2026-09-18 14:14:23",
    "currencyCode": "JPY",
    "id": 2,
    "isoCode": "JP",
    "lastUpdated": "2026-09-18 14:14:23",
    "name": "Japan",
    "population": 125000000
  },
  {
    "capital": "Bangkok",
    "continent": "ASIA",
    "createdAt": "2026-09-18 14:14:28",
    "currencyCode": "THB",
    "id": 3,
    "isoCode": "TH",
    "lastUpdated": "2026-09-18 14:14:28",
    "name": "Thailand",
    "population": 70000000
  }
]

CHECK CONSOLE, QUERY IS THERE
```

AGAIN HIT THE SAME URL

GET http://localhost:8080/countries/continent/ASIA

```
CHECK CONSOLE, NO QUERY
```

GET http://localhost:8080/countries/continent/ASIA_PACIFIC

```
{
  "detail": "Invalid value 'ASIA_PACIFIC' for parameter 'continent'",
  "instance": "/countries/continent/ASIA_PACIFIC",
  "status": 400,
  "title": "Invalid Parameter Value"
}
```



GET http://localhost:8080/countries/continent/ASIA/sorted

```
[
  {
    "capital": "New Delhi",
    "continent": "ASIA",
    "createdAt": "2026-09-18 14:14:12",
    "currencyCode": "INR",
    "id": 1,
    "isoCode": "IN",
    "lastUpdated": "2026-09-18 14:14:12",
    "name": "India",
    "population": 1400000000
  },
  {
    "capital": "Tokyo",
    "continent": "ASIA",
    "createdAt": "2026-09-18 14:14:23",
    "currencyCode": "JPY",
    "id": 2,
    "isoCode": "JP",
    "lastUpdated": "2026-09-18 14:14:23",
    "name": "Japan",
    "population": 125000000
  },
  {
    "capital": "Bangkok",
    "continent": "ASIA",
    "createdAt": "2026-09-18 14:14:28",
    "currencyCode": "THB",
    "id": 3,
    "isoCode": "TH",
    "lastUpdated": "2026-09-18 14:14:28",
    "name": "Thailand",
    "population": 70000000
  }
]
CHECK CONSOLE, QUERY IS THERE
```

AGAIN HIT THE SAME URL

GET http://localhost:8080/countries/continent/ASIA/sorted

```
CHECK CONSOLE, NO QUERY
```



GET http://localhost:8080/countries/currency/EUR

```
[
  {
    "capital": "Paris",
    "continent": "EUROPE",
    "createdAt": "2026-09-18 14:14:32",
    "currencyCode": "EUR",
    "id": 4,
    "isoCode": "FR",
    "lastUpdated": "2026-09-18 14:14:32",
    "name": "France",
    "population": 68000000
  },
  {
    "capital": "Berlin",
    "continent": "EUROPE",
    "createdAt": "2026-09-18 14:14:36",
    "currencyCode": "EUR",
    "id": 5,
    "isoCode": "DE",
    "lastUpdated": "2026-09-18 14:14:36",
    "name": "Germany",
    "population": 84000000
  }
]

CHECK CONSOLE, QUERY IS THERE
```

AGAIN HIT THE SAME URL

GET http://localhost:8080/countries/currency/EUR

```
CHECK CONSOLE, NO QUERY
```



GET http://localhost:8080/countries/population/greater-than/215000000

```
[
  {
    "capital": "New Delhi",
    "continent": "ASIA",
    "createdAt": "2026-11-28 17:04:26",
    "currencyCode": "INR",
    "id": 1,
    "isoCode": "IN",
    "lastUpdated": "2026-11-28 17:04:26",
    "name": "India",
    "population": 1400000000
  },
  {
    "capital": "Washington, D.C.",
    "continent": "NORTH_AMERICA",
    "createdAt": "2026-11-28 17:05:12",
    "currencyCode": "USD",
    "id": 6,
    "isoCode": "US",
    "lastUpdated": "2026-11-28 17:05:12",
    "name": "United States",
    "population": 335000000
  }
]

CHECK CONSOLE, QUERY IS THERE
```

AGAIN HIT THE SAME URL
GET http://localhost:8080/countries/population/greater-than/215000000

```
CHECK CONSOLE, QUERY IS THERE because no caching
```



PUT http://localhost:8080/countries/IN

Request Body

```
{
  "isoCode": "IN",
  "name": "India",
  "capital": "New Delhi",
  "currencyCode": "INR",
  "continent": "ASIA",
  "population": 1500000000
}
```

Output

```
{
  "capital": "New Delhi",
  "continent": "ASIA",
  "createdAt": "2026-11-28 17:04:26",
  "currencyCode": "INR",
  "id": 1,
  "isoCode": "IN",
  "lastUpdated": "2026-11-28 17:04:26",
  "name": "India",
  "population": 1500000000
}

GET http://localhost:8080/countries/IN

NO query because taken from cache

GET http://localhost:8080/countries/continent/ASIA

QUERY will be there because cache invalidated

GET http://localhost:8080/countries/currency/EUR

QUERY will be there because cache invalidated

GET http://localhost:8080/countries/continent/ASIA/sorted

QUERY will be there because cache invalidated
```



DELETE http://localhost:8080/countries/IN

```
Output: 204 No Content
```

GET http://localhost:8080/countries/IN

```
{
  "detail": "No country found with ISO code: IN",
  "instance": "/countries/IN",
  "status": 404,
  "title": "Country Not Found"
}
```

GET http://localhost:8080/countries/continent/ASIA

```
QUERY will be there because cache invalidated
```

GET http://localhost:8080/countries/currency/EUR

```
QUERY will be there because cache invalidated
```

GET http://localhost:8080/countries/continent/ASIA/sorted

```
QUERY will be there because cache invalidated
```

#### The actuator endpoints

GET http://localhost:8080/actuator

```
{
  "_links": {
    "self": {
      "href": "http://localhost:8080/actuator",
      "templated": false
    },
    "info": {
      "href": "http://localhost:8080/actuator/info",
      "templated": false
    },
    "mappings": {
      "href": "http://localhost:8080/actuator/mappings",
      "templated": false
    },
    "caches-cache": {
      "href": "http://localhost:8080/actuator/caches/{cache}",
      "templated": true
    },
    "caches": {
      "href": "http://localhost:8080/actuator/caches",
      "templated": false
    },
    "metrics": {
      "href": "http://localhost:8080/actuator/metrics",
      "templated": false
    },
    "metrics-requiredMetricName": {
      "href": "http://localhost:8080/actuator/metrics/{requiredMetricName}",
      "templated": true
    }
  }
}
```

GET http://localhost:8080/actuator/info

```
{
  "app": {
    "name": "Spring Chaching Actuator Example",
    "version": "1.0.0",
    "developer": "Team Kapil"
  }
}
```



GET http://localhost:8080/actuator/mappings

```
{
  "contexts": {
    "Spring_boot_caching_example": {
      "mappings": {
        "servletFilters": [
          {
            "className": "org.springframework.boot.servlet.filter.OrderedRequestContextFilter",
            "name": "requestContextFilter",
            "servletNameMappings": [],
            "urlPatternMappings": [
              "/*"
            ]
          },
          ...
        ],
        "dispatcherServlets": {...},
        "servlets": [...]
      }
    }
  }
}
```



GET http://localhost:8080/actuator/caches

```
{
  "cacheManagers": {
    "cacheManager": {
      "caches": {
        "countriesByIso": {
          "target": "java.util.concurrent.ConcurrentHashMap"
        },
        "countriesByContinentSorted": {
          "target": "java.util.concurrent.ConcurrentHashMap"
        },
        "countriesByContinent": {
          "target": "java.util.concurrent.ConcurrentHashMap"
        },
        "countriesByCurrency": {
          "target": "java.util.concurrent.ConcurrentHashMap"
        }
      }
    }
  }
}
```




GET http://localhost:8080/actuator/caches/countriesByIso

```
{
  "cacheManager": "cacheManager",
  "name": "countriesByIso",
  "target": "java.util.concurrent.ConcurrentHashMap"
}
```

GET http://localhost:8080/actuator/metrics

```
{
  "names": [
    "application.ready.time",
    "application.started.time",
    ...
    "tomcat.sessions.rejected"
  ]
}
```

GET http://localhost:8080/actuator/metrics/application.ready.time

```
{
  "availableTags": [
    {
      "tag": "main.application.class",
      "values": [
        "com.example.SpringBootCachingExampleApplication"
      ]
    }
  ],
  "baseUnit": "seconds",
  "description": "Time taken for the application to be ready to service requests",
  "measurements": [
    {
      "statistic": "VALUE",
      "value": 8.384
    }
  ],
  "name": "application.ready.time"
}
```