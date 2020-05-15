package com.absentm.spbt.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "multconfig")
public class MultConfigBean {
    private String configA;
    private String configB;
    private String configC;

    public String getConfigA() {
        return configA;
    }

    public void setConfigA(String configA) {
        this.configA = configA;
    }

    public String getConfigB() {
        return configB;
    }

    public void setConfigB(String configB) {
        this.configB = configB;
    }

    public String getConfigC() {
        return configC;
    }

    public void setConfigC(String configC) {
        this.configC = configC;
    }
}
