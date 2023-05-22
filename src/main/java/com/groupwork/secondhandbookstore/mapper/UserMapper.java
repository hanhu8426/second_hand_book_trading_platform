package com.groupwork.secondhandbookstore.mapper;

import com.groupwork.secondhandbookstore.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
//    @Results({
//            @Result(column = "username",property = "account.username"),
//            @Result(column = "password",property = "account.password")
//    })
    @Select("select * from user where account = #{account} and password = #{password}")
    User getByAccountAndPassword(User user);

    @Insert("insert into user(account, password) values (#{account},#{password})")
    void register(User user);

    @Select("select * from user where account = #{account}")
    User getByAccount(String account);
    @Select("select * from user where id = #{id}")
    User getById(String id);
}
