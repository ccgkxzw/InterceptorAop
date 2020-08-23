package com.xzw.intercetortest.service.impl;

import com.xzw.intercetortest.mapper.UserMapper;
import com.xzw.intercetortest.pojo.User;
import com.xzw.intercetortest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public User getUser(String name) {
        return userMapper.selectByUserName(name);
    }
}
