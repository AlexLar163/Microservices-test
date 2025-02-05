package com.alexlar163.account_service.controllers;

import com.alexlar163.account_service.entities.AccountEntity;
import com.alexlar163.account_service.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountEntity> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountEntity> getAccountById(@PathVariable Long id) {
        Optional<AccountEntity> account = accountService.findById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AccountEntity createAccount(@RequestBody AccountEntity account) {
        return accountService.save(account);
    }

    @PutMapping("/{id}")
    public AccountEntity updateAccount(@PathVariable Long id, @RequestBody AccountEntity accountDetails) {
        accountDetails.setId(id);
        return accountService.save(accountDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        Optional<AccountEntity> account = accountService.findById(id);
        if (account.isPresent()) {
            accountService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}