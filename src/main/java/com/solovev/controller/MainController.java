package com.solovev.controller;

import com.solovev.common.ProcessNames;
import com.solovev.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {

    @PostMapping("/person")
    public ResponseEntity<?> tested(@RequestBody Person person) {
        log.info("Got person: [{}]", person);
        if (notValidName(person) || person.getAge() > 18 && !person.isAdult()) {
            log.error("Not valid person!");
            return ResponseEntity.badRequest().body("Not valid person!");
        }
        return ResponseEntity.ok("Valid person!");
    }

    @GetMapping("/hello")
    public ResponseEntity<?> sayHello() {
        var
                result =
                camunda
                        .getRuntimeService()
                        .startProcessInstanceByKey(ProcessNames.HELLO); //started async by ajusting diagram!
        return ResponseEntity.ok("Hello will be said in process: " + result.getProcessDefinitionId());
    }

    private boolean notValidName(Person person) {
        return isNull(person.getName()) || person.getName().isBlank();
    }

    private final ProcessEngine camunda;
}
