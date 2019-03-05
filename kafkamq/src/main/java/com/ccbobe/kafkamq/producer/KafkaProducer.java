package com.ccbobe.kafkamq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
  
    
    @RequestMapping(value = "stringKafkaProducer")
    public void stringKafkaProducer(String name){
        logger.info("请求参数信息{}",name);
    }
}
