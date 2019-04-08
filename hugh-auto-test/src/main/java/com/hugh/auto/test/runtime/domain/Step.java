package com.hugh.auto.test.runtime.domain;

import lombok.Data;

/**
 * 执行步骤
 */
@Data
public class Step {

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

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", testCaseDesc='" + testCaseDesc + '\'' +
                ", testCaseId='" + testCaseId + '\'' +
                ", step=" + step +
                ", stepDescription='" + stepDescription + '\'' +
                ", pageName='" + pageName + '\'' +
                ", byType='" + byType + '\'' +
                ", byExpression='" + byExpression + '\'' +
                ", pageObjectName='" + pageObjectName + '\'' +
                ", actionKeyword='" + actionKeyword + '\'' +
                ", testData='" + testData + '\'' +
                ", testExpectValue='" + testExpectValue + '\'' +
                ", officalData='" + officalData + '\'' +
                ", officalExpectValue='" + officalExpectValue + '\'' +
                ", parentId=" + parentId +
                ", parentDesc='" + parentDesc + '\'' +
                ", childId=" + childId +
                ", childDesc='" + childDesc + '\'' +
                ", dataRow=" + dataRow +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
