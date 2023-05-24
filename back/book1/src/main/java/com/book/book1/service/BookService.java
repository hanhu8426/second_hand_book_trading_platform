package com.book.book1.service;

import com.book.book1.pojo.PageBean;
import com.book.book1.pojo.book.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> list();
    void delete(Integer id);

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
    PageBean page(Integer page, Integer pageSize,short type,String name,String author);

    /**
     * 批量删除操作
     * @param ids
     */
    void deleteBatch(List<Integer> ids);
}
