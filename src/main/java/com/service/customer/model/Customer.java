package com.service.customer.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Customer {
    @Id
    @Type(type = "pg-uuid")
    private UUID id;
    private String firstName;
    private String surname;

    public Customer() {
        this.id = UUID.randomUUID();
    }
}
