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
        return personService.updatePerson(person).doOnError(e->Mono.just(e.getMessage()));
    }
}
