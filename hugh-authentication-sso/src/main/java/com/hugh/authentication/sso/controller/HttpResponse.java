package com.hugh.authentication.sso.controller;

import lombok.Data;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/18 14:54
 * @Description: http接口通用
 */
@Data
public class HttpResponse {

    /**
     * code
     */
    private Integer code = 200;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 请求是否成功
     */
    private Boolean success;

    /**
     * 数据
     */
    private Object data;


    public HttpResponse(Integer code, String msg, Boolean success, Object data) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }
}
