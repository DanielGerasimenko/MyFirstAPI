package com.daniel.delivery.controller;

import com.daniel.delivery.dto.PersonDto;
import com.daniel.delivery.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Get all person")
    public List<PersonDto> getPersonAll() {
        return personService.getAllPerson();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PersonDto createPersonAll(@RequestBody PersonDto personDto) {
        return personService.createPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable ("id") Long id){
        return personService.getPersonById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updatePersonsId(@PathVariable Long id, @RequestBody PersonDto personDto){
        personService.updatePerson(id,personDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deletePersonId(@PathVariable Long id){
        personService.deletePerson(id);
    }

}