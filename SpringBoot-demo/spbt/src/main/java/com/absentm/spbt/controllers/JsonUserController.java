package com.absentm.spbt.controllers;

import com.absentm.spbt.entity.JsonResult;
import com.absentm.spbt.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/jsonret")
public class JsonUserController {

    @RequestMapping("/user")
    public JsonResult<User> getUser() {
        User user = new User(0, "Join", "123456");
        return new JsonResult<>(user);
    }

    @RequestMapping("/list")
    public JsonResult<List> getUserList() {
        ArrayList<User> userArrayList = new ArrayList<>();
        User user1 = new User(1, "Kitty", "12wse");
        User user2 = new User(2, "Demoin", "12wse");
        userArrayList.add(user1);
        userArrayList.add(user2);
        return new JsonResult<>(userArrayList, "Get user list success.");
    }

    @RequestMapping("/detail")
    public JsonResult<HashMap<String, Object>> getUserInfoDetail() {
        HashMap<String, Object> hashMap = new HashMap<>();
        User user0 = new User(0, "AbsentM", "passw0rd");
        hashMap.put("userInfo", user0);
        hashMap.put("phone", "15802361024");
        hashMap.put("addr", "https://absentm.github.io/");
        hashMap.put("nickname", null);
        return new JsonResult<>(hashMap);
    }
}
