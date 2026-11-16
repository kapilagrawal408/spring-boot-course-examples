package com.example.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ConfigCheckRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("\n========== 1. APPLICATION RUNNER ==========");
		System.out.println("Task: Verifying Startup Configurations");
		
		if (args.containsOption("admin.email")) {
            String email = args.getOptionValues("admin.email").get(0);
            System.out.println("[SUCCESS] Admin email registered: " + email);
        } else {
            System.out.println("[WARNING] No admin.email provided! Falling back to default.");
        }
		
		if (args.getNonOptionArgs().contains("strict-mode")) {
            System.out.println("[INFO] Engine starting in STRICT validation mode.");
        } else {
            System.out.println("[INFO] Engine starting in STANDARD mode.");
        }
        System.out.println("===========================================\n");
	}
}
