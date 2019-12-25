package com.ccbobe.websocket.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AsyncService {

    @Async
    public String asyncPrimary(){
      log.info("执行 Primary 异步......");
      return null;
    }

    @Async("threadPool")
    public String asyncPools(){
        log.info("执行 threadPool 异步......");
        return null;
    }
}
