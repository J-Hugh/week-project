package com.hugh.auto.test.selenium.driver;

import org.openqa.selenium.WebDriver;

/**
 * Android程序驱动
 */
public class AdroidDriver extends Driver {

    public AdroidDriver(String nodeUrl) {
        super(nodeUrl);
    }

    @Override
    WebDriver initDriver(String nodeUrl) {
        return null;
    }
}
