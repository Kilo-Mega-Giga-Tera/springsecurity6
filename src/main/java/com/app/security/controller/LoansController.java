package com.app.security.controller;

import com.app.security.model.Loans;
import com.app.security.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestParam("id") int id) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    }

}
