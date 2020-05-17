package com.absentm.spbt.controllers;

import com.absentm.spbt.entity.JsonResult;
import com.absentm.spbt.expection.SelfErrorException;
import com.absentm.spbt.expection.SelfErrorMsgEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/nullpoint")
    public JsonResult testNullPointException() {
        String str = null;
        str.length();
        return new JsonResult();
    }

    @GetMapping("/self")
    public JsonResult testSelfException() {
        try {
            int num = 1 / 0;
        } catch (Exception e) {
            throw new SelfErrorException(SelfErrorMsgEnum.UNEXPECTED_EXCEPTION);
        }

        return new JsonResult();
    }
}
