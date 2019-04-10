package com.hugh.auto.test.runtime.domain;

import lombok.Data;

/**
 * 执行步骤
 */
@Data
public class Step {

    private Integer id;

    private String testCaseDesc;

    private String stepDescription;

    private String byType;

    private String byExpression;

    private String actionKeyword;

    private String testData;

    private String testExpectValue;

}
