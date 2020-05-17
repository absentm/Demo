package com.absentm.spbt.expection;

public enum SelfErrorMsgEnum {
    PARAM_EXCEPTION("10001", "Parameter Exception!"),
    SERVICE_TIME_OUT("10002", "Service timeout!"),
    UNEXPECTED_EXCEPTION("500", "System Exception!");

    private String code;
    private String msg;

    private SelfErrorMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
