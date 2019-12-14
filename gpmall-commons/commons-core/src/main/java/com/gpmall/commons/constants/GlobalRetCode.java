package com.gpmall.commons.constants;

/**
 * @author liqibo
 * @description TODO
 * @date 2019/11/28 16:05
 **/
public enum  GlobalRetCode {
    SUCCESS("000000", "ok"),
    SYSTEM_ERROR("000500", "系统错误");

    private String code;

    private String message;

    GlobalRetCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
