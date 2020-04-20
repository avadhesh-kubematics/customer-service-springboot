package com.service.customer.e2e;

import com.service.customer.CustomerApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(
        classes = CustomerApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public abstract class SpringIntegration {
    protected static final String DEFAULTURL = "http://localhost:8081/";
    protected RestTemplate restTemplate = new RestTemplate();
}