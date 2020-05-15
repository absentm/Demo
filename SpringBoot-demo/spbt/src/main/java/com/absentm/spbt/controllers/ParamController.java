package com.absentm.spbt.controllers;

import com.absentm.spbt.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/param", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
public class ParamController {
    private static final Logger logger = LoggerFactory.getLogger(ParamController.class);

    @GetMapping("/test/{name}")
    public String testPathVariable1(@PathVariable String name) {
//        http://localhost:8081/config/test/ddmmm
        logger.debug("test path variable is: {}", name);
        return "Success!";
    }

    @GetMapping("/test/{id}/{username}")
    public String testPathVariable2(@PathVariable Long id, @PathVariable(value = "username") String name) {
//      http://localhost:8081/config/test/122/ddmmm
        logger.debug("test path variable is: id = {}, name = {}", id, name);
        return "Success!";
    }

    @GetMapping("/test1")
    public String testRequestParam(@RequestParam String user) {
//       http://localhost:8081/config/test1?user=ddmm
        logger.debug("test request variable is: name = {}", user);
        return "Success!";
    }

    @GetMapping("/test2")
    public String testRequestParam2(@RequestParam(value = "user", required = false) String username) {
//       http://localhost:8081/config/test1?user=ddmm
        logger.debug("test request variable is: name = {}", username);
        return "Success!";
    }

    @PostMapping("/user")
    public String testRequestBody(@RequestBody User user) {
        logger.debug("test request variable is: name = {}", user.getUserName());
        logger.debug("test request variable is: passwd = {}", user.getUserPasswd());
        return "success";
    }
}
