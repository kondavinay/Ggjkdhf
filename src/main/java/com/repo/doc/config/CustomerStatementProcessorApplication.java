package com.repo.doc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.repo.doc.*")
public class CustomerStatementProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerStatementProcessorApplication.class, args);
	}
}
