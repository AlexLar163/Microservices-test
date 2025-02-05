package com.alexlar163.customer_service.services;

import com.alexlar163.customer_service.entities.CustomerEntity;
import com.alexlar163.customer_service.entities.PersonEntity;
import com.alexlar163.customer_service.repositories.CustomerRepository;
import com.alexlar163.customer_service.repositories.PersonRepository;
import com.alexlar163.customer_service.services.interfaces.CustomerServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerServiceInterface {

    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final CreateAccountProducerService createAccountProducerService;

    public CustomerServiceImpl(CustomerRepository customerRepository, PersonRepository personRepository, CreateAccountProducerService createAccountProducerService) {
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
        this.createAccountProducerService = createAccountProducerService;
    }

    @Override
    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<CustomerEntity> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Transactional
    @Override
    public CustomerEntity save(CustomerEntity customer) {
        PersonEntity person = customer.getPerson();
        person = personRepository.save(person);

        customer.setPerson(person);
        CustomerEntity result = customerRepository.save(customer);
        createAccountProducerService.createAccountSend(customer);

        return result;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}