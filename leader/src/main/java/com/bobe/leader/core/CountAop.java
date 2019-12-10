package com.bobe.leader.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Random;

/**
 * @Author:ccbobe
 */


@Aspect
@Service
@Order(100)
public class CountAop {

    /**
     *  动态修改注解参数
     */
    @Before(value = "@annotation(com.bobe.leader.core.Count)")
    public void count(JoinPoint joinPoint){
        //动态修改注解属性
        Signature signature = joinPoint.getSignature();
        // 类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 方法名
        String methodName = joinPoint.getSignature().getName();
        // 参数
        Object[] arguments = joinPoint.getArgs();
        // 切点类
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {

        }
        Method[] methods = targetClass.getMethods();
        Count count = null;
        Method target = null;
        //获取当前方法注解信息
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) {
                    count = method.getAnnotation(Count.class);
                    target = method;
                    break;
                }
            }
        }
        //反射修改当前注解属性值
        MethodSignature methodSignature = (MethodSignature) signature;
        boolean b = target.isAnnotationPresent(Count.class);
        if (b){
            Count annotation = target.getAnnotation(Count.class);
            InvocationHandler handler = Proxy.getInvocationHandler(annotation);
            try {
                Field memberValues = handler.getClass().getDeclaredField("memberValues");
                memberValues.setAccessible(true);
                Map<Object, Object> map = (Map<Object, Object>) memberValues.get(handler);
                map.put("count", new Random().nextInt());

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

}
