package com.secondhandbookstore.mapper;

import com.secondhandbookstore.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from order")
    List<Order> list();

    @Delete("delete from order where orderId=#{orderId}")
    void delete(Integer orderId);

    @Insert("insert into order (buyerId, sellerId, deliveryAddress, beginTime) values " +
            "(#{buyerId},#{sellerId},#{deliveryAddress},#{beginTime})")
    void insert(Order order);

    void updateOrderEnd(Order order);
    void updateOrder(Order order);

    List<Order> pageList(String deliveryAddress, LocalDateTime beginTime, LocalDateTime endTime);


}
