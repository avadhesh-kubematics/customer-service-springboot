package com.service.customer.e2e;

import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.nl.En;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class CustomerStepDefinition extends SpringIntegration implements En, TestContextInterface {

    private CustomerVO customer = new CustomerVO();
    private ResponseEntity<Customer> createResponseEntityResponseEntity;
    private ResponseEntity<Customer> getCustomerResponseEntity;
    private int customerId;

    @Given("Customer provides its first name {string} and surname {string}")
    public void customer_provides_its_first_name_and_surname(String firstName, String surname) {
        customer.setFirstName(firstName);
        customer.setSurname(surname);
    }

    @When("The customer makes a call to store the details")
    public void the_customer_makes_a_call_to_store_the_details() {
        createResponseEntityResponseEntity = restTemplate.postForEntity(DEFAULT_URL + "/create", customer, Customer.class);
    }

    @Then("The API should return the Customer Data with Id")
    public void the_API_should_return_the_Customer_Data_with_Id() {
        assertEquals(createResponseEntityResponseEntity.getStatusCodeValue(), CREATED.value());
        Customer customerResponse = createResponseEntityResponseEntity.getBody();
        assertEquals("Abhishek", customerResponse.getFirstName());
        assertEquals("Rajput", customerResponse.getSurname());
        assertNotNull(customerResponse.getId());
    }

    @Given("Customer provides a valid customer id {int}")
    public void customer_provides_a_valid_customer_id(int customerId) {
        this.customerId = customerId;
    }

    @When("The customer makes a call to get the customer details")
    public void the_customer_makes_a_call_to_get_the_customer_details() {

        try {
            getCustomerResponseEntity = restTemplate.getForEntity(DEFAULT_URL + "search/" + this.customerId, Customer.class);
        } catch (HttpClientErrorException exception) {
            CustomResponseEntity customResponseEntity = new CustomResponseEntity();
            customResponseEntity.setStatusCode(exception.getRawStatusCode());
            customResponseEntity.setResponseMessage(exception.getResponseBodyAsString());
            testContext().setPayload(customResponseEntity);
        }
    }

    @Then("The API should return the associated Customer Data")
    public void the_API_should_return_the_associated_Customer_Data() {
        assertEquals(getCustomerResponseEntity.getStatusCodeValue(), OK.value());
        Customer customerResponse = getCustomerResponseEntity.getBody();
        assertEquals("Abhishek", customerResponse.getFirstName());
        assertEquals("Rajput", customerResponse.getSurname());
        assertEquals(this.customerId, customerResponse.getId());
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}