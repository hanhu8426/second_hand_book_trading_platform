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
    private Integer bookId;
    private String name;
    private String author;
    private String isbn;
    private Short type;
    private String description;
    private Boolean status;
    private String image;
    private Short campus;
    private Float price;
    private Integer recommend;
    //private String buyerID;
    private Integer sellerId;
    //private int bookSort[];
}
