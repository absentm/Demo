package com.absentm.spbt.controllers;

import com.absentm.spbt.entity.JsonResult;
import com.absentm.spbt.entity.User;
import com.absentm.spbt.service.UserService;
import com.absentm.spbt.utils.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping("/mybits")
public class MybitsController {
    private static final Logger logger = LoggerFactory.getLogger(MybitsController.class);

    @Resource
    private UserService userService;

    @RequestMapping("/user/list")
    public JsonResult getUserList() {
        ArrayList<User> userArrayList = userService.getAllUserList();
        logger.info("in getUserList");
        return new JsonResult(userArrayList);
    }

    @RequestMapping("/user/{name}")
    public JsonResult getUserInfoByName(@PathVariable String name) {
        User user = userService.getUserByName(name);
        return new JsonResult(user);
    }

    @PostMapping("/user/new")
    public JsonResult addNewUserInfo() {
        String userName = SystemUtil.getRandomString(10);
        String userPasswd = SystemUtil.getRandomString(16);
        User newUser = new User(userName, userPasswd);
        int user = userService.addUser(newUser);
        return new JsonResult();
    }

    @PutMapping("/user/edit/{id}/{name}")
    public JsonResult updateUserInfo(@PathVariable Integer id, @PathVariable String name) {
        User user = new User(id, name);
        int ret = userService.updateUserById(user);
        return new JsonResult();
    }

    @DeleteMapping("/user/remove/{id}")
    public JsonResult deleteUser(@PathVariable Integer id) {
        int user = userService.deleteUserById(id);
        return new JsonResult();
    }

}
