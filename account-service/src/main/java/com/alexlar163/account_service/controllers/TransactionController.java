package com.alexlar163.account_service.controllers;

import com.alexlar163.account_service.entities.TransactionEntity;
import com.alexlar163.account_service.services.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionEntity> getAllTransactions() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable Long id) {
        Optional<TransactionEntity> transaction = transactionService.findById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TransactionEntity createTransaction(@RequestBody TransactionEntity transaction) {
        return transactionService.save(transaction);
    }

    @PutMapping("/{id}")
    public TransactionEntity updateTransaction(@PathVariable Long id, @RequestBody TransactionEntity transactionDetails) {
        transactionDetails.setId(id);
        return transactionService.save(transactionDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        Optional<TransactionEntity> transaction = transactionService.findById(id);
        if (transaction.isPresent()) {
            transactionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}