package com.ccbobe.websocket.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirstBean {

    public FirstBean() {
        log.info("初始化...First...");
    }
}
