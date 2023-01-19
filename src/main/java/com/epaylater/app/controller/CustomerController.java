package com.epaylater.app.controller;


import com.epaylater.app.dto.AmountUpdateDto;
import com.epaylater.app.entities.Customer;
import com.epaylater.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
//@EnableCaching
public class CustomerController{
    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) throws Exception {
        String message = "Customer with id : " + customerService.create(customer) + " created";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getALlCustomers(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(customerService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/get/loaned-amount/{id}")
    public ResponseEntity<String> getCustomerLoanedAmount(@PathVariable("id") Long id){
        String response = "Customer " + "'s withstanding amount: " + customerService.getCustomerLoanedAmount(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/credit-limit")
    public ResponseEntity<String> updateCreditLimit(@RequestBody AmountUpdateDto creditLimit){
        return new ResponseEntity<>(customerService.updateCustomerCreditLimit(creditLimit.getId(), creditLimit.getAmount()), HttpStatus.OK);
    }

    @PutMapping("/update/loaned-amount")
    public ResponseEntity<String> updateLoanedAmount(@RequestBody AmountUpdateDto loanedAmount){
        return new ResponseEntity<>(customerService.updateCustomerLoanedAmount(loanedAmount.getId(), loanedAmount.getAmount()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.deleteById(id), HttpStatus.OK);
    }
}