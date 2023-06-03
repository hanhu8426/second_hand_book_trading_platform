package com.secondhandbookstore.mapper;

import com.secondhandbookstore.pojo.Address;
import com.secondhandbookstore.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    User getById(Integer id);

    @Update("update user set balance = balance+#{balance_m} where id = #{id}")
    void modifyUserBalance(Integer id,float balance_m);

    @Select("select balance from user where id = #{id}")
    float queryCurrentBalance(Integer id);

    void modifyUserInfo(User userInfo);

    @Select("select * from user where id = #{id} and password = #{oldPassword}")
    User getByIdAndPassword(Integer id,String oldPassword);

    @Update("update user set password = #{newPassword} where id = #{id}")
    void modifyPassword(Integer id,String newPassword);

//    @Insert("insert into address (name,phone,address,id,area) " +
//            "values (#{name},#{phone},#{address},#{id},#{area})")
    void addAddressList(Address address);

//    @Select("select last_insert_id()")
//    Integer getAddId();
    @Update("update address set name = #{name},phone = #{phone}," +
            "address = #{address},area = #{area} where addId = #{addId}")
    void modifyUserAddress(Address modifyAddress);

    @Select("select balance from user where id=#{id}")
    Float checkUserBalance(Integer id);

    @Select("select name from address where addId=#{addId}")
    String checkName(Integer addId);

    @Select("select phone from address where addId=#{addId}")
    String checkPhone(Integer addId);

    @Select("select address from address where addId=#{addId}")
    String checkAddress(Integer addId);

    @Select("select * from address where id=#{id}")
    List<Address> getBuyerAddress(Integer id);
}
