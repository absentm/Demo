package com.demo.dao;

import com.demo.beans.User;

import java.util.List;

public interface IUser {
    //@Select("select * from user where id= #{id}")
    //public User getUserById(int id);
    public List<User> getUserList();

    public void insertUser(User user);

    public void updateUser(User user);

    public void deleteUser(int userId);

    public User getUser(int id);
}
