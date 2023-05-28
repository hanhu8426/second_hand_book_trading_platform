package com.secondhandbookstore.controller;

import com.secondhandbookstore.pojo.Order;
import com.secondhandbookstore.pojo.PageBean;
import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("orders")
@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 查询所有订单数据
     * @return
     */
    @GetMapping
    public Result list(){
        log.info("查询订单所有数据");
        List<Order> orderList=orderService.list();
        return Result.success(orderList);
    }

    /**
     * 根据id删除订单数据
     * @param orderId
     * @return
     */
    @DeleteMapping("/{orderId}")
    public Result delete(@PathVariable Integer orderId){
        log.info("根据orderId删除订单");
        orderService.delete(orderId);
        return Result.success();
    }

    /**
     * 增加新的订单数据
     * @param order
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Order order){
        log.info("新增订单：{}",order);
        orderService.add(order);
        return Result.success();
    }

    /**
     * 订单结束，修改订单状态
     * @param order
     * @return
     */
    @PostMapping("/end")
    public Result updateEnd(@RequestBody Order order){
        log.info("订单已结束,{}",order);
        orderService.updateEnd(order);
        return Result.success();
    }

    /**
     * 修改订单数据，收货地址
     * @param order
     * @return
     */
    @PostMapping("/update")
    public Result modify(@RequestBody Order order){
        log.info("修改订单数据{}",order);
        orderService.update(order);
        return Result.success();
    }

    /**
     * 条件分页查询
     * @param page
     * @param pageSize
     * @param deliveryAddress
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/pages")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String deliveryAddress,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDateTime beginTime,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDateTime endTime){
        log.info("分页查询，参数{},{},{},{},{}",page,pageSize,deliveryAddress,beginTime,endTime);
        PageBean pageBean=orderService.page(page,pageSize,deliveryAddress,beginTime,endTime);
        return Result.success(pageBean);
    }

}

