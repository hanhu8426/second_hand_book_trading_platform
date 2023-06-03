package com.secondhandbookstore.mapper;

import com.secondhandbookstore.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    /**
     * 查询全部书籍
     * @return
     */
    @Select("select * from book where status=1")
    List<Book> list();


    /**
     * 根据ID删除书籍
     * @param
     */
    @Delete("delete from book where bookId=#{bookId}")
    void deleteById(Integer bookId);

    /**
     * 新增书籍
     * @param book
     */
    @Insert("insert into book(name, author, isbn, type, description, status, image, campus, price, recommend, sellerId) " +
            "values (#{name},#{author},#{isbn},#{type},#{description},#{status},#{image},#{campus},#{price},#{recommend},#{sellerId})")
    void insert(Book book);


    /**
     * 书籍信息查询分页展示 插件
     * @return
     */
    //@Select("select * from book.book")
    public List<Book> pageList(Short type,String name,String author);

    /*    @Select("select *from book where type=#{type}")
    List<Book> getBooksByType(Short type);*/

    /**
     * 批量删除操作
     * @param bookIds
     */
    void deleteBatch(List<Integer> bookIds);

    @Select("select * from book where bookId=#{bookId}")
    Book getById(Integer bookId);

    /**
     * 更新书籍
     * @param book
     */
    void update(Book book);

    List<Book> pageListByType(Short type);

    List<Book> pageListByRecommend(Boolean recommend);


    //用户和书籍结合，书摊
    @Select("select * from book where sellerId=#{sellerId}")
    List<Book> listSellerBook(Integer sellerId);

    @Delete("delete from book where bookId=#{bookId}")
    void deleteSellerBook(Integer bookId);

    @Update("update book set status=0 where bookId=#{bookId}")
    void modifyBookStatus(Integer bookId);

    @Select("select price from book where bookid=#{bookId}")
    Float checkPrice(Integer bookId);

    @Select("select sellerId from book where bookId=#{bookId}")
    Integer checkSeller(Integer bookId);
}
