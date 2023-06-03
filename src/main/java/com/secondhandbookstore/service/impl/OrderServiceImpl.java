package com.secondhandbookstore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.secondhandbookstore.mapper.BookMapper;
import com.secondhandbookstore.mapper.OrderMapper;
import com.secondhandbookstore.pojo.Order;
import com.secondhandbookstore.pojo.PageBean;
import com.secondhandbookstore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<Order> list() {
        List<Order> orderList=orderMapper.list();
        return orderList;
    }

    @Override
    public void delete(Integer orderId) {
        orderMapper.delete(orderId);
    }

    @Override
    public void add(Order order) {
        order.setBeginTime(LocalDateTime.now());
        orderMapper.insert(order);
        bookMapper.modifyBookStatus(order.getBookId());
    }

    @Override
    public void updateEnd(Order order) {
        order.setEndTime(LocalDateTime.now());
        order.setStatus(2);
        orderMapper.updateOrderEnd(order);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateOrder(order);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String deliveryAddress, LocalDateTime beginTime, LocalDateTime endTime) {
        PageHelper.startPage(page,pageSize);
        List<Order> orderList=orderMapper.pageList(deliveryAddress,beginTime,endTime);
        Page<Order> p=(Page<Order>) orderList;
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public List<Order> listDifStatus(Integer buyerId, Integer status) {
        return orderMapper.listDifStatus(buyerId,status);
    }

}
