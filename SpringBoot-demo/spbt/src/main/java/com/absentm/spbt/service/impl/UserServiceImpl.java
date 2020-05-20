package com.absentm.spbt.service.impl;

import com.absentm.spbt.dao.UserMapper;
import com.absentm.spbt.entity.User;
import com.absentm.spbt.event.MyEvent;
import com.absentm.spbt.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserByIdDao(id);
    }

    @Override
    public ArrayList<User> getAllUserList() {
        ArrayList<User> userArrayList = userMapper.getAllUserListDao();
        MyEvent myEvent = new MyEvent(this, userArrayList);
        applicationContext.publishEvent(myEvent);

        return userArrayList;
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public User getUserByIdAndName(Integer id, String name) {
        return userMapper.getUserByIdAndNameDao(id, name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) {
        return userMapper.addNewUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }
}
