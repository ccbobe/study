package com.ccbobe.controller;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author ccbobe
 */
@RestController
@RequestMapping("/")
public class HazelMapController {
    @Autowired
    private Config config;


    HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);

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
        Collection<Object> instruments = instance.getMap("instruments").values();
        return instruments;
    }
}
