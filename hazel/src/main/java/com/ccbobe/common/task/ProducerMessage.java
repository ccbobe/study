package com.ccbobe.common.task;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class ProducerMessage {

    transient HazelcastInstance instance = Hazelcast.newHazelcastInstance();

    @Scheduled(fixedDelay = 1000)
    public void produceMsg(){

        ITopic<Object> topic = instance.getTopic("topic");

        topic.publish("message......"+ LocalDateTime.now());
        log.info("发布消息。。。。。");
       log.info("{}",instance.getCluster().getClusterState());
    }
}
