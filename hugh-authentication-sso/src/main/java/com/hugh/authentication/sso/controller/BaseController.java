package com.hugh.authentication.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/18 14:53
 * @Description:
 */
@Controller
public class BaseController {

    private static final Integer SUCCESS_CODE = 200;

    private static final Integer ERROR_CODE = 500;

    public HttpResponse success(String msg) {
        return new HttpResponse(SUCCESS_CODE, msg, Boolean.TRUE, null);
    }

    public HttpResponse success(Integer code,String msg) {
        return new HttpResponse(code, msg, Boolean.TRUE, null);
    }

    public HttpResponse success(String msg,Object data) {
        return new HttpResponse(SUCCESS_CODE, msg, Boolean.TRUE, data);
    }

    public HttpResponse error(String msg) {
        return new HttpResponse(ERROR_CODE, msg, Boolean.FALSE, null);
    }

    @RequestMapping(value = "/page/{url}", method = {RequestMethod.GET, RequestMethod.POST})
    public String toPage(@PathVariable("url") String url) {
        return url;
    }
}
