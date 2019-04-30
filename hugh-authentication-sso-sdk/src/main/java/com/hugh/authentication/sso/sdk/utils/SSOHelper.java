package com.hugh.authentication.sso.sdk.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * servlet相关操作
 * @author luoyulin1
 */
public class SSOHelper {


    /**
     * 判断存不存在某cookie
     * @param request
     * @param cookieName
     * @return
     */
    public static Boolean hasCookie(HttpServletRequest request,String cookieName) {
        return !StringUtils.isEmpty(getCookieValue(request, cookieName));
    }

    /**
     * 获取某一cookie值
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            Cookie cookie = Arrays.stream(cookies).filter(v -> cookieName.equals(v.getName())).findFirst().get();
            return null == cookie ? null : cookie.getValue();
        }
        return null;
    }

    /**
     * 获取访问者真实IP地址
     * @param request
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null) {
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            int position = ip.indexOf(",");
            if (position > 0) {
                ip = ip.substring(0, position);
            }
        }
        return ip;
    }

    /**
     * 获取访问者浏览器user-agent
     * @param request
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT");
        return userAgent;
    }

    /**
     * 判断是否是ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        return (request.getRequestURI().endsWith(".json") || ("XMLHttpRequest".equals(requestType)));
    }
}
