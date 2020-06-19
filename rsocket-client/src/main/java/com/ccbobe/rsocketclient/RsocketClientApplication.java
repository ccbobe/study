package com.ccbobe.rsocketclient;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class RsocketClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsocketClientApplication.class, args);
    }


    @Bean
    public RSocket rSocket() {
        return RSocketFactory
                .connect()
                .dataMimeType(MimeTypeUtils.ALL_VALUE)
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .transport(TcpClientTransport.create(8989))
                .start()
                .block();
    }

    @Bean
    RSocketRequester rSocketRequester(RSocketStrategies rSocketStrategies) {
        return RSocketRequester.builder()
                .rsocketFactory(factory -> factory
                        .dataMimeType(MimeTypeUtils.ALL_VALUE)
                        .frameDecoder(PayloadDecoder.ZERO_COPY))
                .rsocketStrategies(rSocketStrategies)
                .connect(TcpClientTransport.create(8989))
                .retry(6).block();
    }

    @Autowired
    private RSocketRequester requester;

    @Scheduled(fixedDelay = 1000)
    public void push(){
        requester.route("hello").data("ccbobe").send().retry();

        log.info("push data client.....{}");
    }

}
