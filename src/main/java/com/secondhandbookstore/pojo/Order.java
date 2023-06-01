package com.secondhandbookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId;
    private Integer buyerId;
    private Integer sellerId;
    private String name;
    private String phone;
    private String address;
    private Integer bookId;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private Short status;
}
