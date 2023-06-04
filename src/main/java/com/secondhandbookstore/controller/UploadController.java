package com.secondhandbookstore.controller;


import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.utils.HuaWeiOBSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    private HuaWeiOBSUtils huaWeiOBSUtils;
    @RequestMapping("/Upload")
    public Result upload(MultipartFile file) throws Exception{
        log.info("文件上传，文件名：{}",file.getOriginalFilename());
        String url = huaWeiOBSUtils.upload(file);
        return Result.success(url);
    }
}
