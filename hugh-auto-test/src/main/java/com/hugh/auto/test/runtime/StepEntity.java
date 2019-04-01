package com.hugh.auto.test.runtime;

import lombok.Data;

/**
 * 测试步骤实体
 */
@Data
public class StepEntity {

    private Integer id;

    private String testCaseDesc;

    private String testCaseId;

    private Integer step;

    private String stepDescription;

    private String pageName;

    private String byType;

    private String byExpression;

    private String pageObjectName;

    private String actionKeyword;

    private String testData;

    private String testExpectValue;

    private String officalData;

    private String officalExpectValue;

    private Integer parentId;

    private String parentDesc;

    private Integer childId;

    private String childDesc;

    private Integer dataRow;

    private String createTime;

    private String updateTime;

}
