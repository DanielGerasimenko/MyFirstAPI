package com.daniel.delivery.repository;

import com.daniel.delivery.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
