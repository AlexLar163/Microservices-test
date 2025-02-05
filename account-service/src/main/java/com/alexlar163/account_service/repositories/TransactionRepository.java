package com.alexlar163.account_service.repositories;

import com.alexlar163.account_service.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
