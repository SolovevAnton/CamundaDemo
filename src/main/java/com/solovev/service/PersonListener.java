package com.solovev.service;

import com.solovev.common.ProcessNames;
import com.solovev.common.VariableNames;
import com.solovev.config.RabbitConfig;
import com.solovev.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonListener {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receivePerson(@Payload Person person) {
        log.info("Received from RMQ {}", person);
        camunda
                .getRuntimeService()
                .startProcessInstanceByKey(ProcessNames.PERSON_ENCHANCE,
                        Variables.putValue(VariableNames.PERSON_RMQ, person));
        log.info("Process started");
    }

    private final ProcessEngine camunda;

}
