package com.ccbobe;

import com.hazelcast.core.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class SubMessageRunner implements CommandLineRunner {

    transient HazelcastInstance instance = Hazelcast.newHazelcastInstance();

    @Override
    public void run(String... args) throws Exception {
        log.info("监听topic 信息。。。。。");
        ITopic<String> topic = instance.getTopic("topic");
        topic.addMessageListener(new MessageListenerImpl());
    }

    private class MessageListenerImpl implements MessageListener<String> {
        public void onMessage(Message<String> m) {
            System.out.println("Received: " + m.getMessageObject());
        }
    }
}
