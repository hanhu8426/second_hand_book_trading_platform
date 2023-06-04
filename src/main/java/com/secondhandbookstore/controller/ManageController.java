package com.secondhandbookstore.controller;

import com.secondhandbookstore.pojo.PageBean;
import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class ManageController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/manager/showUsers")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        log.info("分页查询，参数：{},{}", page, pageSize);
        //调用service分页查询
        PageBean pageBean = managerService.UserPage(page, pageSize);
        return Result.success(pageBean);
    }


    @RequestMapping("/manager/deleteUser")
    public Result deleteUser(@RequestParam(value = "id") Integer UserId){
        managerService.deleteUser(UserId);
        return Result.success();
    }

    @RequestMapping("/manager/deleteBook")
    public Result deleteBook(@RequestParam(value = "id")Integer bookId){
        managerService.deleteBook(bookId);
        return Result.success();
    }
}
