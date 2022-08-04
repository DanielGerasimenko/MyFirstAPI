package com.daniel.delivery.servicetest;

import com.daniel.delivery.dto.PersonDto;
import com.daniel.delivery.entity.Person;
import com.daniel.delivery.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Optional;


public class ServiceTest extends BaseIT {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createPerson(){
        PersonDto personDto = new PersonDto();
        personDto.setFirstName("Иван");
        personDto.setLastName("Иванов");
        personDto.setAddress("12345str");

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<PersonDto> personDtoHttpEntity = new HttpEntity<>(personDto, httpHeaders);

        ResponseEntity<PersonDto> responseEntity =
                restTemplate.exchange("/person", HttpMethod.POST, personDtoHttpEntity, PersonDto.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Optional<Person> expectedPerson = personRepository.findById(responseEntity.getBody().getId());
        Assertions.assertTrue(expectedPerson.isPresent());
    }

}

