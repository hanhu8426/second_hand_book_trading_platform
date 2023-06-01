package com.secondhandbookstore.mapper;

import com.secondhandbookstore.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from order1")
    List<Order> list();

    @Delete("delete from order1 where orderId=#{orderId}")
    void delete(Integer orderId);

    @Insert("insert into order1 (buyerId, sellerId,bookId, deliveryAddress, beginTime) values " +
            "(#{buyerId},#{sellerId},#{bookId},#{deliveryAddress},#{beginTime})")
    void insert(Order order);

    void updateOrderEnd(Order order);
    void updateOrder(Order order);

    List<Order> pageList(String deliveryAddress, LocalDateTime beginTime, LocalDateTime endTime);


}
