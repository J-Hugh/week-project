package com.hugh.auto.test.chronicler.impl;

import com.hugh.auto.test.chronicler.IChroniclerService;
import com.hugh.auto.test.runtime.domain.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChroniclerServiceImpl implements IChroniclerService {

    @Override
    public void recordRunDriver(Boolean status) {
        //TODO
    }

    @Override
    public void recordSuccessStep(Step step) {
//TODO
    }

    @Override
    public void recordFailureStep(Step step, String reason) {
//TODO
    }

    @Override
    public void recordStartCase() {
//TODO
    }
}
