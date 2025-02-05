package com.alexlar163.account_service.services.interfaces;

import com.alexlar163.account_service.entities.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface AccountServiceInterface {
    List<AccountEntity> findAll();
    Optional<AccountEntity> findById(Long id);
    AccountEntity save(AccountEntity customer);
    void deleteById(Long id);
}
