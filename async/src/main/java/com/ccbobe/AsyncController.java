package com.ccbobe;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Slf4j
@RequestMapping
@RestController
public class AsyncController {


    @Bean
    public ConcurrentHashMap concurrentHashMap(){
        return new ConcurrentHashMap(32);
    }


    public String pullMsg(String msgId,String data){

        return "";
    }

    @RequestMapping("addClass")
    public String addClass(String className) throws Exception {
       String fullName = "com.ccbobe."+className;
        ApplicationContextUtils.addT(fullName);
        Base base = ApplicationContextUtils.getBean(className);
        return base.run(className);

    }

    @RequestMapping("addClassBySpring")
    public String addClassBySpring(String className) throws Exception, BeanDefinitionStoreException {
        //将applicationContext转换为ConfigurableApplicationContext
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) ApplicationContextUtils.getContext();

        // 获取bean工厂并转换为DefaultListableBeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();


        Class<?> aClass = Class.forName("com.ccbobe." + className);

        // 通过BeanDefinitionBuilder创建bean定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(aClass);

        // 注册bean
        defaultListableBeanFactory.registerBeanDefinition(className, beanDefinitionBuilder.getRawBeanDefinition());


        Base base = (Base) ApplicationContextUtils.getBean(className);
        return base.run(className);

    }


}
