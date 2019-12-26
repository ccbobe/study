package com.ccbobe.websocket.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties",encoding = "utf-8")
public class ValueService {
    @Value("${user.fullName}")
    String fullName;

    @Value("${user.ext}")
    String ext;

}
