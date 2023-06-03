package com.secondhandbookstore.service;

import com.secondhandbookstore.pojo.Order;
import com.secondhandbookstore.pojo.PageBean;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<Order> list();

    void delete(Integer orderId);

    void add(Order order);

    void updateEnd(Order order);

    void update(Order order);

    PageBean page(Integer page, Integer pageSize, String deliveryAddress, LocalDateTime beginTime, LocalDateTime endTime);

    List<Order> listDifStatus(Integer buyerId, Integer status);
}
