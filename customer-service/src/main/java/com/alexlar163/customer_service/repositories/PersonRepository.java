package com.alexlar163.customer_service.repositories;

import com.alexlar163.customer_service.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
