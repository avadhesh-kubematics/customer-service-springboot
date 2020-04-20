Feature: Create and Get a Customer

  Scenario: When valid customer details are passed should store the details in Db
    Given Customer provides its first name "Abhishek" and surname "Rajput"
    When The customer makes a call to store the details
    Then The API should return the Customer Data with Id
