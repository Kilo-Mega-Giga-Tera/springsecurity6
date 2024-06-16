package com.app.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication

/*
 *  This code is used at the debug.
 *  Caution: Use for development and testing purposes only.
 *
 *  import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
 */
// @EnableWebSecurity(debug = true)

/*
 *  This code is used at the method level security.
 */
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)

@EnableJpaRepositories("com.app.security.repository")
@EntityScan("com.app.security.model")
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
