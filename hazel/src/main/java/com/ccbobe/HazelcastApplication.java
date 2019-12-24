package com.ccbobe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
/**
 * @author ccbobe
 */
@EnableCaching
@SpringBootApplication(exclude = {HazelcastAutoConfiguration.class})
public class HazelcastApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastApplication.class, args);
	}

}
