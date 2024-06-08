package com.app.security.controller.security;

import com.app.security.model.Customer;
import com.app.security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) {
        Customer savedCustomer;
        ResponseEntity<String> responseEntity = null;

        try {
            String password = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(password);

            savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId() > 0) {
                responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("Success");
            }
        } catch (Exception e) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("Fail");
        }

        return responseEntity;
    }


}