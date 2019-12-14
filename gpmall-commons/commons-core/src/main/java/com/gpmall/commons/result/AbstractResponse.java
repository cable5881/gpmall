package com.gpmall.commons.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */

@Data
public abstract class AbstractResponse implements Serializable {

    private static final long serialVersionUID = 7505997295595095971L;

    private String code;

    private String msg;

    public AbstractResponse() {
    }

    public AbstractResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
