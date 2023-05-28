package com.secondhandbookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String name;
    private String phone;
    private String address;
    private Integer id;
    private Integer area;
    private Integer addId;
}
