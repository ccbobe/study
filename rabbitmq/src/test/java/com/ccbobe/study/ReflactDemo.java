package com.ccbobe.study;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ReflactDemo {
    
    public static void main(String[] args) throws Throwable {
        
       Class clazz = Class.forName("com.ccbobe.study.User");
    
        System.out.println(JSON.toJSONString(clazz));
    
        Field[] fields = clazz.getDeclaredFields();
    
        System.out.println(fields.length);
    
    
        Object instance = clazz.newInstance();
    
        Method method = clazz.getMethod("setUserName", String.class);
        
        method.invoke(instance,"ccbobe");
    
        Method userName = clazz.getMethod("getUserName", null);
    
        Object invoke = userName.invoke(instance, null);
    
        System.out.println(invoke);
    
        System.out.println(JSON.toJSONString(instance));
    }
    
    
    @Test
    public void testDefaultMethod() throws Throwable{
        Class<?> name = Class.forName("com.ccbobe.study.Teacher");
    
        Method say = name.getMethod("say", String.class);
    
        Object instance = name.newInstance();
    
        Object o = say.invoke(instance, "我是学生");
    }
    
    @Test
    public void testConstruct() throws Throwable{
        
        
    
    
        Class clazz2 = User.class;
        //获取私有构造器
        Constructor<?> constr2 = clazz2.getConstructor();
       
        Object o2 = constr2.newInstance();
    
        Method method2 = clazz2.getMethod("user", String[].class);
        
        method2.invoke(o2,new String[][]{new String[]{"1","232ddd"}});
    
        
    }
    
    
    @Test
    public void testPachage() throws Throwable{
        User user = new User();
        
        String[] string = new String[]{"1","2","hello","java","demo"};
        
        user.user(string);
    
    
    }
    
    @Test
    public void testConsumner(){
        //测试消费型接口
        strTrim("hello",(x)-> System.out.println("购物消息"+ x +"*"));
        
       Supplier<User> supplier = User::new;
    
        User user = supplier.get();
        
        user.setAge(20);
    
        User user1 = supplier.get();
    
        System.out.println(JSON.toJSONString(user));
    
        System.out.println(JSON.toJSONString(user1));
    }
    
    public void strTrim(String str, Consumer<String> consumer){
        consumer.accept(str.toUpperCase().trim());
    }
    
    public String strGet( Supplier<String> supplier){
        return supplier.get();
    }
    
}
