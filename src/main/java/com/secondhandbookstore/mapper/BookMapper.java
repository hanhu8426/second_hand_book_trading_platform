package com.secondhandbookstore.mapper;

import com.secondhandbookstore.pojo.book.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    /**
     * 查询全部部门
     * @return
     */
    @Select("select * from book")
    List<Book> list();


    /**
     * 根据ID删除部门
     * @param id
     */
    @Delete("delete from book where id=#{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     * @param book
     */
    @Insert("insert into book(name, author, isbn, type, description, status, image, campus, price, newProduct, recommend, sellerID) " +
            "values (#{name},#{author},#{isbn},#{type},#{description},#{status},#{image},#{campus},#{price},#{newProduct},#{recommend},#{sellerID})")
    void insert(Book book);

/*    *//**
     * 查询总记录数
     * @return
     *//*
    @Select("select count(*) from book")
    public Long count();


    *//**
     * 分页查询，获取列表数据
     * @param start
     * @param pageSize
     * @return
     *//*
    @Select("select * from book.book limit #{start},#{pageSize}")
    public List<Book> page(Integer start,Integer pageSize);*/

    /**
     * 书籍信息查询分页展示 插件
     * @return
     */
    //@Select("select * from book.book")
    public List<Book> pageList(Short type,String name,String author);

    /**
     * 批量删除操作
     * @param ids
     */
    void deleteBatch(List<Integer> ids);

    @Select("select * from  book where id=#{id}")
    Book getById(Integer id);

    /**
     * 更新员工
     * @param book
     */
    void update(Book book);
}
