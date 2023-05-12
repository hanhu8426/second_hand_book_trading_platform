package com.groupwork.secondhandbookstore.controller;

import com.groupwork.secondhandbookstore.pojo.Result;
import com.groupwork.secondhandbookstore.pojo.User;
import com.groupwork.secondhandbookstore.service.UserService;
import com.groupwork.secondhandbookstore.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("员工登录:{}",user);
        User login = userService.login(user);
        if(login!=null){
            Map<String, Object> claims=new HashMap<>();
            claims.put("username",login.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return Result.error("用户名或密码错误");
    }
}
