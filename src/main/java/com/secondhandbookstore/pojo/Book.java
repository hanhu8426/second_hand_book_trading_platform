package com.secondhandbookstore.pojo;

/**
 * @author: 沈三
 * @data: 2023.5
 * @description: 图书实体类
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer id;
    private String name;
    private String author;
    private String isbn;
    private Short type;
    private String description;
    private Boolean status;
    private String image;
    private Short campus;
    private Float price;
    private Boolean newProduct;
    private Boolean recommend;
    //private String buyerID;
    private String sellerID;
    //private int bookSort[];
}
