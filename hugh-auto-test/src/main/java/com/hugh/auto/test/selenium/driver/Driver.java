package com.hugh.auto.test.selenium.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * 设备
 * @author hugh
 */
public abstract class Driver {

    private WebDriver webDriver;

    public Driver(String nodeUrl) {
       webDriver = initDriver(nodeUrl);
    }

    /**
     * 初始化设备
     * @param nodeUrl 节点地址
     */
    abstract WebDriver initDriver(String nodeUrl);

    /**
     * 获取元素
     * @param by
     * @return
     */
    WebElement getElement(By by) {
        WebElement element = webDriver.findElement(by);
        if (null != element) {
            jsExecutor("arguments[0].style.border = '3px solid black'", element);
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {

            }
            jsExecutor("arguments[0].style.border = 'none'", element);
        }
        return element;
    }

    /**
     * js脚本执行
     * @param script
     * @param element
     */
    void jsExecutor(String script, WebElement element) {

    }

    /**
     *  使当前元素显示在页面
     * @param element
     */
    void scrollIntoView(WebElement element) {
        jsExecutor("arguments[0].scrollIntoView();", element);
    }

    /**
     * 单击当前元素
     * @param element
     */
    void click(WebElement element) {
        scrollIntoView(element);
        element.click();
    }

    /**
     * 输入内容
     * @param element
     * @param text
     */
    void inputText(WebElement element,String text) {
        scrollIntoView(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * 根据文字选择下拉框
     * @param element
     * @param text
     */
    public void selectByText(WebElement element,String text) {
        scrollIntoView(element);
        Select sel = new Select(element);
        sel.selectByVisibleText(text);
    }

    /**
     * 根据值选择下拉框
     * @param element
     * @param val
     */
    public void selectByVal(WebElement element,String val) {
        scrollIntoView(element);
        Select sel = new Select(element);
        sel.selectByValue(val);
    }

}
