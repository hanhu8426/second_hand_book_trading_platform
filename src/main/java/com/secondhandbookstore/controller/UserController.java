package com.secondhandbookstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.secondhandbookstore.pojo.*;
import com.secondhandbookstore.service.UserService;
import com.secondhandbookstore.utils.HuaWeiOBSUtils;
import com.secondhandbookstore.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HuaWeiOBSUtils huaWeiOBSUtils;

    //获取用户信息
    @RequestMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader("Authorization")String jwt){
        Integer id=JwtUtils.parseJWTAndGenerateId(jwt);
        User show=userService.getById(id);
        log.info("查询用户信息:{}",show.getAccount());
        return Result.success(show);
    }

    //修改用户信息一:修改用户余额
    //这里是帮前端擦屁股的写法，前端传进来的参数为：{charge:20.5}(example)
    //后端对前端传进来的参数做了截断，然后转了Float
    @RequestMapping("/user/userInfoOne")
    public Result modifyUserBalance(@RequestHeader("Authorization")String jwt,@RequestBody JSONObject object){
        System.out.println(object.getString("charge"));
        String balance=object.getString("charge").substring(8);
        System.out.println(balance);
        Float currentBalance = userService.modifyUserBalance(JwtUtils.parseJWTAndGenerateId(jwt),
                Float.parseFloat(balance.substring(0,balance.length()-1)));
        return Result.success(currentBalance);
    }



    //修改用户信息二:添加一些用户的基础信息
    @RequestMapping("/user/userInfo")
    public Result modifyUserInfo(@RequestHeader("Authorization")String jwt,@RequestBody User userInfo){
        Integer id = JwtUtils.parseJWTAndGenerateId(jwt);
        userInfo.setId(id);
        log.info("进入上传user信息的方法，准备打印捕捉到的上传信息:{}",userInfo);
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

    @RequestMapping("/addUserAddress")
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

    @RequestMapping("/getUserAddress")
    public Result getUserAddress(@RequestHeader("Authorization")String jwt){
        Integer id = JwtUtils.parseJWTAndGenerateId(jwt);
        List<Address> addressList = userService.getUserAddressById(id);
        return Result.success(addressList);
    }

    @RequestMapping("/delUserAddress")
    public Result delUserAddress(Integer addId){
        userService.delUserAddressById(addId);
        return Result.success();
    }
}
