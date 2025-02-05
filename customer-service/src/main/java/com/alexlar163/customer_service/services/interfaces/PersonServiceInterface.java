package com.alexlar163.customer_service.services.interfaces;

import com.alexlar163.customer_service.entities.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface PersonServiceInterface {
    List<PersonEntity> findAll();
    Optional<PersonEntity> findById(Long id);
    PersonEntity save(PersonEntity person);
    void deleteById(Long id);
}