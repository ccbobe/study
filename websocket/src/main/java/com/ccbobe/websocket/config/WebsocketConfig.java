package com.ccbobe.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.socket.server.support.OriginHandshakeInterceptor;

import java.util.LinkedList;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/messages").withSockJS()
        .setWebSocketEnabled(true)
        ;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableStompBrokerRelay("/queue/", "/topic/")
                .setAutoStartup(true)
                .setRelayHost("192.168.124.44")
                .setRelayPort(61613)
                .setSystemPasscode("guest")
                .setClientLogin("guest")
                .setClientPasscode("guest")
                .setSystemLogin("guest")
                .setVirtualHost("/");

        // 表示在queue和topic这两个域上可以向客户端发消息

        //registry.enableSimpleBroker("/queue/", "/topic/");

        // 表示给指定用户发送一对一的主题前缀是/user

        registry.setUserDestinationPrefix("/user");
        registry.setApplicationDestinationPrefixes("/app");

        
    }

}
