package com.ccbobe.study.consumer;

import com.alibaba.fastjson.JSON;
import com.ccbobe.study.message.Order;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rabbitmq.tools.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(bindings={
            @QueueBinding(value = @Queue("demo"),exchange = @Exchange(value = "amq.direct",durable ="true" ),
            key = "demo.master")})
    public void simpleRecever(Order recever){
        logger.info("demo.master接受消息信息{}",JSON.toJSONString(recever));

    }

    @RabbitListener(bindings={
            @QueueBinding(value = @Queue("demo"),exchange = @Exchange(value = "amq.direct",durable ="true"),
                    key = "demo.bak")})
    public void simpleReceverByKeyBak(Order recever){
        logger.info("demo.bak接受消息信息{}",JSON.toJSONString(recever));

    }

}
