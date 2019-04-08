package com.hugh.auto.test.runtime.domain;

import lombok.Data;

import java.util.List;

/**
 * 执行手册
 */
@Data
public class PerformManual {

    /**
     * 名称
     */
    private String name;

    /**
     * 执行环境
     */
    private Environment environment;

    /**
     * 执行步骤
     */
    private List<Step> steps;

}
