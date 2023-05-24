package com.secondhandbookstore.service;

import com.secondhandbookstore.pojo.Address;
import com.secondhandbookstore.pojo.User;

public interface UserService {
    public User login(User user);
    public User getByAccount(String account);

    public void register(User user);

    public User getById(Integer id);

    public float modifyUserBalance(Integer id, Float balance);

    public void modifyUserInfo(User userInfo);

    User getByIdAndPassword(Integer id, String oldPassword);

    void modifyPassword(Integer id,String newPassword);

    void addAddressList(Address address);

    void modifyUserAddress(Address modifyAddress);
}
