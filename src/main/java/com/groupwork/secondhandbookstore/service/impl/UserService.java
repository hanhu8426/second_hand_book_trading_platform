package com.groupwork.secondhandbookstore.service.impl;

import com.groupwork.secondhandbookstore.mapper.UserMapper;
import com.groupwork.secondhandbookstore.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements com.groupwork.secondhandbookstore.service.UserService {

    @Autowired
    private UserMapper userMapper;
    public User login(User user){
        return userMapper.getByUserNameAndPassword(user);
    }

    public void register(User user) {
        userMapper.register(user);
    }

    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }
}
