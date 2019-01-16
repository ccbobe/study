package com.ccbobe.study.consumer;

import com.alibaba.fastjson.JSON;
import com.ccbobe.study.message.Order;
import com.ccbobe.study.message.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@RabbitListener( bindings = {@QueueBinding(value = @Queue("hello"),exchange = @Exchange(value = "topic.hello",durable =
        "true",
        type = ExchangeTypes.TOPIC),key = "topic.hello"
)},priority ="2")
@Component
public class CustomerListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void receive(Order order){
        logger.info("Order接受参数信息{}",JSON.toJSONString(order));
    }


    @RabbitHandler
    public void receiveByShop(Shop shop){
        logger.info("Shop接受参数信息{}",JSON.toJSONString(shop));
    }

}
