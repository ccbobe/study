package com.ccbobe.study.producer;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.ccbobe.study.message.Order;
import com.ccbobe.study.message.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

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
	
	@RequestMapping("sendMsgToFanout")
	public Order sendMsgToFanout(@RequestBody Order order) {
		//简单消息发送信息
		logger.info("发送消息信息{}",JSON.toJSONString(order));
		template.convertAndSend("amq.fanout","demo.*",order);
		
		return order;
	}

	@Autowired
	private RabbitAdmin rabbitAdmin;

    @Autowired
    private Queue queue;

    @RequestMapping("declareQueue")
    @ResponseBody
    public Object  RabbitAdminTo() {
        //创建队列
        String queue = rabbitAdmin.declareQueue(this.queue);
        return queue;
    }


    @RequestMapping("declareExchange")
    @ResponseBody
    public Object declareExchange() {
        //创建交换机信息
        rabbitAdmin.declareExchange(new TopicExchange("topic.hello", true, false));
        return "declareExchange";
    }


    @RequestMapping("sendMsgToHelloOrder")
    public Order sendMsgToHello(@RequestBody Order order) {
        //简单消息发送信息
        logger.info("发送消息信息{}",JSON.toJSONString(order));
        template.convertAndSend("topic.hello","topic.hello",order);

        return order;
    }

    @RequestMapping("sendMsgToHelloShop")
    public Shop sendMsgToHello(@RequestBody Shop shop) {
        //简单消息发送信息
        logger.info("发送消息信息{}",JSON.toJSONString(shop));
        template.convertAndSend("topic.hello","topic.hello",shop);

        return shop;
    }


	
}
