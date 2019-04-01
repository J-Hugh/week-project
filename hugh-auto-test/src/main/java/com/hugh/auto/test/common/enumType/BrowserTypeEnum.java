package com.hugh.auto.test.common.enumType;

/**
 * 浏览器类型
 */
public enum  BrowserTypeEnum {

    IE("ie","IE");

    private String name;

    private String des;

    BrowserTypeEnum(String name, String des) {
        this.name = name;
        this.des = des;
    }}
