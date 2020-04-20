package com.service.customer.e2e;

import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class StepDefinition extends SpringIntegration {

    private CustomerVO customer = new CustomerVO();
    private ResponseEntity<Customer> createResponseEntityResponseEntity;

    @Given("Customer provides its first name {string} and surname {string}")
    public void customer_provides_its_first_name_and_surname(String firstName, String surname) {
        customer.setFirstName(firstName);
        customer.setSurname(surname);
    }

    @When("The customer makes a call to store the details")
    public void the_customer_makes_a_call_to_store_the_details() {
        createResponseEntityResponseEntity = restTemplate.postForEntity(DEFAULTURL + "/create", customer, Customer.class);
    }

    @Then("The API should return the Customer Data with Id")
    public void the_API_should_return_the_Customer_Data_with_Id() {
        assertEquals(createResponseEntityResponseEntity.getStatusCodeValue(), CREATED.value());
        assertEquals("Abhishek", createResponseEntityResponseEntity.getBody().getFirstName());
        assertEquals("Rajput", createResponseEntityResponseEntity.getBody().getSurname());
        assertNotNull(createResponseEntityResponseEntity.getBody().getId());
    }
}
