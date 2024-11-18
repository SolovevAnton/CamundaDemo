package com.solovev.delegate;

import com.solovev.common.DelegateNames;
import com.solovev.common.VariableNames;
import com.solovev.config.RabbitConfig;
import com.solovev.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component(DelegateNames.RabbitProducer)
@RequiredArgsConstructor
@Slf4j
public class RmqProducerDelegate implements JavaDelegate {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Person person = (Person) delegateExecution.getVariable(VariableNames.PERSON);
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME,person);
        log.info("Send person {}", person);
    }
}
