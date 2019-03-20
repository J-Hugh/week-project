package com.hugh.authentication.sso.sdk.springmvc.interceptor;

import com.hugh.authentication.sso.sdk.utils.SSOHelper;
import com.hugh.authentication.sso.sdk.web.LoginContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/13 15:07
 * @Description:
 */
public class SpringSSOInterceptor extends SsoInterceptor implements HandlerInterceptor {
    private final static Log log = LogFactory.getLog(SpringSSOInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (null != LoginContext.getLoginUser()) {
            log.error("警告：线程变量未清除");
            LoginContext.remove();
        }
        //排除不需要拦截的请求
        if (isExclude(request)) {
            return true;
        }
        if (SSOHelper.hasCookie(request, SSO_COOKIE_NAME)) {
            //getLoginUser(request, response);


        }

        response.sendRedirect(getLoginUrl());
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

