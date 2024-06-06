package com.app.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "EMAIL", nullable = false, length = 45)
    private String email;

    @Column(name = "PWD", nullable = false, length = 45)
    private String pwd;

    @Column(name = "ROLE", nullable = false, length = 45)
    private String role;

}