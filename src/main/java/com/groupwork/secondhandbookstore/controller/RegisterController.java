package com.groupwork.secondhandbookstore.controller;

import com.groupwork.secondhandbookstore.pojo.Result;
import com.groupwork.secondhandbookstore.pojo.User;
import com.groupwork.secondhandbookstore.service.impl.UserService;
import com.groupwork.secondhandbookstore.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class RegisterController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        log.info("用户注册:{}",user.getUsername());
        String username=user.getUsername();
        User check=userService.getUserByUserName(username);
        if(check!=null){
            System.out.println("用户"+username+"已存在");
            return Result.error("用户名重复，请重新输入用户名");
        }
        userService.register(user);
        return Result.success("用户"+username+"已成功注册");
    }
}
