package com.groupwork.secondhandbookstore;

import com.groupwork.secondhandbookstore.pojo.User;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SecondHandBookStoreApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Test
//    public void testGenJWT(){
//        Map<String, Object> claims=new HashMap<>();
//        claims.put("id",1);
//        claims.put("name","hwj");
//
//        String jwt = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256, "itheiam")
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis()))
//                .compact();
//
//        System.out.println(jwt);
//    }
//    @Test
//    public void testParseJWT(){
//        Claims claims = Jwts.parser()
//                .setSigningKey("itheima")
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiaHdqIiwiaWQiOjEsImV4cCI6MTY4MzQ0ODU1MH0.z9cNl1Omz2fBAp5lqr0Ju2KneCvSL-CMqVZfqBRgDcQ")
//                .getBody();
//        System.out.println(claims);
//    }
    @Test
    public void upload(){
        // Endpoint以北京四为例，其他地区请按实际情况填写。
        String endPoint = "obs.cn-south-1.myhuaweicloud.com";
        String ak = "M1ATZ7LSKEBWEBVEYL3X";
        String sk = "zBJuwkNFCMTY18DFkHnhRFvbAy3nOk0VkZjbCtI3";
// 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);

// localfile为待上传的本地文件路径，需要指定到具体的文件名
        obsClient.putObject("hwj-first-test", "objectkey", new File("C:\\Users\\黄文敬\\Desktop\\1zl.jpg"));

// localfile2 为待上传的本地文件路径，需要指定到具体的文件名
        PutObjectRequest request = new PutObjectRequest();
        request.setBucketName("hwj-first-test");
        request.setObjectKey("objectkey2");
        request.setFile(new File("C:\\Users\\黄文敬\\Desktop\\1zl.jpg"));
        obsClient.putObject(request);

    }

}
