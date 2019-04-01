package com.hugh.auto.test.selenium.driver;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Android程序驱动
 */
public class AdroidDriver extends Driver {

    public AdroidDriver(String nodeUrl) {
        super(nodeUrl);
    }

    @Override
    void initDriver(String nodeUrl) {
        
    }
}
