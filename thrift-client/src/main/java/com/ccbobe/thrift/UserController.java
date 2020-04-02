package com.ccbobe.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.async.AsyncMethodCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.ByteBuffer;

@Slf4j
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


    @RequestMapping("getUserAsync")
    public User getUserAsync(Long userId)throws Exception{


        client.getUserAsyncClient().getUser(userId,new AsyncMethodCallback<User>(){
            @Override
            public void onComplete(User response) {
                log.info(response.toString());
            }

            @Override
            public void onError(Exception exception) {
                ByteBuffer buffer = null;
                buffer.rewind();
            }
        });
        return null;
    }
}
