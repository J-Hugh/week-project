package com.hugh.auto.test.selenium.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * web程序驱动
 */
@Slf4j
public class PcDriver extends Driver {

    public PcDriver(String nodeUrl) {
        super(nodeUrl);
    }

    @Override
    public WebDriver initDriver(String nodeUrl) {
        try {
            RemoteWebDriver webDriver = new RemoteWebDriver(new URL(nodeUrl), null);
            return webDriver;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.error("创建driver失败:{}", e);
        }
        return null;
    }
}
