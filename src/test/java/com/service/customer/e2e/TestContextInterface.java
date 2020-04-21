package com.service.customer.e2e;

import static com.service.customer.e2e.TestContext.CONTEXT;

public interface TestContextInterface {
    default TestContext testContext() {
        return CONTEXT;
    }
}
