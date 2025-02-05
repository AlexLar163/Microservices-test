package com.alexlar163.customer_service.services;

import com.alexlar163.customer_service.dto.CustomerEventDto;
import com.alexlar163.customer_service.entities.CustomerEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountProducerService {
    private final KafkaTemplate<String, CustomerEventDto> kafkaTemplate;

    public CreateAccountProducerService(KafkaTemplate<String, CustomerEventDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createAccountSend(CustomerEntity customer) {
        CustomerEventDto event = new CustomerEventDto(customer.getCustomerId(), customer.getPerson().getName(), "SAVINGS");
        kafkaTemplate.send("customer-topic", event);
    }
}
