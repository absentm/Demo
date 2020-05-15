package com.absentm.spbt.controllers;

import com.absentm.spbt.entity.JsonResult;
import com.absentm.spbt.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/param", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
@Api(value = "参数使用在线接口文档")
public class ParamController {
    private static final Logger logger = LoggerFactory.getLogger(ParamController.class);

    @GetMapping("/test/{name}")
    @ApiOperation(value = "根据用户名称获取用户信息")
    public JsonResult testPathVariable1(@PathVariable @ApiParam(value = "用户名") String name) {
//        http://localhost:8081/config/test/ddmmm
        logger.debug("test path variable is: {}", name);
        return new JsonResult<>();
    }

    @GetMapping("/test/{id}/{username}")
    @ApiOperation(value = "根据用户名称和用户 ID 获取用户信息")
    public JsonResult testPathVariable2(@PathVariable @ApiParam(value = "用户唯一标识符") Long id, @PathVariable(value = "username") @ApiParam(value = "用户名") String name) {
//      http://localhost:8081/config/test/122/ddmmm
        logger.debug("test path variable is: id = {}, name = {}", id, name);
        return new JsonResult<>();
    }

    @GetMapping("/test1")
    public JsonResult testRequestParam(@RequestParam String user) {
//       http://localhost:8081/config/test1?user=ddmm
        logger.debug("test request variable is: name = {}", user);
        return new JsonResult<>();
    }

    @GetMapping("/test2")
    public JsonResult testRequestParam2(@RequestParam(value = "user", required = false) String username) {
//       http://localhost:8081/config/test1?user=ddmm
        logger.debug("test request variable is: name = {}", username);
        return new JsonResult<>();
    }

    @PostMapping("/user")
    public JsonResult testRequestBody(@RequestBody User user) {
        logger.debug("test request variable is: name = {}", user.getUserName());
        logger.debug("test request variable is: passwd = {}", user.getUserPasswd());
        return new JsonResult<>();
    }
}
