package com.daniel.delivery.service;

import com.daniel.delivery.dto.PersonDto;
import com.daniel.delivery.entity.Person;
import com.daniel.delivery.exception.PersonNotFoundException;
import com.daniel.delivery.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<PersonDto> getAllPerson() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto getPersonById(Long id){
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        return convertToDto(person);
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = convertToEntity(personDto);
        Person personCreated = personRepository.save(person);
        return convertToDto(personCreated);
    }

    @Override
    public void updatePerson(Long id, PersonDto personDto) {
        if(!Objects.equals(id, personDto.getId())){
            throw new PersonNotFoundException(id);
        }

        Person person = convertToEntity(personDto);
        personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        personRepository.delete(person);
    }

    private PersonDto convertToDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    private Person convertToEntity(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }
}