package com.alexlar163.customer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerEventDto {
    private String customerId;
    private String customerName;
    private String accountType;
}
