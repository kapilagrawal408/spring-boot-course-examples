package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLogginDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringLogginDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        // Fetch the actual Root Logger object from Logback's memory
        Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.info("This message is printed directly by the Root Logger!");
        
        //Create another logger object
		Logger logger = LoggerFactory.getLogger(SpringLogginDemoApplication.class);
		logger.trace("This is a TRACE message");
		logger.debug("This is a DEBUG message");
		logger.info("This is a INFO message");
		logger.warn("This is a WARN message");
		logger.error("This is a ERROR message");
	}
}