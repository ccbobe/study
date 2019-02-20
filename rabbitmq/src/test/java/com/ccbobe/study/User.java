package com.ccbobe.study;

public class User {
    
    private String userName;
    
    private String password;
    
    private int age;
    
    private int sex;
    
    
    
    public void user(String ... strings){
        for (String string:strings) {
            System.out.println("变成数组信息"+string);
        }
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public int getSex() {
        return sex;
    }
    
    public void setSex(int sex) {
        this.sex = sex;
    }
}
