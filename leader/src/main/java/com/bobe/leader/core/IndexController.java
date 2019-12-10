package com.bobe.leader.core;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ccbobe
 */
@RestController
public class IndexController {

    @RequestMapping("/count/{name}")
    @Count
    public String count(@PathVariable("name") String name){
        System.out.println(name);
        return name;
    }
}
