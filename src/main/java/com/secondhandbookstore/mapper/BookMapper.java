package com.secondhandbookstore.mapper;

import com.secondhandbookstore.pojo.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    /**
     * 查询全部书籍
     * @return
     */
    @Select("select * from book")
    List<Book> list();


    /**
     * 根据ID删除书籍
     * @param id
     */
    @Delete("delete from book where id=#{id}")
    void deleteById(Integer id);

    /**
     * 新增书籍
     * @param book
     */
    @Insert("insert into book(name, author, isbn, type, description, status, image, campus, price, newProduct, recommend, sellerID) " +
            "values (#{name},#{author},#{isbn},#{type},#{description},#{status},#{image},#{campus},#{price},#{newProduct},#{recommend},#{sellerID})")
    void insert(Book book);


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

    List<Book> pageListByType(Short type);

    @Select("select * from book where recommend=#{recommend}")
    List<Book> pageListByRecommend(Boolean recommend);
}
