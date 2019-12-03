package com.ccbobe.kafkamq;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableAdminServer
@EnableKafka
@SpringBootApplication
public class KafkamqApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkamqApplication.class, args);
    }

}
