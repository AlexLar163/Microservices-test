package com.alexlar163.customer_service.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@Table(name = "customer-tbl")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id", nullable = false, unique = true)
    private PersonEntity person;

    @Column(unique = true, nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String password;

    private boolean status;
}