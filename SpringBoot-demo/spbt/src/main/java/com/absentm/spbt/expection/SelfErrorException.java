package com.absentm.spbt.expection;

public class SelfErrorException extends RuntimeException {
    private String errorCode;
    private String errorMsg;

    public SelfErrorException(SelfErrorMsgEnum selfErrorMsgEnum) {
        this.errorCode = selfErrorMsgEnum.getCode();
        this.errorMsg = selfErrorMsgEnum.getMsg();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
