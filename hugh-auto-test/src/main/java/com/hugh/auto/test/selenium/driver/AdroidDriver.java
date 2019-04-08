package com.hugh.auto.test.selenium.driver;

import com.hugh.auto.test.runtime.domain.Environment;
import org.openqa.selenium.WebDriver;

/**
 * Android程序驱动
 */
public class AdroidDriver extends Driver {

    public AdroidDriver(Environment environment) {
        super(environment);
    }

    @Override
    WebDriver initDriver(Environment environment) {
        return null;
    }
}
