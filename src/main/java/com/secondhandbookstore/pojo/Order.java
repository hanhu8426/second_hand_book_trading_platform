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
    private String deliveryAddress;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
}
