package com.alexlar163.customer_service.repositories;

import com.alexlar163.customer_service.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
