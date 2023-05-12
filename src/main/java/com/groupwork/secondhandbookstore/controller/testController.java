package com.groupwork.secondhandbookstore.controller;

import com.groupwork.secondhandbookstore.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class testController {
    @RequestMapping("/testing")
    public Result testing1(){
        return Result.success("this is a testing");
    }
}
