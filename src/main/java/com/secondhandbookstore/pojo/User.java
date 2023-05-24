package com.secondhandbookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String account;
    private String password;
    private boolean manage;
    private String img;
    Integer gender;
    private Float blance;
    private Integer area;
    private String address;
    private String phone;
    private String introduce;
    public User(String account,String password){
        this.account=account;
        this.password=password;
    }
}
