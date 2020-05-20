package com.absentm.spbt.controllers;

import com.absentm.spbt.entity.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")
public class InterceptorController {

    @GetMapping("/test")
    public JsonResult testInterceptor() {
        return new JsonResult("hello, I am in interceptor");
    }
}
