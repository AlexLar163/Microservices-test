package com.alexlar163.customer_service.dto;
import lombok.Data;

@Data
public class CustomerDto extends PersonDto{
    private String customerId;
    private String password;
    private String state;
}
