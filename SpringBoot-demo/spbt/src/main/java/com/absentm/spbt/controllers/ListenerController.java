package com.absentm.spbt.controllers;

import com.absentm.spbt.entity.JsonResult;
import com.absentm.spbt.entity.User;
import com.absentm.spbt.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/listener")
public class ListenerController {

    @Resource
    private UserService userService;

    @GetMapping("/user/list")
    public JsonResult getAllListenerUser(HttpServletRequest httpServletRequest) {
        ServletContext servletContext = httpServletRequest.getServletContext();
        ArrayList<User> userArrayList = (ArrayList<User>) servletContext.getAttribute("allUserList");
        return new JsonResult(userArrayList);
    }

    @GetMapping("/user/online/total")
    public JsonResult getTotalOnlineUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Cookie cookie;
        try {
            cookie = new Cookie("JSESSIONID", URLEncoder.encode(httpServletRequest.getSession().getId(), "utf-8"));
            cookie.setPath("/");
            //设置cookie有效期为2天，设置长一点
            cookie.setMaxAge(48 * 60 * 60);
            httpServletResponse.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Integer count = (Integer) httpServletRequest.getSession().getServletContext().getAttribute("userCounter");
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("userCounter", count);
        return new JsonResult(hashMap);
    }

    @GetMapping("/request/name")
    public JsonResult getRequestInfo(HttpServletRequest httpServletRequest) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("requestName", (String) httpServletRequest.getAttribute("requestName"));
        return new JsonResult(hashMap);
    }

    @GetMapping("/publish")
    public JsonResult getEventInfo() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("publishData", userService.getAllUserList());
        return new JsonResult(hashMap);
    }
}
