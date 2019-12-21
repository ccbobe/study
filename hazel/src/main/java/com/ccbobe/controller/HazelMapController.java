package com.ccbobe.controller;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICountDownLatch;
import com.hazelcast.core.IExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author ccbobe
 */

@Slf4j
@RestController
@RequestMapping("/")
public class HazelMapController {
    @Autowired
    private Config config;

    transient HazelcastInstance  instance = Hazelcast.newHazelcastInstance();

    //------------------------map------------------------------------
    @RequestMapping("maps/{key}/{value}")
    public String set(@PathVariable("key") String key, @PathVariable("value") String value){

        instance.getMap("instruments").set(key,value);
        return key+":" +value;
    }

    @RequestMapping("maps/{key}")
    public String findByKey(@PathVariable("key") String key){
        Object o = instance.getMap("instruments").get(key);
        return key+":" +o;
    }

    @RequestMapping("maps/all")
    public Collection findAll(){
        // 获取集合中的所有信息
        Collection<Object> instruments = instance.getMap("instruments").values();

        return instruments;
    }


    @RequestMapping("execService")
    public Object execService(){

        IExecutorService executorService = instance.getExecutorService("exec");
        executorService.executeOnAllMembers(new Echo());
        return 123;
    }

    @RequestMapping("submitService")
    public Object submitService(){
        ICountDownLatch latch = instance.getCPSubsystem().getCountDownLatch("latch");
        IExecutorService executorService = instance.getExecutorService("exec");
        Future future = executorService.submit(new EchoTask("2", latch));
        try {
            Object o = future.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 123;
    }
}
