package com.quangduong.SE114backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableElasticsearchRepositories(basePackages = "com.quangduong.SE114backend.repository.elastic")
@RestController
public class Application {

	@GetMapping("greeting")
	public String greeting() {
		return "Hello Khai";
	}

	@PostMapping("greeting")
	public String postTest(@RequestBody String name) {
		return name;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
