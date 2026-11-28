package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.dto.PostDTO;

@RestController
@RequestMapping("/posts")
public class PostClientController {
	private RestTemplate restTemplate;
	private String baseURL;
	
	public PostClientController(RestTemplate restTemplate, @Value("${api.external.jsonplaceholder.posts.url}") String baseURL) {
		this.restTemplate = restTemplate;
		this.baseURL = baseURL;
	}

	//No-self invocation issue here because HTTP Verb annotations and not AOP advice hence no Proxy object involved
	private HttpEntity<PostDTO> getPostAsHttpEntity(PostDTO post){
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    return new HttpEntity<>(post, headers);
	}	

	@GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        String url = baseURL + "/" + id;
        PostDTO response = restTemplate.getForObject(url, PostDTO.class);
        return ResponseEntity.ok(response);
    }
	
	/*
	GET http://localhost:8080/api/proxy/posts/1
	
	Output
	{
	    "id": 1,
	    "userId": 1,
	    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
	    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
	}
	
	GET http://localhost:8080/api/proxy/posts/999
	
	Output
	{
	    "detail": "We encountered an issue fetching this information. Please try again later.",
	    "instance": "/api/proxy/posts/999",
	    "status": 502,
	    "title": "Information Unavailable"
	}
	*/
	
	@PostMapping
    public ResponseEntity<PostDTO> createNewPost(@RequestBody PostDTO newPost) {
		HttpEntity<PostDTO> request = getPostAsHttpEntity(newPost);
        ResponseEntity<PostDTO> response = restTemplate.postForEntity(this.baseURL, request, PostDTO.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
	
	/*
	 POST http://localhost:8080/api/proxy/posts
	 {
	   "userId": 1,
	   "title": "Spring Boot Training Notes",
	   "body": "This post was created via RestTemplate postForEntity method."
	 }
	 
	 Output
	 {
	   "id": 101,
	   "userId": 1,
	   "title": "Spring Boot Training Notes",
	   "body": "This post was created via RestTemplate postForEntity method."
	 }
	 */
	
	@PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostDTO updatedPost) {
		HttpEntity<PostDTO> request = getPostAsHttpEntity(updatedPost);
	    String url = this.baseURL + "/" + id;        
        restTemplate.put(url, request);
        return ResponseEntity.ok("Post " + id + " successfully updated at external API.");
    }
	/*
	 PUT http://localhost:8080/api/proxy/posts/1
	 {
	   "id": 1,
	   "userId": 1,
	   "title": "Updated Title via RestTemplate",
	   "body": "The body content has been modified."
	 }
	 
	 Output
	 Post 1 successfully updated at external API.
	 
	 */
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        String url = this.baseURL + "/" + id;
        restTemplate.delete(url);
        return ResponseEntity.ok("Post " + id + " successfully deleted at external API.");
    }
	/*
	 DELETE http://localhost:8080/api/proxy/posts/1
	 
	 Output
	 Post 1 successfully deleted at external API.
	*/
}
