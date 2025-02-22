package com.alexlar163.account_service.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account-tbl")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private String status;
    private String customerName;
    private String customerId;

}