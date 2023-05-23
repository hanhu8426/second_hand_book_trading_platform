package com.groupwork.secondhandbookstore.controller;

import com.groupwork.secondhandbookstore.pojo.Result;
import com.groupwork.secondhandbookstore.pojo.User;
import com.groupwork.secondhandbookstore.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class RegisterController {
    @Autowired
    private UserService userService;
    @RequestMapping("/Register")
    public Result register(@RequestParam String account,@RequestParam String password){
        log.info("用户注册:{}",account);

        User check=userService.getByAccount(account);

        if(check!=null){
            System.out.println("用户"+account+"已存在");
            return Result.error("用户名重复，请重新输入用户名");
        }
//      User user=new User(account,password);

        userService.register(new User(account,password));
        return Result.success("用户"+account+"已成功注册");
    }
}
