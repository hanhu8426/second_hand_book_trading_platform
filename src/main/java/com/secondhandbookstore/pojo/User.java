package com.secondhandbookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String account;
    private String password;
    private Boolean manage;
    private String img;
    private String gender;
    private Float balance;
    private String area;
    private String phone;
    private String introduction;
    public User(String account,String password){
        this.account=account;
        this.password=password;
    }
}
