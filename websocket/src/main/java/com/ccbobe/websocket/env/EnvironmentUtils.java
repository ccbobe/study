package com.ccbobe.websocket.env;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author ccbobe
 */
@Component
public class EnvironmentUtils implements EnvironmentAware {

    private static Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        EnvironmentUtils.environment = environment;
    }

    public static String searchByKey(String key){
        return EnvironmentUtils.environment.getProperty(key);
    }
}
