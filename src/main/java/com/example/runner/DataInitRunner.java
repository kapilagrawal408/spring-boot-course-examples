package com.example.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class DataInitRunner implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		System.out.println("========== 2. COMMAND LINE RUNNER =========");
        System.out.println("Task: Populating Initial Database Data (Just dummy, no actual data)");
        
        // CommandLineRunner does not distinguish between optional/non-optional. 
        // It just sees a raw array of strings.
        System.out.println("[INFO] Raw arguments received: " + String.join(", ", args));
        
        System.out.println("[INFO] Creating default database tables...");
        System.out.println("[INFO] Inserting default roles (ADMIN, USER)...");
        System.out.println("[SUCCESS] Data initialization complete.");
        System.out.println("===========================================\n");
	}
}
