package com.ccbobe.kafkamq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaTemplate kafkaTemplate;
    
    @RequestMapping(value = "stringKafkaProducer")
    public void stringKafkaProducer(String name){
        logger.info("请求参数信息{}",name);
        kafkaTemplate.send("test","你好");
    }

    @Scheduled(fixedDelay = 2000)
    public void send(){
        logger.info("请求参数信息");
        kafkaTemplate.send("test","你好");
    }

    @Scheduled(fixedDelay = 1000)
    public void sendPush(){
        logger.info("请求参数信息");
        kafkaTemplate.send("test",0,"100","你好");
    }
}
