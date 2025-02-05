package com.alexlar163.account_service.services.interfaces;

import com.alexlar163.account_service.entities.TransactionEntity;

import java.util.List;
import java.util.Optional;

public interface TransactionServiceInterface {
    List<TransactionEntity> findAll();
    Optional<TransactionEntity> findById(Long id);
    TransactionEntity save(TransactionEntity customer);
    void deleteById(Long id);
}
