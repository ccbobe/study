package com.bobe.leader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.EnableIntegrationManagement;
/**
 * @author ccbobe
 */
@EnableAspectJAutoProxy
@EnableIntegrationManagement
@EnableIntegration
@SpringBootApplication
public class LeaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaderApplication.class, args);
	}

}
