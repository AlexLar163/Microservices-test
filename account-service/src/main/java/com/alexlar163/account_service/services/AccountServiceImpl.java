package com.alexlar163.account_service.services;

import com.alexlar163.account_service.entities.AccountEntity;
import com.alexlar163.account_service.repositories.AccountRepository;
import com.alexlar163.account_service.services.interfaces.AccountServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountServiceInterface {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountEntity> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<AccountEntity> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public AccountEntity save(AccountEntity account) {
        Random random = new Random();
        String accountNumber = String.valueOf(100000000 + random.nextInt(900000000));
        account.setAccountNumber(accountNumber);
        account.setBalance(0.0);
        account.setStatus("active");

        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}