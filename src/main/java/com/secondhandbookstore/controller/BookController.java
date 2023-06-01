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
//@RequestMapping("/sort")
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



    //首页书籍信息管理（与个人无关）
    /**
     * 查询书籍数据
     * @return
     */
    @GetMapping("/test")
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
    public Result delete(@PathVariable Integer bookId){
        log.info("根据id删除书籍：{}",bookId);
        //调用service删除书籍
        bookService.delete(bookId);
        return Result.success();
    }

    /**
     * 新增书籍
     * @return
     */



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
     * @param bookIds
     * @return
     */
    @DeleteMapping("/deleteBooksByIds/{ids}")
    public Result delete(@PathVariable List<Integer> bookIds){
        log.info("批量删除操作，ids: {}",bookIds);
        bookService.deleteBatch(bookIds);
        return Result.success();
    }

    /**
     * 根据id查询书籍
     * @param bookId
     * @return
     */
    @GetMapping("/getBookById/{id}")
    public Result getById(@PathVariable Integer bookId){
        log.info("根据ID查询书籍，id:{}",bookId);
        Book book=bookService.getById(bookId);
        return Result.success(book);
    }

    /**
     * 卖家修改书籍的信息
     * @param book
     * @return
     */

    /**
     * 根据不同类别展示书籍
     * @param page
     * @param pageSize
     * @param type
     * @return
     */
    @GetMapping("/getBookListBySort")
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
    @GetMapping("/recommend")
    public Result getPagesByRecommend(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      Boolean recommend){
        log.info("分页查询，参数：{},{},{}",1,6,recommend);
        PageBean pageBean=bookService.getPagesByRecommend(1,6,recommend);
        return Result.success(pageBean);
    }




    //个人账号下的书籍信息管理

    /**
     * 根据用户账号获得书籍
     * @param sellerId
     * @return
     */
    @GetMapping("/getBookList")
    public Result getSellerBookList(Integer sellerId){
        log.info("根据用户账号获得书籍：sellerId:{}",sellerId);
        List<Book> bookList=bookService.listSellerBook(sellerId);
        return Result.success(bookList);
    }

    @DeleteMapping("/delBook")
    public Result delSellerBook(Integer sellerId,Integer bookId){
        log.info("在用户界面里删除单本书籍：sellerId,bookId:{},{}",sellerId,bookId);
        bookService.deleteSellerBook(sellerId, bookId);
        return Result.success();
    }

    @PostMapping("/addBook")
    public Result add(@RequestBody Book book){
        log.info("新增书籍：{}",book);
        //调用service新增书籍
        bookService.add(book);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Book book){
        log.info("更新书籍信息：{}",book);
        bookService.update(book);
        return Result.success();
    }


}
