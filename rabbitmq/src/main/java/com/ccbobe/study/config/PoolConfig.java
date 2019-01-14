package com.ccbobe.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.ArrayBlockingQueue;

@Configuration
public class PoolConfig {
	
	@Bean
	@Primary
	public ArrayBlockingQueue arrayBlockingQueue(){
		return new ArrayBlockingQueue(200);
	}
	
}
