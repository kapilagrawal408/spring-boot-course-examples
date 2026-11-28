package com.example.config;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {	
    ConnectionConfig connectionConfig = ConnectionConfig.custom()
	  .setConnectTimeout(Timeout.ofSeconds(10))
	  .setSocketTimeout(Timeout.ofSeconds(10))
	  .build();

    PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
	  .setDefaultConnectionConfig(connectionConfig)
	  .build();
						
    RequestConfig requestConfig = RequestConfig.custom()
	  .setConnectionRequestTimeout(Timeout.ofSeconds(10))
	  .setResponseTimeout(Timeout.ofSeconds(10))
	  .build();

    CloseableHttpClient apacheClient = HttpClients.custom()
	  .setConnectionManager(connectionManager)
	  .setDefaultRequestConfig(requestConfig)
	  .build();

    HttpComponentsClientHttpRequestFactory factory =
			new HttpComponentsClientHttpRequestFactory(apacheClient);

    return builder
	  .requestFactory(() -> factory) // Provides the bridge to your pooled Apache client
	  .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE) // Sets a global default
	  .build(); // Creates the final, fully-configured RestTemplate
  }
}