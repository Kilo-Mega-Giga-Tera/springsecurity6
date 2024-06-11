package com.app.security.controller;

import com.app.security.model.Accounts;
import com.app.security.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam("id") int id) {
        return accountRepository.findByCustomerId(id);
    }

}
