package com.epaylater.app.controller;

import com.epaylater.app.entities.Credit;
import com.epaylater.app.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;

    @PostMapping("/add")
    public ResponseEntity<String> addCredit(@RequestBody Credit credit) throws Exception {
        String message = "Credit info with " + creditService.addCredit(credit) + " added.";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
