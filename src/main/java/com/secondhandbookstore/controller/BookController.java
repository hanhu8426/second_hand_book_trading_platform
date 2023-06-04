package com.secondhandbookstore.controller;

import com.secondhandbookstore.mapper.UserMapper;
import com.secondhandbookstore.pojo.Address;
import com.secondhandbookstore.pojo.PageBean;
import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.pojo.Book;
import com.secondhandbookstore.service.BookService;
import com.secondhandbookstore.service.UserService;
import com.secondhandbookstore.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//一个完整的请求路径，应该是类上的@RequestMapping的value属性+方法上的@RequestMapping的value属性
@Slf4j
@RestController
//@RequestMapping("/sort")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    /**
     * 测试
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "hello";
    }

    //首页书籍信息管理（与个人无关）
    /**
     * 查询书籍数据
     * @return
     */
    @RequestMapping("/test")
    public Result list(){
        log.info("查询所有书籍数据");
        List<Book> bookList=bookService.list();
        return Result.success(bookList);
    }

    /**
     * 删除书籍
     * @return
     */
    @RequestMapping("/deleteBookById/{bookId}")
    public Result delete(@PathVariable Integer bookId){
        log.info("根据id删除书籍：{}",bookId);
        //调用service删除书籍
        bookService.delete(bookId);
        return Result.success();
    }


    /**
     * 书籍分页筛选查询
     * @param page
     * @param pageSize
     * @param type
     * @param name
     * @param author
     * @return
     */
    @RequestMapping("/getBookListByName")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       Short type,
                       String name,
                       String author
                       ){
        log.info("分页查询，参数：{},{},{},{},{},{},{}",page,pageSize,type,name,author);
        //调用service分页查询
        PageBean pageBean=bookService.page(page,pageSize,type,name,author);
        return Result.success(pageBean);
    }


    /**
     * 根据id查询书籍,进入详情页面
     * @param bookId
     * @return
     */
    @RequestMapping("/getBook/{bookId}")
    public Result getById(@PathVariable Integer bookId){
        log.info("根据ID查询书籍，id:{}",bookId);
        Book book=bookService.getById(bookId);
        return Result.success(book);
    }


    /**
     * 根据不同类别展示书籍
     * @param page
     * @param pageSize
     * @param type
     * @return
     */
    @RequestMapping("/getBookListBySort")
    public Result getPagesByType(@RequestParam(defaultValue = "1") Integer page,
                                 //@RequestParam Integer page,
                                 //@RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 Short type){
        System.out.println("testing");
        System.out.println(type);
        log.info("分页查询，参数：{},{},{}",page, pageSize, type);
        PageBean pageBean=bookService.getPagesByType(page,pageSize,type);
        return Result.success(pageBean);
    }

/*    @GetMapping("/getBookListBySort")
    public Result getPagesByType(@RequestParam(defaultValue = "1") Integer page,
                                 //@RequestParam Integer page,
                                 //@RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 Short type){
        System.out.println("testing");
        System.out.println(type);
        log.info("分页查询，参数：{},{},{}",page, pageSize, type);
        List<Book> bookList=bookService.getBooksByType(type);
        //PageBean pageBean=bookService.getPagesByType(page,pageSize,type);
        return Result.success(bookList);
    }*/

    /**
     * 推荐书籍展示
     * @param page
     * @param pageSize
     * @param recommend
     * @return
     */
    @RequestMapping("/getRecBookList")
    public Result getPagesByRecommend(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      Boolean recommend){
        log.info("分页查询，参数：{},{},{}",1,8,recommend);
        PageBean pageBean=bookService.getPagesByRecommend(1,8,true);
        return Result.success(pageBean);
    }




    //个人账号下的书籍信息管理

    /**
     * 根据用户账号获得书籍
     * @param
     * @return
     */
    @RequestMapping("/getBookList")
    public Result getSellerBookList(@RequestHeader("Authorization")String jwt){
        Integer sellerId = JwtUtils.parseJWTAndGenerateId(jwt);
        log.info("根据用户账号获得书籍：sellerId:{}",sellerId);
        List<Book> bookList=bookService.listSellerBook(sellerId);
        return Result.success(bookList);
    }

    @RequestMapping("/delBook")
    public Result delSellerBook(Integer bookId){
        log.info("在用户界面里删除单本书籍：bookId:{},{}",bookId);
        bookService.deleteSellerBook(bookId);
        return Result.success();
    }

    /**
     * 批量删除书籍
     * @param bookIds
     * @return
     */
    @RequestMapping("/deleteBooksByIds/{bookIds}")
    public Result delete(@PathVariable List<Integer> bookIds){
        log.info("批量删除操作，ids: {}",bookIds);
        bookService.deleteBatch(bookIds);
        return Result.success();
    }

    @RequestMapping("/addBook")
    public Result add(@RequestHeader("Authorization")String jwt,@RequestBody Book book){
        log.info("新增书籍：{}",book);
        //调用service新增书籍
        Integer sellerId = JwtUtils.parseJWTAndGenerateId(jwt);
        book.setSellerId(sellerId);
        bookService.add(book);
        return Result.success();
    }

    @RequestMapping("/modifyBook")
    public Result update(@RequestBody Book book){
        log.info("更新书籍信息：{}",book);
        bookService.update(book);
        return Result.success();
    }
    @RequestMapping("/buyPage")
    public Result buyPage(@RequestHeader("Authorization")String jwt){
        Integer buyerId = JwtUtils.parseJWTAndGenerateId(jwt);
        List<Address> addressList=userService.getBuyerAddress(buyerId);
        log.info("获取买家地址列表");
        return Result.success(addressList);
    }


}
