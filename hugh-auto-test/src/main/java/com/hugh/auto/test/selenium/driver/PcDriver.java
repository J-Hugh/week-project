package com.hugh.auto.test.selenium.driver;

import com.hugh.auto.test.runtime.domain.Environment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * web程序驱动
 */
@Slf4j
public class PcDriver extends Driver {

    public PcDriver(Environment environment) {
        super(environment);
    }

    @Override
    public WebDriver initDriver(Environment environment) {
        try {
            DesiredCapabilities capability = new DesiredCapabilities(environment.getBrowserName(), environment.getVersion(), environment.getPlatform());

            RemoteWebDriver webDriver = new RemoteWebDriver(new URL(environment.getNodeUrl()), capability);

            return webDriver;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.error("创建driver失败:{}", e);
        }
        return null;
    }
}
