package com.secondhandbookstore.service;

import com.secondhandbookstore.pojo.PageBean;
import org.springframework.stereotype.Service;

@Service
public interface ManagerService {

    void deleteUser(Integer id);

    void deleteBook(Integer id);

    PageBean UserPage(Integer page,Integer pageSize);
}
