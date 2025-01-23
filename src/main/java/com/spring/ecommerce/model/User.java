package com.spring.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bmrusers")
public class User {

    @Id
    private int id;
    private String username;
    private String password;

}
