package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.example.model.Student;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) { //

        // Define the HTTP methods to disable
        HttpMethod[] unsupportedMethods = { HttpMethod.PUT, HttpMethod.DELETE };

        // Apply the restrictions to the Student entity
        config.getExposureConfiguration()
                .forDomainType(Student.class) 
                .withItemExposure((metadata, http) -> http.disable(unsupportedMethods))
                .withCollectionExposure((metadata, http) -> http.disable(unsupportedMethods));
    }
}