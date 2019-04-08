package com.hugh.auto.test.runtime.domain;

import com.hugh.auto.test.common.enumType.EnvTypeEnum;
import lombok.Data;
import org.openqa.selenium.Platform;

/**
 * 运行环境
 */
@Data
public class Environment {

    private EnvTypeEnum envTypeEnum;

    /**
     * 运行节点地址
     */
    private String nodeUrl;

    /**
     * 浏览器名称
     */
    private String browserName;

    /**
     * 运行平台
     */
    private Platform platform;

    /**
     * 版本
     */
    private String version;

    /**
     * 执行首頁
     */
    private String index;

}
