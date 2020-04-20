package com.service.customer.controller;

import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import com.service.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.service.customer.helper.TestData.getCustomerData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    private CustomerController customerController;
    private CustomerService mockCustomerService;
    private CustomerVO mockCustomer;
    private Customer customerData = getCustomerData();
    private int CUSTOMER_ID = 1000;

    @BeforeEach
    void setUp() {
        mockCustomerService = mock(CustomerService.class);
        mockCustomer = mock(CustomerVO.class);
        customerController = new CustomerController(mockCustomerService);
    }

    @Test
    void create_whenCustomerDataIsPassed_shouldCallServiceWithGivenInput() {
        customerController.create(mockCustomer);

        verify(mockCustomerService, times(1)).create(mockCustomer);
    }

    @Test
    void create_whenCustomerDataIsPassed_shouldReturnTheCustomerData_returnedByTheService() {
        when(mockCustomerService.create(mockCustomer)).thenReturn(customerData);

        Customer actualCustomerData = customerController.create(mockCustomer);

        assertEquals(customerData, actualCustomerData);
    }

    @Test
    void getCustomer_whenCustomerIdsPassed_shouldCallServiceWithGivenInput() {
        customerController.getCustomer(CUSTOMER_ID);

        verify(mockCustomerService, times(1)).getCustomer(CUSTOMER_ID);
    }

    @Test
    void getCustomer_whenAValidCustomerIdIsPassed_shouldReturnCustomerData() {
        when(mockCustomerService.getCustomer(CUSTOMER_ID)).thenReturn(customerData);

        Customer actualCustomer = customerController.getCustomer(CUSTOMER_ID);

        assertEquals(customerData, actualCustomer);
    }
}