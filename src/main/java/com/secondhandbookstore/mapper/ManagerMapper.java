package com.secondhandbookstore.mapper;

import com.secondhandbookstore.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ManagerMapper {

    @Delete("delete from user where id = #{id}")
    void deleteUserById(Integer id);

    @Delete("delete from bookstore.book where id = #{id}")
    void deleteBookById(Integer id);

    List<User> userPageList();
}
