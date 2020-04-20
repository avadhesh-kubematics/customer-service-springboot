package com.service.customer.service;

import com.service.customer.mapper.CustomerVOToCustomerMapper;
import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import com.service.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerVOToCustomerMapper customerVOToCustomerMapper;

    public CustomerService(CustomerVOToCustomerMapper customerVOToCustomerMapper,
                           CustomerRepository customerRepository) {
        this.customerVOToCustomerMapper = customerVOToCustomerMapper;
        this.customerRepository = customerRepository;
    }

    public Customer create(CustomerVO customerVO) {
        Customer customer = customerVOToCustomerMapper.map(customerVO);
        return customerRepository.save(customer);
    }

}
