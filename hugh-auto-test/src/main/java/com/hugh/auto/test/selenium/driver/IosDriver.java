package com.hugh.auto.test.selenium.driver;

import com.hugh.auto.test.runtime.domain.Environment;
import org.openqa.selenium.WebDriver;

/**
 * IOS程序驱动
 */
public class IosDriver extends Driver {

    public IosDriver(Environment environment) {
        super(environment);
    }

    @Override
    WebDriver initDriver(Environment environment) {
        return null;
    }
}
