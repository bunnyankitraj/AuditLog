package com.example.AuditLog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync  // Enables asynchronous execution
@SpringBootApplication
public class AuditLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditLogApplication.class, args);
	}

}
