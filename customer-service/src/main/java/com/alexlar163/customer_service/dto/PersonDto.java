package com.alexlar163.customer_service.dto;
import lombok.Data;

@Data
public class PersonDto {
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;
}
