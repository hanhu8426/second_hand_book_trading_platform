package com.secondhandbookstore.controller;

import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.pojo.CollectPassword;
import com.secondhandbookstore.pojo.User;
import com.secondhandbookstore.pojo.Address;
import com.secondhandbookstore.service.UserService;
import com.secondhandbookstore.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    //获取用户信息
    @RequestMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader("Authorization")String jwt){
        Integer id=JwtUtils.parseJWTAndGenerateId(jwt);
        User show=userService.getById(id);
        log.info("查询用户信息:{}",show.getAccount());
        return Result.success(show);
    }

    //修改用户信息一:修改用户余额
    @RequestMapping("/user/userInfo/ModUserInfo_1")
    public Result modifyUserBalance(@RequestParam("charge")String balance,@RequestHeader("token")String jwt){
        float currentBalance = userService.modifyUserBalance(JwtUtils.parseJWTAndGenerateId(jwt),Float.parseFloat(balance));
        return Result.success(currentBalance);
    }

    //修改用户信息二:添加一些用户的基础信息
    @RequestMapping("/user/userInfo")
    public Result modifyUserInfo(@RequestHeader("Authorization")String jwt,@RequestBody User userInfo){
        Integer id = JwtUtils.parseJWTAndGenerateId(jwt);
        userInfo.setId(id);
        userService.modifyUserInfo(userInfo);
        return Result.success();
    }

    @RequestMapping("/user/PwdManage")
    public Result modifyPassword(@RequestHeader("Authorization")String jwt,@RequestBody CollectPassword pwds){
        Integer id = JwtUtils.parseJWTAndGenerateId(jwt);
        User user=userService.getByIdAndPassword(id,pwds.getOldPassword());
        if(user!=null){
            userService.modifyPassword(id,pwds.getNewPassword());
            return Result.success();
        }else
            return Result.error("密码错误");
    }

    @RequestMapping("/Address")
    public Result addAddress(@RequestHeader("Authorization")String jwt,@RequestBody Address address){
        Integer id = JwtUtils.parseJWTAndGenerateId(jwt);
        address.setId(id);
        userService.addAddressList(address);
        return Result.success(address);
    }

    @RequestMapping("/modifyUserAddress")
    public Result modifyUserAddress(@RequestHeader("Authorization")String jwt,@RequestBody Address modifyAdd){
        Integer id = JwtUtils.parseJWTAndGenerateId(jwt);
        modifyAdd.setId(id);
        userService.modifyUserAddress(modifyAdd);
        return Result.success();
    }
}
