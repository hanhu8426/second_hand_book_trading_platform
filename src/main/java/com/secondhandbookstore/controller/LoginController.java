package com.secondhandbookstore.controller;

import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.pojo.User;
import com.secondhandbookstore.service.UserService;
import com.secondhandbookstore.utils.JwtUtils;
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
    public Result login(@RequestBody User user, HttpServletResponse rs){
        log.info("用户登录:{}",user);
        User login = userService.login(user);
        if(login!=null){
            Map<String, Object> claims=new HashMap<>();
            claims.put("id",login.getId());
            String jwt = JwtUtils.generateJwt(claims);
            //将jwt令牌添加到响应头
            rs.addHeader("Access-Control-Expose-Headers","authorization");
            rs.addHeader("authorization",jwt);
            return Result.success(login);
        }
        return Result.error("用户名或密码错误");
    }
}
