package com.groupwork.secondhandbookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String image;//头像之类的？传入url
    //余下的属性还没有想好
}
