package com.ccbobe.thrift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/")
public class UserController {
    @Autowired
    private UserServiceClient client;

    @RequestMapping("addUser")
    public User addUser(@RequestBody User user)throws Exception{
        client.getClient().addUser(user);
        return user;
    }

    @RequestMapping("getUser")
    public User getUser(Long userId)throws Exception{
        User user = client.getClient().getUser(userId);
        return user;
    }


    @RequestMapping("getDate")
    public Response getDate(Long userId)throws Exception{
        Response response = client.getDateClient().getDate();
        return response;
    }
}
