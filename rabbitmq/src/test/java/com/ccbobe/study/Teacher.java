package com.ccbobe.study;

public class Teacher implements Person {
    
    @Override
    public void say(String say) {
        System.out.println("teacher=====::::"+say);
    }
    
}
