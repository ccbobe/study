package com.ccbobe.jetcache.controller;

import com.ccbobe.jetcache.entity.User;
import com.ccbobe.jetcache.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.Session;

@RestController
@RequestMapping(value = "/users/")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @PostMapping("save")
    public User save (@RequestBody User user){
        logger.info("写入用户信息");
        return userService.saveUser(user);
    }

    @GetMapping("findUserById")
    public Integer findUserById (Integer id){
        logger.info("查询用户信息{}",id);
        User user = userService.findUserById(id);
        return user.getId();
    }

}
