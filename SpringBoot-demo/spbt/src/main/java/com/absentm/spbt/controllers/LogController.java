package com.absentm.spbt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/log")
public class LogController {
    private final static Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/test")
    public String testLog() {
        logger.debug("debug level log print");
        logger.info("info level log print");
        logger.error("error level log print");
        logger.warn("warn level log print");

        return "See log in log file and console.";
    }
}
