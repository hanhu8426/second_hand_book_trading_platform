package com.secondhandbookstore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.secondhandbookstore.mapper.ManagerMapper;
import com.secondhandbookstore.pojo.PageBean;
import com.secondhandbookstore.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ManagerService implements com.secondhandbookstore.service.ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    @Override
    public void deleteUser(Integer id){
        managerMapper.deleteUserById(id);
    }

    @Override
    public void deleteBook(Integer id){
        managerMapper.deleteBookById(id);
    }

    @Override
    public PageBean UserPage(Integer page,Integer pageSize){
        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<User> userList=managerMapper.userPageList();
        Page<User> p=(Page<User>) userList;

        //3. 封装PageBean对象
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }
}
