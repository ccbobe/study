package com.ccbobe.websocket.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CheckHealth implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up().withDetail("正常",true).build();
    }
}
