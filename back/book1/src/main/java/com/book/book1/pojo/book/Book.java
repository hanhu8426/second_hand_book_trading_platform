package com.book.book1.pojo.book;

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
    private short type;
    private String description;
    private boolean status;
    private String image;
    private short campus;
    private float price;
    private boolean newProduct;
    private boolean recommend;
    //private String buyerID;
    private String sellerID;
    //private int bookSort[];
}
