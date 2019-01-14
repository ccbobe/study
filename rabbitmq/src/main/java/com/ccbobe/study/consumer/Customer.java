package com.ccbobe.study.consumer;

import com.alibaba.fastjson.JSON;
import com.ccbobe.study.message.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

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
	
    @Autowired
    private ArrayBlockingQueue arrayBlockingQueue;
    
	
	@RabbitListener(bindings={
			@QueueBinding(value = @Queue("testfan"),exchange = @Exchange(value = "amq.fanout",durable ="true"),
					key = "test.fanout")})
	public void simpleReceverByFanout(Order recever){
		logger.info("demo.bak接受消息信息{}",JSON.toJSONString(recever));
		arrayBlockingQueue.offer(recever);
		
		logger.info("当前阻塞队列中收到的元素信息大概有{}",arrayBlockingQueue.size());
		
		Order poll =(Order) arrayBlockingQueue.poll();
		
		System.out.println(JSON.toJSON(poll));
		
	}
	
	
	@RabbitListener(bindings={
			@QueueBinding( value = @Queue (value = "demo",durable = "true"),exchange = @Exchange(value = "amq.rabbitmq.event",durable ="true"))})
	public void receverEvent(Object recever){
		logger.info("事件接受消息信息{}",JSON.toJSONString(recever));
		
	}
	
	
	
}
