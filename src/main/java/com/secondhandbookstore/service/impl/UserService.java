package com.secondhandbookstore.service.impl;

import com.secondhandbookstore.mapper.UserMapper;
import com.secondhandbookstore.pojo.Address;
import com.secondhandbookstore.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements com.secondhandbookstore.service.UserService {

    @Autowired
    private UserMapper userMapper;
    public User login(User user){
        return userMapper.getByAccountAndPassword(user);
    }

    public void register(User user) {
        userMapper.register(user);
    }

    public User getByAccount(String account) {
        return userMapper.getByAccount(account);
    }

    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    public float modifyUserBalance(Integer id, Float balance) {
        userMapper.modifyUserBalance(id,balance);
        return userMapper.queryCurrentBalance(id);
    }

    public void modifyUserInfo(User userInfo){
        userMapper.modifyUserInfo(userInfo);
    }

    public User getByIdAndPassword(Integer id, String oldPassword) {
        return userMapper.getByIdAndPassword(id,oldPassword);
    }

    public void modifyPassword(Integer id,String newPassword) {
        userMapper.modifyPassword(id,newPassword);
    }

    public void addAddressList(Address address){
        userMapper.addAddressList(address);
    }

    public void modifyUserAddress(Address modifyAddress){
        userMapper.modifyUserAddress(modifyAddress);
    }

    @Override
    public Float checkBalance(Integer id) {
        return userMapper.checkUserBalance(id);
    }

    @Override
    public String checkName(Integer addId) {
        return userMapper.checkName(addId);
    }

    @Override
    public String checkPhone(Integer addId) {
        return userMapper.checkPhone(addId);
    }

    @Override
    public String checkAddress(Integer addId) {
        return userMapper.checkAddress(addId);
    }
}
