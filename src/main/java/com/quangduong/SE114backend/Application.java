package com.quangduong.SE114backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestController
public class Application {

	@GetMapping("greeting")
	public ResponseEntity<GreetingResponse> greeting() {
		return ResponseEntity.ok(new GreetingResponse("Server is running", "3bro"));
	}

	record GreetingResponse(String hello, String author) {}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
