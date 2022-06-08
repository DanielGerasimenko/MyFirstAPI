package com.daniel.delivery.service;

import com.daniel.delivery.entity.Person;
import com.daniel.delivery.exception.PersonNotFoundException;
import com.daniel.delivery.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Person save(Person person) {
      return  personRepository.save(person);
    }

    public List<Person> listAll() {
        return (List<Person>) personRepository.findAll();
    }

    public Person get(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public Person editPerson(Person newPerson, Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        person.setFirstName(newPerson.getFirstName());
        person.setLastName(newPerson.getLastName());
        person.setAddress(newPerson.getAddress());
        return personRepository.save(person);
    }
}
