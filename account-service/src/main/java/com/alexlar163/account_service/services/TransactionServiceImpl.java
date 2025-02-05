package com.alexlar163.account_service.services;

import com.alexlar163.account_service.entities.TransactionEntity;
import com.alexlar163.account_service.repositories.TransactionRepository;
import com.alexlar163.account_service.services.interfaces.TransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionServiceInterface {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionEntity> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<TransactionEntity> findById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public TransactionEntity save(TransactionEntity transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}