package com.hugh.auto.test.chronicler;

import com.hugh.auto.test.runtime.domain.Step;

public interface IChroniclerService {

    void recordStartCase();

    void recordRunDriver(Boolean status);

    void recordSuccessStep(Step step);

    void recordFailureStep(Step step,String reason);
}
