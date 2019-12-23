package com.ccbobe.controller;


import com.hazelcast.core.*;
import com.hazelcast.cp.lock.FencedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author ccbobe
 */

@Slf4j
@RestController
@RequestMapping("/")
public class HazelMapController {


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


    @RequestMapping("offerService")
    public Object offerService(String name){
        //生产数据

        IQueue<Object> queue = instance.getQueue("queue");
        boolean offer = queue.offer(name);
        return offer;
    }

    @RequestMapping("takeService")
    public Object takeService(String name) throws Exception{
        IQueue<Object> queue = instance.getQueue("queue");
        //消费数据
        Object take = queue.take();
        return take;
    }

    @RequestMapping("lockService")
    public Object lockService(String name) throws Exception{
        FencedLock fencedLock = instance.getCPSubsystem().getLock("lock");
        if (fencedLock.tryLock()){
            log.info("已经加锁。。。。。别人无法加锁啦");
            Thread.sleep(30000);
        }
        FencedLock lock = fencedLock;
        lock.unlock();
        return lock.isLocked();
    }
}
