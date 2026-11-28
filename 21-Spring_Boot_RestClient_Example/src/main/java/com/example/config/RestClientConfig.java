package com.example.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

	//because we are using spring boot 4.x that's RestClient.Builder configured by Spring boot is available
	//in spring boot 3.x we have to manually configure the same
    @Bean
    public RestClient jsonPlaceholderClient(RestClient.Builder builder, @Value("${api.external.jsonplaceholder.posts.url}") String baseURL) {
    		// =================================================================
        // STEP 1: Configure Apache HttpClient5 request-level settings
        // =================================================================
        // RequestConfig holds the timeouts and other per-request policies
        // that the underlying Apache client applies to every outgoing call.
        //
        // Three timeouts matter in production, and they guard different phases
        // of a request. Setting all three prevents a single slow endpoint from
        // tying up threads indefinitely.
        //
        //   connectionRequestTimeout -> time spent waiting for a free connection
        //                                from the connection pool (pool exhaustion)
        //   responseTimeout          -> time spent waiting for the response after
        //                                the request has been fully sent
        //
        // Without these, a slow or unreachable server can hang the calling
        // thread for the OS-level default, which is often minutes or infinite.
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(Timeout.ofSeconds(10)) // Time to get a connection from the pool
            .setResponseTimeout(Timeout.ofSeconds(10))          // Read timeout
            .build();

        // =================================================================
        // STEP 2: Build the Apache HttpClient5 instance
        // =================================================================
        // HttpClients.custom() returns a builder for CloseableHttpClient.
        // The Apache client is the actual engine that performs the HTTP calls.
        //
        // We attach our RequestConfig as the default policy, so every request
        // made through this client inherits the timeouts above without needing
        // to specify them again at the call site.
        CloseableHttpClient apacheClient = HttpClients.custom()
        		.setDefaultRequestConfig(requestConfig)
            .build();

        // =================================================================
        // STEP 3: Wrap the Apache client in a Spring ClientHttpRequestFactory
        // =================================================================
        // Spring's RestClient does not speak to Apache HttpClient directly.
        // It talks to a ClientHttpRequestFactory abstraction, which is a thin
        // adapter that Spring uses to make requests. This factory is the bridge
        // between Spring's template API and Apache's low-level HTTP engine.
        //
        // By passing our pre-configured apacheClient into the factory, we get
        // all the Apache behaviour (HTTP/1.1, buffered bodies, connection pool)
        // while still using RestClient's high-level template methods.
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(apacheClient);

        // =================================================================
        // STEP 4. Build and expose the RestClient
        // =================================================================
		//RestClient.Builder is a Spring Framework class. Because it belongs to the
		//core framework, it is completely agnostic. It does not contain a .connectTimeout() 
		//method because the core framework refuses to assume whether you want    
		//to use the JDK, Apache, or OkHttp. It forces you to manually configure a   
		//ClientHttpRequestFactory if you want custom timeouts—which is exactly how you ran   
        //into those ClientHttpRequestFactorySettings import errors earlier.
        return builder.baseUrl(baseURL)
            .requestFactory(factory)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}
