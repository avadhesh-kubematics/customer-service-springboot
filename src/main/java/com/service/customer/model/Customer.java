package com.service.customer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq",
            allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    private Integer id;
    private String firstName;
    private String surname;
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
