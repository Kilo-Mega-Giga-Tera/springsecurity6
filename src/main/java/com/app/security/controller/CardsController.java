package com.app.security.controller;

import com.app.security.model.Cards;
import com.app.security.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardRepository cardRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam int id) {
        return cardRepository.findByCustomerId(id);
    }

}
