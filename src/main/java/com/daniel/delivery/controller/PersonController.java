package com.daniel.delivery.controller;


import com.daniel.delivery.entity.Person;
import com.daniel.delivery.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    List<Person> all(){
        return personService.listAll();
    }

    @PostMapping("/new")
    Person newPerson(@RequestBody Person Person){
        return personService.save(Person);
    }

    @GetMapping("/{id}")
    Person one(@PathVariable Long id){
        return personService.get(id);
    }

    @PutMapping("/{id}")
    Person replacePerson (@RequestBody Person newPerson, @PathVariable Long id){
      return personService.editPerson(newPerson, id);
    }

    @DeleteMapping("/{id}")
     void deletePerson(@PathVariable Long id){
        personService.delete(id);
    }
}
