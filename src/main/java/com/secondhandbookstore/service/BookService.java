package com.secondhandbookstore.service;

import com.secondhandbookstore.pojo.PageBean;
import com.secondhandbookstore.pojo.Book;
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
    PageBean page(Integer page, Integer pageSize,Short type,String name,String author);

    /**
     * 批量删除操作
     * @param ids
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    Book getById(Integer id);

    void update(Book book);

    PageBean getPagesByType(Integer page, Integer pageSize, Short type);

    PageBean getPagesByRecommend(Integer page, Integer pageSize, Boolean recommend);
}
