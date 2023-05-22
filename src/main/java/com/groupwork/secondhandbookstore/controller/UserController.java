package com.groupwork.secondhandbookstore.controller;

import com.groupwork.secondhandbookstore.pojo.Result;
import com.groupwork.secondhandbookstore.pojo.User;
import com.groupwork.secondhandbookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/user")
    public Result showUserInfo(@RequestHeader("token")String jwt){
//        User user = userService.showUserById(id);
//        if(user!=null)return Result.success(user);
//        return Result.error("用户不存在");
        System.out.println(jwt);
        return Result.success(jwt);
    }
}
