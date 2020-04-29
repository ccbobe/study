package com.ccbobe.websocket.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@DependsOn(value = {"firstBean"})
public class AfterBean {

    public AfterBean() {
        log.info("AfterBean   init....");
    }
}
