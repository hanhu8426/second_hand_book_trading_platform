package com.groupwork.secondhandbookstore.controller;

import com.groupwork.secondhandbookstore.pojo.Result;
import com.groupwork.secondhandbookstore.pojo.User;
import com.groupwork.secondhandbookstore.service.UserService;
import com.groupwork.secondhandbookstore.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/user")
    public Result showUserInfo(@RequestHeader("token")String jwt){
//        User user = userService.showUserById(id);
//        if(user!=null)return Result.success(user);
//        return Result.error("用户不存在");
        Map<String, Object> claims = JwtUtils.parseJWT(jwt);
        System.out.println(jwt);

        User show=userService.getByAccount(claims.get("account").toString());
        System.out.println(show);

        return Result.success(show);
    }
}
