package com.ccbobe.google;

import com.google.common.eventbus.Subscribe;

public class EventListener {

    @Subscribe
    public void subscribeMsg(OrderEvent event){
        System.out.println("收到消息=====>"+event.getOrderBiz()+"==="+Thread.currentThread().getName());
    }
}
