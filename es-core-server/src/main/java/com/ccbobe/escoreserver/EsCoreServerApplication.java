package com.ccbobe.escoreserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class EsCoreServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsCoreServerApplication.class, args);
    }




    @Bean
    public SelfHealth selfHealth() {
        return new SelfHealth();
    }
}
