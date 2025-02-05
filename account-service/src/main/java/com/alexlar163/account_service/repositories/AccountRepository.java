package com.alexlar163.account_service.repositories;

import com.alexlar163.account_service.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
