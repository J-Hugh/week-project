package com.hugh.auto.test.common.enumType;

/**
 * 浏览器类型
 */
public enum  BrowserTypeEnum {

    IE("ie","IE浏览器"),
    CHROME("Chrome","谷歌浏览器");

    private String name;

    private String des;

    BrowserTypeEnum(String name, String des) {
        this.name = name;
        this.des = des;
    }}
