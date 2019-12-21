package com.ccbobe.controller;

import java.io.Serializable;

public class Echo implements Runnable, Serializable {
    @Override
    public void run() {
        System.out.println("Echo......."+System.currentTimeMillis());
    }
}
