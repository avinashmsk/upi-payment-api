package com.example.upi.controller;

import com.example.upi.dto.UserRegistrationRequestDto;
import com.example.upi.entities.UserAccount;
import com.example.upi.service.UpiService;
import com.example.upi.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upi")
public class UpiController {

    @Autowired
    private UpiService service;

    private final UserAccountService userAccountService;

    public UpiController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/balance/{upiId}")
    public double checkBalance(@PathVariable String upiId) {
        return service.getBalance(upiId);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String from,
                           @RequestParam String to,
                           @RequestParam double amount) {
        return service.transferMoney(from, to, amount);
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(@RequestBody UserRegistrationRequestDto request) {
        UserAccount registered = userAccountService.registerUser(request);
        return ResponseEntity.ok(registered);
    }
}

