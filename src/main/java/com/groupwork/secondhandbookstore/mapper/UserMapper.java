package com.groupwork.secondhandbookstore.mapper;

import com.groupwork.secondhandbookstore.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
//    @Results({
//            @Result(column = "username",property = "account.username"),
//            @Result(column = "password",property = "account.password")
//    })
@Select("select * from User where username = #{username} and password = #{password}")
User getByUserNameAndPassword(User user);

    @Insert("insert into user(username, password) values (#{username},#{password})")
    void register(User user);

    @Select("select * from user where username = #{username}")
    User getUserByUserName(String username);
}
