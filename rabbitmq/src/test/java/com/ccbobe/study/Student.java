package com.ccbobe.study;

public class Student implements Person {
    private Integer age;
    
    private String name;
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Student(Integer age, String name) {
        this.age = age;
        this.name = name;
    }
    
    public Student() {
    }
}
