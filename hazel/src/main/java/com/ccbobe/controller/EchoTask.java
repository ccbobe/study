package com.ccbobe.controller;
import com.hazelcast.core.ICountDownLatch;

import java.io.Serializable;

import java.util.concurrent.Callable;

public class EchoTask implements Callable, Serializable {

    private final String msg;

    private ICountDownLatch latch;

    public EchoTask(String msg, ICountDownLatch latch) {
        this.msg = msg;
        this.latch = latch;
        System.out.println(msg);
    }

    public Integer call() {
        try {
            Thread.sleep(5000);
            return  new java.lang.Integer(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }finally {
            latch.countDown();
            System.out.println("Echo: " + msg);
        }


    }
}