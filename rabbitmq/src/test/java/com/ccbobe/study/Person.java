package com.ccbobe.study;

public interface Person {
    
    default void say(String say){
        System.out.println("++++++"+say);
    }
}
