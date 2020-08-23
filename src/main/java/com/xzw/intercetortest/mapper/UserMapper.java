package com.xzw.intercetortest.mapper;


import com.xzw.intercetortest.pojo.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {

    User selectByUserName(@Param("username") String username);

}