package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLogginDifferentLevelDifferentFileApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(SpringLogginDifferentLevelDifferentFileApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Logger logger = LoggerFactory.getLogger(SpringLogginDifferentLevelDifferentFileApplication.class);
		logger.trace("This is a TRACE message");
		logger.debug("This is a DEBUG message");
		logger.info("This is a INFO message");
		logger.warn("This is a WARN message");
		logger.error("This is a ERROR message");
	}
}