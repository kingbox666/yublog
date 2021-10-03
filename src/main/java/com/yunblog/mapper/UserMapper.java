package com.yunblog.mapper;

import com.yunblog.entiy.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    User checkUser(@Param("username") String username,@Param("password") String password);

    User Usercheck(@Param("username") String username);


}
