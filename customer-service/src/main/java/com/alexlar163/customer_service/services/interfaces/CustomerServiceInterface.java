package com.alexlar163.customer_service.services.interfaces;

import com.alexlar163.customer_service.entities.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {
    List<CustomerEntity> findAll();
    Optional<CustomerEntity> findById(Long id);
    CustomerEntity save(CustomerEntity customer);
    void deleteById(Long id);
}