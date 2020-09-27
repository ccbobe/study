package com.ccbobe.google;

import com.google.common.util.concurrent.RateLimiter;

public class LimitDemo {
    public static void main(String[] args) throws Exception {
        RateLimiter limiter = RateLimiter.create(8);
        for (int i = 0; i < 100; i++) {
            boolean b = limiter.tryAcquire(1);
            Thread.sleep(200);
            if (b){
                System.out.println("ok...");
            }else {
                System.out.println("pass ...");
            }
        }

    }
}
