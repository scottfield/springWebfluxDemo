package com.example;

import com.example.dao.PersonRepository;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Mono<Person> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    public Mono<Person> addPerson(Person person) {
        return personRepository.save(person);
    }

    public Mono<Void> deletePerson(Integer id) {
        return personRepository.deleteById(id);
    }

    public Mono updatePerson(Person person) {
        Mono<Person> updatedPerson = personRepository.save(person);
        if (person.getUsername().equals("admin")) {
            throw new IllegalArgumentException("cannot update admin");
        }
        return updatedPerson;
    }
}
