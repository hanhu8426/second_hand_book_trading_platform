package com.secondhandbookstore.controller;

import com.secondhandbookstore.pojo.Order;
import com.secondhandbookstore.pojo.OrderUtils;
import com.secondhandbookstore.pojo.PageBean;
import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.service.BookService;
import com.secondhandbookstore.service.OrderService;
import com.secondhandbookstore.service.UserService;
import com.secondhandbookstore.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/*@RequestMapping("/orders")*/
@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    /**
     * 查询所有订单数据
     * @return
     */
    @RequestMapping("/test1")
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
    @RequestMapping("/deleteOrder/{orderId}")
    public Result delete(@PathVariable Integer orderId){
        log.info("根据orderId删除订单");
        orderService.delete(orderId);
        return Result.success();
    }

    @RequestMapping("/GetOrderByOrderId")
    public Result getOrderByOrderId(Integer orderId){
        log.info("根据orderId查找订单信息，{}",orderId);
        Order order=orderService.getOrderByOrderId(orderId);
        return Result.success(order);
    }

    /**
     *
     * 增加新的订单数据
     * @param
     * @return
     */
    @RequestMapping("/addOrder")
    public Result add(@RequestHeader("Authorization")String jwt, @RequestBody OrderUtils orderUtils){
        System.out.println("进入方法");
        Integer buyerId = JwtUtils.parseJWTAndGenerateId(jwt);
        Integer bookId=orderUtils.getBookId();
        Float buyerBalance = userService.checkBalance(buyerId);
        Float bookPrice = bookService.checkPrice(bookId);
        if(buyerBalance>=bookPrice){
            Integer sellerId=bookService.checkSeller(bookId);
            Order order=new Order();
            order.setBuyerId(buyerId);
            order.setSellerId(sellerId);
            order.setName(userService.checkName(orderUtils.getAddId()));
            order.setPhone(userService.checkPhone(orderUtils.getAddId()));
            order.setAddress(userService.checkAddress(orderUtils.getAddId()));
            order.setBookId(bookId);
            order.setBeginTime(LocalDateTime.now());
            order.setStatus(1);
            log.info("新增订单：{}",order);
            orderService.add(order);
            userService.modifyUserBalance(sellerId,bookPrice);
            userService.modifyUserBalance(buyerId,-bookPrice);
            return Result.success();
        }else {
            return Result.success2();
        }

    }

    /**
     * 订单结束，修改订单状态
     * @param order
     * @return
     */
    @RequestMapping("/end")
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
    @RequestMapping("/update")
    public Result modify(@RequestBody Order order){
        log.info("修改订单数据{}",order);
        orderService.update(order);
        return Result.success();
    }


    @RequestMapping("/pages")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String address,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDateTime beginTime,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDateTime endTime){
        log.info("分页查询，参数{},{},{},{},{}",page,pageSize,address,beginTime,endTime);
        PageBean pageBean=orderService.page(page,pageSize,address,beginTime,endTime);
        return Result.success(pageBean);
    }

    @RequestMapping("/orderStatus")
    public Result listDifStatus(@RequestHeader("Authorization")String jwt,Integer status){
        Integer buyerId = JwtUtils.parseJWTAndGenerateId(jwt);
        List<Order> orderList=orderService.listDifStatus(buyerId,status);
        return Result.success(orderList);

    }

    @RequestMapping("/listUserOrders")
    public Result listUserOrders(@RequestHeader("Authorization")String jwt){
        Integer buyerId = JwtUtils.parseJWTAndGenerateId(jwt);
        List<Order> orderList=orderService.listUserOrders(buyerId);
        return Result.success(orderList);
    }

}

