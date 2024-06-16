package com.app.security.controller;

import com.app.security.model.Customer;
import com.app.security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) {
        Customer savedCustomer;
        ResponseEntity<String> responseEntity = null;

        try {
            String password = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(password);
            customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));

            savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId() > 0) {
                responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("Successfully registered");
            }
        } catch (Exception e) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("Failure register");
        }

        return responseEntity;
    }

    @GetMapping("/user")
    public Customer getUserDetail(Authentication authentication) {
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());

        if (ObjectUtils.isEmpty(customers))
            return null;
        else
            return customers.get(0);

    }

}
