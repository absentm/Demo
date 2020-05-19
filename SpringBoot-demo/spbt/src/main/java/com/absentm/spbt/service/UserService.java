package com.absentm.spbt.service;

import com.absentm.spbt.entity.User;

import java.util.ArrayList;

public interface UserService {
    User getUserById(Integer id);

    ArrayList<User> getAllUserList();

    User getUserByName(String name);

    User getUserByIdAndName(Integer id, String name);

    int addUser(User user);

    int updateUserById(User user);

    int deleteUserById(Integer id);
}
