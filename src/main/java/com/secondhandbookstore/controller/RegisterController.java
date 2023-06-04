package com.secondhandbookstore.controller;

import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.pojo.User;
import com.secondhandbookstore.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class RegisterController {
    @Autowired
    private UserService userService;
    @RequestMapping("/Register")
    public Result register(@RequestParam String account, @RequestParam String password){
        log.info("用户注册:{}",account);

        if(userService.getByAccount(account)!=null){
            System.out.println("用户"+account+"已存在");
            return Result.error("用户名重复，请重新输入用户名");
        }

        userService.register(new User(account,password));
        return Result.success("用户"+account+"已成功注册");
    }
}
