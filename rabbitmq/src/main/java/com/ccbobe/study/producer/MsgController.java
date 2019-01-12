package com.ccbobe.study.producer;


import com.alibaba.fastjson.JSON;
import com.ccbobe.study.message.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/msgs/")
public class MsgController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate template;


    @RequestMapping("sends")
    public Order sendMsgToMaster(@RequestBody Order order) {
        //简单消息发送信息
        logger.info("发送消息信息{}",JSON.toJSONString(order));
        template.convertAndSend("amq.direct","demo.master",order);
        return order;
    }

    @RequestMapping("sendToBak")
    public Order sendMsgToBak(@RequestBody Order order) {
        //简单消息发送信息
        logger.info("发送消息信息{}",JSON.toJSONString(order));
        template.convertAndSend("amq.direct","demo.bak",order);
        return order;
    }
}
