package com.secondhandbookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectUserInfo {
    //private String img;
    private ArrayList<String> gender;
    private ArrayList<String> area;
    private String introduce;
}
