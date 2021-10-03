package com.yunblog.service.impl;

import com.yunblog.entiy.User;
import com.yunblog.mapper.UserMapper;
import com.yunblog.service.UserService;
import com.yunblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     *
     * @param username
     * @param password
     * @return user 对象
     */
    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.checkUser(username, MD5Utils.code(password));
        return user;
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public User Usercheck(String username) {
        return userMapper.Usercheck(username);
    }


}
