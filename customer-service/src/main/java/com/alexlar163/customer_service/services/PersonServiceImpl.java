package com.alexlar163.customer_service.services;

import com.alexlar163.customer_service.entities.PersonEntity;
import com.alexlar163.customer_service.repositories.PersonRepository;
import com.alexlar163.customer_service.services.interfaces.PersonServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonServiceInterface {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonEntity> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<PersonEntity> findById(Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    @Override
    public PersonEntity save(PersonEntity person) {
        return personRepository.save(person);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}