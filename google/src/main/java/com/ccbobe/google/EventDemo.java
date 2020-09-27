package com.ccbobe.google;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class EventDemo {

    public static void main(String[] args) {
        //同步消息
        /*EventBus eventBus = new EventBus("order");
        eventBus.register(new EventListener());
        OrderEvent event = new OrderEvent();
        event.setName("ccbobe");
        for (int i = 0; i < 100; i++) {
            event.setOrderBiz("order"+i);
            eventBus.post(event);
        }*/
        //异步时间消息
        ExecutorService service = Executors.newFixedThreadPool(20);
        AsyncEventBus eventBus = new AsyncEventBus(service);
        eventBus.register(new EventListener());
        OrderEvent event = new OrderEvent();
        event.setName("ccbobe");
        for (int i = 0; i < 100; i++) {
            event.setOrderBiz("order" + i);
            eventBus.post(event);
        }
    }
}
