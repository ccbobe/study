package com.ccbobe.rsocketserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
@Slf4j
@RestController
@SpringBootApplication
public class RsocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsocketServerApplication.class, args);
    }



    @MessageMapping("hello")
    public Flux<String> hello(String name) {
        log.info("消息请求{}",name);
        return Flux.just(name);
    }
}
