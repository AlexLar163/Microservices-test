package com.alexlar163.customer_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @GetMapping("/get")
    public String getCostumer() {
        return "Costumer";
    }
}
