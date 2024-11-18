package com.solovev.delegate;

import com.solovev.common.DelegateNames;
import com.solovev.common.VariableNames;
import com.solovev.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component(DelegateNames.PERSON_MODIFIER)
@Slf4j
public class PersonModifyDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Person person = (Person) execution.getVariable(VariableNames.PERSON_RMQ);
        modifyPerson(person);
    }

    private void modifyPerson(Person toModify) {
        if (toModify.getAge() > 18) {
            toModify.setAdult(true);
        }
    }
}
