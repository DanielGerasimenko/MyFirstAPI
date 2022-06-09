package com.daniel.delivery.controller;

import com.daniel.delivery.dto.PersonDto;
import com.daniel.delivery.entity.Person;
import com.daniel.delivery.exception.PersonNotFoundException;
import com.daniel.delivery.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final ModelMapper modelMapper;

    private final PersonService personService;

    public PersonController(PersonService personService, ModelMapper modelMapper){
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<PersonDto> getAllPerson(){
        List<Person> personList = personService.getAllPerson();
        return personList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PersonDto createPerson(@RequestBody PersonDto personDto) {
        Person person = convertToEntity(personDto);
        Person personCreated = personService.createPerson(person);
        return convertToDto(personCreated);
    }

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable("id") Long id){
        return convertToDto(personService.getPersonById(id));
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public void updatePerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto){
        if(!Objects.equals(id, personDto.getId())){
            throw new PersonNotFoundException(id);
        }

        Person person = convertToEntity(personDto);
        personService.updatePerson(person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable("id") Long id){
        personService.deletePerson(id);
    }

    private PersonDto convertToDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    private Person convertToEntity(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }
}