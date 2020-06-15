package com.example.controller;

import com.example.PersonService;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("hello world");
    }

    @GetMapping("/user/{userId}")
    public Mono<Person> getPersonById(@PathVariable("userId") Integer userId) {
        return personService.getPersonById(userId);
    }

    @DeleteMapping("/user/{userId}")
    public Mono deletePersonById(@PathVariable("userId") Integer userId) {
        return personService.deletePerson(userId);
    }

    @PostMapping("/user")
    public Mono<Person> addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @PutMapping("/user")
    public Mono updatePerson(@RequestBody Person person) {
        //just a demo how to handle error in-line,maybe this kind of error handle is not very good, please use a global error handler instead.
//        return personService.updatePerson(person).doOnError(e->System.out.println("update person failure")).onErrorReturn(ErrorResponse.builder().msg("failed to udpate").build());
        return personService.updatePerson(person).doOnError(e->System.out.println("update person failure"));
    }
}
