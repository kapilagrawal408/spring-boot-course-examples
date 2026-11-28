# Spring_Boot_RestTemplate_Example

The twentieth Spring Boot application. It demonstrates use of RestTemplate to communicate with other application.

###### Spring Boot Version: 4.1.1

## What this app demonstrates
- Use of httpclient5 dependency to overcome the no buffernig and HTTP protocol version issue 
- Use of RestTemplateBuilder
- RestClientException and its subclasses like HttpClientErrorException, HttpServerErrorException

## Prerequisites
- Java 17 or higher.
- Maven is included via the Maven Wrapper; no separate Maven installation is needed.
- Postman to hit API endpoint

## Running
- Import this project into the Spring-Tool-Suite (STS) and run as spring boot application (Alt + Shift + X, B)
- or Simply run the commands

```
mvnw.cmd clean spring-boot:run
```

The application.properties file is

```
spring.application.name=Spring_Boot_RestTemplate_Example
server.servlet.context-path=/api/proxy
api.external.jsonplaceholder.posts.url=https://jsonplaceholder.typicode.com/posts
```

##### Note: The https://jsonplaceholder.typicode.com/posts is dummy API used in this example. For the Create, Update and Delete request, it will give us proper status code by actually no changes are made to the server.

## Output

GET http://localhost:8080/api/proxy/posts/1

Output

```
{
	"id": 1,
	"userId": 1,
	"title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
	"body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
```



GET http://localhost:8080/api/proxy/posts/999

Output

```
{
	"detail": "We encountered an issue fetching this information. Please try again later.",
	"instance": "/api/proxy/posts/999",
	"status": 502,
	"title": "Information Unavailable"
}
```



POST http://localhost:8080/api/proxy/posts

Request Body

```
{
	"userId": 1,
	"title": "Spring Boot Training Notes",
	"body": "This post was created via RestTemplate postForEntity method."
}
```

output

```
{
	"id": 101,
	"userId": 1,
	"title": "Spring Boot Training Notes",
	"body": "This post was created via RestTemplate postForEntity method."
}
```



PUT http://localhost:8080/api/proxy/posts/1

Request Body
```
{
	"id": 1,
	"userId": 1,
	"title": "Updated Title via RestTemplate",
	"body": "The body content has been modified."
}

Output

```
Post 1 successfully updated at external API.
```



DELETE http://localhost:8080/api/proxy/posts/1

Output

```
Post 1 successfully deleted at external API.
```