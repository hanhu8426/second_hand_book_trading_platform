package com.secondhandbookstore.controller;

import com.secondhandbookstore.pojo.PageBean;
import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.pojo.book.Book;
import com.secondhandbookstore.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//一个完整的请求路径，应该是类上的@RequestMapping的value属性+方法上的@RequestMapping的value属性
@Slf4j
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 测试
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "hello";
    }

    /**
     * 查询书籍数据
     * @return
     */
    @GetMapping("/books")
    public Result list(){
        log.info("查询所有部门数据");
        List<Book> bookList=bookService.list();
        return Result.success(bookList);
    }

    /**
     * 删除部门
     * @return
     */
    @DeleteMapping("/deleteBook/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除书籍：{}",id);
        //调用service删除书籍
        bookService.delete(id);
        return Result.success();
    }

    /**
     * 新增书籍
     * @return
     */
    @PostMapping("/addBooks")
    public Result add(@RequestBody Book book){
        log.info("新增部门：{}",book);
        //调用service新增书籍
        bookService.add(book);
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
    @GetMapping("/getPages")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       Short type,
                       String name,
                       String author
    ){
        //默认值设置
        if(type==null) type=1;
//        if(pageSize==null) pageSize=10;
        log.info("分页查询，参数：{},{},{},{},{},{},{}",page,pageSize,type,name,author);
        //调用service分页查询
        PageBean pageBean=bookService.page(page,pageSize,type,name,author);
        return Result.success(pageBean);
    }

    @DeleteMapping("deleteBooks/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作，ids: {}",ids);
        bookService.deleteBatch(ids);
        return Result.success();
    }
}
