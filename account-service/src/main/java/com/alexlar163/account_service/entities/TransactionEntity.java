package com.alexlar163.account_service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "transactions-tbl")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private String status;
    private String clientName;
    private String clientId;
    private LocalDate date;
    private String transactionType;
    private Double amount;
    private Double balance;

}