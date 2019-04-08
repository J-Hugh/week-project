package com.hugh.auto.test.runtime;

import com.hugh.auto.test.chronicler.IChroniclerService;
import com.hugh.auto.test.runtime.domain.Environment;
import com.hugh.auto.test.runtime.domain.PerformManual;
import com.hugh.auto.test.runtime.domain.Step;
import com.hugh.auto.test.selenium.driver.AdroidDriver;
import com.hugh.auto.test.selenium.driver.Driver;
import com.hugh.auto.test.selenium.driver.IosDriver;
import com.hugh.auto.test.selenium.driver.PcDriver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 测试用例执行器
 */
@Service
@Slf4j
public class TestCaseActuator {

    @Autowired
    private IChroniclerService chroniclerService;

    /**
     * 开始执行
     * @param manual
     */
    public void start(PerformManual manual) {
        String uuid = UUID.randomUUID().toString();
        log.info("开始执行测试 编号:{} 用例名称:{}", manual.getName(), uuid);
        chroniclerService.recordStartCase();

        Driver driver = null;
        Environment environment = manual.getEnvironment();
        switch (environment.getEnvTypeEnum()) {
            case PC:
                driver = new PcDriver(environment);
                break;
            case ANDROID:
                driver = new AdroidDriver(environment);
                break;
            case IOS:
                driver = new IosDriver(environment);
                break;
        }
        if (null == driver) {
            chroniclerService.recordRunDriver(Boolean.FALSE);
            log.info("设备启动失败");
            return;
        }
        chroniclerService.recordRunDriver(Boolean.TRUE);
        log.info("设备启动成功");

        for (Step step : manual.getSteps()) {
            try {
                driver.runStep(step);
                chroniclerService.recordSuccessStep(step);
            } catch (Exception e) {
                log.error("执行失败 {},{}", step, e);
                driver.screenShot();
                chroniclerService.recordFailureStep(step, e.getMessage());
            }
        }
    }
}
