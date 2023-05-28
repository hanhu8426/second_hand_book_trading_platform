package com.secondhandbookstore.controller;

import com.secondhandbookstore.pojo.PageBean;
import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.pojo.Book;
import com.secondhandbookstore.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//一个完整的请求路径，应该是类上的@RequestMapping的value属性+方法上的@RequestMapping的value属性
@Slf4j
@RestController
@RequestMapping("book")
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
    @GetMapping
    public Result list(){
        log.info("查询所有书籍数据");
        List<Book> bookList=bookService.list();
        return Result.success(bookList);
    }

    /**
     * 删除书籍
     * @return
     */
    @DeleteMapping("/deleteBookById/{id}")
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
        log.info("新增书籍：{}",book);
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
        log.info("分页查询，参数：{},{},{},{},{},{},{}",page,pageSize,type,name,author);
        //调用service分页查询
        PageBean pageBean=bookService.page(page,pageSize,type,name,author);
        return Result.success(pageBean);
    }

    /**
     * 批量删除书籍
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBooksByIds/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作，ids: {}",ids);
        bookService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据id查询书籍
     * @param id
     * @return
     */
    @GetMapping("/getBookById/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询书籍，id:{}",id);
        Book book=bookService.getById(id);
        return Result.success(book);
    }

    /**
     * 卖家修改书籍的信息
     * @param book
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Book book){
        log.info("更新书籍信息：{}",book);
        bookService.update(book);
        return Result.success();
    }

    /**
     * 根据不同类别展示书籍
     * @param page
     * @param pageSize
     * @param type
     * @return
     */
    @GetMapping("/getBookListBySort")
    public Result getPagesByType(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 Short type){
        System.out.println("testing");
        log.info("分页查询，参数：{},{},{}",page, pageSize, type);
        PageBean pageBean=bookService.getPagesByType(page,pageSize,type);
        return Result.success(pageBean);
    }

    @GetMapping("recommend")
    public Result getPagesByRecommend(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      Boolean recommend){
        log.info("分页查询，参数：{},{},{}",page,pageSize,recommend);
        PageBean pageBean=bookService.getPagesByRecommend(page,pageSize,recommend);
        return Result.success(pageBean);
    }

}
