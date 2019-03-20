package com.hugh.authentication.sso.sdk.domain;

import java.io.Serializable;

/**
 * 账号信息
 */
public class UserInfo implements Serializable {

    /**
     * 用户编号
     **/
    private String userCode;

    /**
     * 用户账号
     **/
    private String userAccount;

    /**
     * 用户姓名
     **/
    private String realName;

    /**
     * 登录密码
     **/
    private String userPwd;

    /**
     * 部门编号
     **/
    private String deptCode;

    /**
     * 用户Ip地址
     * */
    private String ip;

    /**
     * 用户浏览器agent
     */
    private String userAgent;

    /**
     * 是否是测试账号
     */
    private Boolean isTest;

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return this.userAccount;
    }

    public void setUserName(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserPwd() {
        return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getDeptCode() {
        return this.deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Boolean getTest() {
        return this.isTest;
    }

    public void setTest(Boolean test) {
        this.isTest = test;
    }
}
