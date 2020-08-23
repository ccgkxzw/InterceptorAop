package com.xzw.intercetortest.service;


import com.xzw.intercetortest.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {



    User getUser(String name);


}
