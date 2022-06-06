package com.daniel.delivery.controller;


import com.daniel.delivery.repository.PersonRepository;
import com.daniel.delivery.entity.Person;
import com.daniel.delivery.exception.PersonNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository personRepository;


    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/person")
    List<Person> all(){
        return personRepository.findAll();
    }

    @PostMapping("/person")
    Person newPerson(@RequestBody Person newPerson){
        return personRepository.save(newPerson);
    }

    @GetMapping("/person/{id}")
    Person one(@PathVariable Long id){
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/person/{id}")
    Person replacePerson (@RequestBody Person newPerson, @PathVariable Long id){
        return personRepository.findById(id)
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    person.setAddress(newPerson.getAddress());
                    return personRepository.save(person);
                }).orElseGet(()->{
                    newPerson.setId(id);
                    return personRepository.save(newPerson);
                });
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Long id){
        personRepository.deleteById(id);
    }
}
