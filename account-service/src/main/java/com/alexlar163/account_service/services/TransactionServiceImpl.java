package com.alexlar163.account_service.services;

import com.alexlar163.account_service.entities.AccountEntity;
import com.alexlar163.account_service.entities.TransactionEntity;
import com.alexlar163.account_service.exceptions.InsufficientBalanceException;
import com.alexlar163.account_service.repositories.AccountRepository;
import com.alexlar163.account_service.repositories.TransactionRepository;
import com.alexlar163.account_service.services.interfaces.TransactionServiceInterface;
import jakarta.transaction.Transaction;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionServiceInterface {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
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
    public TransactionEntity save(TransactionEntity transactionEntity) {
        Optional<AccountEntity> accountOpt = accountRepository.findById(transactionEntity.getAccount().getId());
        if (accountOpt.isEmpty()) {
            throw new NotFoundException("Account not found");
        }
        AccountEntity account = accountOpt.get();
        double newBalance = account.getBalance() + transactionEntity.getAmount();
        if (newBalance < 0) {
            throw new InsufficientBalanceException("Saldo no disponible");
        }
        account.setBalance(newBalance);
        accountRepository.save(account);
        return transactionRepository.save(transactionEntity);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}