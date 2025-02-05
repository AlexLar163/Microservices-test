package com.alexlar163.customer_service.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "person-tbl")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String gender;

    private int age;

    private String identification;

    private String address;

    private String phone;

}
