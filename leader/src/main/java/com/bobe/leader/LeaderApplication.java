package com.bobe.leader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.EnableIntegrationManagement;
@EnableIntegrationManagement
@EnableIntegration
@SpringBootApplication
public class LeaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaderApplication.class, args);
	}

}
