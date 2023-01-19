package com.epaylater.app.controller;


import com.epaylater.app.entities.Merchant;
import com.epaylater.app.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @PostMapping("/add")
    public ResponseEntity<String> addMerchant(@RequestBody Merchant merchant) throws Exception {
        String message = "Merchant with " + merchantService.create(merchant) + " created.";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Merchant>> getAllMerchants(){
        return new ResponseEntity<>(merchantService.getAllMerchants(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Merchant> getMerchant(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(merchantService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMerchant(@PathVariable("id") Long id){
        merchantService.deleteById(id);
        String message = "Merchant with id " + id + " deleted.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}


