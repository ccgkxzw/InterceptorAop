package com.xzw.intercetortest.controller;

import com.xzw.intercetortest.pojo.User;
import com.xzw.intercetortest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserService userService;

//    @RequestMapping("/slave")
//    @ResponseBody
//    public User test01(){
//        String name="测试用户007";
//        User users=userService.getUserSlave(name);
//        return  users;
//    }
//
//    @RequestMapping("/master")
//    @ResponseBody
//    public User test02(){
//        String name="测试用户007";
//        User users=userService.getUserMaster(name);
//        return users;
//    }
    @ResponseBody
    @RequestMapping("/test")
    public User Hello(){
        String name="测试用户007";
        User user = userService.getUser(name);
        return user;
    }
}
