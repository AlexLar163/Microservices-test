package com.alexlar163.account_service.services;

import com.alexlar163.account_service.dto.CustomerEventDto;
import com.alexlar163.account_service.entities.AccountEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountConsumerService {

    private final AccountServiceImpl accountService;

    public CreateAccountConsumerService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @KafkaListener(topics = "customer-topic", groupId = "account-service",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeMessage(CustomerEventDto message) {
        AccountEntity account = new AccountEntity();
        account.setCustomerId(message.getCustomerId());
        account.setCustomerName(message.getCustomerName());
        account.setAccountType(message.getAccountType());

        accountService.save(account);
    }
}