package com.daniel.delivery.service;

import com.daniel.delivery.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPerson();

    Person createPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Long id);

    Person getPersonById(Long id);
}
