package com.ccbobe.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
public class UserProvider implements UserService.Iface {
    @Override
    public User addUser(User user) throws TException {
        return user;
    }

    @Override
    public User getUser(long userId) throws TException {
        User user = new User();
        user.setUserId(userId);
        user.setUserName("ccbobe"+System.currentTimeMillis());
        return user;
    }

    @Override
    public List<User> queryUsers() throws TException {
        return null;
    }

    @Override
    public User updateUser(User user) throws TException {
        return user;
    }

}
