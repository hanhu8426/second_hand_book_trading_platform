package com.secondhandbookstore.utils;

import com.obs.services.ObsClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class HuaWeiOBSUtils {
    // Endpoint以北京四为例，其他地区请按实际情况填写。
    private String endPoint = "https://obs.cn-south-1.myhuaweicloud.com";
    private String ak = "M1ATZ7LSKEBWEBVEYL3X";
    private String sk = "zBJuwkNFCMTY18DFkHnhRFvbAy3nOk0VkZjbCtI3";

    private String bucketName="hwj-first-test";


    public String upload(MultipartFile file) throws IOException {
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);

        //获取上传的文件流
        InputStream inputStream = file.getInputStream();

        //利用uuid创建文件名
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));


        //上传文件
        obsClient.putObject(bucketName,fileName,inputStream);

        //返回上传到云上的文件对应的url
        String url = endPoint.split("//")[0] + "//" + bucketName + "." + endPoint.split("//")[1] + "/" + fileName;
        return url;
    }

}
