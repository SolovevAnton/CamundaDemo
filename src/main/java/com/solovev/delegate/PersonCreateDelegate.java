package com.solovev.delegate;

import com.solovev.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.solovev.common.VariableNames.PERSON;

@Component("PersonCreateDelegate")
@Slf4j
public class PersonCreateDelegate implements JavaDelegate {
    public static final int MAX_AGE = 36;
    private static int personCounter;

    private final Random random = new Random();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Person person = createPerson();
        delegateExecution.setVariable(PERSON, person);
        log.info("Person created: " + person);
    }

    private Person createPerson() {
        Person person = new Person();
        person.setName("Number: " + ++personCounter);
        person.setAge(random.nextInt(MAX_AGE));
        return person;
    }
}
