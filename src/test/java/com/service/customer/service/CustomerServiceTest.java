package com.service.customer.service;

import com.service.customer.mapper.CustomerVOToCustomerMapper;
import com.service.customer.mapper.CustomerVOToCustomerMapperImpl;
import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import com.service.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.service.customer.helper.TestData.getCustomerData;
import static com.service.customer.helper.TestData.getCustomerVOData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
    private CustomerService customerService;
    private CustomerVOToCustomerMapper mockCustomerVOToCustomerMapper;
    private CustomerVO customerVOData = getCustomerVOData();
    private Customer customerData = getCustomerData();

    @BeforeEach
    void setUp() {
        mockCustomerVOToCustomerMapper = mock(CustomerVOToCustomerMapperImpl.class);
        customerService = new CustomerService(mockCustomerVOToCustomerMapper, mockCustomerRepository);
    }

    @Test
    void create_whenPassedCustomerRequestData_shouldCallTheRepositoryWithConvertedDAO() {
        when(mockCustomerVOToCustomerMapper.map(customerVOData)).thenReturn(customerData);

        customerService.create(customerVOData);

        verify(mockCustomerRepository, times(1)).save(customerData);
    }


    @Test
    void create_whenPassedCustomerRequestData_shouldReturnCustomerDataFromRepository() {
        Customer expectedCustomer = new Customer();
        when(mockCustomerVOToCustomerMapper.map(customerVOData)).thenReturn(customerData);
        when(mockCustomerRepository.save(customerData)).thenReturn(expectedCustomer);

        Customer actual = customerService.create(customerVOData);

        assertEquals(expectedCustomer, actual);
    }

    @Test
    void getCustomer_whenCustomerIdPassed_shouldCallTheRepositoryWithSameCustomerId() {
        UUID customerId = customerData.getId();
        when(mockCustomerRepository.findById(customerId)).thenReturn(java.util.Optional.ofNullable(customerData));

        customerService.getCustomer(customerId);

        verify(mockCustomerRepository, times(1)).findById(customerId);
    }

    @Test
    void getCustomer_whenCustomerIdPassed_shouldCallTheRepositoryAndReturnCustomerData() {
        UUID id = customerData.getId();
        when(mockCustomerRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(customerData));

        Customer actualCustomer = customerService.getCustomer(id);

        assertEquals(customerData, actualCustomer);
    }
}