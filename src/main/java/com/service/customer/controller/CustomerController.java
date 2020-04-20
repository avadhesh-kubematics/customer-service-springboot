package com.service.customer.controller;

import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import com.service.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody CustomerVO customerVO) {
        Customer customer = customerService.create(customerVO);
        return customer;
    }
}
