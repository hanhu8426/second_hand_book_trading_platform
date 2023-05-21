package com.groupwork.secondhandbookstore.service;

import com.groupwork.secondhandbookstore.pojo.User;

public interface UserService {
    public User login(User user);
    public User getByAccount(String account);

    public void register(User user);

    public User showUserById(String id);
}
