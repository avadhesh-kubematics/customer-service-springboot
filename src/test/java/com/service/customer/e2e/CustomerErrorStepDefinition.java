package com.service.customer.e2e;

import io.cucumber.java.en.Then;
import io.cucumber.java.nl.En;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerErrorStepDefinition implements En, TestContextInterface {

    @Then("The API should return error {string} and status code {int}")
    public void the_API_should_return_error_and_status_code(String expectedErrorMsg, Integer expectedStatusCode) {
        CustomResponseEntity payload = testContext().getPayload(CustomResponseEntity.class);
        assertEquals(expectedStatusCode, payload.getStatusCode());
        assertEquals(expectedErrorMsg, payload.getResponseMessage());
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
