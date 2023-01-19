package com.epaylater.app.controller;

import com.epaylater.app.entities.Debit;
import com.epaylater.app.service.DebitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debit")
@RequiredArgsConstructor
public class DebitController {
    private final DebitService debitService;

    @PostMapping("/add")
    public ResponseEntity<String> addDebit(@RequestBody Debit debit) throws Exception {
        String message = "Debit info with " +  debitService.addDebit(debit) + " added.";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/get-customer/{id}")
    public ResponseEntity<List<Debit>> getDebitOfCustomer(@PathVariable("id") Long id){
        return new ResponseEntity<>(debitService.getAllCreditsOfCustomer(id), HttpStatus.OK);
    }

    @GetMapping("/get-debit/{id}")
    public ResponseEntity<Debit> getDebitById(@PathVariable("id") Long id){
        return new ResponseEntity<>(debitService.getDebitById(id), HttpStatus.OK);
    }

}
