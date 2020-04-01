package com.ccbobe.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
public class UserProvider implements UserService.Iface,UserService.AsyncIface {
    @Override
    public User addUser(User user) throws TException {
        return null;
    }

    @Override
    public User getUser(long userId) throws TException {
        return null;
    }

    @Override
    public List<User> queryUsers() throws TException {
        return null;
    }

    @Override
    public User updateUser(User user) throws TException {
        return null;
    }

    @Override
    public void addUser(User user, AsyncMethodCallback<User> resultHandler) throws TException {

    }

    @Override
    public void getUser(long userId, AsyncMethodCallback<User> resultHandler) throws TException {

    }

    @Override
    public void queryUsers(AsyncMethodCallback<List<User>> resultHandler) throws TException {

    }

    @Override
    public void updateUser(User user, AsyncMethodCallback<User> resultHandler) throws TException {

    }
}
