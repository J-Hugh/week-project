package com.hugh.auto.test.selenium.driver;

import com.hugh.auto.test.runtime.domain.Environment;
import com.hugh.auto.test.runtime.domain.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.Iterator;

/**
 * 设备
 *
 * @author hugh
 */
public abstract class Driver {

    private WebDriver webDriver;

    public Driver(Environment environment) {
        webDriver = initDriver(environment);
    }

    /**
     * 初始化设备
     *
     * @param environment 运行环境
     */
    abstract WebDriver initDriver(Environment environment);

    /**
     * 获取元素
     *
     * @param by
     * @return
     */
    WebElement getElement(By by) {
        WebElement element = webDriver.findElement(by);
        if (null != element) {
            scrollIntoView(element);
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
     *
     * @param script
     * @param element
     */
    void jsExecutor(String script, WebElement element) {
        try {
            ((JavascriptExecutor) webDriver).executeScript(script, element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使当前元素显示在页面
     *
     * @param element
     */
    void scrollIntoView(WebElement element) {
        jsExecutor("arguments[0].scrollIntoView();", element);
    }

    /**
     * 单击当前元素
     *
     * @param element
     */
    void click(WebElement element) {
        scrollIntoView(element);
        element.click();
    }

    /**
     * 输入内容
     *
     * @param element
     * @param text
     */
    void inputText(WebElement element, String text) {
        scrollIntoView(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * 根据文字选择下拉框
     *
     * @param element
     * @param text
     */
    public void selectByText(WebElement element, String text) {
        scrollIntoView(element);
        Select sel = new Select(element);
        sel.selectByVisibleText(text);
    }

    /**
     * 根据值选择下拉框
     *
     * @param element
     * @param val
     */
    public void selectByVal(WebElement element, String val) {
        scrollIntoView(element);
        Select sel = new Select(element);
        sel.selectByValue(val);
    }

    /**
     * 判断某元素的文本值是否也预期一致
     *
     * @param element
     * @param expectValue
     * @throws Exception
     */
    public void check(WebElement element, String expectValue) throws Exception {
        scrollIntoView(element);
        String data = element.getText();
        if (!expectValue.equals(data)) {
            throw new Exception(String.format("实际值:%s 与期望值:%s 不一致", data, expectValue));
        }
    }

    /**
     * 右击元素
     * @param element
     */
    public void rightClick(WebElement element) {
        scrollIntoView(element);
        Actions action = new Actions(webDriver);
        action.contextClick(element).perform();
    }

    /**
     * 清空元素
     * @param element
     */
    public void clear(WebElement element) {
        scrollIntoView(element);
        element.clear();
    }

    /**
     * 切换至新窗口
     */
    public void switchToNewWindow() {
        String currentWindow = webDriver.getWindowHandle();// 获取当前窗口句柄
        java.util.Set<String> handles = webDriver.getWindowHandles();// 获取所有窗口句柄
        Iterator<String> it = handles.iterator();
        while (it.hasNext()) {
            String win = it.next();
            if (win.equals(currentWindow)) {
                continue;
            }
            webDriver.switchTo().window(win);// 切换到新窗口
            break;
        }
    }

    /**
     * 上传图片
     * @param element
     * @param url
     */
    public void uploadImage(WebElement element,String url) {
        scrollIntoView(element);
        Runtime time = Runtime.getRuntime();
        try{
            time.exec(new String[]{"cmd","/c","upfile.exe"},null,new File(url));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 截图
     */
    public void screenShot() {
        File screenShotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            //FileUtils.copyFile(screenShotFile, new File(path +"\\"+ name + ".jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public By getBy(String type, String expression) {
        switch (type) {
            case "id":
                return By.id(expression);
            case "name":
                return By.name(expression);
            case "css":
                return By.cssSelector(expression);
            case "xpath":
                return By.xpath(expression);
            case "classname":
                return By.className(expression);
            case "atext":
                return By.linkText(expression);
            case "patext":
                return By.partialLinkText(expression);
            case "tagname":
                return By.tagName(expression);
            default:
                return By.xpath(expression);
        }
    }

    /**
     * 执行一条用例
     *
     * @param step
     * @throws Exception
     */
    public void runStep(Step step) throws Exception {
        WebElement element = getElement(getBy(step.getByType(), step.getByExpression()));
        if (element == null) {
            throw new Exception("元素不存在");
        }
        String actionKeyword = step.getActionKeyword();
        String testExpectValue = step.getTestExpectValue();
        String testData = step.getTestData();
        switch (actionKeyword) {
            case "input":
                inputText(element,testExpectValue);
                break;
            case "clear":
                clear(element);
                break;
            case "click":
                click(element);
                break;
            case "check":
                check(element,testExpectValue);
                break;
            case "right_click":
                rightClick(element);
                break;
            case "sleep":
                Thread.sleep(Long.valueOf(testExpectValue));
                break;
            case "select":
                selectByVal(element,testData);
                break;
            case "switchto_new":
                switchToNewWindow();
                break;
            case "uploadimage":
                uploadImage(element,testData);
                break;
            default:
                throw new Exception("未知的操作码");
        }
    }
}
