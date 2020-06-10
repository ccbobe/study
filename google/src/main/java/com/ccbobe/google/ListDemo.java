package com.ccbobe.google;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ListDemo {

    public static void main(String[] args) {
        ArrayList<Object> list = Lists.newArrayList();
        System.out.println(list.size());
        boolean add = list.add(new Integer(1));
        System.out.println(add);
        System.out.println(list.get(0));
    }
}
