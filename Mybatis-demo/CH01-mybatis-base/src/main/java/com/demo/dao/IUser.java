package com.demo.dao;

import com.demo.beans.User;
import org.apache.ibatis.annotations.Select;

public interface IUser {
    @Select("select * from user where id= #{id}")
    User getUserById(int id);
}
