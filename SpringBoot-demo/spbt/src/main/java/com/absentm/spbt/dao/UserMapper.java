package com.absentm.spbt.dao;

import com.absentm.spbt.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface UserMapper {
    @Select("select * from user where user_id = #{id}")
    @Results({
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPasswd", column = "user_passwd"),
    })
    User getUserByIdDao(Integer id);

    @Select("select * from user where user_id = #{id} and user_name = #{name}")
    User getUserByIdAndNameDao(@Param("id") Integer id, @Param("name") String name);

    @Select("select * from user")
    ArrayList<User> getAllUserListDao();

    // xml way
    User getUserByName(String userName);

    int addNewUser(User user);

    int updateUserById(User user);

    int deleteUserById(Integer id);
}
