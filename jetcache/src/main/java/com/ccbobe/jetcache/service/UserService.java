package com.ccbobe.jetcache.service;

import com.ccbobe.jetcache.entity.User;
import com.ccbobe.jetcache.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {


    User saveUser(User user);

    User findUserById(Integer id);

}
