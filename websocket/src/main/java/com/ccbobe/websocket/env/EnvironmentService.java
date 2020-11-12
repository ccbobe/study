package com.ccbobe.websocket.env;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @author ccbobe
 */
@Slf4j
@Service
public class EnvironmentService implements InitializingBean {

    @Autowired
    private Environment environment;

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    //@Scheduled(fixedDelay = 100)
    public void scheduler(){
        ValueInfo info = new ValueInfo();
        info.setData();
    }


}
