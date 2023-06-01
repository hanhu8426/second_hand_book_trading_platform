package com.secondhandbookstore.service;

import com.secondhandbookstore.pojo.PageBean;
import com.secondhandbookstore.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> list();
    void delete(Integer bookId);

    /**
     * 新增书籍
     * @param book
     */
    void add(Book book);

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize,Short type,String name,String author);

    //List<Book> getBooksByType(Short type);

    /**
     * 批量删除操作
     * @param bookIds
     */
    void deleteBatch(List<Integer> bookIds);

    /**
     * 根据ID查询书籍
     * @param bookId
     * @return
     */
    Book getById(Integer bookId);

    void update(Book book);

    PageBean getPagesByType(Integer page, Integer pageSize, Short type);

    PageBean getPagesByRecommend(Integer page, Integer pageSize, Boolean recommend);





    //用户和书籍结合
    List<Book> listSellerBook(Integer sellerId);
    void deleteSellerBook(Integer bookId);
    Float checkPrice(Integer bookId);

    Integer checkSeller(Integer bookId);
}
