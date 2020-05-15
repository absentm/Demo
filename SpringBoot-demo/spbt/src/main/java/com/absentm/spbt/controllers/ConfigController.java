package com.absentm.spbt.controllers;

import com.absentm.spbt.entity.MultConfigBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/config")
@RestController
public class ConfigController {
    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Value("${tmpconfig.config1}")
    private String config1;

    @Resource
    private MultConfigBean multConfigBean;

    @RequestMapping("/test")
    public String testConfig() {
        logger.debug("config1 is: {}", config1);
        logger.debug("configA is: {}", multConfigBean.getConfigA());
        logger.debug("configB is: {}", multConfigBean.getConfigB());
        logger.debug("configC is: {}", multConfigBean.getConfigC());
        return "Success!";
    }

}
