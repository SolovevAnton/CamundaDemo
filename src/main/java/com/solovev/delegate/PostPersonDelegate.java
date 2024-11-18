package com.solovev.delegate;

import com.solovev.common.DelegateNames;
import com.solovev.common.VariableNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component(DelegateNames.POST_PERSON_DELEGATE)
@RequiredArgsConstructor
@Slf4j
public class PostPersonDelegate implements JavaDelegate {

    public static final String url = "http://localhost:8080/person";

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var person = execution.getVariable(VariableNames.PERSON_RMQ);
        log.info("Person from modifier: {}", person);
        var result = restTemplate.postForEntity(url, person, String.class);
        log.info("Got result for person: [{}]", result);
    }

    private final RestTemplate restTemplate;
}
