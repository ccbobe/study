package com.ccbobe;

import org.springframework.stereotype.Component;
@RoutingClass(name = "TestBase",values = {"add"},version = "1")
@Component
public class TestBase implements BaseRun {
    @Override
    public Context run(Context context) {
        return context;
    }
}
