package com.absentm.spbt.controllers;

import com.absentm.spbt.entity.JsonResult;
import com.absentm.spbt.entity.User;
import com.absentm.spbt.service.RedisService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {
    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Resource
    private RedisService redisService;

    @GetMapping("/test")
    public JsonResult testRedis() {
        //测试redis的string类型
        redisService.setString("weichat", "程序员私房菜");
        logger.info("我的微信公众号为：{}", redisService.getString("weichat"));

        // 如果是个实体，我们可以使用json工具转成json字符串，
        User user = new User("CSDN", "123456");
        redisService.setString("userInfo", JSON.toJSONString(user));
        logger.info("用户信息：{}", redisService.getString("userInfo"));


        //测试redis的hash类型
        redisService.setHash("user", "name", JSON.toJSONString(user));
        logger.info("用户姓名：{}", redisService.getHash("user", "name"));

        //测试redis的list类型
        redisService.setList("list", "football");
        redisService.setList("list", "basketball");
        List<String> valList = redisService.getList("list", 0, -1);
        for (String value : valList) {
            logger.info("list中有：{}", value);
        }

//        测试设置key失效时间
        redisService.setTimeOut("CSDN", 9);
//        让当前线程休眠10秒
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("CSDN：", redisService.getString("CSDN"));

        return new JsonResult();
    }
}
