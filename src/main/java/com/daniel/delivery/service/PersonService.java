package com.daniel.delivery.service;

import com.daniel.delivery.dto.PersonDto;

import java.util.List;

public interface PersonService {

    List<PersonDto> getAllPerson();

    PersonDto getPersonById(Long id);

    PersonDto createPerson(PersonDto personDto);

    void updatePerson(Long id, PersonDto personDto);

    void deletePerson(Long id);

}
