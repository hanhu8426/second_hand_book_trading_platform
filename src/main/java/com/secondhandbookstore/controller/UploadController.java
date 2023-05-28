package com.secondhandbookstore.controller;


import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.utils.HuaWeiOBSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private HuaWeiOBSUtils huaWeiOBSUtils;
    @RequestMapping("/upload")
    public Result upload(MultipartFile image) throws Exception{
        log.info("文件上传，文件名：{}",image.getOriginalFilename());
        String url = huaWeiOBSUtils.upload(image);
        return Result.success(url);
    }
}
