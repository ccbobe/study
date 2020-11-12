package com.ccbobe.websocket.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class sendController {

    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping("msg")
    public String sendMsg(String msg){
        template.convertAndSend("/topic/test",msg);
        return msg;
    }

    @MessageMapping("submsg")
    public String submsg(@Payload String msg){
        log.info(msg);
        return msg;
    }

    @Scheduled(fixedDelay = 100)
    public void sendMsg(){
        System.out.println("test");
        template.convertAndSend("/topic/test","你好");

    }

}
