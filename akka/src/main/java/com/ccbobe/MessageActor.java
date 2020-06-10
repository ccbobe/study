package com.ccbobe;

import akka.actor.UntypedAbstractActor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageActor extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        log.info("this is message ：{}",message);
    }
}
