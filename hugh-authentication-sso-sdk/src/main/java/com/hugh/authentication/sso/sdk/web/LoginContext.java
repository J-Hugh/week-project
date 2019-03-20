package com.hugh.authentication.sso.sdk.web;

import com.hugh.authentication.sso.sdk.domain.UserInfo;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/13 11:29
 * @Description:
 */
public class LoginContext {

    /**
     * 当前用户
     */
    private final static ThreadLocal<UserInfo> LOGIN_USER = new ThreadLocal<UserInfo>() {
        @Override
        protected UserInfo initialValue() {
            return null;
        }
    };

    /**
     * 当前线程赋值 用户信息
     * @param userInfo 对象
     */
    public static void setLoginUser(UserInfo userInfo) {
        LOGIN_USER.set(userInfo);
    }

    /**
     * 获取当前登录用户
     * @return null 如果没有的话
     */
    public static UserInfo getLoginUser() {
        return LOGIN_USER.get();
    }

    /**
     * 获取当前登录用户账号
     * @return
     */
    public static String getUserName() {
        UserInfo userInfo = LOGIN_USER.get();
        return null == userInfo ? null : userInfo.getUserName();
    }

    /**
     * 获取当前登录用户编码
     * @return
     */
    public static String getUserCode() {
        UserInfo userInfo = LOGIN_USER.get();
        return null == userInfo ? null : userInfo.getUserCode();
    }

    /**
     * 删除上下文
     */
    public static void remove() {
        LOGIN_USER.remove();
    }

}
