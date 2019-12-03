package com.ccbobe.kafkamq.consumer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Component;
import sun.reflect.generics.tree.Tree;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test")
    public void listen (ConsumerRecord<?, ?> record) throws Exception {
        System.out.println(":"+record.key()+"--->"+record.offset()+"--"+record.headers()+"++"+record.value());
    }
    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @KafkaListener(topics = "test",topicPartitions = {@TopicPartition(topic = "test", partitions = {"0", "1"})})
    public void listenTest (ConsumerRecord<?, ?> record) throws Exception {
        System.out.println("2---->"+record.key()+"--->"+record.offset()+"--"+record.headers()+"++"+record.value());
    }

    public void printIds(){
        registry.start();

        Map map = new TreeMap();
    }

}
