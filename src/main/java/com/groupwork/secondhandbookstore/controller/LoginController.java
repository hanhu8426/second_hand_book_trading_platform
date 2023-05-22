package com.groupwork.secondhandbookstore.controller;

import com.groupwork.secondhandbookstore.pojo.Result;
import com.groupwork.secondhandbookstore.pojo.User;
import com.groupwork.secondhandbookstore.service.UserService;
import com.groupwork.secondhandbookstore.utils.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping  ("/Login")
    public void login(@RequestBody User user, HttpServletResponse rs){
        log.info("员工登录:{}",user);
        User login = userService.login(user);
        if(login!=null){
            Map<String, Object> claims=new HashMap<>();
            claims.put("account",login.getAccount());
            String jwt = JwtUtils.generateJwt(claims);
            //将jwt令牌添加到响应头
            rs.addHeader("Access-Control-Expose-Headers","authorization");
            rs.addHeader("authorization",jwt);
            //return Result.success();
        }
        //return Result.error("用户名或密码错误");
    }
}
