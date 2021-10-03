package com.yunblog.service;


import com.yunblog.entiy.User;

import java.util.List;

public interface UserService {

    User checkUser(String username,String password);

    User Usercheck(String username);



}
