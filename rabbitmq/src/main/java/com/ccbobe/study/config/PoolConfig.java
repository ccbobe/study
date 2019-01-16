package com.ccbobe.study.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
	    return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue queue(){
	    return new Queue("hello",true,false,false);
    }


	
}
