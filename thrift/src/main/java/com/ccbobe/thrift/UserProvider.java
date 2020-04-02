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

    @Override
    public void addUser(User user, AsyncMethodCallback<User> resultHandler) throws TException {
        try {
            resultHandler.onComplete(user);
        } catch (Exception e) {
            resultHandler.onError(new Exception("添加用户异常"));
        }

    }

    @Override
    public void getUser(long userId, AsyncMethodCallback<User> resultHandler) throws TException {
        try {
            log.info("获取用户信息{}");
            User user = new User();
            user.setUserId(userId);
            user.setAge(29);
            user.setUserName("ccbobe"+System.currentTimeMillis());
            resultHandler.onComplete(user);
        } catch (Exception e) {
            log.info("出现异常");
        }
    }

    @Override
    public void queryUsers(AsyncMethodCallback<List<User>> resultHandler) throws TException {

    }

    @Override
    public void updateUser(User user, AsyncMethodCallback<User> resultHandler) throws TException {

    }
}
