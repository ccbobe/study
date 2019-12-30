package com.ccbobe.websocket.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

}
