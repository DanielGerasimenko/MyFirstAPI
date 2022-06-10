package com.daniel.delivery.controller;

import com.daniel.delivery.dto.PersonDto;
import com.daniel.delivery.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping
    @ResponseBody
    public List<PersonDto> getAllPersons() {
        return personService.getAllPerson();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PersonDto createPersons(@RequestBody PersonDto personDto) {
        return personService.createPerson(personDto);
    }

    @GetMapping
    public PersonDto getPerson(@PathVariable Long id){
        return personService.getPersonById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updatePersons(@PathVariable("id") Long id, @RequestBody PersonDto personDto){
        personService.updatePerson(id,personDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deletePersons(@PathVariable("id") Long id){
        personService.deletePerson(id);
    }

}