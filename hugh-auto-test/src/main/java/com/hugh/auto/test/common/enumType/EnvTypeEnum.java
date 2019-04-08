package com.hugh.auto.test.common.enumType;

/**
 * 环境类型
 */
public enum  EnvTypeEnum {

    PC("PC","pc"),
    ANDROID("ANDROID", "安卓"),
    IOS("IOS", "苹果");

    private String name;

    private String des;

    EnvTypeEnum(String name, String des) {
        this.name = name;
        this.des = des;
    }
}
