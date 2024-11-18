package com.solovev.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("HelloDelegate")
@Slf4j
public class HelloDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Hello World: {}", delegateExecution);
    }
}
