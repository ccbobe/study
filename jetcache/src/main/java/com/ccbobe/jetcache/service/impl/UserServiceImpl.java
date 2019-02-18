package com.ccbobe.jetcache.service.impl;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.ccbobe.jetcache.entity.User;
import com.ccbobe.jetcache.repository.UserRepository;
import com.ccbobe.jetcache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ccbobe
 * jpa使用信息
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    @Cached(name = "user.",key = "#id",expire = 3600,localLimit = 50,cacheType = CacheType.BOTH)
    @CacheRefresh(refresh = 200,stopRefreshAfterLastAccess = 100,timeUnit = TimeUnit.MINUTES,refreshLockTimeout = 2)
    @Override
    public User findUserById(Integer id) {
        return userRepository.getOne(id);
    }
}
