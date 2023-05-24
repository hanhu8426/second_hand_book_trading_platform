package com.book.book1.service.impl;

import com.book.book1.mapper.BookMapper;
import com.book.book1.pojo.PageBean;
import com.book.book1.pojo.book.Book;
import com.book.book1.service.BookService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<Book> list() {
        List<Book> bookList=bookMapper.list();
        return bookList;
    }

    @Override
    public void delete(Integer id) {
        bookMapper.deleteById(id);
    }

    @Override
    public void add(Book book) {
        bookMapper.insert(book);
    }

/*    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1. 获取总记录数
        Long count = bookMapper.count();

        //2. 获取分页查询结果列表
        Integer start=(page-1)*pageSize;
        List<Book> bookList = bookMapper.page(start, pageSize);

        //3. 封装到PageBean对象
        PageBean pageBean=new PageBean(count,bookList);
        return pageBean;
    }*/

    @Override
    public PageBean page(Integer page, Integer pageSize,short type,String name,String author) {
        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<Book> bookList=bookMapper.pageList(type,name,author);
        Page<Book> p=(Page<Book>) bookList;

        //3. 封装PageBean对象
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        bookMapper.deleteBatch(ids);
    }
}
