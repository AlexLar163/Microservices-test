package com.alexlar163.account_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEventDto {
    private String customerId;
    private String customerName;
    private String accountType;
}
