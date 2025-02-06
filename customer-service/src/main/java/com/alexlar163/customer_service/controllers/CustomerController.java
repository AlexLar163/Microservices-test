package com.alexlar163.customer_service.controllers;

import com.alexlar163.customer_service.dto.CustomerDto;
import com.alexlar163.customer_service.entities.CustomerEntity;
import com.alexlar163.customer_service.entities.PersonEntity;
import com.alexlar163.customer_service.services.CustomerServiceImpl;
import com.alexlar163.customer_service.services.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;
    private final PersonServiceImpl personServiceImpl;

    public CustomerController(CustomerServiceImpl customerServiceImpl, PersonServiceImpl personServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
        this.personServiceImpl = personServiceImpl;
    }

    @GetMapping
    public List<CustomerEntity> getAllCustomers() {
        return customerServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CustomerEntity> getCustomerById(@PathVariable Long id) {
        return customerServiceImpl.findById(id);
    }

    @PostMapping("/new")
    public CustomerEntity createCustomer(@RequestBody CustomerDto customerDto) {
        PersonEntity person = new PersonEntity();
        person.setName(customerDto.getName());
        person.setGender(customerDto.getGender());
        person.setAge(customerDto.getAge());
        person.setIdentification(customerDto.getIdentification());
        person.setAddress(customerDto.getAddress());
        person.setPhone(customerDto.getPhone());
        person = personServiceImpl.save(person);

        CustomerEntity customer = new CustomerEntity();
        customer.setPerson(person);
        customer.setCustomerId("C" + UUID.randomUUID().toString().replace("-", ""));
        customer.setPassword(customerDto.getPassword());
        customer.setState("ACTIVE");
        customer = customerServiceImpl.save(customer);
        return ResponseEntity.ok(customer).getBody();



    }

    @PutMapping("/{id}")
    public CustomerEntity updateCustomer(@PathVariable Long id, @RequestBody CustomerEntity customer) {
        customer.setId(id);
        return customerServiceImpl.save(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerServiceImpl.deleteById(id);
    }
}
