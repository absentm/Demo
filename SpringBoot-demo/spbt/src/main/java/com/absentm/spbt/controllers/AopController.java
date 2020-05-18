package com.absentm.spbt.controllers;

import com.absentm.spbt.entity.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class AopController {

    @GetMapping("/test/{name}")
    public JsonResult testAop(@PathVariable String name) {

        return new JsonResult("hello Aop test: " + name, name);
    }
}
