package com.daniel.delivery.dtotest;

import com.daniel.delivery.dto.PersonDto;
import com.daniel.delivery.entity.Person;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonDtoUnitTest {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertPersonEntityToPersonDto_thenCorrect() {
        Person person = new Person();
        person.setId(11L);
        person.setFirstName("daniel");
        person.setLastName("ger");
        person.setAddress("12345g");

        PersonDto personDto = modelMapper.map(person, PersonDto.class);
        assertEquals(person.getId(), personDto.getId());
        assertEquals(person.getFirstName(), personDto.getFirstName());
        assertEquals(person.getLastName(), personDto.getLastName());
        assertEquals(person.getAddress(), personDto.getAddress());

    }

    @Test
    public void whenConvertPersonDtoToPersonEntity_thenCorrect() {
        PersonDto personDto = new PersonDto();
        personDto.setId(11L);
        personDto.setFirstName("daniel");
        personDto.setLastName("ger");
        personDto.setAddress("12345g");

        Person person = modelMapper.map(personDto, Person.class);
        assertEquals(personDto.getId(), person.getId());
        assertEquals(personDto.getFirstName(), person.getFirstName());
        assertEquals(personDto.getLastName(), person.getLastName());
        assertEquals(personDto.getAddress(), person.getAddress());
    }
}
