package com.absentm.spbt.controllers;

import com.absentm.spbt.entity.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @PostMapping("/lack")
    public JsonResult testLackParam(@RequestParam("name") String name, @RequestParam("passwd") String passwd) {
        logger.info("name: {}", name);
        logger.info("passwd: {}", passwd);

        return new JsonResult();
    }
}
